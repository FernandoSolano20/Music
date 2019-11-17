package com.musica.ui.views.login;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import java.io.IOException;

public class Login extends MusicUI {

    @FXML
    private GridPane rootPane;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Window owner = submitButton.getScene().getWindow();
        String email = emailField.getText();
        String pass = passwordField.getText();

        if(email.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Digite su correo");
            return;
        }

        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Digite su contraseña");
            return;
        }

        boolean response = controller.login(email,pass);
        if(response){
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Bienvenido!",
                    "Bienvenido " + emailField.getText());
            show.ShowWindow(event,"./views/registro/registro.fxml", "Musica");
        }
        else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "No encontrado",
                    "Usuario o contraseña incorrecta");
        }




    }

    @FXML
    protected void showRegisterView(ActionEvent event) throws IOException{
        show.ShowWindow(event,"views/registro/registro.fxml", "Registro");
    }
}

