package br.saojudas.mobile.healthcare.dao;

import java.util.ArrayList;
import java.util.List;

import br.saojudas.mobile.healthcare.model.Agendamento;

public class AgendamentoDAO {
    private final static List<Agendamento> agendamentos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salvar(Agendamento agendamento) {
        agendamento.setId(contadorDeIds);
        agendamentos.add(agendamento);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Agendamento agendamento) {
        Agendamento agendamentoEncontrado = buscaAgendamento(agendamento);
        if (agendamentoEncontrado != null) {
            int posicaoAgendamento = agendamentos.indexOf(agendamentoEncontrado);
            agendamentos.set(posicaoAgendamento, agendamentoEncontrado);
        }

    }

    private Agendamento buscaAgendamento(Agendamento agendamento) {
        for (Agendamento a :
                agendamentos) {
            if (a.getId() == agendamento.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Agendamento> todos() {
        return new ArrayList<>(agendamentos);
    }

    public void remove(Agendamento agendamento) {
        Agendamento agendamentoDevolvido = buscaAgendamento(agendamento);

        if (agendamentoDevolvido != null){
            agendamentos.remove(agendamentoDevolvido);
        }
    }
}
