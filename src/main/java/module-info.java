module com.sakila.main.sakila {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;


    opens com.sakila.main to javafx.fxml;
    opens com.sakila.entity;
    exports com.sakila.main;
}