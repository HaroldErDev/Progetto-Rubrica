package rubrica.gui;

import javax.swing.JTable;
import javax.swing.JTextField;

import rubrica.CaricatorePersone;
import rubrica.Persona;

public class FinestraEditorModifica extends FinestraEditor {
	
	private FinestraPrincipale finestraPrincipale;
	private Persona persona;
	
	public FinestraEditorModifica(FinestraPrincipale finestraPrincipale, CaricatorePersone caricatore, Persona persona) {
		super(finestraPrincipale, caricatore);
		this.finestraPrincipale = finestraPrincipale;
		this.persona = persona;
	}
	
	@Override
	protected void salvaButtonPressed() {
		JTable jtable = this.finestraPrincipale.getJtable();
		
		this.persona.setNome(this.nomeTextField.getText());
		this.persona.setCognome(this.cognomeTextField.getText());
		this.persona.setIndirizzo(this.indirizzoTextField.getText());
		this.persona.setTelefono(this.telefonoTextField.getText());
		this.persona.setEta(Integer.valueOf(this.etaTextField.getText()));
		
		int row = jtable.getSelectedRow();
		
		jtable.setValueAt(this.persona.getNome(), row, 0);
		jtable.setValueAt(this.persona.getCognome(), row, 1);
		jtable.setValueAt(this.persona.getTelefono(), row, 2);
		
		salvaDati();
		
		jtable.clearSelection();
		this.getJframeEditor().dispose();
	}
	
	@Override
	protected void setTextFields() {
		this.nomeTextField = new JTextField(this.persona.getNome());
		this.nomeTextField.setBounds(150, 27, 193, 28);
		
		this.cognomeTextField = new JTextField(this.persona.getCognome());
		this.cognomeTextField.setBounds(150, 74, 193, 28);
		
		this.indirizzoTextField = new JTextField(this.persona.getIndirizzo());
		this.indirizzoTextField.setBounds(150, 121, 193, 28);
		
		this.telefonoTextField = new JTextField(this.persona.getTelefono());
		this.telefonoTextField.setBounds(150, 168, 193, 28);
		
		this.etaTextField = new JTextField(String.valueOf(this.persona.getEta()));
		this.etaTextField.setBounds(150, 215, 193, 28);
	}
	
}
