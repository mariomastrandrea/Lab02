package it.polito.tdp.model;

public abstract class Word
{
	private final String alienWord;
	
	
	public Word(String alienWord)
	{
		this.alienWord = alienWord;
	}
	
	public String getAlienWord() { return this.alienWord; }
	public abstract boolean addTranslation(String newTranslation);
	public abstract String printTranslations();
	
	@Override
	public abstract String toString();
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alienWord == null) ? 0 : alienWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if(alienWord == null)
		{
			if(other.alienWord != null)
				return false;
		}
		else if(!alienWord.equals(other.alienWord))
			return false;
		return true;
	}
	
}
