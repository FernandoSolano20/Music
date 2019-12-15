package com.musica.ui.views.Gender.Lists;

import com.musica.ui.AlertHelper;
import com.musica.ui.ButtonCell;
import com.musica.ui.MusicUI;
import com.musica.ui.views.Artist.Lists.ListArtist;
import com.musica.ui.views.reproductionList.Lists.ListsReproList;
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

public class ListGender extends MusicUI implements Initializable {
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> columnName;
    @FXML
    private TableColumn<String, String> columnDescript;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnEdit;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnDelete;
    @FXML
    private TextField search;

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
        super.listSong(event);
    }

    @FXML
    protected void rAlbum(ActionEvent event) throws IOException {
        super.rAlbum(event);
    }

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        super.logout(event);
    }

    public void showGender() {
        List<String> songs = controller.getAllGender();
        ObservableList<String> details = FXCollections.observableArrayList(songs);
        columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[1]));
        columnDescript.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[2]));

        columnEdit.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));
        columnEdit.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    int i = -2;
                    List<String> elements = songs;

                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        i++;
                        String id = "";
                        if (i < elements.size() && i >= 0) {
                            id = elements.get(i).split(",")[0];
                        }
                        ButtonCell btnCell = new ButtonCell(id, "Actualizar");
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                try {
                                    ListGender.super.uGender(t, btnCell.getIdBtn());
                                } catch (IOException e) {
                                    e.printStackTrace();
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
                    int i = -2;
                    List<String> elements = songs;

                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        i++;
                        String id = "";
                        if (i < elements.size() && i >= 0) {
                            id = elements.get(i).split(",")[0];
                        }
                        ButtonCell btnCell = new ButtonCell(id, "Borrar");
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                try {
                                    Window owner = search.getScene().getWindow();
                                    boolean response = controller.deleteGender(Integer.parseInt(btnCell.getIdBtn()));
                                    if (response){
                                        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Genero eliminado");
                                        ListGender.super.rGender(t);
                                    }
                                    else {
                                        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El genero no se puede eliminar");
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
        showGender();
    }

    @FXML
    protected void cGender(ActionEvent event) throws IOException {
        super.cGender(event);
    }
}
