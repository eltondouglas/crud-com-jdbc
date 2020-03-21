package br.com.generatebit.controller;

import br.com.generatebit.Person;
import br.com.generatebit.model.Pessoa;
import br.com.generatebit.util.AlertDialog;
import br.com.generatebit.util.DateUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CadastroController {

    private static Pessoa pessoa;

    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfSobrenome;
    @FXML
    private TextField tfRua;
    @FXML
    private TextField tfCidade;
    @FXML
    private TextField tfCep;
    @FXML
    private DatePicker dpNasc;

    @FXML
    void initialize() {
        pessoa = new Pessoa();
        DateUtil.formaterDatePicker(dpNasc.getEditor());
    }

    @FXML
    void cancelar() {
        pessoa = null;
        Person.atualizarLista();
        try {
            Person.changeParent("PersonView");
        } catch (IOException e) {
            Platform.runLater(() -> {
                AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING);
            });
        }
    }

    @FXML
    void confirmar() {
        try {
            pessoa.setNome(tfNome.getText());
            pessoa.setSobrenome(tfSobrenome.getText());
            pessoa.setRua(tfRua.getText());
            pessoa.setCidade(tfCidade.getText());
            pessoa.setCep(tfCep.getText());
            pessoa.setNasc(DateUtil.stringToDate(dpNasc.getEditor().getText()));
            Person.getDao().incluir(pessoa);
            try {
                Person.changeParent("PersonView");
            } catch (IOException e) {
                Platform.runLater(() -> {
                    AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING);
                });
            }
        } catch (IllegalArgumentException e) {
            Platform.runLater(() -> {
                AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.ERROR);
            });
        }

        Person.atualizarLista();
    }
}
