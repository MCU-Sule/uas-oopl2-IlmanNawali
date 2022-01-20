module com.example.uas1972003 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;
    requires java.persistence;


    opens com.example.uas1972003 to javafx.fxml;
    exports com.example.uas1972003;
    exports com.example.Model;
}