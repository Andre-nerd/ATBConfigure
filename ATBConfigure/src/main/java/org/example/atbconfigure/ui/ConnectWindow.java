package org.example.atbconfigure.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.example.atbconfigure.domain.enums.ResponseState;
import org.example.atbconfigure.tcpService.TCPService;


import static org.example.atbconfigure.util.IndicatorUtil.getIndicatorStack;
import static org.example.atbconfigure.util.IndicatorUtil.setColorIndication;
import static org.example.atbconfigure.util.PaneUtil.*;

public class ConnectWindow {
    private static TCPService tcpService;
    private static Text textStateConnection = new Text();
    private static StackPane stackPane;

    public ConnectWindow(TCPService tcpService) {
        ConnectWindow.tcpService = tcpService;
    }

    public Pane create() {
        TextField textIp = new TextField();
        textIp.setText(TCPService.mHost + ":" + TCPService.mPort);

        stackPane = getIndicatorStack(8, Color.GRAY);
        Button buttonConnect = new Button("Connect");
        buttonConnect.setOnAction(actionEvent ->
                tcpService.start(TCPService.mHost, TCPService.mPort)
        );

        GridPane gridBox1 = constructPanelTextIndicatorButton(textIp, stackPane, buttonConnect);
        HBox hBox = getHBoxPadding(10);
        hBox.getChildren().add(textStateConnection);


        GridPane rootPane = getGridPane(80, 20, 5, false);

        rootPane.add(gridBox1, 0, 0);
        rootPane.add(hBox, 1, 0);
        VBox vBox = new VBox();
        Text title = new Text("IP device:");
        title.setFill(Color.DARKBLUE);
        vBox.getChildren().add(title);
        vBox.getChildren().add(rootPane);
        Pane pane = new Pane(vBox);
        pane.setPadding(new Insets(5,0,5,0));
        return pane;
    }

    public static void connectSuccess() {
        setColorIndication(stackPane, Color.GREEN);
        textStateConnection.setText(ResponseState.SUCCESS_CONNECT.getState());
    }
    public static void connectWrong() {
        setColorIndication(stackPane, Color.RED);
        textStateConnection.setText(ResponseState.WRONG_CONNECT.getState());
    }
}
