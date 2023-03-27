package rubrica.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import rubrica.Persona;
import rubrica.Rubrica;

public class RubricaDataBase {
	
	private String url = PropertiesReader.getURL();
	private String username = PropertiesReader.getUsername();
	private String password = PropertiesReader.getPassword();
	
	private Rubrica rubrica;
	
	private Connection connection;
	
	public RubricaDataBase(Rubrica rubrica) {
		this.rubrica = rubrica;
		
		try {
			// Effettua la connesione con il Data Base
			this.connection = DriverManager.getConnection(url, username, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		String query = "SELECT * FROM persona";
		
		try {
			Statement statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				Persona persona = new Persona(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), 
											  resultSet.getString(5), resultSet.getInt(6));
				persona.setId(resultSet.getInt(1));
				
				this.rubrica.addPersona(persona);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void insert(Persona persona) {
		String query = "INSERT INTO persona(nome,cognome,indirizzo,telefono,eta) VALUES(?,?,?,?,?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, persona.getNome());
			statement.setString(2, persona.getCognome());
			statement.setString(3, persona.getIndirizzo());
			statement.setString(4, persona.getTelefono());
			statement.setInt(5, persona.getEta());
			
			statement.executeUpdate();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				persona.setId(generatedKeys.getInt(1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Persona persona) {
		String query = "UPDATE persona SET nome=?, cognome=?, indirizzo=?, telefono=?, eta=? WHERE id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, persona.getNome());
			statement.setString(2, persona.getCognome());
			statement.setString(3, persona.getIndirizzo());
			statement.setString(4, persona.getTelefono());
			statement.setInt(5, persona.getEta());
			statement.setInt(6, persona.getId());
			
			statement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Persona persona) {
		String query = "DELETE FROM persona WHERE id="+persona.getId();
		
		try {
			connection.prepareStatement(query).executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
