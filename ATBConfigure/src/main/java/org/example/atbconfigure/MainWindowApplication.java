package org.example.atbconfigure;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.atbconfigure.domain.enums.ResponseState;
import org.example.atbconfigure.tcpService.ResponseCallback;
import org.example.atbconfigure.tcpService.ResponseStateCallback;
import org.example.atbconfigure.tcpService.SendMessageCallback;
import org.example.atbconfigure.tcpService.TCPService;
import org.example.atbconfigure.ui.BrowsingWindow;
import org.example.atbconfigure.ui.ConnectWindow;
import org.example.atbconfigure.ui.OnOffDeviceWindow;

import java.io.IOException;

import static org.example.atbconfigure.util.AppConstant.APP_NAME;
import static org.example.atbconfigure.util.PaneUtil.*;

public class MainWindowApplication extends Application {

    private BrowsingWindow browsingWindow =new BrowsingWindow();
    private ConnectWindow connectWindow;
    ResponseStateCallback stateCallback = new ResponseStateCallback() {
        @Override
        public void sendState(ResponseState state) {
            switch (state) {
                case SUCCESS_CONNECT:
                    ConnectWindow.connectSuccess();
                    break;
                case WRONG_CONNECT:
                    ConnectWindow.connectWrong();
                    break;
            }
        }
    };

    ResponseCallback responseCallback = new ResponseCallback() {
        @Override
        public void sendResponse(byte[] inputLine) {
            browsingWindow.putByteArray(inputLine);
            switch (inputLine[2]){
                case 2: onOffDeviceWindow.putResponse(inputLine);
            }
        }
    };
    TCPService tcpService = new TCPService(stateCallback, responseCallback);
    SendMessageCallback sendMessageCallback = new SendMessageCallback() {
        @Override
        public void send(byte[] value) {
            tcpService.sendMessage(value);
        }
    };
    OnOffDeviceWindow onOffDeviceWindow = new OnOffDeviceWindow(sendMessageCallback);

    @Override
    public void start(Stage stage) throws IOException {

        connectWindow = new ConnectWindow(tcpService);
        GridPane connectRaw = connectWindow.create();

        Pane paneOnOfRaw = onOffDeviceWindow.create();

        VBox vBox = new VBox();
        vBox.getChildren().add(connectRaw);
        vBox.getChildren().add(paneOnOfRaw);

        VBox browsing = browsingWindow.create();

        GridPane gridPane = getGridPane(40, 60,10, false);
        gridPane.add(vBox,0,0);
        gridPane.add(browsing,1,0);
        ScrollPane scrollPane = new ScrollPane(gridPane);

        Scene scene = new Scene(scrollPane, 1320, 800);
        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}