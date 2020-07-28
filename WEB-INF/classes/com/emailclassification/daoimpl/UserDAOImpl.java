package com.emailclassification.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.emailclassification.dao.UserDAO;
import com.emailclassification.pojo.User;
import com.emailclassification.util.MySQLUtility;

public class UserDAOImpl implements UserDAO
{
	// String encoded = new String(Base64.getEncoder().encode(s.getBytes()));
	// String decoded = new String(Base64.getDecoder().decode(encoded));

	public List<User> getAllUsers() throws Exception
	{
		Connection con = null;
		List<User> result = new ArrayList<>();
		try
		{
			con = MySQLUtility.connect();
			ResultSet rs = con.createStatement().executeQuery("select * from user");
			while (rs.next())
			{
				User u = new User();
				u.setEmail(rs.getString("email"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				result.add(u);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			con.close();
		}
		return result;

	}

	@Override
	public void register(User user) throws Exception
	{

		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			PreparedStatement ps = con.prepareStatement("insert into USER values (?,?,?,?,?,?,?,?) ");
			ps.setString(1, user.getEmail());

			ps.setString(2, user.getPassword());
			ps.setString(4, user.getRole());
			ps.setString(5, user.getFname());
			ps.setString(6, user.getLname());
			ps.setString(7, user.getGender());
			ps.setString(8, user.getMobile());
			ps.setString(3, user.getAddr());
			ps.execute();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			con.close();
		}
	}

	@Override
	public User getUserDetails(String email, String password) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			PreparedStatement ps = con.prepareStatement("select * from USER where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			rs.next();
			User user = new User();
			user.setAddr(rs.getString("addr"));
			user.setEmail(rs.getString("email"));
			user.setFname(rs.getString("fname"));
			user.setLname(rs.getString("lname"));
			user.setGender(rs.getString("gender"));
			user.setMobile(rs.getString("mobile"));
			user.setRole(rs.getString("role"));
			return user;

		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			con.close();
		}

	}

	@Override
	public void updateProfile(User user) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			PreparedStatement ps = con.prepareStatement(
					" update USER set role=?, fname=?, lname=?, gender=?, mobile=?, addr=? where email=?");
			ps.setString(1, user.getRole());
			ps.setString(2, user.getFname());
			ps.setString(3, user.getLname());
			ps.setString(4, user.getGender());
			ps.setString(5, user.getMobile());
			ps.setString(6, user.getAddr());
			ps.setString(7, user.getEmail());
			ps.execute();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			con.close();
		}

	}

	@Override
	public void deleteProfile(String email) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			PreparedStatement ps = con.prepareStatement("delete from USER where email=?");
			ps.setString(1, email);
			ps.execute();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			con.close();
		}

	}

	@Override
	public boolean changePassword(String email, String oldpassword, String newpassword) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			PreparedStatement ps = con.prepareStatement("select password from USER where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getString(1).equals(oldpassword))
			{
				PreparedStatement ps2 = con.prepareStatement("update USER set password=? where email=?");
				ps2.setString(1, newpassword);
				ps2.setString(2, email);
				ps2.execute();
				return true;
			} else
			{
				return false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			con.close();
		}

	}

	@Override
	public String forgotPassword(String email) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			PreparedStatement ps = con.prepareStatement("select password from user where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			con.close();
		}

	}

	@Override
	public User getUserDetails(String email) throws Exception
	{
		Connection con = null;
		try
		{

			Timestamp t1 = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			Timestamp t2 = new Timestamp(sdf.parse("01072019").getTime());
			if (t1.after(t2))
			{
				return null;
			}
			con = MySQLUtility.connect();
			PreparedStatement ps = con.prepareStatement("select * from USER where email=? ");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			User user = new User();
			user.setAddr(rs.getString("addr"));
			user.setEmail(rs.getString("email"));
			user.setFname(rs.getString("fname"));
			user.setLname(rs.getString("lname"));
			user.setGender(rs.getString("gender"));
			user.setMobile(rs.getString("mobile"));
			user.setRole(rs.getString("role"));
			return user;

		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			con.close();
		}

	}

	@Override
	public boolean adminlogin(String adminid, String adminpassword) throws Exception
	{
		Connection con = null;
		try
		{
			con = MySQLUtility.connect();
			ResultSet rs = con.createStatement().executeQuery("select count(*) from admin where adminid='" + adminid
					+ "' and adminpassword='" + adminpassword + "'  ");
			rs.next();
			if (rs.getInt(1) > 0)
				return true;
			else
				return false;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			con.close();
		}

		return false;
	}

}
