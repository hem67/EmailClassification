package com.emailclassification.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class NBWorker
{

	public static final String hamClassString = "ham";
	public static final String spamClassString = "spam";

	public static HashSet<String> stopWords = new HashSet<>();
	static HashSet<String> vocabularyWithStopWords = new HashSet<String>();
	static HashSet<String> vocabularyWithoutStopWords = new HashSet<String>();

	static int nHam = 0, nSpam = 0, n = 0;

	public static String run(String filename) throws Exception
	{
		MultinomialNaiveBayes naiveBayesClassifier = null;

		readStopWords();

		constructVocabulary();

		ArrayList<String> classes = new ArrayList<>();
		classes.add(hamClassString);
		classes.add(spamClassString);

		naiveBayesClassifier = new MultinomialNaiveBayes(vocabularyWithoutStopWords, nHam, nSpam, n, classes);
		try
		{
			naiveBayesClassifier.trainMultinomialNB();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));

			String result = naiveBayesClassifier.applyMultinomailNB(reader);
			return result;
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

	}

	public static String checkSpam(String contents) throws Exception
	{
		MultinomialNaiveBayes naiveBayesClassifier = null;

		readStopWords();

		constructVocabulary();

		ArrayList<String> classes = new ArrayList<>();
		classes.add(hamClassString);
		classes.add(spamClassString);

		naiveBayesClassifier = new MultinomialNaiveBayes(vocabularyWithoutStopWords, nHam, nSpam, n, classes);
		try
		{
			naiveBayesClassifier.trainMultinomialNB();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			String result = naiveBayesClassifier.applyMultinomailNB(contents);
			return result;
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

	private static void readStopWords()
	{
		try
		{
			File stopWordsFile = new File(Parameters.stopwordsFileName);
			BufferedReader stopWordsReader = new BufferedReader(new FileReader(stopWordsFile));
			String stopWord = stopWordsReader.readLine();
			while (stopWord != null)
			{
				String words[] = stopWord.split(" ");
				stopWords.addAll(Arrays.asList(words));
				stopWord = stopWordsReader.readLine();
			}

			stopWordsReader.close();
		} catch (Exception ex)
		{
			System.out.println(
					"Error reading the stopwords file. Please make sure that the \"StopWords.txt\" file is in the same folder as the java files."
							+ "Check the README file for more details");
			return;
		}

	}

	private static void constructVocabulary()
	{
		BufferedReader reader = null;
		try
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
						if (!vocabularyWithStopWords.contains(term))
						{
							vocabularyWithStopWords.add(term);
						}
						if (!vocabularyWithoutStopWords.contains(term) && !stopWords.contains(term))
						{
							vocabularyWithoutStopWords.add(term);
						}
					}
					line = reader.readLine();
				}
				nHam++;
				n++;
			}
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
						if (!vocabularyWithStopWords.contains(term))
						{
							vocabularyWithStopWords.add(term);
						}
						if (!vocabularyWithoutStopWords.contains(term) && !stopWords.contains(term))
						{
							vocabularyWithoutStopWords.add(term);
						}
					}
					line = reader.readLine();
				}
				nSpam++;
				n++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return;
		}

	}

}
