package org.example.atbconfigure.ui;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.example.atbconfigure.tcpService.SendMessageCallback;
import org.example.atbconfigure.util.PPane;

import static org.example.atbconfigure.util.CreateCommandUtil.createCommand02Get;
import static org.example.atbconfigure.util.PaneUtil.getGridPane;
import static org.example.atbconfigure.util.PaneUtil.getGridThreePane;

public class NavSolutionWindow {
    SendMessageCallback sendMessageCallback;
    private RadioButton GSM = new RadioButton("GSM  ");
    private RadioButton WIFI = new RadioButton("WiFi RTT  ");
    private RadioButton IBeacon = new RadioButton("IBeacon  ");
    private RadioButton LSN = new RadioButton("LSN  ");
    private RadioButton GNSS = new RadioButton("GNSS ");
    Text setParam = new Text();
    Text getParam = new Text();

    public NavSolutionWindow(SendMessageCallback sendMessageCallback){
        this.sendMessageCallback = sendMessageCallback;
    }

    public Pane create() {
        GridPane gridPane = getGridPane( 40, 60, 10, false);
        HBox hBox = new HBox(GSM, WIFI, IBeacon, LSN, GNSS);
        VBox vBox = new VBox();
        Text title = new Text("Navigation solution:");
        Button setParameters = new Button("Set parameters");
        Button getParameters = new Button("Get parameters");
        getParameters.setOnAction(event -> {
            byte[] command = createCommand02Get();
            sendMessageCallback.send(command);
        });
        title.setFill(Color.DARKBLUE);
        gridPane.add(new PPane(setParameters), 0,0);
        gridPane.add(new PPane(getParameters), 1,0);
        gridPane.add(new PPane(setParam), 1,0);
        gridPane.add(new PPane(getParam), 1,1);
        vBox.getChildren().add(title);

        vBox.getChildren().add(new PPane(hBox));
        vBox.getChildren().add(new PPane(setParameters));
        vBox.getChildren().add(new PPane(getParameters));
        Pane pane = new Pane(vBox);
        return new PPane(pane);
    }
    public void putText(String get, String set) {
        if(get != null) getParam.setText(get);
        if(set != null) getParam.setText(set);
    }

    public void putResponse(byte[] array) {
        StringBuilder builder = new StringBuilder();
        builder.append("Status: ");
        if (array[4] == 0) builder.append("OK");
        else builder.append("Incorrect mode");
        putText(null, null);
    }

    public void clear() {
        putText("","");
    }
}
