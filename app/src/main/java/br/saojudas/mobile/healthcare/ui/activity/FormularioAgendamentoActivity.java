package br.saojudas.mobile.healthcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomAgendamentoDAO;
import br.saojudas.mobile.healthcare.model.Agendamento;
import br.saojudas.mobile.healthcare.ui.adapter.FormularioAgendamentoAdapter;

import static br.saojudas.mobile.healthcare.ui.activity.ConstantesActivities.CHAVE_AGENDAMENTO;

public class FormularioAgendamentoActivity extends AppCompatActivity{

    private static final String TITULO_APPBAR_NOVO_AGENDAMENTO = "Novo agendamento";
    private static final String TITULO_APPBAR_EDITA_AGENDAMENTO = "Editar agendamento";
    private EditText campoNomeMedicamento;
    private EditText campoDose;
    private EditText campoNomeMedico;
    private Spinner campoFrequenciaMedicamento;
    private EditText campoHoraDose;

    private RoomAgendamentoDAO dao;
    private Agendamento agendamento;
    private FormularioAgendamentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_agendamento);
        HaelthCareDatabase database = HaelthCareDatabase.getInstance(this);
        dao = database.getRoomAgendamentoDAO();
        adapter = new FormularioAgendamentoAdapter(this);
        adapter.criaSpinner(this);
        inicializacaoDosCampos();
        carregaAgendamento();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_agendamento_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAgendamento() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_AGENDAMENTO)) {
        setTitle(TITULO_APPBAR_EDITA_AGENDAMENTO);
            agendamento = (Agendamento) dados.getSerializableExtra(CHAVE_AGENDAMENTO);
            preencheCamposDeAgendamento();
        } else {
        setTitle(TITULO_APPBAR_NOVO_AGENDAMENTO);
            agendamento = new Agendamento();
        }
    }

    private void preencheCamposDeAgendamento() {
        campoNomeMedicamento.setText(agendamento.getNomeMedicamento());
        campoNomeMedico.setText(agendamento.getNomeMedico());
        campoDose.setText(agendamento.getDoseMedicamento());
        adapter.setPosition(this, agendamento.getFrequenciaMedicamento());
        campoHoraDose.setText(agendamento.getHoraPrimeiraDoseString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_agendamento_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void finalizaFormulario() {
        preencheAgendamento();
        if (agendamento.temIdValido()) {
            dao.edita(agendamento);
        } else {
            dao.salvar(agendamento);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNomeMedicamento = findViewById(R.id.activity_formulario_agendamento_EditText_nomeRemedio);
        campoDose = findViewById(R.id.activity_formulario_agendamento_EditText_dose);
        campoNomeMedico = findViewById(R.id.activity_formulario_agendamento_EditText_nomeMedico);
        campoFrequenciaMedicamento = (Spinner) findViewById(R.id.activity_formulario_agendamento_spinner_frequencia_remedio);
        campoHoraDose = (EditText) findViewById(R.id.activity_formulario_agendamento_EditText_horaDose);
    }

    private void preencheAgendamento() {
        String nomeMedicamento = campoNomeMedicamento.getText().toString();
        String nomeMedico = campoNomeMedico.getText().toString();
        String dose = campoDose.getText().toString();
        int frequenciaMedicamento = campoFrequenciaMedicamento.getSelectedItemPosition();
        String primeiraDose = campoHoraDose.getText().toString();

        agendamento.setNomeMedicamento(nomeMedicamento);
        agendamento.setNomeMedico(nomeMedico);
        agendamento.setDoseMedicamento(dose);
        agendamento.setFrequenciaMedicamento(frequenciaMedicamento);
        agendamento.setHoraPrimeiraDoseString(primeiraDose);
    }
}
