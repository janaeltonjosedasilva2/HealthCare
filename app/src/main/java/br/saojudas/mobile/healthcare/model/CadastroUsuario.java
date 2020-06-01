package br.saojudas.mobile.healthcare.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "usuario", indices = {@Index(value = "login", unique = true)})
public class CadastroUsuario implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "login")
    private String login;
    @ColumnInfo(name = "senha")
    private String senha;
    @ColumnInfo(name = "data_nascimento")
    private String dataNascimento;
    @ColumnInfo(name = "sexo")
    private String sexo;
    @ColumnInfo(name = "telefone")
    private String telefone;
    @ColumnInfo(name = "contato_emergencia")
    private String contatoEmergencia;
    @ColumnInfo(name = "telefone_emergencia")
    private String telefoneEmergencia;

    private static CadastroUsuario _session;

    public CadastroUsuario(int id, String nome, String login, String senha, String dataNascimento,
         String sexo, String telefone, String contatoEmergencia, String telefoneEmergencia) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.telefone = telefone;
        this.contatoEmergencia = contatoEmergencia;
        this.telefoneEmergencia = telefoneEmergencia;

        setSession(this);
    }

    public CadastroUsuario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getContatoEmergencia() { return contatoEmergencia; }

    public void setContatoEmergencia(String contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia; }

    public String getTelefoneEmergencia() {
        return telefoneEmergencia;
    }

    public void setTelefoneEmergencia(String telefoneEmergencia) {
        this.telefoneEmergencia = telefoneEmergencia;
    }

    public static CadastroUsuario session(){
        return _session;
    }

    public static void setSession(CadastroUsuario session){
        _session = session;
    }
}
