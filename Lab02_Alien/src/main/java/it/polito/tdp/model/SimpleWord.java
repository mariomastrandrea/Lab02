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
		return String.format("Unica traduzione di \"%s\":  \"%s\"", this.getAlienWord(), this.translatedWord); 
	}
	
	@Override
	public boolean addTranslation(String overwriteWord)
	{
		if(this.translatedWord.equals(overwriteWord))
			return false;	//not added
		else
		{
			this.translatedWord = overwriteWord;	
			return true;	//added
		}
	}

	@Override
	public String toString()
	{
		return String.format("\"%s\"\t-->\t\"%s\"", super.getAlienWord(), this.translatedWord);
	}
	
}
