package br.com.generatebit;

import br.com.generatebit.dao.PessoaDAO;
import br.com.generatebit.model.Pessoa;
import br.com.generatebit.util.AlertDialog;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Person extends Application {

    private static PessoaDAO dao;

    static {
        dao = new PessoaDAO();
    }

    //Lista observavel onde será armazenado os objetos pessoa
    private static ObservableList<Pessoa> pessoaObservableList;

    static {
        try {
            pessoaObservableList = FXCollections.observableArrayList(dao.listarTudo());
        } catch (SQLException | NullPointerException e) {
            Runnable runnable = () -> AlertDialog.showAlertDialog("Não foi possivel carregar os dados " + e.getMessage(), Alert.AlertType.WARNING);
            Platform.runLater(runnable);
        }
    }


    //Container raiz
    private static BorderPane pane;

    public static void main(String[] args) {
        launch(args);
    }

    public static PessoaDAO getDao() {
        return dao;
    }

    /**
     * Muda a para outra cena de a cordo com o parametro passado
     *
     * @param parent que carrega o arquivo fxml
     * @throws IOException lançada se o arquivo não for encotrado
     */
    public static void changeParent(String parent) throws IOException {
        pane.setCenter(loadFXML(parent));
    }

    /**
     * Carrega o arquivo fxml correspondente ao parametro informado e retorna um container
     *
     * @param fxml nome do arquivo a ser invocado
     * @return Pane
     * @throws IOException se o arquivo não for encontrado
     */
    private static Pane loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Person.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    /**
     * Recebe uma lista de objetos observáveis do tipo Pessoa
     *
     * @return ObservableList
     */
    public static ObservableList<Pessoa> getPessoaObservableList() {
        return pessoaObservableList;
    }

    public static void atualizarLista() {
        pessoaObservableList.clear();
        try {
            pessoaObservableList.setAll(dao.listarTudo());
        } catch (SQLException | NullPointerException e) {
            System.out.println("Erro ao arregar lista no metodo atualizarLista" + e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        pane = (BorderPane) loadFXML("root");
        changeParent("PersonView");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(String.valueOf(Person.class.getResource("img/computer.png"))));
        primaryStage.show();

    }
}