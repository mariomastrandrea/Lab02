package it.polito.tdp.model;

import java.util.*;

public class AlienWordsManager
{
	private final Map<String,Word> dictionary; 
	
	
	public AlienWordsManager()
	{
		this.dictionary = new HashMap<>();
	}
	
	public boolean addWord(String alienWord, String translation)
	{
		if(this.dictionary.containsKey(alienWord))
		{
			boolean overwritten = this.dictionary.get(alienWord).addTranslation(translation);
			return overwritten;
		}
		else
		{
			Word newWord = new SimpleWord(alienWord, translation);
			this.dictionary.put(alienWord, newWord);
			return true;	//added
		}
	}
	
	public String getTranslationOf(String alienWord)
	{
		if(!this.dictionary.containsKey(alienWord))
			return String.format("Non esiste alcuna parola aliena chiamata \"%s\"!", alienWord);
		else
			return this.dictionary.get(alienWord).printTranslations();
	}
	
	public void resetAll()
	{
		this.dictionary.clear();
	}
}
