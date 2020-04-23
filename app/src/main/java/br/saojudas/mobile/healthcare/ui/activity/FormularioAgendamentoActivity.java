package br.saojudas.mobile.healthcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.dao.AgendamentoDAO;
import br.saojudas.mobile.healthcare.model.Agendamento;

import static br.saojudas.mobile.healthcare.ui.activity.ConstantesActivities.CHAVE_AGENDAMENTO;

public class FormularioAgendamentoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_AGENDAMENTO = "Novo agendamento";
    private static final String TITULO_APPBAR_EDITA_AGENDAMENTO = "Editar agendamento";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AgendamentoDAO dao = new AgendamentoDAO();
    private Agendamento agendamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_agendamento);
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
        campoNome.setText(agendamento.getNome());
        campoEmail.setText(agendamento.getEmail());
        campoTelefone.setText(agendamento.getTelefone());
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
        campoNome = findViewById(R.id.activity_formulario_agendamento_EditText_nomeRemedio);
        campoTelefone = findViewById(R.id.activity_formulario_agendamento_EditText_telefone);
        campoEmail = findViewById(R.id.activity_formulario_agendamento_EditText_email);
    }

    private void preencheAgendamento() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String telefone = campoTelefone.getText().toString();

        agendamento.setNome(nome);
        agendamento.setEmail(email);
        agendamento.setTelefone(telefone);
    }
}
