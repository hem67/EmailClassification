package com.emailclassification.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpSession;

import com.emailclassification.util.Constants;

public class ObscureThread implements Runnable
{

	String user_path;
	Thread t;
	HttpSession session;

	public ObscureThread(String user_path, HttpSession httpSession)
	{
		this.user_path = user_path;
		this.session = httpSession;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run()
	{
		try
		{
			File user_folder = new File(Constants.DATASET_PATH + File.separator + user_path);
			File user_folder_obscured = new File(Constants.DATASET_PATH + File.separator + user_path + "_obscured");
			user_folder_obscured.mkdirs();

			for (String file : user_folder.list())
			{
				File obscured_file = new File(user_folder_obscured + File.separator + file);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(new File(user_folder + File.separator + file))));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(obscured_file)));
				String line = "";
				while ((line = br.readLine()) != null)
				{
					String obscured_line = ObscureService.obscure(line);
					bw.write(obscured_line);
					bw.newLine();
				}
				br.close();
				bw.close();
			}

			session.removeAttribute("obscure_inprogress");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
