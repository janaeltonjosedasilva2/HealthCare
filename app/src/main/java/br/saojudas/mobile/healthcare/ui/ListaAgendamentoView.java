package br.saojudas.mobile.healthcare.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomAgendamentoDAO;
import br.saojudas.mobile.healthcare.model.Agendamento;
import br.saojudas.mobile.healthcare.model.CadastroUsuario;
import br.saojudas.mobile.healthcare.ui.activity.FormularioAgendamentoActivity;
import br.saojudas.mobile.healthcare.ui.adapter.ListaAgendamentoAdapter;

public class ListaAgendamentoView {
    private final ListaAgendamentoAdapter adapter;
    private final RoomAgendamentoDAO dao;
    private final Context context;
    private final CadastroUsuario cadastroUsuario;

    public ListaAgendamentoView(Context context) {
        this.context = context;
        this.adapter = new ListaAgendamentoAdapter(this.context);
        this.dao = HaelthCareDatabase.getInstance(context)
                .getRoomAgendamentoDAO();
        this.cadastroUsuario = new CadastroUsuario();
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo agendamento")
                .setMessage("Tem certeza que deseja remover esse agendamento?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Agendamento agendamentoEscolhido = adapter.getItem(menuInfo.position);
                        removeAgendamento(agendamentoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaListaDeAgendamentos() {
        adapter.atualiza(dao.getAgendamentosParaUsuarios(CadastroUsuario.session().getId()));
    }

    private void removeAgendamento(Agendamento agendamento) {
        dao.remove(agendamento);
        adapter.remove(agendamento);
    }

    public void configuraAdpter(ListView listaAgendamento) {
        listaAgendamento.setAdapter(adapter);
    }
}
