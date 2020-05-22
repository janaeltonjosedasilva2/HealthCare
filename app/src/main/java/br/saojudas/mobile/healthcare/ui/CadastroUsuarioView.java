package br.saojudas.mobile.healthcare.ui;

import android.content.Context;

import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomAgendamentoDAO;
import br.saojudas.mobile.healthcare.ui.activity.CadastroUsuarioActivity;
import br.saojudas.mobile.healthcare.ui.adapter.CadastroUsuarioAdapter;

public class CadastroUsuarioView {
    private final CadastroUsuarioAdapter adapter;
    private final RoomAgendamentoDAO dao;
    private final Context context;


    public CadastroUsuarioView(Context context) {
        this.context = context;
        this.adapter = new CadastroUsuarioAdapter((CadastroUsuarioActivity) this.context);
        this.dao = HaelthCareDatabase.getInstance(context)
                .getRoomAgendamentoDAO();
    }
}
