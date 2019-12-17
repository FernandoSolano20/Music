package com.musica.ui.views.Album.Lists;

import com.musica.ui.AlertHelper;
import com.musica.ui.ButtonCell;
import com.musica.ui.MusicUI;
import com.musica.ui.views.Compositor.Lists.ListCompositor;
import com.sun.prism.impl.Disposer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

public class ListAlbum extends MusicUI implements Initializable {
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> columnName;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnSong;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnEdit;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnDelete;
    @FXML
    private TextField search;
    int countDelete = -2;
    int countEdit = -2;
    int countSong = -2;

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

    public void showInfo() throws Exception {
        List<String> songs = controller.getAllAlbum();
        ObservableList<String> details = FXCollections.observableArrayList(songs);
        columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[1]));

        columnEdit.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));
        columnEdit.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    List<String> elements = songs;

                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        countEdit++;
                        String id = "";
                        if (countEdit < table.getItems().size() && countEdit >= 0) {
                            for (int j = 0; j < elements.size(); j++){
                                if(table.getItems().get(countEdit).split(",")[0].equals(elements.get(j).split(",")[0])){
                                    id = elements.get(j).split(",")[0];
                                    break;
                                }
                            }
                        }
                        ButtonCell btnCell = new ButtonCell(id, "Actualizar");
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                try {
                                    ListAlbum.super.uAlbum(t, btnCell.getIdBtn());
                                } catch (IOException e) {
                                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                                }
                            }
                        };
                        btnCell.setEvent(event);
                        return btnCell;
                    }

                });

        columnSong.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));
        columnSong.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    List<String> elements = songs;

                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        countSong++;
                        String id = "";
                        if (countSong < table.getItems().size() && countSong >= 0) {
                            for (int j = 0; j < elements.size(); j++){
                                if(table.getItems().get(countSong).split(",")[0].equals(elements.get(j).split(",")[0])){
                                    id = elements.get(j).split(",")[0];
                                    break;
                                }
                            }
                        }
                        ButtonCell btnCell = new ButtonCell(id, "Ver");
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                try {
                                    ListAlbum.super.adminSongsAlbumList(t, btnCell.getIdBtn());
                                } catch (IOException e) {
                                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                                }
                            }
                        };
                        btnCell.setEvent(event);
                        return btnCell;
                    }

                });

        columnDelete.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));
        columnDelete.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    List<String> elements = songs;

                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        countDelete++;
                        String id = "";
                        if (countDelete < table.getItems().size() && countDelete >= 0) {
                            for (int j = 0; j < elements.size(); j++){
                                if(table.getItems().get(countDelete).split(",")[0].equals(elements.get(j).split(",")[0])){
                                    id = elements.get(j).split(",")[0];
                                    break;
                                }
                            }
                        }
                        ButtonCell btnCell = new ButtonCell(id, "Borrar");
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                try {
                                    Window owner = search.getScene().getWindow();
                                    boolean response = controller.deleteAlbum(Integer.parseInt(btnCell.getIdBtn()));
                                    if (response){
                                        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Album eliminado");
                                        ListAlbum.super.rAlbum(t);
                                    }
                                    else {
                                        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El Album no se puede eliminar");
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
        FilteredList<String> filteredData = new FilteredList<>(details, b -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                String[] result = item.split(",");
                if (result[1].toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else
                    return false; // Does not match.
            });
            countDelete = -1;
            countEdit = -1;
            countSong = -1;
            table.refresh();
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<String> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showInfo();
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    protected void cAlbum(ActionEvent event) throws IOException {
        super.cAlbum(event);
    }
}
