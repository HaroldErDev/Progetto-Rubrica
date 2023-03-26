package rubrica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class CaricatorePersone {
	
	public static final String PATH = System.getProperty("user.home")+"\\Documents\\Informazioni";
	public static final String EXTENSION = ".txt";
	
	private Rubrica rubrica;
	private File info;
	
	public CaricatorePersone(Rubrica rubrica) {
		this.rubrica = rubrica;
		this.info = new File(PATH);
	}
	
	public void carica() {
		Scanner scanner = null;
				
		for (File fileEntry : this.info.listFiles()) {
			try {
				scanner = new Scanner(fileEntry);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				scanner.close();
				return;
			}
			
			String nextLine = scanner.nextLine();
			String[] data = nextLine.split(";");
			this.rubrica.addPersona(new Persona(data[0], data[1], data[2], data[3], Integer.valueOf(data[4])));
			
			scanner.close();
		}
	}
	
	public void salva() {
		PrintStream printStream = null;
		for (File file : this.info.listFiles()) file.delete();
		
		for (Persona persona : this.rubrica.getPersone()) {
			String fileName = persona.getNome().toUpperCase()+"-"+persona.getCognome().toUpperCase();
			
			File newFile = new File(this.info, fileName+EXTENSION);
			
			int i = 0;
			while (newFile.exists()) {
				newFile.renameTo(new File(this.info, fileName+"-"+String.valueOf(i++)+EXTENSION));
			}
			
			try {
				printStream = new PrintStream(newFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				printStream.close();
				return;
			}
			
			printStream.print(persona.toString());
			printStream.close();
		}
	}
	
}
