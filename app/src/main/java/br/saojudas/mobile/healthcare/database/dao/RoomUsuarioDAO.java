package br.saojudas.mobile.healthcare.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.saojudas.mobile.healthcare.model.CadastroUsuario;
@Dao
public interface RoomUsuarioDAO {
    @Insert
    void salvarUsuario(CadastroUsuario cadastroUsuario);

    @Query("SELECT * FROM usuario WHERE id = :id")
    CadastroUsuario findById(int id);

    @Query("SELECT * FROM usuario WHERE senha LIKE :senha AND login LIKE :login LIMIT 1")
    CadastroUsuario findByLoginAndSenha(String login, String senha);

    @Query("SELECT * FROM usuario WHERE data_nascimento = :dataNascimento AND login = :login LIMIT 1")
    CadastroUsuario findByLoginAndDataNasc(String login, String dataNascimento);

//    @Query("UPDATE usuario SET nome = :nome, data_nascimento = :dataNascimento, senha = :senha, " +
//            "telefone = :telefone, contato_emergencia = :contatoEmergencia, telefone_emergencia = :telefoneEmergencia " +
//            "WHERE id= :id")
//    void updateUsuario(int id, String nome, String dataNascimento, String senha,
//                                String telefone, String contatoEmergencia, String telefoneEmergencia);


//    @Delete
//    void removeUsuario(CadastroUsuario cadastroUsuario);
//
    @Update
    void editarUsuario(CadastroUsuario cadastroUsuario);
}
