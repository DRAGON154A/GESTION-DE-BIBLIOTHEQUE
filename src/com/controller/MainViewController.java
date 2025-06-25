package com.controller;

import java.io.IOException;

import com.utils.NavigationHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MainViewController {
    @FXML
    private ImageView backgroundImage;

    @FXML
    private StackPane rootPane; 
    
    @FXML
    public void initialize() {
        // Liaison dynamique pour que l'image suive la taille de la fenêtre
        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());
    }
	
	@FXML
	public void handleLoginButton(ActionEvent event) throws IOException{
		NavigationHelper.changerPage((Node) event.getSource(), "../view/login_view.fxml", "Connexion");
	}
	
	@FXML
	public void handleRegisterButton(ActionEvent event) throws IOException{
		
	}
	
	@FXML
	public void handleInfoLink(ActionEvent event) {
		
	}
}
