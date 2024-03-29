package com.musica.ui.views.Compositor.Update;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpdateCompositor extends MusicUI {
    @FXML
    private TextField name;
    @FXML private TextField lastName;
    @FXML private ChoiceBox country;
    @FXML private TextField old;
    @FXML private Button save;
    private int idComp;
    private String countryName;

    @FXML
    public void listarPaises(MouseEvent mouseEvent) throws IOException {
        ObservableList<String> countries = Stream.of(Locale.getISOCountries())
                .map(locales -> new Locale("", locales))
                .map(Locale::getDisplayCountry)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        country.setItems(countries);
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();
        try {
            String name = this.name.getText();
            String lastName = this.lastName.getText();
            String country = (String) this.country.getValue();
            if(country == null){
                country = countryName;
            }
            int old = Integer.parseInt(this.old.getText());

            boolean response = false;

                response = controller.updateCompositor(idComp,name,lastName,country,old);

            if (response == true){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Compositor almacenado");
                super.rComp(event);
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El Compositor no se pudo almacenar");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }

    @Override
    public void transferId(String message) {
        super.transferId(message);
        try {
            String song[] = new String[0];
            song = controller.getCompositorById(Integer.parseInt(getId())).split(",");
            idComp = Integer.parseInt(song[0]);
            name.setText(song[1]);
            lastName.setText(song[2]);
            countryName = song[3];
            old.setText(song[4]);
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
}
