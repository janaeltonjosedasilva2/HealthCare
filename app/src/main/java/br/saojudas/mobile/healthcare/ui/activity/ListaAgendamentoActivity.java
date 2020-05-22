package br.saojudas.mobile.healthcare.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.model.Agendamento;
import br.saojudas.mobile.healthcare.ui.ListaAgendamentoView;

import static br.saojudas.mobile.healthcare.ui.activity.ConstantesActivities.CHAVE_AGENDAMENTO;

public class ListaAgendamentoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de Agendamentos";
    private ListaAgendamentoView listaAgendamentoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_agendamento);
        listaAgendamentoView = new ListaAgendamentoView(this);
        setTitle(TITULO_APPBAR);
        configuraFabCriaAgendamento();
        configuraFabCriaUsuario();
        criaListaAgendamentos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_agendamento_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.activity_lista_agendamento_menu_remover) {
            listaAgendamentoView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraFabCriaAgendamento() {
        FloatingActionButton botaoNovoAgendamento = findViewById(R.id.activity_lista_agendamento_fab_novo_agendamento);
        botaoNovoAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioModoInsereAgendamento();
            }
        });
    }

    private void configuraFabCriaUsuario() {
        FloatingActionButton botaoNovoUsuario = findViewById(R.id.activity_cadastro_usuario_fab_novo_usuario);
        botaoNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui metodo da view cria usuario
                Intent it = new Intent(ListaAgendamentoActivity.this, CadastroUsuarioActivity.class);
                startActivity(it);
            }
        });
    }

    private void abreFormularioModoInsereAgendamento() {
        startActivity(new Intent(this, FormularioAgendamentoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAgendamentoView.atualizaListaDeAgendamentos();
    }

    private void criaListaAgendamentos() {
        ListView listaDeAgendamentos = findViewById(R.id.activity_lista_agendamento_listview);
        listaAgendamentoView.configuraAdpter(listaDeAgendamentos);
        configuraListenerDeClickPorItem(listaDeAgendamentos);
        registerForContextMenu(listaDeAgendamentos);
    }

    private void configuraListenerDeClickPorItem(ListView listaAgendamento) {
        listaAgendamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Agendamento AgendamentoEscolhido = (Agendamento) parent.getItemAtPosition(position);
                Log.i("agendamento", "" + AgendamentoEscolhido);

                abreFormularioModoEditaAgendamento(AgendamentoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAgendamento(Agendamento agedamentoEscolhido) {
        Intent enviarParaFormularioActivity = new Intent(ListaAgendamentoActivity.this, FormularioAgendamentoActivity.class);
        enviarParaFormularioActivity.putExtra(CHAVE_AGENDAMENTO, agedamentoEscolhido);
        startActivity(enviarParaFormularioActivity);
    }

}
