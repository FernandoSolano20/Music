package com.musica.ui.views.reproductionList.Lists;

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
                        i++;
                        String id = "";
                        if(i < elements.size() && i >= 0){
                            id = elements.get(i).split(",")[0];
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
                        i++;
                        String id = "";
                        if(i < elements.size() && i >= 0){
                            id = elements.get(i).split(",")[0];
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
                        i++;
                        String id = "";
                        if(i < elements.size() && i >= 0){
                            id = elements.get(i).split(",")[0];
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

        table.setItems(details);
    }
}
