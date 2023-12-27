package org.example.atbconfigure.util;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class PaneUtil {
    public static GridPane getGridPane(int p1, int p2, int padding, boolean lineVisible){
        GridPane gridPane = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(p1);
        gridPane.getColumnConstraints().add(column1);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(p2);
        gridPane.getColumnConstraints().add(column2);
        gridPane.setPadding(new Insets(padding,padding,padding,padding));
        gridPane.setGridLinesVisible(lineVisible);
        return gridPane;
    }
    public static GridPane getGridThreePane(int p1, int p2,int p3, int padding, boolean lineVisible){
        GridPane gridPane = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(p1);
        gridPane.getColumnConstraints().add(column1);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(p2);
        gridPane.getColumnConstraints().add(column2);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(p3);
        gridPane.getColumnConstraints().add(column3);
        gridPane.setPadding(new Insets(padding,padding,padding,padding));
        gridPane.setGridLinesVisible(lineVisible);
        return gridPane;
    }

    public static GridPane constructPanelTextIndicatorButton(TextField textField, StackPane stackPane, Button button){
        GridPane gridBox = getGridThreePane(70, 10, 20, 10, false);
        gridBox.add(textField, 0, 0);
        gridBox.add(stackPane, 1, 0);
        gridBox.add(button, 2, 0);
        return gridBox;
    }

    public static HBox getHBoxPadding(int p){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(p,p,p,p));
        return hBox;
    }
    public static AnchorPane getAnchorPanePadding(int p){
        AnchorPane pane = new AnchorPane();
        pane.setPadding(new Insets(p,p,p,p));
        return pane;
    }
}
//AnchorPane