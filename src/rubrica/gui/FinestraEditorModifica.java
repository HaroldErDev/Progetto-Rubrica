package rubrica.gui;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import rubrica.Persona;
import rubrica.costanti.CostantiGUI;
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
		
		String nome = this.nomeTextField.getText();
		String cognome = this.cognomeTextField.getText();
		String indirizzo = this.indirizzoTextField.getText();
		String telefono = this.telefonoTextField.getText();
		
		if (nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || telefono.isEmpty()) {
			JOptionPane.showMessageDialog(null, CostantiGUI.EMPTY_TEXT_ERROR_MESSAGE, "ERRORE", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int eta = 0;
		try {
			eta = Integer.valueOf(this.etaTextField.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, CostantiGUI.INTEGER_ERROR_MESSAGE, "ERRORE", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.persona.setNome(nome);
		this.persona.setCognome(cognome);
		this.persona.setIndirizzo(indirizzo);
		this.persona.setTelefono(telefono);
		this.persona.setEta(eta);
		
		this.rubricaDataBase.updatePersona(this.persona);
		
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
