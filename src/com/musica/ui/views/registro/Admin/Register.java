package com.musica.ui.views.registro.Admin;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class Register extends MusicUI {
    @FXML
    private TextField name;
    @FXML private TextField lastName;
    @FXML private TextField id;
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
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();
        //System.out.println(name.getText());
        int id = Integer.parseInt(this.id.getText());
        String name = this.name.getText();
        String lastName = this.lastName.getText();
        String email = this.email.getText();
        String pass = this.pass.getText();
        String userName = this.userName.getText();
        String image = this.image.getText();
        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",controller.registerAdmin(id,name,lastName,email,pass,userName,image));
        controller.getAllUser();
        //show.ShowWindow(event,"./views/login/login.fxml", "Iniciar Sesión");
    }
}
