package org.example.atbconfigure.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.example.atbconfigure.tcpService.SendMessageCallback;
import org.example.atbconfigure.util.CreateCommandUtil;
import org.example.atbconfigure.util.PPane;

import static org.example.atbconfigure.util.CommonUtils.crc8;
import static org.example.atbconfigure.util.PaneUtil.getGridPane;
import static org.example.atbconfigure.util.PaneUtil.getGridThreePane;

public class OnOffDeviceWindow {
    Text message = new Text();
    SendMessageCallback sendMessageCallback;

    public OnOffDeviceWindow(SendMessageCallback sendMessageCallback) {
        this.sendMessageCallback = sendMessageCallback;
    }

    public Pane create() {
        GridPane gridPane = getGridThreePane(20, 40, 40, 0, false);
        RadioButton rOn = new RadioButton("Reload device  ");
        RadioButton rOff = new RadioButton("OFF device  ");
        rOff.setPadding(new Insets(5, 0, 0, 0));
        Text title = new Text("Reset device:");
        title.setFill(Color.DARKBLUE);
        ToggleGroup toggleGroup = new ToggleGroup();
        rOn.setToggleGroup(toggleGroup);
        rOff.setToggleGroup(toggleGroup);
        VBox vBox = new VBox();
        vBox.getChildren().add(title);
        vBox.getChildren().add(rOn);
        vBox.getChildren().add(rOff);
        Button button = new Button("Send");
        button.setOnAction(event -> {
            byte[] command = CreateCommandUtil.createCommand00(rOn, rOff);
            if (command != null) sendMessageCallback.send(command);
        });
        gridPane.add(vBox, 0, 0);
        gridPane.add(button, 1, 0);
        gridPane.add(message, 2, 0);
        return new PPane(gridPane);
    }

    public void putText(String value) {
        message.setText(value);
    }

    public void putResponse(byte[] array) {
        StringBuilder builder = new StringBuilder();
        builder.append("Status: ");
        if (array[4] == 0) builder.append("OK");
        else builder.append("Incorrect mode");
        putText(builder.toString());
    }

    public void clear() {
        putText("");
    }
}
