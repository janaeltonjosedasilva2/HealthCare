package br.saojudas.mobile.healthcare.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.saojudas.mobile.healthcare.model.Agendamento;
import br.saojudas.mobile.healthcare.model.CadastroUsuario;

@Dao
public interface RoomAgendamentoDAO {
    @Insert
    void salvar(Agendamento agendamento);

    @Query("SELECT * FROM Agendamento")
    List<Agendamento> todos();

    @Delete
    void remove(Agendamento agendamento);

    @Update
    void edita(Agendamento agendamento);

    @Insert
    void salvarUsuario(CadastroUsuario cadastroUsuario);

    @Query("SELECT * FROM usuario WHERE login LIKE :login LIMIT 1")
    CadastroUsuario findByLogin(String login);

    @Delete
    void removeUsuario(CadastroUsuario cadastroUsuario);

    @Update
    void editarUsuario(CadastroUsuario cadastroUsuario);
}
