package org.example.atbconfigure.util;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;

public class PPane extends Pane {
    public PPane(){
        this.setPadding(new Insets(5,5,5,5));
    }
    public PPane(Pane pane){
        this.setPadding(new Insets(5,5,5,5));
        this.getChildren().add(pane);
    }
}
