package com.musica.ui.views.SongQueue;

import com.musica.ui.AlertHelper;
import com.musica.ui.ButtonCell;
import com.musica.ui.MusicUI;
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

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;


public class SongQueue extends MusicUI implements Initializable {
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> columnName;
    @FXML
    private TableColumn<String, String> columnScore;
    @FXML
    private TableColumn<String, String> columnGender;
    @FXML
    private TableColumn<String, String> columnArt;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnAdd;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField search;
    static MyAudioPlayer thePlayer;
    int countAdd = -2;

    Iterator<String> iterator;
    private static  AdvancedPlayer player;
    private static int pausedOnFrame = 0;
    private static boolean playing = false;

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
        try {
            showMedia();
        } catch (MalformedURLException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        } catch (FileNotFoundException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR,  "Error", e.getMessage());
        } catch (JavaLayerException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR,  "Error", e.getMessage());
        } catch (BasicPlayerException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR,  "Error", e.getMessage());
        }
    }

    private void showMedia() throws MalformedURLException, FileNotFoundException, JavaLayerException, BasicPlayerException {
        List<String> list = null;
        try {
            list = controller.showMediaUser();
        ObservableList<String> details = FXCollections.observableArrayList(list);

        columnName.setCellValueFactory(c -> {
            String[] result = c.getValue().split(",");
            String element = "";
            if(result[0].equals("Song")) {
                element = result[2];
            }
            else {
                element = result[3];
            }
            return new SimpleStringProperty(element);
        });
        columnScore.setCellValueFactory(c -> {
            String[] result = c.getValue().split(",");
            String element = "";
            if(result[0].equals("Song")) {
                element = result[29];
            }
            return new SimpleStringProperty(element);
        });
        columnGender.setCellValueFactory(c -> {
            String[] result = c.getValue().split(",");
            String element = "";
            if(result[0].equals("Song")) {
                element = result[4];
            }
            return new SimpleStringProperty(element);
        });
        columnArt.setCellValueFactory(c -> {
            String[] result = c.getValue().split(",");
            String element = "";
            if(result[0].equals("Song")) {
                element = result[7];
            }
            return new SimpleStringProperty(element);
        });
        columnAdd.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));
            List<String> finalList = list;
            columnAdd.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    int i = -2;
                    List<String> elements = finalList;
                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        String path = "";
                        countAdd++;
                        String id = "";
                        if (countAdd < table.getItems().size() && countAdd >= 0) {
                            for (int j = 0; j < elements.size(); j++){
                                String[] item = elements.get(j).split(",");
                                if (elements.get(countAdd).split(",")[0].equals("Song")) {
                                    if(table.getItems().get(countAdd).split(",")[30].equals(item[30])){
                                        path = item[30];
                                        break;
                                    }
                                }
                                else if(table.getItems().get(countAdd).split(",")[1].equals(item[1])) {
                                    path = item[1];
                                    break;
                                }
                            }
                        }

                        ButtonCell btnCell = new ButtonCell(path, "Añadir");
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t) {
                                System.out.println(btnCell.getIdBtn());
                                if(!tryParseInt(btnCell.getIdBtn())){
                                    MusicUI.setQueue(btnCell.getIdBtn());
                                }
                                else {
                                    int idList = Integer.parseInt(btnCell.getIdBtn());
                                    List<String> songs = null;
                                    try {
                                        songs = controller.searchSongsByReproductionListId(idList);
                                        for (String item:songs) {
                                            MusicUI.setQueue(item.split(",")[29]);
                                        }
                                    } catch (Exception e) {
                                        AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                                    }
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
                if(result[0].equals("Song")){
                    if (result[2].toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.
                    } else if (result[29].toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    else if (result[4].toLowerCase().indexOf(lowerCaseFilter) != -1)
                        return true;
                    else if (result[7].toLowerCase().indexOf(lowerCaseFilter) != -1)
                        return true;
                    else
                        return false; // Does not match.
                }
                else {
                    if (result[3].toLowerCase().indexOf(lowerCaseFilter) != -1)
                        return true;
                    else
                        return false; // Does not match.
                }
            });
            countAdd = -1;
            table.refresh();
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<String> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void playSongQueue(){
        iterator = MusicUI.getQueue().iterator();
        if(SongQueue.thePlayer != null){
            SongQueue.thePlayer.close();
        }
        SongQueue.thePlayer = new MyAudioPlayer(iterator, true);
        SongQueue.thePlayer.start();
    }
}
