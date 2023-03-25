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

import rubrica.CaricatorePersone;
import rubrica.Persona;
import rubrica.Rubrica;
import rubrica.costanti.CostantiGUI;

public class FinestraPrincipale implements ActionListener {
	
	private CaricatorePersone caricatore;
	private Rubrica rubrica;
	
	private Vector<Vector<String>> data;
	
	private JFrame jframeRubrica;
	private JTable jtable;
	private JButton nuovoButton, modificaButton, eliminaButton;
	
	public FinestraPrincipale(CaricatorePersone caricatore, Rubrica rubrica) {
		this.caricatore = caricatore;
		this.rubrica = rubrica;
		this.data = new Vector<>();
	}
	
	public void setFrameRubrica() {
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
		this.nuovoButton = new JButton("Nuovo");
		this.nuovoButton.setFocusable(false);
		this.nuovoButton.addActionListener(this);
		
		this.modificaButton = new JButton("Modifica");
		this.modificaButton.setFocusable(false);
		this.modificaButton.addActionListener(this);
		
		this.eliminaButton = new JButton("Elimina");
		this.eliminaButton.setFocusable(false);
		this.eliminaButton.addActionListener(this);
	}
	
	private void setToolBar() {
		JToolBar jtoolbar = new JToolBar("Comandi Rubrica");
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
		this.jtable = new JTable() {
			// Imposta "Non Modificabile" sulle celle
			@Override
			public boolean isCellEditable(int col, int row) {
				return false;
			}
		};
		this.jtable.getTableHeader().setReorderingAllowed(false); // Rimuovi il "drag and drop" delle colonne
		this.jtable.setFocusable(false); // Rimuovi il "focus" dalle celle
		this.jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Imposta "una per volta" la selezione delle righe
		
		// Aggiungi colonne e righe alla tabella
		DefaultTableModel defaultTable = (DefaultTableModel) this.jtable.getModel();
		defaultTable.setColumnIdentifiers(CostantiGUI.NOMI_COLONNE);
		for (Vector<String> personaData : this.data) defaultTable.addRow(personaData);
		
		// Imposta il testo delle celle centrato nella tabella
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
		FinestraEditor finestraEditor = new FinestraEditor(this, this.caricatore);
		finestraEditor.setFrameEditor();
		
		this.jtable.clearSelection();
	}
	
	private void modificaButtonPressed(int row) {
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "Per modificare una persona devi prima selezionarla", "ERRORE", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String nome = (String) this.jtable.getValueAt(row, 0);
		String cognome = (String) this.jtable.getValueAt(row, 1);
		String telefono = (String) this.jtable.getValueAt(row, 2);
		
		for (Persona p : this.rubrica.getPersone()) {
			if (p.getNome().equals(nome) && p.getCognome().equals(cognome) && p.getTelefono().equals(telefono)) {
				FinestraEditor frameEditorModifica = new FinestraEditorModifica(this, this.caricatore, p);
				frameEditorModifica.setFrameEditor();
				return;
			}
		}
	}
	
	private void eliminaButtonPressed(int row) {
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "Per eliminare una persona devi prima selezionarla", "ERRORE", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String nome = (String) this.jtable.getValueAt(row, 0);
		String cognome = (String) this.jtable.getValueAt(row, 1);
		
		int option = JOptionPane.showConfirmDialog(null, "Eliminare la persona "+nome.toUpperCase()+" "+cognome.toUpperCase()+"?", 
												   "Elimina Persona", JOptionPane.YES_NO_OPTION);
		
		if (option == 0) {
			DefaultTableModel defaultTableModel = (DefaultTableModel) this.jtable.getModel();
			defaultTableModel.removeRow(row);
			this.rubrica.getPersone().remove(row);
			
			this.caricatore.salva();
		}
		
		this.jtable.clearSelection();
	}
	
	public void fillData() {
		for (Persona persona : this.rubrica.getPersone()) {
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

	public Rubrica getRubrica() {
		return rubrica;
	}

	public void setRubrica(Rubrica rubrica) {
		this.rubrica = rubrica;
	}
	
}
