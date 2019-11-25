package com.musica.ui;

import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

import static sun.security.krb5.internal.crypto.Nonce.value;

public class ButtonCell extends TableCell<Disposer.Record, Boolean> {
    Button cellButton;
    EventHandler<ActionEvent> event;

    public ButtonCell(String i, String text){
        cellButton = new Button(text);
        cellButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                event.handle(t);
            }
        });
        setId(i);
    }

    public void setEvent(EventHandler<ActionEvent> event) {
        this.event = event;
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellButton);
        }
    }

    public String getIdBtn() {
        return super.getId();
    }
}
