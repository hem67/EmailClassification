package com.emailclassification.pojo;

import java.sql.Timestamp;

public class Mail
{
	private String id;
	private String sender;
	private String rec;
	private String subject;
	private String body;
	private String hmm;
	private String nb;
	private String trash;
	private Timestamp entry_time;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getSender()
	{
		return sender;
	}

	public void setSender(String sender)
	{
		this.sender = sender;
	}

	public String getTrash()
	{
		return trash;
	}

	public void setTrash(String trash)
	{
		this.trash = trash;
	}

	public String getRec()
	{
		return rec;
	}

	public void setRec(String rec)
	{
		this.rec = rec;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public String getHmm()
	{
		return hmm;
	}

	public void setHmm(String hmm)
	{
		this.hmm = hmm;
	}

	public String getNb()
	{
		return nb;
	}

	public void setNb(String nb)
	{
		this.nb = nb;
	}

	public Timestamp getEntry_time()
	{
		return entry_time;
	}

	public void setEntry_time(Timestamp entry_time)
	{
		this.entry_time = entry_time;
	}

}
