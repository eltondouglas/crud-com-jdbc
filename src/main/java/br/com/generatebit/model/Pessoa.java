package br.com.generatebit.model;

import br.com.generatebit.util.DateUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("unused")
public class Pessoa {

    //Atributos
    private BigInteger ID;
    private final StringProperty nome;
    private final StringProperty sobrenome;
    private final StringProperty rua;
    private final StringProperty cidade;
    private final StringProperty cep;
    private final ObjectProperty<LocalDate> nasc;

    public Pessoa() {
        this("", "", "", "", "", "");
    }

    public Pessoa(String nome, String sobrenome, String rua, String cidade, String cep, String idade) {
        this.nome = new SimpleStringProperty(nome);
        this.sobrenome = new SimpleStringProperty(sobrenome);

        this.rua = new SimpleStringProperty(rua);
        this.cidade = new SimpleStringProperty(cidade);
        this.cep = new SimpleStringProperty(cep);
        this.nasc = new SimpleObjectProperty<>();
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        if (nome.trim().isEmpty()){
            throw new IllegalArgumentException("VERIFIQUE OS DADOS PREENCHIDOS");
        }
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome.get();
    }

    public void setSobrenome(String sobrenome) {
        if (sobrenome.trim().isEmpty()){
            throw new IllegalArgumentException("VERIFIQUE OS DADOS PREENCHIDOS");
        }
        this.sobrenome.set(sobrenome);
    }

    public StringProperty sobrenomeProperty() {
        return sobrenome;
    }

    public String getRua() {
        return rua.get();
    }

    public void setRua(String rua) {
        if (rua.trim().isEmpty()){
            throw new IllegalArgumentException("VERIFIQUE OS DADOS PREENCHIDOS");
        }
        this.rua.set(rua);
    }

    public StringProperty ruaProperty() {
        return rua;
    }

    public String getCidade() {
        return cidade.get();
    }

    public void setCidade(String cidade) {
        if (cidade.trim().isEmpty()){
            throw new IllegalArgumentException("VERIFIQUE OS DADOS PREENCHIDOS");
        }
        this.cidade.set(cidade);
    }

    public StringProperty cidadeProperty() {
        return cidade;
    }

    public String getCep() {
        return cep.get();
    }

    public void setCep(String cep) {
        if (cep.trim().isEmpty()){
            throw new IllegalArgumentException("VERIFIQUE OS DADOS PREENCHIDOS");
        }
        this.cep.set(cep);
    }

    public StringProperty cepProperty() {
        return cep;
    }

    public String getNasc() {
        return DateUtil.dateToString(nasc.get());
    }

    public void setNasc(LocalDate nasc) {
        this.nasc.set(nasc);
    }

    public ObjectProperty<LocalDate> nascProperty() {
        return nasc;
    }

    public BigInteger getID() {
        return this.ID;
    }

    public void setID(BigInteger ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(ID, pessoa.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "ID=" + ID +
                ", nome=" + nome.get() +
                ", sobrenome=" + sobrenome.get() +
                ", rua=" + rua.get() +
                ", cidade=" + cidade.get() +
                ", cep=" + cep.get() +
                ", nasc=" + DateUtil.dateToString(nasc.get()) +
                '}';
    }
}
