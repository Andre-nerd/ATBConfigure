package org.example.atbconfigure.ui;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class BrowsingWindow {
    private VBox vBox;
    ScrollPane scrollPane = new ScrollPane();
    @Getter
    private final List<byte[]> rawList = new ArrayList<>();

    public ScrollPane create(){
        vBox = new VBox();
        scrollPane.setContent(vBox);
        scrollPane.setPrefViewportHeight(700.0);
        scrollPane.setVvalue(1.0);
        return scrollPane;
    }
    public void putByteArray(byte[] value){
        rawList.add(value);
        printList(value);
        scrollPane.setVvalue(1.0);
    }

    private void printList(byte[] value){
        String string = bytesArrayToString(value);
        Text text = new Text(string);
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                vBox.getChildren().add(text);
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
