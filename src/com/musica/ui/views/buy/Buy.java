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
    private TableColumn<Disposer.Record, Boolean> columnBuy;
    @FXML
    private Button create;

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

    public void showReproductionList() {

        List<String> list = controller.getAllSongs();

        ObservableList<String> details = FXCollections.observableArrayList(list);

        columnName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[1]));
        columnPrice.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().split(",")[30]));
        columnBuy.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue() != null));

        columnBuy.setCellFactory(
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
                        ButtonCell btnCell = new ButtonCell(id, "Comprar");
                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t) {
                                System.out.println(btnCell.getIdBtn());
                                Window owner = create.getScene().getWindow();
                                boolean response = controller.buy(Integer.parseInt(btnCell.getIdBtn()));
                                if (response){
                                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Cancion comprada");
                                }
                                else {
                                    AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion no se compro");
                                }
                            }
                        };
                        btnCell.setEvent(event);
                        return btnCell;
                    }

                });


        table.setItems(details);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showReproductionList();
    }
}
