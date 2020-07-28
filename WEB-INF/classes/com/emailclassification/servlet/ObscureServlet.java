package com.emailclassification.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emailclassification.pojo.User;
import com.emailclassification.service.ObscureService;
import com.emailclassification.service.ObscureThread;
import com.emailclassification.util.Constants;

public class ObscureServlet extends HttpServlet
{
   private static final long serialVersionUID = 1L;

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
         String requestType = req.getParameter("requestType");
         User user = (User) req.getSession().getAttribute("user");
         String user_path = user.getEmail().substring(0, user.getEmail().lastIndexOf('@'));
         if (requestType == null)
         {
            resp.sendRedirect("error.jsp?msg=Error: Bad Request");
         }
         else
         {
            if (requestType.equals("run"))
            {

               new ObscureThread(user_path, req.getSession());
               req.getSession().setAttribute("obscure_inprogress", "yes");
               resp.sendRedirect("obscure_run.jsp?msg=Obscure Process started. It might take several minutes to complete");
            }
            else if (requestType.equals("get"))
            {
               List<String> filenames = new ArrayList<>();
               File folder_path = new File(Constants.DATASET_PATH + File.separator + user_path + "_obscured");
               for (String filename : folder_path.list())
               {
                  filenames.add(filename);
               }
               req.setAttribute("filenames", filenames);
               req.getRequestDispatcher("obscure_get.jsp").forward(req, resp);

            }
            else if (requestType.equals("download"))
            {
               resp.setContentType("text/html");
               PrintWriter out = resp.getWriter();
               String filename = req.getParameter("filename");
               String filepath = Constants.DATASET_PATH + File.separator + user_path + "_obscured" + File.separator;
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

            }
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("error.jsp?msg=Error: " + e.getMessage());
      }
   }

}
