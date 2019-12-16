package com.musica.ui.views.Album.Lists.Songs;

import com.musica.ui.AlertHelper;
import com.musica.ui.ButtonCell;
import com.musica.ui.MusicUI;
import com.musica.ui.views.reproductionList.Lists.Song.ListsSongsRL;
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

public class ListSongAlbum extends MusicUI {
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> columnName;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnDelete;
    private int idAlbum;

    @FXML
    protected void rGender(ActionEvent event) throws IOException {
        super.rGender(event);
    }

    @FXML
    protected void rComp(ActionEvent event) throws IOException {
        super.rComp(event);
    }

    @FXML
    protected void rArt(ActionEvent event) throws IOException {
        super.rArt(event);
    }

    @FXML
    protected void listSong(ActionEvent event) throws IOException {
        super.rSongAdmin(event);
    }

    @FXML
    protected void rAlbum(ActionEvent event) throws IOException {
        super.rAlbum(event);
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        super.logout(event);
    }

    public void transferId(String message) {
        super.transferId(message);
        idAlbum = Integer.parseInt(message);
        List<String> songs = controller.searchSongByAlbumId(Integer.parseInt(getId()));
        ObservableList<String> details = FXCollections.observableArrayList(songs);
        columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[1]));
        columnDelete.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));
        columnDelete.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    int i = -2;
                    List<String> elements = songs;
                    int listId = Integer.parseInt(message);
                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        i++;
                        String id = "";
                        if(i < elements.size() && i >= 0){
                            id = elements.get(i).split(",")[0];
                        }
                        ButtonCell btnCell = new ButtonCell(id, "Eliminar");
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t) {
                                try {
                                    Window owner = table.getScene().getWindow();
                                    boolean response = controller.deleteSong(Integer.parseInt(btnCell.getIdBtn()));
                                    if (response){
                                        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Cancion eliminada");
                                        ListSongAlbum.super.adminSongsAlbumList(t,""+listId);
                                    }
                                    else {
                                        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La lista aun tiene canciones");
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        btnCell.setEvent(event);
                        return btnCell;
                    }

                });
        table.setItems(details);
    }
}
