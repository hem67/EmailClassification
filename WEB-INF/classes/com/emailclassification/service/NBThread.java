package com.emailclassification.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.emailclassification.core.NBWorker;

public class NBThread implements Runnable
{

	Thread t;

	File folder;
	HttpSession session;

	public NBThread(File folder, HttpSession session)
	{
		this.folder = folder;
		this.session = session;
		t = new Thread(this);
		t.start();
	}

	public void run()
	{
		session.setAttribute("nb_running", "yes");

		session.removeAttribute("nb_result");
		Map<String, String> nb_result = new HashMap<>();
		for (File file : folder.listFiles())
		{
			try
			{
				System.out.println("NB: Processing: " + file.getPath());
				nb_result.put(file.getPath(), NBWorker.run(file.getPath()));
				System.out.println("NB Done: " + file.getPath());
			} catch (Exception e)
			{
				nb_result.put(file.getPath(), "[ERROR]");
			}

		}
		session.setAttribute("nb_result", nb_result);
		session.removeAttribute("nb_running");

	}
}
