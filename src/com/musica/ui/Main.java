package com.musica.ui;

import com.musica.tl.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        try {
            Controller controller = new Controller();
            if(controller.isAdminOnDB()){
                Parent page = FXMLLoader.load(getClass().getResource("views/login/login.fxml"));
                Scene scene = new Scene(page);
                primaryStage.setTitle("Iniciar Sesión");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            else {
                Parent page = FXMLLoader.load(getClass().getResource("views/registro/Admin/Register.fxml"));
                Scene scene = new Scene(page);
                primaryStage.setTitle("Registro Administrador");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        }
        catch (Exception e){
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }


    public static void main(String[] args) throws ClassNotFoundException {
        launch(args);
    }
}
