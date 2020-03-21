package br.com.generatebit.dao;

import br.com.generatebit.conection.SingleConection;
import br.com.generatebit.model.Pessoa;
import br.com.generatebit.util.AlertDialog;
import br.com.generatebit.util.DateUtil;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private Connection connection;

    public PessoaDAO() {
        connection = SingleConection.getConnection();
    }

    public void incluir(Pessoa pessoa) {
        String sqlQuery = "insert into pessoa (nome, sobrenome, rua, cidade, cep, datanasc) values (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getSobrenome());
            statement.setString(3, pessoa.getRua());
            statement.setString(4, pessoa.getCidade());
            statement.setString(5, pessoa.getCep());
            statement.setString(6, pessoa.getNasc());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void atualizar(Pessoa pessoa) {
        String sqlQuery = "UPDATE pessoa" +
                " SET nome=?, sobrenome=?, cep=?, cidade=?, rua=?, datanasc=?" +
                " WHERE id = " + pessoa.getID();
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getSobrenome());
            statement.setString(3, pessoa.getCep());
            statement.setString(4, pessoa.getCidade());
            statement.setString(5, pessoa.getRua());
            statement.setString(6, pessoa.getNasc());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public Pessoa buscar(BigInteger id) {
        String sqlQuery = "select * from pessoa where id =" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            Pessoa pessoa = null;
            while (resultSet.next()) {
                pessoa = new Pessoa();
                pessoa.setID(new BigInteger(resultSet.getString("id")));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setSobrenome(resultSet.getString("sobrenome"));
                pessoa.setCep(resultSet.getString("cep"));
                pessoa.setCidade(resultSet.getString("cidade"));
                pessoa.setRua(resultSet.getString("rua"));
                pessoa.setNasc(DateUtil.stringToDate(resultSet.getString("datanasc")));
            }
            return pessoa;
        } catch (SQLException e) {
            Platform.runLater(()-> AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING));
        }
        return null;
    }

    public List<Pessoa> listarTudo() throws SQLException, NullPointerException {
        String sqlQuery = "select * from pessoa";
        List<Pessoa> pessoas = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            if (statement != null) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setID(new BigInteger(resultSet.getString("id")));
                    pessoa.setNome(resultSet.getString("nome"));
                    pessoa.setSobrenome(resultSet.getString("sobrenome"));
                    pessoa.setCep(resultSet.getString("cep"));
                    pessoa.setCidade(resultSet.getString("cidade"));
                    pessoa.setRua(resultSet.getString("rua"));
                    pessoa.setNasc(DateUtil.stringToDate(resultSet.getString("datanasc")));
                    pessoas.add(pessoa);
                }
        }
        return pessoas;
    }

    public void excluir(BigInteger id) {
        String sqlQuery = "delete from pessoa where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Platform.runLater(()->AlertDialog.showAlertDialog(ex.getMessage(), Alert.AlertType.WARNING));
            }
            Platform.runLater(()->AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING));
        }
    }

    public void fecharConexao() {
        try {
            connection.close();
        } catch (SQLException e) {
            Platform.runLater(()->AlertDialog.showAlertDialog(e.getMessage(), Alert.AlertType.WARNING));
        }
    }
}
