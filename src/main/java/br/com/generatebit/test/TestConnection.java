package br.com.generatebit.test;

import br.com.generatebit.dao.PessoaDAO;
import br.com.generatebit.model.Pessoa;
import br.com.generatebit.util.DateUtil;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class TestConnection {

    @Test
    public void InitBD() {
        PessoaDAO dao = new PessoaDAO();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Everaldo");
        pessoa.setSobrenome("Junior");
        pessoa.setCep("58292-000");
        pessoa.setCidade("Mataraca");
        pessoa.setRua("Lirio");
        pessoa.setNasc(DateUtil.stringToDate("13/05/2007"));
        dao.incluir(pessoa);
    }

    @Test
    public void buscar() {
        PessoaDAO dao = new PessoaDAO();
        Pessoa pessoa = dao.buscar(BigInteger.valueOf(3));
        if (pessoa != null)
            System.out.println(pessoa);
        else
            System.out.println("Não existem dados com esse id!");
    }

    @Test
    public void listar() throws SQLException {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> pessoas = dao.listarTudo();
        pessoas.forEach(System.out::println);
    }

    @Test
    public void initAtualizar() {
        PessoaDAO dao = new PessoaDAO();
        Pessoa pessoa = dao.buscar(BigInteger.valueOf(2));
        pessoa.setRua("Granja São José");
        dao.atualizar(pessoa);
    }

    @Test
    public void initExcluir() {
        PessoaDAO dao = new PessoaDAO();
        dao.excluir(BigInteger.valueOf(2));
    }
}
