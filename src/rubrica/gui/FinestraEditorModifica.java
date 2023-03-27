package rubrica.gui;

import javax.swing.JTable;

import rubrica.Persona;
import rubrica.repository.RubricaDataBase;

public class FinestraEditorModifica extends FinestraEditor {
	
	private FinestraPrincipale finestraPrincipale;
	private RubricaDataBase rubricaDataBase;
	private Persona persona;
	
	public FinestraEditorModifica(FinestraPrincipale finestraPrincipale, RubricaDataBase rubricaDataBase, Persona persona) {
		super(finestraPrincipale, rubricaDataBase);
		
		this.finestraPrincipale = finestraPrincipale;
		this.rubricaDataBase = rubricaDataBase;
		this.persona = persona;
		
		updateTextFields();
	}
	
	@Override
	protected void salvaButtonPressed() {
		JTable jtable = this.finestraPrincipale.getJtable();
		
		this.persona.setNome(this.nomeTextField.getText());
		this.persona.setCognome(this.cognomeTextField.getText());
		this.persona.setIndirizzo(this.indirizzoTextField.getText());
		this.persona.setTelefono(this.telefonoTextField.getText());
		this.persona.setEta(Integer.valueOf(this.etaTextField.getText()));
		
		this.rubricaDataBase.update(this.persona);
		
		int row = jtable.getSelectedRow();
		
		jtable.setValueAt(this.persona.getNome(), row, 0);
		jtable.setValueAt(this.persona.getCognome(), row, 1);
		jtable.setValueAt(this.persona.getTelefono(), row, 2);
		
		jtable.clearSelection();
		
		this.closeFrameEditor();
	}
	
	private void updateTextFields() {
		this.nomeTextField.setText(this.persona.getNome());
		this.cognomeTextField.setText(this.persona.getCognome());
		this.indirizzoTextField.setText(this.persona.getIndirizzo());
		this.telefonoTextField.setText(this.persona.getTelefono());
		this.etaTextField.setText(String.valueOf(this.persona.getEta()));
	}
	
}
