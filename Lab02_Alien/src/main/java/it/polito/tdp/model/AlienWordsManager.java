package it.polito.tdp.model;

import java.util.*;
import static java.util.stream.Collectors.toSet;

public class AlienWordsManager
{
	private final Map<String,Word> dictionary; 
	public static AlienWordsManager singletonInstance;
	
	
	private AlienWordsManager()
	{
		this.dictionary = new HashMap<>();
	}
	
	public boolean addWord(String alienWord, String translation)
	{
		if(this.dictionary.containsKey(alienWord))
		{
			boolean added = this.dictionary.get(alienWord).addTranslation(translation);
			return added;
		}
		else
		{
			Word newWord = new WordEnhanced(alienWord, translation); //<---- *** change Word *****
			//Word newWord = new SimpleWord(alienWord, translation); //<---- *** change Word *****
			this.dictionary.put(alienWord, newWord);
			return true;	//added
		}
	}
	
	public String getTranslationOf(String alienWord)
	{
		if(alienWord.contains("?"))
			return this.getWildcardTranslationOf(alienWord);
		else
		{
			if(!this.dictionary.containsKey(alienWord))
				return String.format("Non esiste alcuna parola aliena chiamata \"%s\"!", alienWord);
			else
				return this.dictionary.get(alienWord).printTranslations();
		}
	}
	
	private String getWildcardTranslationOf(String wildcardWord)
	{
		String[] twoParts = wildcardWord.split("\\?",-1);
		String regex = String.format("%s%c%s", twoParts[0], '.', twoParts[1]);
		
		Set<String> allWords = this.dictionary.keySet();
		Set<String> matchingWords = allWords.stream()
											.filter(s -> s.matches(regex))
											.collect(toSet());
		if(matchingWords.size() == 0)
			return String.format("Non esiste alcuna parola aliena del tipo \"%s\"!", wildcardWord);
		else
		{
			StringBuilder translationsList = new StringBuilder();
			for(String w : matchingWords)
			{
				if(translationsList.length() > 0)
					translationsList.append('\n');
				
				String translation = this.dictionary.get(w).toString();
				translationsList.append(translation);
			}			
			return String.format("Possibili traduzioni di parole aliene del tipo \"%s\":\n%s", wildcardWord, translationsList);
		}
	}
	
	public void resetAll()
	{
		this.dictionary.clear();
	}
	
	public static AlienWordsManager instance()
	{
		if(singletonInstance == null)
			singletonInstance = new AlienWordsManager();
		
		return singletonInstance;
	}
}
