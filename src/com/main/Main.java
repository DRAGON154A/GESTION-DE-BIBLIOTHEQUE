package com.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

 @Override
    public void start(Stage stage) throws IOException {
        // Chargement du fichier FXML pour définir l'interface utilisateur
        // Assurez-vous que "primary.fxml" est dans src/main/resources/com/exemple/monapp/
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480); // Création de la scène avec une taille par défaut
        
        stage.setTitle("Ma Première App JavaFX !"); // Titre de la fenêtre
        stage.setScene(scene); // Définition de la scène pour la fenêtre
        stage.show(); // Affichage de la fenêtre
    }

    public static void main(String[] args) {
        launch(); // Méthode standard pour lancer une application JavaFX
    }

}