package com.musica.ui.views.registro;

import com.musica.ui.ShowView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;


public class Registro {

    private ShowView show  = new ShowView();

    @FXML
    private Button image;

    @FXML
    private Button save;

    @FXML
    protected void uploadImage(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\fersolano\\Desktop"));
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null){
            System.out.println("Hola");
        }
        else {
            System.out.println("Hola");
        }
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        show.ShowWindow(event,"./views/login/login.fxml", "Iniciar Sesión");
    }
}
