package com.emailclassification.core;

import java.io.File;

public class TestNB
{

	public static void main(String[] args) throws Exception
	{

		String spam_folder = "C:/DataSets/test/spam_obs2";
		String ham_folder = "C:/DataSets/test/ham_obs2";

		File sFolder = new File(spam_folder);
		int total = 0;
		int success = 0;

		int i = 0;
		for (File f : sFolder.listFiles())
		{
			i++;
			total++;
			String result = NBWorker.run(f.getPath());
			System.out.println(i + ". " + result);
			if (result.equals("spam"))
				success++;
		}

		System.out.println("========================");

		File hFolder = new File(ham_folder);
		i = 0;
		for (File f : hFolder.listFiles())
		{
			total++;
			i++;
			String result = NBWorker.run(f.getPath());
			System.out.println(i + ". " + result);
			if (result.equals("ham"))
				success++;
		}

		float accuracy = (success / (float) total) * 100;

		System.out.println("Accuracy is : " + accuracy + "%");

	}

}