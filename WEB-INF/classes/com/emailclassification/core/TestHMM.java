package com.emailclassification.core;

import java.io.File;

public class TestHMM {

	public static void main(String[] args) throws Exception {

		String spam_folder = "C:/DataSets/test/spam_obs";
		String ham_folder = "C:/DataSets/test/ham_obs";

		File sFolder = new File(spam_folder);
		int total = 0;
		int success = 0;

		int i = 0;
		for (File f : sFolder.listFiles()) {
			i++;
			total++;

			HMM hmm = new HMM();
			String result = hmm.runHMM(f);

			System.out.println(i + ". " + result);
			if (result.equals("SPAM"))
				success++;
		}

		System.out.println("========================");

		File hFolder = new File(ham_folder);
		i = 0;
		for (File f : hFolder.listFiles()) {
			total++;
			i++;

			HMM hmm = new HMM();
			String result = hmm.runHMM(f);

			System.out.println(i + ". " + result);
			if (result.equals("HAM"))
				success++;
		}

		float accuracy = (success / (float) total) * 100;

		System.out.println("Accuracy is : " + accuracy + "%");

	}

}