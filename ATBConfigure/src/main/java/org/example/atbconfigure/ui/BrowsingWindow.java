package org.example.atbconfigure.ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class BrowsingWindow {
    private VBox vBox;
    ScrollPane scrollPane = new ScrollPane();
    RadioButton radioButton = new RadioButton("Auto scrolling");
    @Getter
    private final List<byte[]> rawList = new ArrayList<>();

    public VBox create(){

        vBox = new VBox();
        vBox.setPadding(new Insets(5,5,5,5));
        scrollPane.setContent(vBox);
        scrollPane.setPrefViewportHeight(700.0);
        scrollPane.setPrefViewportWidth(750.0);
        scrollPane.setVvalue(1.0);
        VBox mainBox = new VBox();
        radioButton.setPadding(new Insets(0,0,5,0));
        mainBox.getChildren().add(radioButton);
        mainBox.getChildren().add(scrollPane);
        return mainBox;
    }
    public void putByteArray(byte[] value){
        rawList.add(value);
        printList(value);
    }

    private void printList(byte[] value){
        String string = bytesArrayToString(value);
        Text text = new Text(string);
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                vBox.getChildren().add(text);
                if(radioButton.isSelected()) scrollPane.setVvalue(1.0);
            }
        });
    }

    private String bytesArrayToString(byte[] value){
        StringBuilder builder = new StringBuilder();
        builder.append("  ");
        for(int i= 0; i< 4; i++){
            builder.append(value[i]);
            builder.append(" . ");
        }
        for(int i= 4; i< value.length; i++){
            builder.append(value[i]);
            builder.append(" ");
        }
        return builder.toString();
    }
}
