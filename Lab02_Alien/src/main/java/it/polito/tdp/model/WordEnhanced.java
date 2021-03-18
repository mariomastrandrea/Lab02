package it.polito.tdp.model;

import java.util.*;

public class WordEnhanced extends Word
{
	private final Set<String> translations;
	
	
	public WordEnhanced(String alienWord, String firstTranslation)
	{
		super(alienWord);
		this.translations = new HashSet<>();
		this.translations.add(firstTranslation);
	}

	@Override
	public boolean addTranslation(String newTranslation)
	{
		return this.translations.add(newTranslation);	//added or not added
	}

	@Override
	public String printTranslations()
	{
		return String.format("Traduzioni di \"%s\":  %s", super.getAlienWord(), this.getTranslations());
	}
	
	private String getTranslations()
	{
		StringBuilder list = new StringBuilder();
		for(String word : translations)
		{
			if(list.length() > 0)
				list.append(", ");
			
			list.append('\"').append(word).append('\"');
		}
		return list.toString();
	}

	@Override
	public String toString()
	{
		return String.format("\"%s\"\t-->\t%s", super.getAlienWord(), this.getTranslations());
	}

}
