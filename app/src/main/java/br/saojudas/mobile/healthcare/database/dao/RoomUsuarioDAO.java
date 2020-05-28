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

//    @Query("SELECT * FROM usuario")
//    List<CadastroUsuario> allUsuarios();

//    @Query("SELECT login FROM usuario WHERE login LIKE :login LIMIT 1")
//    CadastroUsuario findByLogin(String login);

    @Query("SELECT * FROM usuario WHERE senha LIKE :senha AND login LIKE :login LIMIT 1")
    CadastroUsuario findByLoginAndSenha(String login, String senha);


//    @Delete
//    void removeUsuario(CadastroUsuario cadastroUsuario);
//
//    @Update
//    void editarUsuario(CadastroUsuario cadastroUsuario);
}
