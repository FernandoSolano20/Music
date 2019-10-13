package com.musica.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowView {

    public void ShowWindow(ActionEvent event, String path, String title) throws IOException {

        Parent page = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(page);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle(title);
        window.show();
    }
}
