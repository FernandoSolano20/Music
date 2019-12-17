package com.musica.ui.views.Album.Lists.Client.Song;

import com.musica.ui.AlertHelper;
import com.musica.ui.ButtonCell;
import com.musica.ui.MusicUI;
import com.musica.ui.views.Album.Lists.Songs.ListSongAlbum;
import com.sun.prism.impl.Disposer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class ListSongAlbumUser extends MusicUI {
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
    private int idAlbum;

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

    public void transferId(String message) {
        super.transferId(message);
        idAlbum = Integer.parseInt(message);
        List<String> songs = controller.searchSongByAlbumId(Integer.parseInt(getId()));
        ObservableList<String> details = FXCollections.observableArrayList(songs);
        columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[1]));
        columnAlbum.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[25]));
        columnGender.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[3]));
        columnComp.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[19] + " " + c.getValue().split(",")[20]));
        columnScore.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[28]));
        table.setItems(details);
    }
}
