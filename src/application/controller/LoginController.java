package application.controller;

import application.models.modelsServiceDAO.AuthentificationDAO;
import application.utils.AlertHelper;
import application.utils.NavigationHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML private TextField textFieldName;
	@FXML private TextField passwordVisibleField;
	@FXML private PasswordField passwordField;
	@FXML private Button actionPasswordVisibilityButton;
	
	private boolean passwordVisible = false;
	
    @FXML
    private void initialize() {
        // Synchronise les deux champs à chaque modification
    	passwordVisibleField.textProperty().bindBidirectional(passwordField.textProperty());
    }
	
	public void actionPasswordVisibility(ActionEvent event)
	{
		passwordVisible = !passwordVisible;
		
		passwordVisibleField.setVisible(passwordVisible); // rendre visible le mot de passe
		passwordVisibleField.setManaged(passwordVisible);
		passwordField.setVisible(!passwordVisible); //rendre invisible le mot de passe
		passwordField.setManaged(!passwordVisible);
		
		actionPasswordVisibilityButton.setText(passwordVisible ? "🙈" : "👁"); // changer l'apparence du bouton en fonction de l'etat des champs
	}
	
	@FXML
	public void fonctionLogin(ActionEvent event)
	{
        AuthentificationDAO authen = new AuthentificationDAO();

        String nameOrEmail = textFieldName.getText().trim();
        String password = passwordField.isVisible() ? passwordField.getText().trim() : passwordVisibleField.getText().trim();

        if (nameOrEmail.isEmpty() || password.isEmpty()) {
            AlertHelper.showWarning("Champs manquants", "Veuillez remplir tous les champs");
            return;
        }

        int idUser = authen.loginBibliothecaire(nameOrEmail, password);

        if (idUser != -1) {
            AlertHelper.AfficheInfo("Connexion réussie", "Bienvenue " + nameOrEmail.toUpperCase());
            NavigationHelper.changerPage((Node) event.getSource(), "../view/dashboard.fxml", "Accueil"); // Correction d'orthographe
        } else {
            AlertHelper.afficheError("Echec de la connexion", "Nom/email ou mot de passe incorrect");
        }
	}
	
    @FXML
    public void gotoRegister(ActionEvent event) {
        NavigationHelper.changerPage((Node) event.getSource(), "../view/register_view.fxml", "Créer un compte");
    }
}
