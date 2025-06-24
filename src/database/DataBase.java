package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	private static final String dataUrl = "jdbc:sqlite:data/bibliotheque.db";
	private static Connection connection = null;
	
	// creation d'une connexion ou recuperation
	
	public static Connection getConnection() throws SQLException
	{
		if(connection == null || connection.isClosed())
		{
			connect();
		}
		return connection;
	}
	
	public static void connect()
	{

		try
		{
			File dbFile = new File("data/bibliotheque.db");
			
			if(!dbFile.exists())
				dbFile.mkdir();// crée le fichier si il existe pas
			connection = DriverManager.getConnection(dataUrl); // creation d'une connexion
			System.out.println("Connexion établir avec la base de données bibliotheque éffectuer avec succès");
		}
		catch(Exception e)
		{
			System.err.println("Erreur lors de la connexion à la base de données");
			e.printStackTrace();
		}
	}

}
