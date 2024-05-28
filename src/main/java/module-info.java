module org.group9.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires org.apache.commons.lang3;
    requires org.apache.commons.text;
    requires commons.beanutils;
    requires commons.logging;
    requires commons.collections;
    requires org.apache.commons.collections4;
    requires org.jsoup;
    requires java.sql;
    requires com.opencsv;
    requires java.desktop;

    exports org.group9.gui;
    opens org.group9.gui to javafx.fxml;
    exports org.group9.news;
    opens org.group9.news to opencsv;
    exports org.group9.search_engine;
    opens org.group9.search_engine to javafx.fxml;
}
