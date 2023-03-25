package rubrica;

import java.util.Vector;

public class Rubrica {
	
	private Vector<Persona> persone;
	
	public Rubrica() {
		this.persone = new Vector<>();
	}
	
	public Vector<Persona> getPersone() {
		return persone;
	}

	public void setPersone(Vector<Persona> persone) {
		this.persone = persone;
	}
	
	public void addPersona(Persona persona) {
		this.persone.add(persona);
	}
	
}
