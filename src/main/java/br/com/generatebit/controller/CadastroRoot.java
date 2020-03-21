package br.com.generatebit.controller;

import br.com.generatebit.Person;
import br.com.generatebit.model.Pessoa;
import br.com.generatebit.util.AlertDialog;
import br.com.generatebit.util.DateUtil;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public abstract class CadastroRoot {

    public static void confirmAction(Pessoa pessoa, TextField tfNome, TextField tfSobrenome, TextField tfRua, TextField tfCidade, TextField tfCep, DatePicker dpNasc) {
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
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            Platform.runLater(() -> AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.ERROR));
        }
    }
}
