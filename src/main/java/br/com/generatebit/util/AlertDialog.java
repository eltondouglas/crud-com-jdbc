package br.com.generatebit.util;

import javafx.scene.control.Alert;

public class AlertDialog {

    public static void showAlertDialog(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        switch (alertType) {
            case WARNING:
                alert.setTitle("Aviso");
                alert.setHeaderText(message);
                alert.showAndWait();
                break;
            case ERROR:
                alert.setTitle("Erro");
                alert.setHeaderText(message);
                alert.showAndWait();
                break;
            case INFORMATION:
                alert.setTitle("Informação");
                alert.setHeaderText(message);
                alert.showAndWait();
                break;
        }

    }
}
