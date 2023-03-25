package rubrica;

import rubrica.gui.FinestraPrincipale;

public class Main {

	public static void main(String[] args) {
		Rubrica rubrica = new Rubrica();
		
		CaricatorePersone caricatore = new CaricatorePersone(rubrica);
		caricatore.carica();
		
		FinestraPrincipale finestraPrincipale = new FinestraPrincipale(caricatore, rubrica);
		finestraPrincipale.fillData();
		finestraPrincipale.setFrameRubrica();
	}

}
