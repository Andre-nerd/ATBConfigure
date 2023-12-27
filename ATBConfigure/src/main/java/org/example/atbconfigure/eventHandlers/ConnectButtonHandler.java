package org.example.atbconfigure.eventHandlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;



public class ConnectButtonHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Event: " +actionEvent.getEventType());
    }
}
