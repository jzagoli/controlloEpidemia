package com.jgg.controlloEpidemia.view;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.service.UtenteService;
import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    static final UtenteService utenteService = new UtenteService();

    @FXML
    private BorderPane loginPageBorderPane;
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.disableProperty().bind(
                Bindings.or(
                        usernameField.textProperty().isEmpty(),
                        passwordField.textProperty().isEmpty())
        );
    }

    @FXML
    private void loginButtonClicked() {
        String user = usernameField.getText();
        String password = passwordField.getText();
        Utente u = utenteService.findByUsername(user);
        if (u == null) {
            errorLabel.setText("Utente non trovato");
            if (errorLabel.isVisible()) {
                errorAnimation(errorLabel);
            } else {
                errorLabel.setVisible(true);
            }
        } else if (!u.getPassword().equals(password)) {
            errorLabel.setText("La password è errata");
            if (errorLabel.isVisible()) {
                errorAnimation(errorLabel);
            } else {
                errorLabel.setVisible(true);
            }
        } else {
            App.utenteCorrente = u;
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/homePage.fxml"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            loginPageBorderPane.getScene().setRoot(root);
        }
    }

    private void errorAnimation(Label label) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), label);
        st.setByX(0.2);
        st.setByY(0.2);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();
    }

}
