package rubrica.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import rubrica.Persona;
import rubrica.Utente;

public class RubricaDataBase {
	
	private Connection connection;
	
	public RubricaDataBase() {
		PropertiesReader propReader = new PropertiesReader();
		try {
			// Effettua la connessione con il Data Base
			this.connection = DriverManager.getConnection(propReader.getURL(), propReader.getUsername(), propReader.getPassword());
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(1);
		}
	}
	
	public boolean exists(Utente utente) {
		String query = "SELECT 1 FROM utente WHERE username=? AND password=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, utente.getUsername());
			statement.setString(2, utente.getPassword());
			
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		
		return false;
	}
	
	public Vector<Persona> getAllPersone() {
		Vector<Persona> persone = new Vector<>();
		String query = "SELECT * FROM persona";
		
		try {
			Statement statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				Persona persona = new Persona(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), 
											  resultSet.getString(5), resultSet.getInt(6));
				persona.setId(resultSet.getInt(1));
				
				persone.add(persona);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(1);
		}
		
		return persone;
	}
	
	public Persona getPersona(String nome, String cognome, String telefono) {
		String query = "SELECT * FROM persona WHERE nome=? AND cognome=? AND telefono=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, telefono);
			
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				Persona persona = new Persona(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), 
						  					  resultSet.getString(5), resultSet.getInt(6));
				persona.setId(resultSet.getInt(1));
				
				return persona;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return null;
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
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
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
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}
	}
	
	public void delete(Persona persona) {
		String query = "DELETE FROM persona WHERE id="+persona.getId();
		
		try {
			connection.prepareStatement(query).executeUpdate();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}
	}
	
}
