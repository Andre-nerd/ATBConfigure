package org.example.atbconfigure.ui;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.example.atbconfigure.util.PPane;

import static org.example.atbconfigure.util.PaneUtil.getGridThreePane;

public class NavSolutionWindow {
    private RadioButton GSM = new RadioButton("GSM  ");
    private RadioButton WIFI = new RadioButton("WiFi RTT  ");
    private RadioButton IBeacon = new RadioButton("IBeacon  ");
    private RadioButton LSN = new RadioButton("LSN  ");
    private RadioButton GNSS = new RadioButton("GNSS ");

    public Pane create() {
        GridPane gridPane = getGridThreePane(20, 40, 40, 10, false);
        HBox hBox = new HBox(GSM, WIFI, IBeacon, LSN, GNSS);
        VBox vBox = new VBox();
        Text title = new Text("Navigation solution:");
        title.setFill(Color.DARKBLUE);
        vBox.getChildren().add(title);
        vBox.getChildren().add(hBox);
        Pane pane = new Pane(vBox);
        return new PPane(pane);

    }
}
