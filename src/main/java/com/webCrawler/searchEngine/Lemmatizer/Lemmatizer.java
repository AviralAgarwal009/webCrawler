package com.webCrawler.searchEngine.Lemmatizer;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import java.io.*;

public class Lemmatizer {

	public String[] wordLem(String[] tokens) {

		
		for(int i=0;i<tokens.length;i++)
			tokens[i]=tokens[i].toLowerCase();
		String[] lemmas=new String[1];
		try {

			// Parts-Of-Speech Tagging
			// reading parts-of-speech model to a stream

			InputStream posModelIn = new FileInputStream("en-pos-maxent.bin");

			// loading the parts-of-speech model from stream
			POSModel posModel = new POSModel(posModelIn);

			// initializing the parts-of-speech tagger with model
			POSTaggerME posTagger = new POSTaggerME(posModel);

			// Tagger tagging the tokens
			String tags[] = posTagger.tag(tokens);

			// loading the dictionary to input stream
			InputStream dictLemmatizer = new FileInputStream("en-lemmatizer.txt");

			// loading the lemmatizer with dictionary
			DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(dictLemmatizer);

			// finding the lemmas
			lemmas = lemmatizer.lemmatize(tokens, tags);

			// printing the results
			System.out.println("\nPrinting lemmas for the given sentence...");
			System.out.println("WORD - LEMMA");
			for (int i = 0; i < tokens.length; i++) {

				if (lemmas[i].equals("O")) {
					lemmas[i] = tokens[i];
				}
				System.out.println(tokens[i] + " - " + lemmas[i]);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lemmas;

	}

	/*public static void main(String[] args) {
		String[] tokens = new String[] { "Most", "large", "cities", "in", "the", "US", "had", "morning", "and",
				"afternoon", "newspapers", "." };
		Lemmatizer lem = new Lemmatizer();
		lem.wordLem(tokens);
	}*/

}