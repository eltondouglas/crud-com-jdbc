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

public class EditarCadastroController extends CadastroRoot {

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

    static void setPessoa(Pessoa pessoa) {
        EditarCadastroController.pessoa = pessoa;
    }

    @FXML
    void cancelar() {
        try {
            Person.changeParent("PersonView");
        } catch (IOException e) {
            Platform.runLater(()-> AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING));
        }
    }

    @FXML
    public void confirmar() {
        pessoa.setNome(tfNome.getText());
        pessoa.setSobrenome(tfSobrenome.getText());
        pessoa.setCep(tfCep.getText());
        pessoa.setCidade(tfCidade.getText());
        pessoa.setRua(tfRua.getText());
        pessoa.setNasc(DateUtil.stringToDate(dpNasc.getEditor().getText()));
        Person.getDao().atualizar(pessoa);
        Person.atualizarLista();
        try {
            Person.changeParent("PersonView");
        } catch (IOException e) {
            Platform.runLater(()-> AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING));
        }
    }


    @FXML
    void initialize() {
        if (pessoa == null) {
            tfNome.setText("");
            tfSobrenome.setText("");
            tfRua.setText("");
            tfCidade.setText("");
            tfCep.setText("");
            dpNasc.getEditor().setText("");
        } else {
            tfNome.setText(pessoa.getNome());
            tfSobrenome.setText(pessoa.getSobrenome());
            tfRua.setText(pessoa.getRua());
            tfCidade.setText(pessoa.getCidade());
            tfCep.setText(pessoa.getCep());
            dpNasc.setPromptText("dd/MM/AAAA".toLowerCase());
            DateUtil.formaterDatePicker(dpNasc.getEditor());
            dpNasc.getEditor().textProperty().setValue(DateUtil.dateToString(pessoa.nascProperty().get()));
        }
    }
}
