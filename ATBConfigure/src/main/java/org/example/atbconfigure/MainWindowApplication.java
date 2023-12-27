package org.example.atbconfigure;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.atbconfigure.domain.enums.ResponseState;
import org.example.atbconfigure.tcpService.ResponseStateCallback;
import org.example.atbconfigure.tcpService.TCPService;
import org.example.atbconfigure.ui.ConnectWindow;

import java.io.IOException;

import static org.example.atbconfigure.util.AppConstant.APP_NAME;
import static org.example.atbconfigure.util.IndicatorUtil.*;
import static org.example.atbconfigure.util.PaneUtil.*;

public class MainWindowApplication extends Application {

    Stage stage1 = new Stage();
    ResponseStateCallback callback = new ResponseStateCallback() {
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

    @Override
    public void start(Stage stage) throws IOException {

        TCPService tcpService = new TCPService(callback);
        ConnectWindow connectWindow = new ConnectWindow(tcpService);
        GridPane connectRaw = connectWindow.create();
        ScrollPane scrollPane = new ScrollPane(connectRaw);
        Scene scene = new Scene(scrollPane, 800, 600);
        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}