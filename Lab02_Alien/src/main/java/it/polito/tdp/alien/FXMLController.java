package it.polito.tdp.alien;

import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import it.polito.tdp.model.*;

public class FXMLController 
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField wordsTextField;

    @FXML
    private Button translateButton;

    @FXML
    private TextArea textArea;

    @FXML
    private Button clearTextButton;
    
    private static final String EMPTY_FIELD = "il campo non puo' essere vuoto!\nInserisci almeno una parola";
    private static final String TOO_MANY_WORDS = "il campo non puo' contenere piu' di 2 parole!";
    private static final String ONLY_LETTERS_AND_WILDCARD = "il campo puo' contenere solo lettere maiuscole o minuscole [a-z,A-Z]! ('?' wildcard)";
    private static final String ONLY_ONE_WILDCARD = "la parola cercata non puo' contenere più di una wildcard['?']";
    private static final String NO_WILDCARD_ACCEPTED = "le wildcard non possono essere usate nella registrazione di traduzioni!";
    private AlienWordsManager manager;
    
    
    public void setModel(AlienWordsManager manager) { this.manager = manager; }
    
    @FXML
    void handleClearText(ActionEvent event) 
    {
    	this.manager.resetAll();
    	this.textArea.setText("Dizionario resettato correttamente!");
    	this.wordsTextField.clear();
    }

    @FXML
    void handleTranslate(ActionEvent event) 
    {
    	String inputText = this.wordsTextField.getText().trim();
    	
    	if(inputText.isBlank())
    		this.displayError(EMPTY_FIELD);
    	
    	else if(!inputText.matches("[a-zA-Z àèéìòù?]+")) //se vi e' almeno un carattere diverso da lettere, whitespace e wildcard...
    		this.displayError(ONLY_LETTERS_AND_WILDCARD);
    	
    	else
    	{
        	this.wordsTextField.clear();
    		List<String> words = new ArrayList<>(List.of(inputText.toLowerCase().split(" ")));
    		words.removeIf(s -> s.isEmpty());	//tolgo le stringhe vuote
    		
    		String translation, alienWord;
    		switch(words.size())
    		{
    			case 1:
    				alienWord = words.get(0);
    				long numWildcards = alienWord.chars().filter(c -> c =='?').count();
    				
    				if(numWildcards > 1)
    					this.displayError(ONLY_ONE_WILDCARD);
    				else 
    				{
    					translation = this.manager.getTranslationOf(alienWord);
    					this.textArea.setText(translation);
    				}
    				break;
    			case 2:
    				if(inputText.contains("?"))
    				{
    					this.displayError(NO_WILDCARD_ACCEPTED);
    					break;
    				}
    				alienWord = words.get(0);
    				translation = words.get(1);
    				boolean added = this.manager.addWord(alienWord, translation);
    				if(added)
    					this.displayCorrectAddition(alienWord);
    				else
    					this.displayWordAlreadyPresent(alienWord, translation);
    				break;
    			default:
    				this.displayError(TOO_MANY_WORDS);
    		}
    	}
    }

    @FXML
    void initialize() 
    {
        assert wordsTextField != null : "fx:id=\"wordsTextField\" was not injected: check your FXML file 'Scene_Lab02.fxml'.";
        assert translateButton != null : "fx:id=\"translateButton\" was not injected: check your FXML file 'Scene_Lab02.fxml'.";
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'Scene_Lab02.fxml'.";
        assert clearTextButton != null : "fx:id=\"clearTextButton\" was not injected: check your FXML file 'Scene_Lab02.fxml'.";
    }
    
    private void displayError(String errorMessage)
    {
    	this.textArea.setText(String.format("Errore: %s", errorMessage));
    }
    
    private void displayWordAlreadyPresent(String alienWord, String translation)
    {
    	String message = String.format("*** La traduzione di \"%s\" con \"%s\" era gia' presente e non e' stata sovrascritta! ***", alienWord, translation);
    	this.textArea.setText(message);
    }
    
    private void displayCorrectAddition(String newAlienWord)
    {
    	String message = String.format("*** La traduzione di \"%s\" e' stata aggiunta correttamente al dizionario! ***\n %s", 
    			newAlienWord, this.manager.getTranslationOf(newAlienWord));
    	this.textArea.setText(message);
    }
    
}
