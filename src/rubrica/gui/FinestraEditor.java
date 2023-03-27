package rubrica.gui;

import java.awt.Color;
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

import rubrica.Persona;
import rubrica.costanti.CostantiGUI;
import rubrica.repository.RubricaDataBase;

public class FinestraEditor implements ActionListener {
	
	private FinestraPrincipale finestraPrincipale;
	private RubricaDataBase rubricaDataBase;
	
	private JPanel jpanel;
	private JFrame jframeEditor;
	private JLabel nomeLabel, cognomeLabel, indirizzoLabel, telefonoLabel, etaLabel;
	protected JTextField nomeTextField, cognomeTextField, indirizzoTextField, telefonoTextField, etaTextField;
	private JButton salvaButton, annullaButton;
	
	public FinestraEditor(FinestraPrincipale finestraPrincipale, RubricaDataBase rubricaDataBase) {
		this.finestraPrincipale = finestraPrincipale;
		this.rubricaDataBase = rubricaDataBase;
		
		setFrameEditor();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.salvaButton) salvaButtonPressed();
		if (e.getSource() == this.annullaButton) annullaButtonPressed();
	}
	
	private void setFrameEditor() {
		this.jframeEditor = new JFrame("Editor Persona");
		this.jframeEditor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.jframeEditor.setSize(400, 385);
		this.jframeEditor.setLocation(760, 370);
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
		this.nomeLabel.setBounds(100, 8, 70, 20);
		
		this.cognomeLabel = new JLabel(CostantiGUI.LABEL_COGNOME);
		this.cognomeLabel.setBounds(100, 55, 70, 20);
		
		this.indirizzoLabel = new JLabel(CostantiGUI.LABEL_INDIRIZZO);
		this.indirizzoLabel.setBounds(100, 102, 70, 20);
		
		this.telefonoLabel = new JLabel(CostantiGUI.LABEL_TELEFONO);
		this.telefonoLabel.setBounds(100, 149, 70, 20);
		
		this.etaLabel = new JLabel(CostantiGUI.LABEL_ETA);
		this.etaLabel.setBounds(100, 196, 70, 20);
	}
	
	private void setTextFields() {
		this.nomeTextField = new JTextField();
		this.nomeTextField.setBounds(100, 27, 193, 28);
		
		this.cognomeTextField = new JTextField();
		this.cognomeTextField.setBounds(100, 74, 193, 28);
		
		this.indirizzoTextField = new JTextField();
		this.indirizzoTextField.setBounds(100, 121, 193, 28);
		
		this.telefonoTextField = new JTextField();
		this.telefonoTextField.setBounds(100, 168, 193, 28);
		
		this.etaTextField = new JTextField();
		this.etaTextField.setBounds(100, 215, 193, 28);
	}
	
	private void setButtons() {
		this.salvaButton = new JButton(CostantiGUI.BUTTON_SALVA);
		this.salvaButton.setBounds(100, 260, 193, 28);
		this.salvaButton.setForeground(Color.WHITE);
		this.salvaButton.setBackground(Color.BLACK);
		this.salvaButton.setFocusable(false);
		this.salvaButton.addActionListener(this);
		
		this.annullaButton = new JButton(CostantiGUI.BUTTON_ANNULLA);
		this.annullaButton.setBounds(100, 295, 193, 28);
		this.annullaButton.setForeground(Color.WHITE);
		this.annullaButton.setBackground(Color.BLACK);
		this.annullaButton.setFocusable(false);
		this.annullaButton.addActionListener(this);
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
	
	protected void salvaButtonPressed() {
		JTable jtable = this.finestraPrincipale.getJtable();
		
		Persona persona = new Persona(this.nomeTextField.getText(), this.cognomeTextField.getText(), 
									  this.indirizzoTextField.getText(), this.telefonoTextField.getText(), 
									  Integer.valueOf(this.etaTextField.getText()));
		
		this.rubricaDataBase.insertPersona(persona);
		
		Vector<String> newData = new Vector<>();
		newData.add(persona.getNome());
		newData.add(persona.getCognome());
		newData.add(persona.getTelefono());
		
		DefaultTableModel defaultTableModel = (DefaultTableModel) jtable.getModel();
		defaultTableModel.addRow(newData);
		
		jtable.clearSelection();
		
		closeFrameEditor();
	}
	
	private void annullaButtonPressed() {
		this.finestraPrincipale.getJtable().clearSelection();
		
		closeFrameEditor();
	}
	
	protected void closeFrameEditor() {
		this.jframeEditor.dispose();
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
