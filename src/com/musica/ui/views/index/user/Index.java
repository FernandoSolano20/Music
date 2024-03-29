package com.musica.ui.views.index.user;

import com.musica.ui.AlertHelper;
import com.musica.ui.ButtonCell;
import com.musica.ui.MusicUI;
import com.sun.prism.impl.Disposer.Record;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class Index extends MusicUI implements Initializable {
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> columnName;
    @FXML
    private TableColumn<String, String> columnCreate;
    @FXML
    private TableColumn<String, String> columnScore;
    @FXML
    private TableColumn<Record, Boolean> columnListBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showReproductionList();
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    public void showReproductionList() throws Exception {

        List<String> list = controller.searchReproductionListByUser();

        ObservableList<String> details = FXCollections.observableArrayList(list);

        columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[2]));
        columnCreate.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[1]));
        columnScore.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[3]));
        columnListBtn.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));


            columnListBtn.setCellFactory(
                    new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
                        int i = -2;
                        List<String> elements = list;
                        @Override
                        public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                            i++;
                            String id = "";
                            if(i < elements.size() && i >= 0){
                                id = elements.get(i).split(",")[0];
                            }
                            ButtonCell btnCell = new ButtonCell(id, "Ver");
                            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent t) {
                                    System.out.println(btnCell.getIdBtn());
                                    try {
                                        Index.super.songsOnReproductionList(t,btnCell.getIdBtn());
                                    } catch (IOException e) {
                                        AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                                    }
                                }
                            };
                            btnCell.setEvent(event);
                            return btnCell;
                        }

                    });


        table.setItems(details);
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

    @FXML
    protected void logout(ActionEvent event) throws IOException {
        super.logout(event);
    }
}
