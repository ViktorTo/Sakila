module com.sakila.main.sakila {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sakila.main to javafx.fxml;
    exports com.sakila.main;
}