package com.controller;

import com.models.modelsServiceDAO.AuthentificationDAO;
import com.utils.AlertHelper;
import com.utils.NavigationHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController { // Changement de nom de la classe

    @FXML private PasswordField passwordField;
    @FXML private TextField textFieldPassword;
    @FXML private TextField textFieldName;
    @FXML private Button actionPasswordVisibilityButton;

    private boolean passwordVisible = false;

    @FXML
    private void initialize() {
        // Synchronise les deux champs à chaque modification
        textFieldPassword.textProperty().bindBidirectional(passwordField.textProperty());
    }

    @FXML
    public void actionPasswordVisibility() {
        passwordVisible = !passwordVisible;
        textFieldPassword.setVisible(passwordVisible); // rendre visible le mot de passe
        textFieldPassword.setManaged(passwordVisible);
        passwordField.setVisible(!passwordVisible); // rendre invisible le mode password 
        passwordField.setManaged(!passwordVisible);

        actionPasswordVisibilityButton.setText(passwordVisible ? "🙈" : "👁"); // modifier l'apparence du bouton
    }

    @FXML
    public void fonctionLogin(ActionEvent event) {
        AuthentificationDAO authen = new AuthentificationDAO();

        String nameOrEmail = textFieldName.getText().trim();
        String password = passwordField.isVisible() ? passwordField.getText().trim() : textFieldPassword.getText().trim();

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
