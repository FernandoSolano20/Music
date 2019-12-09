package com.musica.ui.views.Catalog;

import com.musica.ui.AlertHelper;
import com.musica.ui.ButtonCell;
import com.musica.ui.MusicUI;
import com.sun.prism.impl.Disposer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Catalog extends MusicUI implements Initializable {
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> columnName;
    @FXML
    private TableColumn<String, String> columnAlbum;
    @FXML
    private TableColumn<String, String> columnGender;
    @FXML
    private TableColumn<String, String> columnComp;
    @FXML
    private TableColumn<String, String> columnScore;
    @FXML
    private Button create;

    @FXML
    protected void index(ActionEvent event) throws IOException {
        super.index(event);
    }

    @FXML
    protected void buy(ActionEvent event) throws IOException {
        super.buy(event);
    }

    @FXML
    protected void lists(ActionEvent event) throws IOException {
        super.lists(event);
    }

    @FXML
    protected void catalogs(ActionEvent event) throws IOException {
        super.catalogs(event);
    }

    @FXML
    protected void albums(ActionEvent event) throws IOException {
        super.albums(event);
    }

    @FXML
    protected void reproductions(ActionEvent event) throws IOException {
        super.reproductions(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCatalog();
    }

    public void showCatalog() {

        List<String> list = controller.getCatalog();

        ObservableList<String> details = FXCollections.observableArrayList(list);

        columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[1]));
        columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[1]));
        columnAlbum.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[25]));
        columnGender.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[3]));
        columnComp.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[19] + " " + c.getValue().split(",")[20]));
        columnScore.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[28]));


        table.setItems(details);
    }
}
