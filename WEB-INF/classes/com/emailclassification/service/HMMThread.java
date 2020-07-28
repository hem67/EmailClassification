package com.emailclassification.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.emailclassification.core.HMM;

public class HMMThread implements Runnable
{

	Thread t;
	File folder;
	HMM hmm;
	HttpSession session;

	public HMMThread(File folder, HttpSession session)
	{
		this.folder = folder;
		t = new Thread(this);
		this.session = session;
		hmm = new HMM();
		t.start();
	}

	public void run()
	{
		session.setAttribute("hmm_running", "yes");
		session.removeAttribute("hmm_result");
		Map<String, String> hmm_result = new HashMap<>();
		for (File file : folder.listFiles())
		{
			try
			{
				System.out.println("HMM: Processing: " + file.getPath());
				hmm_result.put(file.getPath(), hmm.runHMM(file));
				System.out.println("HMM: DOne: " + file.getPath());
			} catch (Exception e)
			{
				hmm_result.put(file.getPath(), "[ERROR]");
			}

		}
		session.setAttribute("hmm_result", hmm_result);
		session.removeAttribute("hmm_running");

	}
}
