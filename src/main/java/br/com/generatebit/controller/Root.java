package br.com.generatebit.controller;

import br.com.generatebit.Person;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class Root {

    @FXML
    private void sair(){
        Person.getDao().fecharConexao();
        Platform.exit();
    }
}
