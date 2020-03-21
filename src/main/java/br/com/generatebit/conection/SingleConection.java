package br.com.generatebit.conection;

import br.com.generatebit.util.AlertDialog;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConection {

    private static Connection connection;

    static {
        connect();

    }

    public SingleConection() {
        connect();
    }

    public static Connection getConnection() {
        try {
            return connection;
        } catch (Exception e) {
            Platform.runLater(()->{
                AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING);
            });
        }
        return null;
    }

    private static void connect() {
        if (connection == null) {
            try {
                String url = "jdbc:postgresql:javabd";
                String user = "postgres";
                String password = "edm1508952010";
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            }catch (Exception e){
                Platform.runLater(()->{
                    AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING);
                });
            }
        }
    }
}
