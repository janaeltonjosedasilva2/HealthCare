package br.saojudas.mobile.healthcare.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.saojudas.mobile.healthcare.database.dao.RoomAgendamentoDAO;
import br.saojudas.mobile.healthcare.model.Agendamento;

@Database(entities = {Agendamento.class}, version = 1, exportSchema = false)
public abstract class HaelthCareDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "agendamento.db";

    public abstract RoomAgendamentoDAO getRoomAgendamentoDAO();

    public static HaelthCareDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, HaelthCareDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                //todo adicionar migration aqui
                .build();
    }

}
