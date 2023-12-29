package org.example.atbconfigure.util;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PPane extends Pane {
    public PPane(){
        this.setPadding(new Insets(5,5,5,5));
    }
    public PPane(Pane pane){
        this.setPadding(new Insets(5,5,5,5));
        this.getChildren().add(pane);
    }
    public PPane(Button button){
        this.setPadding(new Insets(5,5,5,5));
        this.getChildren().add(button);
    }
    public PPane(Text text){
        this.setPadding(new Insets(5,5,5,5));
        this.getChildren().add(text);
    }
}
