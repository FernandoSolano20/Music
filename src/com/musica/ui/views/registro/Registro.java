package com.musica.ui.views.registro;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;


public class Registro extends MusicUI {

    @FXML private TextField name;
    @FXML private TextField lastName;
    @FXML private TextField id;
    @FXML private TextField old;
    @FXML private ChoiceBox country;
    @FXML private PasswordField pass;
    @FXML private TextField email;
    @FXML private TextField userName;
    @FXML private Button image;
    @FXML private Button save;

    @FXML
    protected void uploadImage(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\fersolano\\Desktop"));
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();
        //System.out.println(name.getText());
        int id = Integer.parseInt(this.id.getText());
        String name = this.name.getText();
        String lastName = this.lastName.getText();
        int old = Integer.parseInt(this.old.getText());

        if (!controller.validateYears(old)){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Usted no es mayor a 18 años");
            return;
        }

        String country = (String) this.country.getValue();
        String email = this.email.getText();
        String pass = this.pass.getText();

        if (!controller.validatePassword(pass)){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "La contraseña no cumple con formato");
            return;
        }

        String userName = this.userName.getText();
        String image = this.image.getText();

        boolean response = controller.registerClient(id,name,lastName,old,country,email,pass,userName,image);
        if (response == true){
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Usuario almacenado");
            show.ShowWindow(event,"./views/login/login.fxml", "Iniciar Sesión");
        }
        else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El usuario no se pudo almacenar");
        }
    }
}
