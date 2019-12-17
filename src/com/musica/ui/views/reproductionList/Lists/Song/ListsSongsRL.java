package com.musica.ui.views.reproductionList.Lists.Song;

import com.musica.ui.AlertHelper;
import com.musica.ui.ButtonCell;
import com.musica.ui.MusicUI;
import com.musica.ui.views.Song.Lists.ListsSong;
import com.musica.ui.views.reproductionList.Lists.ListsReproList;
import com.sun.prism.impl.Disposer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class ListsSongsRL extends MusicUI {
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> columnName;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnDelete;
    private int idListReproduction;
    @FXML
    private Button create;


    @FXML
    protected void createReproductionList(ActionEvent event) throws IOException {
        super.createReproductionList(event);
    }

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
        idListReproduction = Integer.parseInt(message);
        List<String> songs = null;
        try {
            songs = controller.searchSongsByReproductionListId(Integer.parseInt(getId()));
            ObservableList<String> details = FXCollections.observableArrayList(songs);
            columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[1]));
            columnDelete.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));
            List<String> finalSongs = songs;
            columnDelete.setCellFactory(
                    new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                        int i = -2;
                        List<String> elements = finalSongs;
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
                                        Window owner = create.getScene().getWindow();
                                        boolean response = controller.deleteSongsReproductionList(Integer.parseInt(btnCell.getIdBtn()),listId);
                                        if (response){
                                            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Cancion eliminada");
                                            ListsSongsRL.super.adminSongsReproductionList(t,""+listId);
                                        }
                                        else {
                                            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La lista aun tiene canciones");
                                        }
                                    } catch (IOException e) {
                                        AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                                    } catch (Exception e) {
                                        AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                                    }
                                }
                            };
                            btnCell.setEvent(event);
                            return btnCell;
                        }

                    });
            table.setItems(details);
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void addSong(ActionEvent event) throws IOException{
        super.addSongtoReproductionList(event,getId());
    }
}
