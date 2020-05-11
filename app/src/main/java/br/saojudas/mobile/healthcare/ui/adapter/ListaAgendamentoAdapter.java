package br.saojudas.mobile.healthcare.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.model.Agendamento;

public class ListaAgendamentoAdapter extends BaseAdapter {
    private final List<Agendamento> agendamentos = new ArrayList<>();
    private final Context context;

    public ListaAgendamentoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return agendamentos.size();
    }

    @Override
    public Agendamento getItem(int position) {
        return agendamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return agendamentos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);

        Agendamento agendamentoDevolvido = agendamentos.get(position);
        vincula(viewCriada, agendamentoDevolvido);

        return viewCriada;
    }

    //Altera layout de listagem
    private void vincula(View viewCriada, Agendamento agendamentoDevolvido) {
        TextView nome = viewCriada.findViewById(R.id.item_remedio_nome);
        nome.setText(agendamentoDevolvido.getNomeMedicamento());

        TextView telefone = viewCriada.findViewById(R.id.item_telefone);
        telefone.setText(agendamentoDevolvido.getDoseMedicamento());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_agendamento, parent, false);
    }

    public void remove(Agendamento agendamento) {
        this.agendamentos.remove(agendamento);
        notifyDataSetChanged();
    }

    public void atualiza(List<Agendamento> agendamentos){
        this.agendamentos.clear();
        this.agendamentos.addAll(agendamentos);
        notifyDataSetChanged();
    }
}
