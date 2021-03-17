package it.polito.tdp.model;

public class SimpleWord extends Word
{
	private String translatedWord;

	
	public SimpleWord(String alienWord, String translatedWord)
	{
		super(alienWord);
		this.translatedWord = translatedWord;
	}
	
	@Override
	public String printTranslations()
	{
		return String.format("Unica traduzione di \"%s\":\t%s", this.getAlienWord(), this.translatedWord); 
	}
	
	@Override
	public boolean addTranslation(String overwriteWord)
	{
		this.translatedWord = overwriteWord;	
		return false;
	}
	
}
