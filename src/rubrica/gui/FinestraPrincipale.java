package rubrica.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import rubrica.Persona;
import rubrica.costanti.CostantiGUI;
import rubrica.repository.RubricaDataBase;

public class FinestraPrincipale implements ActionListener {
	
	private RubricaDataBase rubricaDataBase;
	
	private Vector<Vector<String>> data;
	
	private JFrame jframeRubrica;
	private JTable jtable;
	private JButton nuovoButton, modificaButton, eliminaButton;
	
	public FinestraPrincipale(RubricaDataBase rubricaDataBase) {
		this.rubricaDataBase = rubricaDataBase;
		this.data = new Vector<>();
		
		fillData();
		setFrameRubrica();
	}
	
	private void setFrameRubrica() {
		this.jframeRubrica = new JFrame("Rubrica Telefonica");
		this.jframeRubrica.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.jframeRubrica.setSize(CostantiGUI.LARGHEZZA, CostantiGUI.ALTEZZA);
		this.jframeRubrica.setLocation(750, 300);
		this.jframeRubrica.setResizable(false);
		
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		this.jframeRubrica.setLayout(flow);
		
		setButtons();
		setToolBar();
		setTable();
		
		this.jframeRubrica.setVisible(true);
	}
	
	private void setButtons() {
		this.nuovoButton = new JButton(CostantiGUI.BUTTON_NUOVO);
		this.nuovoButton.setFocusable(false);
		this.nuovoButton.addActionListener(this);
		
		this.modificaButton = new JButton(CostantiGUI.BUTTON_MODIFICA);
		this.modificaButton.setFocusable(false);
		this.modificaButton.addActionListener(this);
		
		this.eliminaButton = new JButton(CostantiGUI.BUTTON_ELIMINA);
		this.eliminaButton.setFocusable(false);
		this.eliminaButton.addActionListener(this);
	}
	
	private void setToolBar() {
		JToolBar jtoolbar = new JToolBar();
		jtoolbar.setFloatable(false);
		
		jtoolbar.add(this.nuovoButton);
		jtoolbar.addSeparator(new Dimension(50,0));
		jtoolbar.add(this.modificaButton);
		jtoolbar.addSeparator(new Dimension(50,0));
		jtoolbar.add(this.eliminaButton);
		
		this.jframeRubrica.add(jtoolbar, BorderLayout.NORTH);
	}
	
	@SuppressWarnings("serial")
	private void setTable() {
		this.jtable = new JTable(this.data, CostantiGUI.NOMI_COLONNE) {
			// Imposta "Non Modificabile" sulle celle
			@Override
			public boolean isCellEditable(int col, int row) {
				return false;
			}
		};
		this.jtable.getTableHeader().setReorderingAllowed(false); // Rimuovi il "drag and drop" delle colonne
		this.jtable.setFocusable(false); // Rimuovi il "focus" dalle celle
		this.jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Imposta "una per volta" la selezione delle righe
		
		// Imposta il testo delle celle centrato
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int col=0; col<this.jtable.getColumnCount(); col++) {
			this.jtable.getColumnModel().getColumn(col).setCellRenderer(cellRenderer);
		}
		
		// Rendi "scorrevole" (scroll-bar) la visione dei componenti della tabella
		this.jframeRubrica.add(new JScrollPane(this.jtable));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = this.jtable.getSelectedRow();
		
		if (e.getSource() == this.nuovoButton) nuovoButtonPressed();
		if (e.getSource() == this.modificaButton) modificaButtonPressed(row);
		if (e.getSource() == this.eliminaButton) eliminaButtonPressed(row);
	}
	
	private void nuovoButtonPressed() {
		new FinestraEditor(this, this.rubricaDataBase);
		this.jtable.clearSelection();
	}
	
	private void modificaButtonPressed(int row) {
		if (row < 0) {
			JOptionPane.showMessageDialog(null, CostantiGUI.MODIFICA_ERROR_MESSAGE, "ERRORE", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String nome = (String) this.jtable.getValueAt(row, 0);
		String cognome = (String) this.jtable.getValueAt(row, 1);
		String telefono = (String) this.jtable.getValueAt(row, 2);
		
		Persona persona = this.rubricaDataBase.getPersona(nome, cognome, telefono);
		
		if (persona == null) {
			JOptionPane.showMessageDialog(null, "Impossibile trovare la persona selezionata", "ERRORE", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		new FinestraEditorModifica(this, this.rubricaDataBase, persona);
	}
	
	private void eliminaButtonPressed(int row) {
		if (row < 0) {
			JOptionPane.showMessageDialog(null, CostantiGUI.ELIMINA_ERROR_MESSAGE, "ERRORE", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String nome = (String) this.jtable.getValueAt(row, 0);
		String cognome = (String) this.jtable.getValueAt(row, 1);
		String telefono = (String) this.jtable.getValueAt(row, 2);
		
		int option = JOptionPane.showConfirmDialog(null, "Eliminare la persona "+nome.toUpperCase()+" "+cognome.toUpperCase()+"?", 
												   "Elimina Persona", JOptionPane.YES_NO_OPTION);
		
		// (0 = "YES"; 1 = "NO")
		if (option == 0) {
			Persona persona = this.rubricaDataBase.getPersona(nome, cognome, telefono);
			
			if (persona == null) {
				JOptionPane.showMessageDialog(null, "Impossibile trovare la persona selezionata", "ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			this.rubricaDataBase.delete(persona);
			
			DefaultTableModel defaultTableModel = (DefaultTableModel) this.jtable.getModel();
			defaultTableModel.removeRow(row);
		}
		
		this.jtable.clearSelection();
	}
	
	public void fillData() {
		for (Persona persona : this.rubricaDataBase.getAllPersone()) {
			Vector<String> newData = new Vector<>();
			newData.add(persona.getNome());
			newData.add(persona.getCognome());
			newData.add(persona.getTelefono());
			
			this.data.add(newData);
		}
	}

	public JFrame getJframeRubrica() {
		return jframeRubrica;
	}

	public void setJframeRubrica(JFrame jframeRubrica) {
		this.jframeRubrica = jframeRubrica;
	}

	public JTable getJtable() {
		return jtable;
	}

	public void setJtable(JTable jtable) {
		this.jtable = jtable;
	}
	
}
