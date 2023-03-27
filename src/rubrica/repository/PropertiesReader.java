package rubrica.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class PropertiesReader {
	
	public static final String PROPERTIES = "credenziali_database.properties";
	
	private Properties properties;
	
	public PropertiesReader() {
		this.properties = new Properties();
		
		try {
			// Leggi le proprietà dal file presente nella stessa directory del .jar file
			this.properties.load(new FileInputStream(PROPERTIES));
			
			// Leggi le proprietà dal file presente nel classpath
			/*this.properties.load(this.getClass().getClassLoader().getResourceAsStream(PROPERTIES));*/
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(1);
		}
	}
	
	public String getURL() {
		return "jdbc:mysql://"+this.properties.getProperty("ip-server-mysql")+":"+this.properties.getProperty("port")+"/rubrica";
	}
	
	public String getUsername() {
		return this.properties.getProperty("username");
	}
	
	public String getPassword() {
		return this.properties.getProperty("password");
	}
	
}
