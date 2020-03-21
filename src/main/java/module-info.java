import br.com.generatebit.Person;

module br.com.generatebit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires junit;

    opens br.com.generatebit to javafx.fxml;
    opens br.com.generatebit.controller;
    exports br.com.generatebit.controller;
    exports br.com.generatebit;
    exports br.com.generatebit.dao;
    exports br.com.generatebit.model;
    uses Person;
}