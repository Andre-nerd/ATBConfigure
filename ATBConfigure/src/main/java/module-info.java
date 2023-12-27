module org.example.atbconfigure {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.atbconfigure to javafx.fxml;
    exports org.example.atbconfigure;
    exports org.example.atbconfigure.util;
    opens org.example.atbconfigure.util to javafx.fxml;
}