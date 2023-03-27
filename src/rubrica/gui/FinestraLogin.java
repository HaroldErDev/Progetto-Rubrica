package rubrica.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import rubrica.Utente;
import rubrica.costanti.CostantiGUI;
import rubrica.repository.RubricaDataBase;

public class FinestraLogin implements ActionListener {
	
	private RubricaDataBase rubricaDataBase;
	
	private JFrame jframeLogin;
	private JPanel jpanel;
	private JButton loginButton;
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameTextField, passwordTextField;
	
	public FinestraLogin(RubricaDataBase rubricaDataBase) {
		this.rubricaDataBase = rubricaDataBase;
		
		setFrameLogin();
	}
	
	private void setFrameLogin() {
		this.jframeLogin = new JFrame("LOGIN");
		this.jframeLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.jframeLogin.setSize(400, 210);
		this.jframeLogin.setLocation(750, 400);
		this.jframeLogin.setResizable(false);
		
		setLabels();
		setTextFields();
		setButtons();
		setPanel();
		
		this.jframeLogin.add(this.jpanel);
		this.jframeLogin.setVisible(true);
	}
	
	private void setLabels() {
		this.usernameLabel = new JLabel(CostantiGUI.LABEL_USERNAME);
		this.usernameLabel.setBounds(100, 8, 70, 20);
		
		this.passwordLabel = new JLabel(CostantiGUI.LABEL_PASSWORD);
		this.passwordLabel.setBounds(100, 55, 70, 20);
	}
	
	private void setTextFields() {
		this.usernameTextField = new JTextField();
		this.usernameTextField.setBounds(100, 27, 193, 28);
		
		this.passwordTextField = new JPasswordField();
		this.passwordTextField.setBounds(100, 74, 193, 28);
	}
	
	private void setButtons() {
		this.loginButton = new JButton(CostantiGUI.BUTTON_LOGIN);
		this.loginButton.setBounds(100, 120, 193, 28);
		this.loginButton.setForeground(Color.WHITE);
		this.loginButton.setBackground(Color.BLACK);
		this.loginButton.setFocusable(false);
		this.loginButton.addActionListener(this);
	}
	
	private void setPanel() {
		this.jpanel = new JPanel();
		this.jpanel.setLayout(null);
		
		this.jpanel.add(this.usernameLabel);
		this.jpanel.add(this.passwordLabel);
		
		this.jpanel.add(this.usernameTextField);
		this.jpanel.add(this.passwordTextField);
		
		this.jpanel.add(this.loginButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Utente utente = new Utente(this.usernameTextField.getText(), this.passwordTextField.getText());
		
		if (this.rubricaDataBase.exists(utente)) {
			this.jframeLogin.dispose();
			new FinestraPrincipale(this.rubricaDataBase);
		} else {
			JOptionPane.showMessageDialog(null, CostantiGUI.LOGIN_ERROR_MESSAGE, "ERRORE", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
}
