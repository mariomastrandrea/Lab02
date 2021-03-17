package it.polito.tdp.alien;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

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
    private static final String ONLY_LETTERS = "il campo puo' contenere solo lettere maiuscole o minuscole [a-z,A-Z]!";
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
    	
    	else if(!inputText.matches("[a-zA-Z ]+")) //se vi e' almeno un carattere diverso da lettere e whitespace...
    		this.displayError(ONLY_LETTERS);
    	
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
    				translation = this.manager.getTranslationOf(alienWord);
    				this.textArea.setText(translation);
    				break;
    			case 2:
    				alienWord = words.get(0);
    				translation = words.get(1);
    				boolean added = this.manager.addWord(alienWord, translation);
    				if(added)
    					this.displayCorrectAddition(alienWord, translation);
    				else
    					this.displayOverwrite(alienWord);
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
    
    private void displayOverwrite(String overwrittenWord)
    {
    	String message = String.format("La parola \"%s\" era gia' presente ed stata sovrascritta!", overwrittenWord);
    	this.textArea.setText(message);
    }
    
    private void displayCorrectAddition(String newAlienWord, String translation)
    {
    	String message = String.format("La parola \"%s\" e' stata aggiunta correttamente al dizionario!\nLa sua traduzione e': %s", newAlienWord, translation);
    	this.textArea.setText(message);
    }
    
}
