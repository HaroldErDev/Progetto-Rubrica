package rubrica.costanti;

import java.util.Arrays;
import java.util.Vector;

public interface CostantiGUI {
	
	public static final int LARGHEZZA = 500;
	
	public static final int ALTEZZA = 510;
	
	public static final String LOGIN_ERROR_MESSAGE = "Le credenziali inserite non sono corrette";
	
	public static final String MODIFICA_ERROR_MESSAGE = "Per modificare una persona devi prima selezionarla";
	
	public static final String ELIMINA_ERROR_MESSAGE = "Per eliminare una persona devi prima selezionarla";
	
	public static final String INTEGER_ERROR_MESSAGE = "'Eta' deve essere un numero intero";
	
	public static final String EMPTY_TEXT_ERROR_MESSAGE = "Non si possono lasciare caselle vuote";
	
	public static final String PERSONA_EXISTS_ERROR_MESSAGE = "Una persona con questo nome, cognome e telefono gi� esiste";
	
	public static final String BUTTON_LOGIN = "LOGIN";
	
	public static final String BUTTON_NUOVO = "Nuovo";
	
	public static final String BUTTON_MODIFICA = "Modifica";
	
	public static final String BUTTON_ELIMINA = "Elimina";
	
	public static final String BUTTON_SALVA = "Salva";
	
	public static final String BUTTON_ANNULLA = "Annulla";
	
	public static final String LABEL_USERNAME = "Username";
	
	public static final String LABEL_PASSWORD = "Password";
	
	public static final String LABEL_NOME = "Nome";
	
	public static final String LABEL_COGNOME = "Cognome";
	
	public static final String LABEL_INDIRIZZO = "Indirizzo";
	
	public static final String LABEL_TELEFONO = "Telefono";
	
	public static final String LABEL_ETA = "Eta";
	
	public static final Vector<String> NOMI_COLONNE = new Vector<>(Arrays.asList(LABEL_NOME.toUpperCase(), LABEL_COGNOME.toUpperCase(), 
																				 LABEL_TELEFONO.toUpperCase()));
	
}
