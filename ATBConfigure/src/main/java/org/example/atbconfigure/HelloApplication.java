package org.example.atbconfigure;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.atbconfigure.domain.ResponseState;
import org.example.atbconfigure.tcpService.ResponseStateCallback;
import org.example.atbconfigure.tcpService.TCPService;

import java.io.IOException;

import static org.example.atbconfigure.util.IndicatorUtil.*;
import static org.example.atbconfigure.util.PaneUtil.*;

public class HelloApplication extends Application {
    Color color = Color.RED;
    StackPane stackPane;
    ResponseStateCallback callback = new ResponseStateCallback() {
        @Override
        public void sendState(ResponseState state) {
            if(state == ResponseState.SUCCESS_CONNECT) {
                setColorIndication(stackPane, Color.GREEN);
            }
        }
    };

    @Override
    public void start(Stage stage) throws IOException {


        TextField textIp = new TextField();
        textIp.setOnKeyPressed(keyEvent -> {
            System.out.println(textIp.getText());
        });
        stackPane = getIndicatorStack(8, Color.RED);
        Button buttonConnect = new Button("Connect");
        buttonConnect.setOnAction(actionEvent -> {
            TCPService tcpService = new TCPService(callback);
            tcpService.start();

//            changeColorIndication(stackPane, Color.RED, Color.BLUE);
        });

        GridPane gridBox1 = constructPanelTextIndicatorButton(textIp,stackPane,buttonConnect);

        Text text2 = new Text("second");
        HBox hBox2 = getHBoxPadding(10);
        hBox2.getChildren().add(text2);



        GridPane rootPane = getGridPane(50, 50, 5, true);

        rootPane.add(gridBox1, 0, 0);
        rootPane.add(hBox2, 1, 0);
        Scene scene = new Scene(rootPane, 800, 600);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}