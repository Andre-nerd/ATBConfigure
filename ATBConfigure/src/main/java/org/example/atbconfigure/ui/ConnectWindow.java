package org.example.atbconfigure.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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

    public GridPane create() {
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


        GridPane rootPane = getGridPane(50, 50, 5, true);

        rootPane.add(gridBox1, 0, 0);
        rootPane.add(hBox, 1, 0);
        return rootPane;
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
