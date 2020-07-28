package com.emailclassification.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.emailclassification.util.Constants;

public class RunService
{

	public void run(String username, HttpServletRequest req) throws Exception
	{

		String foldername = Constants.DATASET_PATH + File.separator + username;
		File folder = new File(foldername);

		new HMMThread(folder, req.getSession());
		new NBThread(folder, req.getSession());

	}

}
