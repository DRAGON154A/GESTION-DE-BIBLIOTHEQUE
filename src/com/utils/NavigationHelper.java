package com.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationHelper {

	public static void changerPage(Node eventSource,String cheminFileFxml,String titre)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(NavigationHelper.class.getResource(cheminFileFxml));
			Parent root = loader.load();
			
			Stage stage = (Stage) eventSource.getScene().getWindow();
			stage.setTitle(titre);
			stage.setScene(new Scene(root));
			stage.show();
		}catch(IOException e) {
			System.err.println("Erreur lors du changement de la page");
			e.printStackTrace();
		}
	}
}
