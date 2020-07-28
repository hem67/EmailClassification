package com.emailclassification.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.emailclassification.pojo.User;
import com.emailclassification.util.Constants;

public class DatasetServlet<StartEncryption> extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private String filePath;
	private int maxFileSize = 5000 * 1024;
	private int maxMemSize = 400 * 1024;
	private File file;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		try
		{
			User user = (User) req.getSession().getAttribute("user");
			String user_path = user.getEmail().substring(0, user.getEmail().lastIndexOf('@'));
			String requestType = req.getParameter("requestType");
			filePath = Constants.DATASET_PATH + File.separator + user_path + File.separator;
			File folder = new File(filePath);
			folder.mkdirs();
			if (requestType != null)
			{
				if (requestType.equals("add"))
				{
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(maxMemSize);
					factory.setRepository(new File("c:\\temp"));
					ServletFileUpload upload = new ServletFileUpload(factory);
					upload.setSizeMax(maxFileSize);
					List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(req));
					Iterator<FileItem> i = fileItems.iterator();
					while (i.hasNext())
					{
						FileItem fi = (FileItem) i.next();
						if (!fi.isFormField())
						{
							String fileName = fi.getName();
							System.out.println("File name .. " + fileName);
							if (fileName.lastIndexOf("\\") >= 0)
							{
								file = new File(
										filePath + File.separator + fileName.substring(fileName.lastIndexOf("\\")));
							} else
							{
								file = new File(
										filePath + File.separator + fileName.substring(fileName.lastIndexOf("\\") + 1));
							}
							fi.write(file);
						}
					}

					resp.sendRedirect("dataset_add.jsp?msg=Data set '" + file.getName() + "' added successfully");
				} else if (requestType.equals("get"))
				{
					List<String> filenames = new ArrayList<>();
					File folder_path = new File(Constants.DATASET_PATH + File.separator
							+ user.getEmail().substring(0, user.getEmail().lastIndexOf('@')));
					for (String filename : folder_path.list())
					{
						filenames.add(filename);
					}
					req.setAttribute("filenames", filenames);
					req.getRequestDispatcher("dataset_get.jsp").forward(req, resp);
				} else if (requestType.equals("delete"))
				{
					String filename = req.getParameter("filename");
					File file = new File(Constants.DATASET_PATH + File.separator
							+ user.getEmail().substring(0, user.getEmail().lastIndexOf('@')) + File.separator
							+ filename);
					file.delete();
					resp.sendRedirect("dataset?requestType=get&msg=File '" + filename + "' deleted");
				} else if (requestType.equals("download"))
				{

					resp.setContentType("text/html");
					PrintWriter out = resp.getWriter();
					String filename = req.getParameter("filename");
					String filepath = Constants.DATASET_PATH + File.separator + user_path + File.separator;
					resp.setContentType("APPLICATION/OCTET-STREAM");
					resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

					FileInputStream fileInputStream = new FileInputStream(filepath + filename);

					int i;
					while ((i = fileInputStream.read()) != -1)
					{
						out.write(i);
					}
					fileInputStream.close();
					out.close();

				} else if (requestType.equals("download_result"))
				{

					resp.setContentType("text/html");
					PrintWriter out = resp.getWriter();

					String file = req.getParameter("file");
					file = file.replaceAll("_obscured", "");
					file = file.replaceAll("_obs", "");

					String filename = file.substring(file.lastIndexOf(File.separator)+1);

					String filepath = file.substring(0, file.lastIndexOf(File.separator));
					
					System.out.println("filename: "+filename);
					System.out.println("filepath: "+filepath);
					resp.setContentType("APPLICATION/OCTET-STREAM");
					resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

					FileInputStream fileInputStream = new FileInputStream(filepath + File.separator+ filename);

					int i;
					while ((i = fileInputStream.read()) != -1)
					{
						out.write(i);
					}
					fileInputStream.close();
					out.close();

				}
			} else
			{
				resp.sendRedirect("error.jsp?msg=Bad Request");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			resp.sendRedirect("error.jsp?msg=Error: " + e.getMessage());
		}
	}

}
