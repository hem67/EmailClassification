package com.emailclassification.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MultinomialNaiveBayes
{

	HashSet<String> vocabulary;
	HashMap<String, Integer> Tct;
	ArrayList<String> classes;
	double prior[], condProb[][];

	int nHam = 0, nSpam = 0, n = 0;

	public MultinomialNaiveBayes(HashSet<String> vocabulary, int nHam, int nSpam, int n, ArrayList<String> classes)
	{
		this.vocabulary = vocabulary;
		this.nHam = nHam;
		this.nSpam = nSpam;
		this.n = n;
		this.classes = classes;
		prior = new double[this.classes.size()];
		condProb = new double[this.vocabulary.size()][this.classes.size()];
		Tct = new HashMap<>();
		for (String term : vocabulary)
		{
			Tct.put(term, 0);
		}
	}

	public void trainMultinomialNB() throws FileNotFoundException, IOException
	{
		for (String className : classes)
		{
			if (className.equals(Parameters.hamClassString))
			{
				prior[classes.indexOf(className)] = (double) nHam / (double) n;
			} else
			{
				prior[classes.indexOf(className)] = (double) nSpam / (double) n;
			}

			// CONCATENATE TEXT OF ALL DOCS IN CLASS + COUNT TOKENS OF TERM
			for (String key : Tct.keySet())
			{
				Tct.put(key, 0);
			}
			BufferedReader reader = null;
			if (className.equals(Parameters.hamClassString))
			{
				File hamTrainingFolder = new File(Parameters.hamTrainingDataDir);
				for (File hamTextFile : hamTrainingFolder.listFiles())
				{
					reader = new BufferedReader(new FileReader(hamTextFile));
					String line = reader.readLine();
					while (line != null)
					{
						String terms[] = line.split(" ");
						for (String term : terms)
						{
							if (vocabulary.contains(term))
							{
								Tct.put(term, Tct.get(term) + 1);
							}
						}
						line = reader.readLine();
					}
				}
			} else
			{
				File spamTrainingFolder = new File(Parameters.spamTrainingDataDir);
				for (File spamTextFile : spamTrainingFolder.listFiles())
				{
					reader = new BufferedReader(new FileReader(spamTextFile));
					String line = reader.readLine();
					while (line != null)
					{
						String terms[] = line.split(" ");
						for (String term : terms)
						{
							if (vocabulary.contains(term))
							{
								Tct.put(term, Tct.get(term) + 1);
							}
						}
						line = reader.readLine();
					}
				}
			}

			int total = 0;
			for (String key : Tct.keySet())
			{
				total += Tct.get(key) + 1;
			}

			int i = 0;
			for (String term : vocabulary)
			{
				condProb[i++][classes.indexOf(className)] = (double) (Tct.get(term) + 1) / (double) total;
			}
		}
	}

	public String applyMultinomailNB(String contents) throws Exception
	{
		HashSet<String> words = new HashSet<String>();
		String line = contents;
		String terms[] = line.split(" ");
		for (String term : terms)
		{
			if (vocabulary.contains(term))
			{
				words.add(term);
			}
		}

		double score[] = new double[classes.size()];
		for (String className : classes)
		{
			int c = classes.indexOf(className);
			score[c] = Math.log(prior[c]);
			int i = 0;
			for (String term : vocabulary)
			{
				if (words.contains(term))
				{
					score[c] += Math.log(condProb[i][c]);
				}
				i++;
			}
		}

		int index = 0;
		double max = score[0];
		for (int i = 0; i < score.length; i++)
		{

			if (score[i] > max)
			{
				max = score[i];
				index = i;
			}
		}

		return classes.get(index);
	}

	public String applyMultinomailNB(BufferedReader documentReader) throws IOException
	{
		if (documentReader == null)
		{
			return null;
		}
		HashSet<String> words = new HashSet<String>();
		String line = documentReader.readLine();
		while (line != null)
		{
			String terms[] = line.split(" ");
			for (String term : terms)
			{
				if (vocabulary.contains(term))
				{
					words.add(term);
				}
			}
			line = documentReader.readLine();
		}

		double score[] = new double[classes.size()];
		for (String className : classes)
		{
			int c = classes.indexOf(className);
			score[c] = Math.log(prior[c]);
			int i = 0;
			for (String term : vocabulary)
			{
				if (words.contains(term))
				{
					score[c] += Math.log(condProb[i][c]);
				}
				i++;
			}
		}

		int index = 0;
		double max = score[0];
		for (int i = 0; i < score.length; i++)
		{

			if (score[i] > max)
			{
				max = score[i];
				index = i;
			}
		}

		return classes.get(index);
	}

}
