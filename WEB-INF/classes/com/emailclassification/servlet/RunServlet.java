package com.emailclassification.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emailclassification.pojo.User;
import com.emailclassification.service.RunService;

public class RunServlet extends HttpServlet
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
		RunService runService = new RunService();
		User user = (User) req.getSession().getAttribute("user");
		try
		{
			String requestType = req.getParameter("requestType");
			if (requestType == null)
			{
				resp.sendRedirect("error.jsp?msg=Bad Request");
			} else
			{
				if (requestType.equals("run"))
				{
					runService.run(user.getEmail().substring(0, user.getEmail().indexOf("@")) + "_obscured", req);
					resp.sendRedirect(
							"run.jsp?msg=The Algorithm has been initiated. It might take several minutes to complete. Please check the results after sometime.");
				}

			}
		} catch (Exception e)
		{
			e.printStackTrace();
			resp.sendRedirect("error.jsp?msg=Error: " + e.getMessage());
		}
	}

}
