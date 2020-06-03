package br.saojudas.mobile.healthcare.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.saojudas.mobile.healthcare.model.Agendamento;

@Dao
public interface RoomAgendamentoDAO {
    @Insert
    void salvar(Agendamento agendamento);

    @Query("SELECT * FROM Agendamento")
    List<Agendamento> todos();

    @Query("SELECT * FROM Agendamento WHERE idUsuario=:idUsuario")
    List<Agendamento> getAgendamentosParaUsuarios(int idUsuario);

    @Delete
    void remove(Agendamento agendamento);

    @Update
    void edita(Agendamento agendamento);
}
