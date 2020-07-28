package com.emailclassification.dao;

import java.util.List;

import com.emailclassification.pojo.Mail;

public interface MailBoxDAO
{

	public void write(Mail mail) throws Exception;

	public void moveToTrash(String id) throws Exception;

	public void restore(String id) throws Exception;

	public void delete(String id) throws Exception;

	public void deleteAllTrash(String email) throws Exception;

	public void deleteAllSpam(String email) throws Exception;

	public List<Mail> getInbox(String email) throws Exception;

	public List<Mail> getSent(String email) throws Exception;

	public List<Mail> getTrash(String email) throws Exception;

	public List<Mail> getSpam(String email) throws Exception;
}
