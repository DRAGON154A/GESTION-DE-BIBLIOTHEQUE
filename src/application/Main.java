package application;
	
import application.database.InitDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
        try {
        	// charger le fichier base de donnée
        	InitDB.initialize();
            // Charger le fichier FXML de la page d'accueil
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/main_view.fxml"));
            Parent root = loader.load();

            // Créer une scène et l'attacher à la fenêtre
            Scene scene = new Scene(root);

            // Définir le titre de la fenêtre
            primaryStage.setTitle("Gestion de Bibliothèque - IAI");

            // Ajouter la scène à la fenêtre
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true); // Pour que la vue prenne tout l'écran au démarrage

            // Afficher la fenêtre
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement de la page d'accueil.");
        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
