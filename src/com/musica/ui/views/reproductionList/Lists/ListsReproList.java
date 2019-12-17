package com.musica.ui.views.reproductionList.Lists;

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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListsReproList extends MusicUI implements Initializable {

    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> columnName;
    @FXML
    private TableColumn<Disposer.Record, Boolean>columnAdd;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnUpdate;
    @FXML
    private TableColumn<Disposer.Record, Boolean> columnDelete;
    @FXML
    private TextField search;
    @FXML
    private Button create;
    int countDelete = -2;
    int countEdit = -2;
    int countAdd = -2;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showReproductionList();
    }

    public void showReproductionList(){
        List<String> list = controller.searchReproductionListByUser();

        ObservableList<String> details = FXCollections.observableArrayList(list);

        columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[2]));
        columnAdd.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));
        columnAdd.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    int i = -2;
                    List<String> elements = list;
                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        countAdd++;
                        String id = "";
                        if (countAdd < table.getItems().size() && countAdd >= 0) {
                            for (int j = 0; j < elements.size(); j++){
                                if(table.getItems().get(countAdd).split(",")[0].equals(elements.get(j).split(",")[0])){
                                    id = elements.get(j).split(",")[0];
                                    break;
                                }
                            }
                        }
                        ButtonCell btnCell = new ButtonCell(id, "Administrar");
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t) {
                                System.out.println(btnCell.getIdBtn());
                                try {
                                    ListsReproList.super.adminSongsReproductionList(t,btnCell.getIdBtn());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        btnCell.setEvent(event);
                        return btnCell;
                    }

                });

        columnUpdate.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));
        columnUpdate.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    int i = -2;
                    List<String> elements = list;
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
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t) {
                                System.out.println(btnCell.getIdBtn());
                                try {
                                    ListsReproList.super.updateReproductionList(t,btnCell.getIdBtn());
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
                    List<String> elements = list;
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
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t) {
                                System.out.println(btnCell.getIdBtn());
                                try {
                                    Window owner = create.getScene().getWindow();
                                    boolean response = controller.deleteReproductionListId(Integer.parseInt(btnCell.getIdBtn()));
                                    if (response){
                                        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Lista eliminada");
                                        ListsReproList.super.lists(t);
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
                if (result[2].toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else
                    return false; // Does not match.
            });
            countAdd = -1;
            countDelete = -1;
            countEdit = -1;
            table.refresh();
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<String> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }
}
