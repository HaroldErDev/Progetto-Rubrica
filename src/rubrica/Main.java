package rubrica;

import rubrica.gui.FinestraPrincipale;
import rubrica.repository.PropertiesReader;
import rubrica.repository.RubricaDataBase;

public class Main {

	public static void main(String[] args) {
		new PropertiesReader(true);
		
		Rubrica rubrica = new Rubrica();
		
		RubricaDataBase rubricaDataBase = new RubricaDataBase(rubrica);
		rubricaDataBase.load();
		
		//CaricatorePersone caricatore = new CaricatorePersone(rubrica);
		//caricatore.carica();
		
		new FinestraPrincipale(rubricaDataBase, rubrica);
	}

}
