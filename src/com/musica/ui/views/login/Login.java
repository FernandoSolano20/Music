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

import javax.mail.MessagingException;
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
        try {
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

            String type = null;
            type = controller.login(email,pass);

            if(type != null){
                if(controller.isFirstTime()){
                    super.changePass(event);
                }
                else {
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Bienvenido!",
                            "Bienvenido " + emailField.getText());
                    super.index(event);
                }
            }
            else{
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "No encontrado",
                        "Usuario o contraseña incorrecta");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }

    @FXML
    protected void showRegisterView(ActionEvent event) throws IOException{
        show.ShowWindow(event,"views/registro/registro.fxml", "Registro");
    }

    @FXML
    protected void forget(ActionEvent event){
        Window owner = submitButton.getScene().getWindow();
        try {
            String email = emailField.getText();
            if(controller.forgetPass(email)){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Revise el correo!",
                        "Revise el correo  con la nueva contraseña");
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                        "No se encontro al usuario");
            }
        } catch (MessagingException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error",
                    "No se encontro al usuario");
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }
}

