package application.controller;

import java.io.IOException;

import application.utils.NavigationHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class MainViewController {
	
	
	@FXML
	public void handleLoginButton(ActionEvent event) throws IOException
	{
		NavigationHelper.changerPage((Node) event.getSource(),"/application/view/login_view.fxml", "Connexion");
	}
	
	@FXML
	public void handleRegisterButton(ActionEvent event) throws IOException
	{
		
	}
	
	@FXML
	public void handleInfoLink(ActionEvent event) throws IOException
	{
		
	}
}
