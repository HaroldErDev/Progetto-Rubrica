package rubrica.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	
	public static final String PROPERTIES = "credenziali_database.properties";
	
	private static Properties properties;
	
	public PropertiesReader(boolean readFromOutside) {
		properties = new Properties();
		
		try {
			if (readFromOutside) {
				// Leggi le proprietà dal file presente nella stessa directory del .jar file
				properties.load(new FileInputStream(PROPERTIES));
			} else {
				// Leggi le proprietà dal file presente nel classpath
				properties.load(this.getClass().getClassLoader().getResourceAsStream(PROPERTIES));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getURL() {
		return "jdbc:mysql://"+properties.getProperty("ip-server-mysql")+":"+properties.getProperty("port")+"/rubrica";
	}
	
	public static String getUsername() {
		return properties.getProperty("username");
	}
	
	public static String getPassword() {
		return properties.getProperty("password");
	}
	
}
