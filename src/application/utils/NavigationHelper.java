package application.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationHelper {
	

	public static void changerPage(Node eventSource,String cheminFile,String titre)
	{

		try
		{
		    // Récupère la scène à partir du noeud source
		    Stage stage = (Stage) eventSource.getScene().getWindow();
		    // Charge le nouveau FXML
		    Parent root = FXMLLoader.load(NavigationHelper.class.getResource(cheminFile));
		    // Crée une nouvelle scène
		    Scene scene = new Scene(root);
		    // Met à jour la scène et le titre
		    stage.setScene(scene);
		    stage.setTitle(titre);
		    stage.show();
		}catch(IOException e) {
			System.err.println("Erreur lors du changement de la page");
			e.printStackTrace();
		}
	}
}
