module com.mycompany.bibliofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.desktop;
    requires java.sql;

    opens com.mycompany.bibliofx to javafx.fxml;
    exports com.mycompany.bibliofx;
}
