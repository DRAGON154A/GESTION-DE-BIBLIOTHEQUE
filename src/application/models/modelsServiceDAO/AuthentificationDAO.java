package application.models.modelsServiceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.database.DataBase;
import application.models.modelsClass.Bibliothecaire;
import application.models.modelsInterfaceDAO.AuthentificationInterfaceDAO;
import application.utils.Hash256;

public class AuthentificationDAO implements AuthentificationInterfaceDAO{

	private final String tableName = "bibliothecaires";
	private final String COLUMN_NOM = "nom";
	private final String COLUMN_PRENOM = "prenom";
	private final String COLUMN_EMAIL = "email";
	private final String COLUMN_PASSWORD_HASH = "password_hash";
	private final String COLUMN_PAYS = "pays";
	private final String COLUMN_TELEPHONE = "telephone";
	private final String COLUMN_VILLE = "ville";
	private final String COLUMN_DATE_NAISSANCE = "date_naissance";
	private final String COLUMN_POSTE = "poste";
	
	@Override
	public boolean registerBibliothecaire(Bibliothecaire bibliothecaire) {
		
		String hashPassword = Hash256.hashPassword(bibliothecaire.getPassword());
		String sql = " INSERT INTO " + tableName + "("+COLUMN_NOM+", "+COLUMN_PRENOM+", "+COLUMN_EMAIL+", "+COLUMN_PASSWORD_HASH+", "+COLUMN_PAYS+", "+COLUMN_TELEPHONE+", "+COLUMN_VILLE+", "+COLUMN_DATE_NAISSANCE+", "+COLUMN_POSTE+")"+
						" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection connection = DataBase.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql))
		{
			pstmt.setString(1, bibliothecaire.getNom());
			pstmt.setString(2, bibliothecaire.getPrenom());
			pstmt.setString(3, bibliothecaire.getEmail());
			pstmt.setString(4, hashPassword);
			pstmt.setString(5, bibliothecaire.getPays());
			pstmt.setString(6, bibliothecaire.getTelephone());
			pstmt.setString(7, bibliothecaire.getVille());
			pstmt.setString(8, bibliothecaire.getDateNaissance());
			pstmt.setString(9, bibliothecaire.getPoste());
			
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			System.err.println("Erreur lors de l'ajout du bibliothecaire");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int loginBibliothecaire(String nameOrEmail, String password) {
		String passwordHash = Hash256.hashPassword(password);
		String sql = "SELECT bibliothecaire_id FROM " + tableName +
				" WHERE " + COLUMN_PASSWORD_HASH + " =?" +
				" AND (" + COLUMN_NOM + "=? OR " + COLUMN_EMAIL + " =?)";
		try(Connection connection = DataBase.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql))
		{
			pstmt.setString(1, passwordHash);
			pstmt.setString(2, nameOrEmail);
			pstmt.setString(3, nameOrEmail);
			
			ResultSet resultat = pstmt.executeQuery();
			int id=-1;
			while(resultat.next())
			{
				id = resultat.getInt("bibliothecaire_id");
			}
			return id;
		}catch(Exception e)
		{
			System.err.println("Erreur de l'authentification");
			e.printStackTrace();
			return -1;
		}
		
	}
}
