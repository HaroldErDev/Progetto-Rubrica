package rubrica.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import rubrica.CaricatorePersone;
import rubrica.Persona;
import rubrica.costanti.CostantiGUI;

public class FinestraEditor implements ActionListener {
	
	private FinestraPrincipale finestraPrincipale;
	private CaricatorePersone caricatore;
	
	private JPanel jpanel;
	private JFrame jframeEditor;
	private JLabel nomeLabel, cognomeLabel, indirizzoLabel, telefonoLabel, etaLabel;
	protected JTextField nomeTextField, cognomeTextField, indirizzoTextField, telefonoTextField, etaTextField;
	private JButton salvaButton, annullaButton;
	
	public FinestraEditor(FinestraPrincipale finestraPrincipale, CaricatorePersone caricatore) {
		this.finestraPrincipale = finestraPrincipale;
		this.caricatore = caricatore;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.salvaButton) salvaButtonPressed();
		if (e.getSource() == this.annullaButton) annullaButtonPressed();
	}
	
	public void setFrameEditor() {
		this.jframeEditor = new JFrame("Editor Persona");
		this.jframeEditor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.jframeEditor.setSize(CostantiGUI.LARGHEZZA, CostantiGUI.ALTEZZA);
		this.jframeEditor.setLocation(750, 300);
		this.jframeEditor.setResizable(false);
		
		setLabels();
		setTextFields();
		setButtons();
		setPanel();
		
		this.jframeEditor.add(this.jpanel);
		this.jframeEditor.setVisible(true);
	}
	
	private void setLabels() {
		this.nomeLabel = new JLabel(CostantiGUI.LABEL_NOME);
		this.nomeLabel.setBounds(150, 8, 70, 20);
		
		this.cognomeLabel = new JLabel(CostantiGUI.LABEL_COGNOME);
		this.cognomeLabel.setBounds(150, 55, 70, 20);
		
		this.indirizzoLabel = new JLabel(CostantiGUI.LABEL_INDIRIZZO);
		this.indirizzoLabel.setBounds(150, 102, 70, 20);
		
		this.telefonoLabel = new JLabel(CostantiGUI.LABEL_TELEFONO);
		this.telefonoLabel.setBounds(150, 149, 70, 20);
		
		this.etaLabel = new JLabel(CostantiGUI.LABEL_ETA);
		this.etaLabel.setBounds(150, 196, 70, 20);
	}
	
	protected void setTextFields() {
		this.nomeTextField = new JTextField();
		this.nomeTextField.setBounds(150, 27, 193, 28);
		
		this.cognomeTextField = new JTextField();
		this.cognomeTextField.setBounds(150, 74, 193, 28);
		
		this.indirizzoTextField = new JTextField();
		this.indirizzoTextField.setBounds(150, 121, 193, 28);
		
		this.telefonoTextField = new JTextField();
		this.telefonoTextField.setBounds(150, 168, 193, 28);
		
		this.etaTextField = new JTextField();
		this.etaTextField.setBounds(150, 215, 193, 28);
	}
	
	private void setPanel() {
		this.jpanel = new JPanel();
		this.jpanel.setLayout(null);
		
		this.jpanel.add(this.nomeLabel);
		this.jpanel.add(this.cognomeLabel);
		this.jpanel.add(this.indirizzoLabel);
		this.jpanel.add(this.telefonoLabel);
		this.jpanel.add(this.etaLabel);
		
		this.jpanel.add(this.nomeTextField);
		this.jpanel.add(this.cognomeTextField);
		this.jpanel.add(this.indirizzoTextField);
		this.jpanel.add(this.telefonoTextField);
		this.jpanel.add(this.etaTextField);
		
		this.jpanel.add(this.salvaButton);
		this.jpanel.add(this.annullaButton);
	}
	
	private void setButtons() {
		this.salvaButton = new JButton(CostantiGUI.BUTTON_SALVA);
		this.salvaButton.setBounds(150, 260, 193, 28);
		this.salvaButton.setFocusable(false);
		this.salvaButton.addActionListener(this);
		
		this.annullaButton = new JButton(CostantiGUI.BUTTON_ANNULLA);
		this.annullaButton.setBounds(150, 295, 193, 28);
		this.annullaButton.setFocusable(false);
		this.annullaButton.addActionListener(this);
	}
	
	protected void salvaButtonPressed() {
		JTable jtable = this.finestraPrincipale.getJtable();
		
		Persona persona = new Persona(this.nomeTextField.getText(), this.cognomeTextField.getText(), this.indirizzoTextField.getText(),
									  this.telefonoTextField.getText(), Integer.valueOf(this.etaTextField.getText()));
		
		Vector<String> newData = new Vector<>();
		newData.add(persona.getNome());
		newData.add(persona.getCognome());
		newData.add(persona.getTelefono());
		
		DefaultTableModel defaultTableModel = (DefaultTableModel) jtable.getModel();
		defaultTableModel.addRow(newData);
		
		this.finestraPrincipale.getRubrica().addPersona(persona);
		
		salvaDati();
		
		jtable.clearSelection();
		this.jframeEditor.dispose();
	}
	
	private void annullaButtonPressed() {
		this.finestraPrincipale.getJtable().clearSelection();
		this.jframeEditor.dispose();
	}
	
	protected void salvaDati() {
		this.caricatore.salva();
	}
	
	public FinestraPrincipale getFinestraPrincipale() {
		return finestraPrincipale;
	}

	public void setFinestraPrincipale(FinestraPrincipale finestraPrincipale) {
		this.finestraPrincipale = finestraPrincipale;
	}

	public JFrame getJframeEditor() {
		return jframeEditor;
	}

	public void setJframeEditor(JFrame jframeEditor) {
		this.jframeEditor = jframeEditor;
	}
	
}
