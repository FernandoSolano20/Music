package com.musica.ui.views.Artist.Update;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;
import java.time.LocalDate;

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

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();

        String name = this.name.getText();
        String lastName = this.lastName.getText();
        int dayBorn = Integer.parseInt(this.dayBorn.getText());
        int monthBorn = Integer.parseInt(this.monthBorn.getText());
        int yearBorn = Integer.parseInt(this.yearBorn.getText());
        LocalDate dateBorn = LocalDate.of(yearBorn,monthBorn,dayBorn);
        String country = "Country";//this.country.getText();
        String artistName = this.artistName.getText();
        int dayDead = Integer.parseInt(this.dayDead.getText());
        int monthDead = Integer.parseInt(this.monthDead.getText());
        int yearDead = Integer.parseInt(this.yearDead.getText());
        LocalDate dateDead = null;
        if(dayDead != 0 && monthDead != 0 && yearDead != 0){
            dateDead = LocalDate.of(yearDead,monthDead,dayDead);
        }
        String reference = this.reference.getText();
        String description = this.description.getText();
        String gender = this.gender.getText();

        boolean response = controller.updateArtist(idArt,name,lastName,country,dateBorn,dateDead,reference,description,gender,artistName);
        if (response == true){
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Artisita almacenado");
            super.rArt(event);
        }
        else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El Artisita no se pudo almacenar");
        }
    }

    @Override
    public void transferId(String message) {
        super.transferId(message);
        String element[] = controller.getArtistById(Integer.parseInt(getId())).split(",");
        idArt = Integer.parseInt(element[0]);
        name.setText(element[1]);
        lastName.setText(element[2]);
        dayBorn.setText(element[5].split("-")[2]);
        monthBorn.setText(element[5].split("-")[1]);
        yearBorn.setText(element[5].split("-")[0]);
        country.setValue(element[3]);
        artistName.setText(element[12]);
        if(element[6] != "" || element[6] != "null"){
            dayDead.setText(element[6].split("-")[2]);
            monthDead.setText(element[6].split("-")[1]);
            yearDead.setText(element[6].split("-")[0]);
        }
        reference.setText(element[7]);
        description.setText(element[8]);
        gender.setText(element[10]);
    }
}
