package rubrica;

import rubrica.gui.FinestraPrincipale;

public class Main {

	public static void main(String[] args) {
		Rubrica rubrica = new Rubrica();
		
		CaricatorePersone caricatore = new CaricatorePersone(rubrica);
		caricatore.carica();
		
		new FinestraPrincipale(caricatore, rubrica);
	}

}
