package com.musica.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.plugin2.message.PrintAppletReplyMessage;

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

    public void ShowWindow(ActionEvent event, String path, String title, String id, MusicUI secondScene) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        //Get controller of scene2
        secondScene = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        secondScene.transferId(id);

        //Show scene 2 in new window
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
    }
}
