package org.example.atbconfigure.ui;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class OnOffDeviceWindow {

    public Pane create(){
        Pane pane = new Pane();
        RadioButton rOn = new RadioButton("Send ON command");
        RadioButton rOff = new RadioButton("Send OFF command");
        ToggleGroup toggleGroup = new ToggleGroup();
        rOn.setToggleGroup(toggleGroup);
        rOff.setToggleGroup(toggleGroup);
        VBox vBox = new VBox();
        vBox.getChildren().add(rOn);
        vBox.getChildren().add(rOff);
        pane.getChildren().add(vBox);
        return pane;
    }
}
