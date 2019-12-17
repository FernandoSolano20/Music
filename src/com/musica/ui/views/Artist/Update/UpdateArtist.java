package com.musica.ui.views.Artist.Update;

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
import java.time.LocalDate;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpdateArtist extends MusicUI {
    @FXML
    private TextField name;
    @FXML private TextField lastName;
    @FXML private TextField dayBorn;
    @FXML private TextField monthBorn;
    @FXML private TextField yearBorn;
    @FXML private ChoiceBox country;
    @FXML private TextField artistName;
    @FXML private TextField dayDead;
    @FXML private TextField monthDead;
    @FXML private TextField yearDead;
    @FXML private TextField reference;
    @FXML private TextField description;
    @FXML private TextField gender;
    @FXML private Button save;
    private int idArt;
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
            int dayBorn = Integer.parseInt(this.dayBorn.getText());
            int monthBorn = Integer.parseInt(this.monthBorn.getText());
            int yearBorn = Integer.parseInt(this.yearBorn.getText());
            LocalDate dateBorn = LocalDate.of(yearBorn,monthBorn,dayBorn);
            String country = (String) this.country.getValue();
            if(country == null){
                country = countryName;
            }
            String artistName = this.artistName.getText();
            String dayDead = this.dayDead.getText();
            String monthDead = this.monthDead.getText();
            String yearDead = this.yearDead.getText();
            LocalDate dateDead = null;
            if(!dayDead.isEmpty() && !monthDead.isEmpty() && !yearDead.isEmpty()){
                dateDead = LocalDate.of(Integer.parseInt(yearDead),Integer.parseInt(monthDead),Integer.parseInt(dayDead));
            }
            String reference = this.reference.getText();
            String description = this.description.getText();
            String gender = this.gender.getText();

            boolean response = false;

                response = controller.updateArtist(idArt,name,lastName,country,dateBorn,dateDead,reference,description,gender,artistName);
            if (response == true){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Artisita almacenado");
                super.rArt(event);
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El Artisita no se pudo almacenar");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }

    @Override
    public void transferId(String message) {
        super.transferId(message);
        String element[] = new String[0];
        try {
            element = controller.getArtistById(Integer.parseInt(getId())).split(",");
            idArt = Integer.parseInt(element[0]);
            name.setText(element[1]);
            lastName.setText(element[2]);
            dayBorn.setText(element[5].split("-")[2]);
            monthBorn.setText(element[5].split("-")[1]);
            yearBorn.setText(element[5].split("-")[0]);
            countryName = element[3];
            artistName.setText(element[12]);
            if(!element[6].equals("null")){
                dayDead.setText(element[6].split("-")[2]);
                monthDead.setText(element[6].split("-")[1]);
                yearDead.setText(element[6].split("-")[0]);
            }
            reference.setText(element[7]);
            description.setText(element[8]);
            gender.setText(element[10]);
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
}
