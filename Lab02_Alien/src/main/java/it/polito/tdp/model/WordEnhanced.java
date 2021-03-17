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
		return this.translations.add(newTranslation);
	}

	@Override
	public String printTranslations()
	{
		StringBuilder list = new StringBuilder();
		for(String word : translations)
		{
			if(list.length() > 0)
				list.append(", ");
			
			list.append(word);
		}
		return String.format("Traduzioni di \"%s\":\t%s", super.getAlienWord(), list);
	}

}
