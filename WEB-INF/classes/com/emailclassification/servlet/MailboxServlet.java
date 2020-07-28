package com.emailclassification.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emailclassification.core.HMM;
import com.emailclassification.core.MultinomialNaiveBayes;
import com.emailclassification.core.NBWorker;
import com.emailclassification.dao.MailBoxDAO;
import com.emailclassification.daoimpl.MailBoxDAOImpl;
import com.emailclassification.pojo.Mail;
import com.emailclassification.pojo.User;
import com.emailclassification.service.ObscureService;
import com.emailclassification.util.Util;

public class MailboxServlet extends HttpServlet
{

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
			String request_type = req.getParameter("request_type");
			User user = (User) req.getSession().getAttribute("user");
			MailBoxDAO mailDao = new MailBoxDAOImpl();
			if (request_type == null)
			{
				resp.sendRedirect("mailbox_inbox.jsp?msg=Bad Request");
			} else
			{
				if (request_type.equals("compose"))
				{
					String rec = req.getParameter("rec");
					String subject = req.getParameter("subject");
					String body = req.getParameter("body");
					String id = Util.generateID();
					String sender = user.getEmail();

					// OBSCURE
					String obscured_body = ObscureService.obscure(body);
					// SPAM CHECK
					String result = NBWorker.checkSpam(obscured_body);
					String nb = result.toLowerCase();

					HMM hmmRunner = new HMM();
					String hmm = hmmRunner.runHMM(obscured_body).toLowerCase();
					String trash = "no";

					Mail mail = new Mail();
					mail.setBody(body);
					mail.setEntry_time(new Timestamp(System.currentTimeMillis()));
					mail.setHmm(hmm);
					mail.setId(id);
					mail.setNb(nb);
					mail.setRec(rec);
					mail.setSender(sender);
					mail.setSubject(subject);
					mail.setTrash(trash);

					mailDao.write(mail);

					resp.sendRedirect("mailbox_inbox.jsp?msg=Mail Sent");
				} else if (request_type.equals("movetotrash"))
				{
					String id = req.getParameter("id");
					mailDao.moveToTrash(id);
					resp.sendRedirect("mailbox_inbox.jsp?msg=Mail Moved to Trash");
				} else if (request_type.equals("deleteSent"))
				{
					String id = req.getParameter("id");
					mailDao.delete(id);
					resp.sendRedirect("mailbox_sent.jsp?msg=Sent Mail Deleted");
				} else if (request_type.equals("restore"))
				{
					String id = req.getParameter("id");
					mailDao.restore(id);
					resp.sendRedirect("mailbox_trash.jsp?msg=Mail Restored");

					
				} else if (request_type.equals("movetotrashfromspam"))
				{
					String id = req.getParameter("id");
					mailDao.moveToTrash(id);
					resp.sendRedirect("mailbox_spam.jsp?msg=Mail Moved to Trash");
					
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			resp.sendRedirect("mailbox_inbox.jsp?msg=Something went wrong: " + e.getMessage());
		}
	}

}
