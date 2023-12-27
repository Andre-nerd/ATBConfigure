package org.example.atbconfigure;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.atbconfigure.domain.enums.ResponseState;
import org.example.atbconfigure.tcpService.ResponseStateCallback;
import org.example.atbconfigure.tcpService.TCPService;

import java.io.IOException;

import static org.example.atbconfigure.util.AppConstant.APP_NAME;
import static org.example.atbconfigure.util.IndicatorUtil.*;
import static org.example.atbconfigure.util.PaneUtil.*;

public class MainWindowApplication extends Application {
    StackPane stackPane;
    Text textState = new Text();
    Stage stage1 = new Stage();
    ResponseStateCallback callback = new ResponseStateCallback() {
        @Override
        public void sendState(ResponseState state) {
            switch (state) {
                case SUCCESS_CONNECT:
                    setColorIndication(stackPane, Color.GREEN);
                    textState.setText(ResponseState.SUCCESS_CONNECT.getState());
                    break;
                case WRONG_CONNECT:
                    setColorIndication(stackPane, Color.RED);
                    textState.setText(ResponseState.WRONG_CONNECT.getState());
                    break;
            }
        }
    };

    @Override
    public void start(Stage stage) throws IOException {


        TextField textIp = new TextField();
        textIp.setText(TCPService.mHost + ":" + TCPService.mPort);
        textIp.setOnKeyPressed(keyEvent -> {
            System.out.println(textIp.getText());
        });
        stackPane = getIndicatorStack(8, Color.GRAY);
        Button buttonConnect = new Button("Connect");
        buttonConnect.setOnAction(actionEvent -> {
            TCPService tcpService = new TCPService(callback);
            tcpService.start(TCPService.mHost, TCPService.mPort);
            stage1.show();

        });

        GridPane gridBox1 = constructPanelTextIndicatorButton(textIp, stackPane, buttonConnect);
        HBox hBox = getHBoxPadding(10);
        hBox.getChildren().add(textState);


        GridPane rootPane = getGridPane(50, 50, 5, true);

        rootPane.add(gridBox1, 0, 0);
        rootPane.add(hBox, 1, 0);
        Scene scene = new Scene(rootPane, 800, 600);
        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.show();

        stage1.setTitle("MORE");
        GridPane rootPane1 = getGridPane(50, 50, 5, true);
        Scene scene1 = new Scene(rootPane1, 400, 400);
        stage1.setScene(scene1);

    }

    public static void main(String[] args) {
        launch();
    }
}