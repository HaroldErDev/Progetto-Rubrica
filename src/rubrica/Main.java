package rubrica;

import rubrica.gui.FinestraLogin;
import rubrica.repository.RubricaDataBase;

public class Main {

	public static void main(String[] args) {
		RubricaDataBase rubricaDataBase = new RubricaDataBase();
		
		new FinestraLogin(rubricaDataBase);
	}

}
