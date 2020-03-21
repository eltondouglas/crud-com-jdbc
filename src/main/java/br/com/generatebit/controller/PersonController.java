package br.com.generatebit.controller;

import br.com.generatebit.Person;
import br.com.generatebit.model.Pessoa;
import br.com.generatebit.util.AlertDialog;
import br.com.generatebit.util.DateUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PersonController {

    private static Pessoa pessoa;
    @FXML
    private TableView<Pessoa> pessoaTable;
    @FXML
    private TableColumn<Pessoa, String> nomeColuna;
    @FXML
    private TableColumn<Pessoa, String> sobrenomeColuna;
    @FXML
    private Label lbnome;
    @FXML
    private Label lbsobrenome;
    @FXML
    private Label lbrua;
    @FXML
    private Label lbcidade;
    @FXML
    private Label lbcep;
    @FXML
    private Label lbnasc;

    //Define se alguma linha da tabela pessoaTable foi seleciona
    private boolean isRowSelected = false;

    @FXML
    void editar() {
        if (!isRowSelected) {
            AlertDialog.showAlertDialog("Nenhuma pessoa selecionada", Alert.AlertType.ERROR);
        } else {
            EditarCadastroController.setPessoa(pessoa);
            try {
                Person.changeParent("EditarCadastroView");
            } catch (IOException e) {
                Platform.runLater(()->AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING));
            }
        }
    }

    @FXML
    void novo() {
        try {
            Person.changeParent("CadastroView");
        } catch (IOException e) {
            Platform.runLater(()-> AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING));
        }
    }

    @FXML
    void excluir(ActionEvent event) {
        event.consume();
        if (event.isConsumed()) {
            Pessoa pessoa = pessoaTable.getSelectionModel().getSelectedItem();
            if (isRowSelected) {
                Person.getDao().excluir(pessoa.getID());
                isRowSelected = false;
                Person.atualizarLista();
                limparLabels();
            } else {
                AlertDialog.showAlertDialog("Nehuma pessoa foi selecionada", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void initialize() {
        initTable();
        showSelectedRow();
    }


    /**
     * Popula a tabela com os dados recebidos da obsevable list
     *
     * @author elton
     * @version 0.0.1
     * @since 15/10/2019
     */
    private void initTable() {

        pessoaTable.setItems(Person.getPessoaObservableList());

        //Icializa as colunas com os dados
        nomeColuna.setCellValueFactory(e -> e.getValue().nomeProperty());
        sobrenomeColuna.setCellValueFactory(e -> e.getValue().sobrenomeProperty());
    }

    /**
     * Mostra os dados de acordo com a linha selecionada na tabela
     */
    private void showSelectedRow() {
        pessoaTable.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            int index = pessoaTable.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                isRowSelected = true;
                pessoa = pessoaTable.getItems().get(index);
                lbnome.setText(pessoa.getNome());
                lbsobrenome.setText(pessoa.getSobrenome());
                lbrua.setText(pessoa.getRua());
                lbcidade.setText(pessoa.getCidade());
                lbcep.setText(pessoa.getCep());
                lbnasc.setText(DateUtil.dateToString(pessoa.nascProperty().get()));
            }
        });
    }

    private void limparLabels() {
        lbnome.setText("");
        lbsobrenome.setText("");
        lbrua.setText("");
        lbcidade.setText("");
        lbcep.setText("");
        lbnasc.setText("");
    }
}
