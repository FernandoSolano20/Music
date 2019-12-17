package com.musica.ui.views.registro;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
    private String pathImage;

    @FXML
    protected void uploadImage(ActionEvent event){
        Scene scene = save.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(!selectedFile.toPath().toString().isEmpty()){
            pathImage = selectedFile.toPath().toString().replace("\\", "\\\\");
        }
    }

    @FXML
    public void listarPaises(MouseEvent mouseEvent) throws IOException {
        ObservableList<String> countries = Stream.of(Locale.getISOCountries())
                .map(locales -> new Locale("", locales))
                .map(Locale::getDisplayCountry)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        country.setItems(countries);
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();
        try {
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

            String country = this.country.getValue().toString();
            String email = this.email.getText();
            String pass = this.pass.getText();

            if (!controller.validatePassword(pass)){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                        "La contraseña no cumple con formato");
                return;
            }

            String userName = this.userName.getText();

            boolean response = false;

            response = controller.registerClient(id,name,lastName,old,country,email,pass,userName,pathImage);

            if (response == true){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Usuario almacenado");
                show.ShowWindow(event,"./views/login/login.fxml", "Iniciar Sesión");
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El usuario no se pudo almacenar");
            }
        } catch (MessagingException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Hubo un error");
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }
}
