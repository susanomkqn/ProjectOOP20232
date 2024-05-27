module org.group9.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.logging;
    requires com.opencsv;
    requires org.jsoup;

    opens org.group9.gui to javafx.fxml;
    exports org.group9.gui;
}
