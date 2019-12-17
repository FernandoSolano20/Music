package com.musica.ui.views.buy;

import com.musica.ui.AlertHelper;
import com.musica.ui.ButtonCell;
import com.musica.ui.MusicUI;
import com.musica.ui.views.index.user.Index;
import com.musica.ui.views.reproductionList.Lists.Song.ListsSongsRL;
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

public class Buy extends MusicUI implements Initializable {

    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> columnName;
    @FXML
    private TableColumn<String, String> columnPrice;
    @FXML
    private TableColumn<String, String> columnGender;
    @FXML
    private TableColumn<String, String> columnArt;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnBuy;
    @FXML
    private Button create;
    @FXML
    private TextField search;
    int countBuy = -2;

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

    public void showReproductionList() throws Exception {

        List<String> list = controller.getAllSongs();

        ObservableList<String> details = FXCollections.observableArrayList(list);

        columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[1]));
        columnPrice.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[30]));
        columnGender.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[3]));
        columnArt.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[17]));
        columnBuy.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));

        columnBuy.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    int i = -2;
                    List<String> elements = list;
                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        countBuy++;
                        String id = "";
                        if (countBuy < table.getItems().size() && countBuy >= 0) {
                            for (int j = 0; j < elements.size(); j++){
                                if(table.getItems().get(countBuy).split(",")[0].equals(elements.get(j).split(",")[0])){
                                    id = elements.get(j).split(",")[0];
                                    break;
                                }
                            }
                        }
                        ButtonCell btnCell = new ButtonCell(id, "Comprar");
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t) {
                                System.out.println(btnCell.getIdBtn());
                                Window owner = create.getScene().getWindow();
                                boolean response = false;
                                try {
                                    response = controller.buy(Integer.parseInt(btnCell.getIdBtn()));
                                    if (response){
                                        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Cancion comprada");
                                    }
                                    else {
                                        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion no se compro");
                                    }
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
                if (result[30].toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                if (result[17].toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else
                    return false; // Does not match.
            });
            countBuy = -1;
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
            showReproductionList();
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    protected void cSong(ActionEvent event) throws IOException {
        super.cSong(event);
    }
}
