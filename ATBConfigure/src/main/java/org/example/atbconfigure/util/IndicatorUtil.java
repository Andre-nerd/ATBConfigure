package org.example.atbconfigure.util;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class IndicatorUtil {
    public static StackPane getIndicatorStack(int r, Paint color){
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        Circle circle = new Circle(r);
        circle.setFill(color);
        stackPane.getChildren().add(circle);
        return stackPane;
    }
    public static void changeColorIndication(StackPane pane, Paint off, Paint on){
        Circle circle = (Circle) pane.getChildren().get(0);
        if(circle.getFill() == off) circle.setFill(on); else circle.setFill(off);
    }
    public static void setColorIndication(StackPane pane, Paint state){
         Circle circle = (Circle) pane.getChildren().get(0);
         circle.setFill(state);
    }
}
