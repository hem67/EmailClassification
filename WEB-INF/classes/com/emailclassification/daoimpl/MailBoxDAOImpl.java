package com.emailclassification.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.emailclassification.dao.MailBoxDAO;
import com.emailclassification.pojo.Mail;
import com.emailclassification.util.MySQLUtility;

public class MailBoxDAOImpl implements MailBoxDAO
{

	@Override
	public void write(Mail mail) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			PreparedStatement ps = con.prepareStatement("insert into mailbox values (?,?,?,?,?,?,?,?,?) ");
			ps.setString(1, mail.getId());
			ps.setString(2, mail.getSender());
			ps.setString(3, mail.getRec());
			ps.setString(4, mail.getSubject());
			ps.setString(5, mail.getBody());
			ps.setString(6, mail.getHmm());
			ps.setString(7, mail.getNb());
			ps.setString(8, mail.getTrash());
			ps.setTimestamp(9, mail.getEntry_time());
			ps.execute();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}

	}

	@Override
	public void moveToTrash(String id) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			con.createStatement().execute("update mailbox set trash='yes' where id='" + id + "' ");

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}

	}

	
	@Override
	public void restore(String id) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			con.createStatement().execute("update mailbox set trash='no' where id='" + id + "' ");

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}

	}

	@Override
	public void delete(String id) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			con.createStatement().execute("delete from mailbox where id='" + id + "' ");

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}

	}

	@Override
	public void deleteAllTrash(String email) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			con.createStatement().execute("delete from mailbox where trash='yes' and rec='" + email + "' ");

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}

	}

	@Override
	public void deleteAllSpam(String email) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			con.createStatement()
					.execute("delete from mailbox where rec='" + email + "'  and hmm='spam'  and nb='spam' ");

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}

	}

	@Override
	public List<Mail> getInbox(String email) throws Exception
	{
		Connection con = null;
		List<Mail> result = new ArrayList<>();
		try
		{
			con = MySQLUtility.connect();
			ResultSet rs = con.createStatement()
					.executeQuery("select * from mailbox where rec='" + email + "' and trash='no' order by entry_time desc");
			while (rs.next())
			{
				Mail mail = new Mail();
				mail.setBody(rs.getString("body"));
				mail.setEntry_time(rs.getTimestamp("entry_time"));
				mail.setHmm(rs.getString("hmm"));
				mail.setId(rs.getString("id"));
				mail.setNb(rs.getString("nb"));
				mail.setRec(rs.getString("rec"));
				mail.setSender(rs.getString("sender"));
				mail.setSubject(rs.getString("subject"));
				mail.setTrash(rs.getString("trash"));
				result.add(mail);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}
		return result;
	}

	@Override
	public List<Mail> getSent(String email) throws Exception
	{
		Connection con = null;
		List<Mail> result = new ArrayList<>();
		try
		{
			con = MySQLUtility.connect();
			ResultSet rs = con.createStatement()
					.executeQuery("select * from mailbox where sender='" + email + "' order by entry_time desc");
			while (rs.next())
			{
				Mail mail = new Mail();
				mail.setBody(rs.getString("body"));
				mail.setEntry_time(rs.getTimestamp("entry_time"));
				mail.setHmm(rs.getString("hmm"));
				mail.setId(rs.getString("id"));
				mail.setNb(rs.getString("nb"));
				mail.setRec(rs.getString("rec"));
				mail.setSender(rs.getString("sender"));
				mail.setSubject(rs.getString("subject"));
				mail.setTrash(rs.getString("trash"));
				result.add(mail);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}
		return result;
	}

	@Override
	public List<Mail> getTrash(String email) throws Exception
	{
		Connection con = null;
		List<Mail> result = new ArrayList<>();
		try
		{
			con = MySQLUtility.connect();
			ResultSet rs = con.createStatement()
					.executeQuery("select * from mailbox where rec='" + email + "' and trash='yes' order by entry_time desc");
			while (rs.next())
			{
				Mail mail = new Mail();
				mail.setBody(rs.getString("body"));
				mail.setEntry_time(rs.getTimestamp("entry_time"));
				mail.setHmm(rs.getString("hmm"));
				mail.setId(rs.getString("id"));
				mail.setNb(rs.getString("nb"));
				mail.setRec(rs.getString("rec"));
				mail.setSender(rs.getString("sender"));
				mail.setSubject(rs.getString("subject"));
				mail.setTrash(rs.getString("trash"));
				result.add(mail);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}
		return result;
	}

	@Override
	public List<Mail> getSpam(String email) throws Exception
	{
		Connection con = null;
		List<Mail> result = new ArrayList<>();
		try
		{
			con = MySQLUtility.connect();
			ResultSet rs = con.createStatement()
					.executeQuery("select * from mailbox where rec='" + email + "' and hmm='spam' and nb='spam' and trash='no' order by entry_time desc");
			while (rs.next())
			{
				Mail mail = new Mail();
				mail.setBody(rs.getString("body"));
				mail.setEntry_time(rs.getTimestamp("entry_time"));
				mail.setHmm(rs.getString("hmm"));
				mail.setId(rs.getString("id"));
				mail.setNb(rs.getString("nb"));
				mail.setRec(rs.getString("rec"));
				mail.setSender(rs.getString("sender"));
				mail.setSubject(rs.getString("subject"));
				mail.setTrash(rs.getString("trash"));
				result.add(mail);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}
		return result;
	}

}
