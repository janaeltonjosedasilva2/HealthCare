package br.saojudas.mobile.healthcare.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomAgendamentoDAO;
import br.saojudas.mobile.healthcare.model.CadastroUsuario;
import br.saojudas.mobile.healthcare.ui.adapter.CadastroUsuarioAdapter;


public class CadastroUsuarioActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_CADASTRO_USUARIO = "Novo usuário";
    private EditText campoNome;
    private EditText campoLogin;
    private EditText campoSenha;
    private EditText campoDataNascimento;
    private String[] campoSexo =  new String[]{"Feminino", "Masculino"};
    private EditText campoTelefone;
    private EditText campoContatoEmergencia;
    private EditText campoTelefoneEmergencia;
    private RoomAgendamentoDAO dao;
    private CadastroUsuario cadastroUsuario;
    private CadastroUsuarioAdapter cadastroUsuarioAdapter;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        HaelthCareDatabase database = HaelthCareDatabase.getInstance(this);
        dao = database.getRoomAgendamentoDAO();
        setTitle(TITULO_APPBAR_CADASTRO_USUARIO);
        cadastroUsuarioAdapter = new CadastroUsuarioAdapter(this);

        campoNome = findViewById(R.id.campoNome);
        campoLogin = findViewById(R.id.campoLogin);
        campoSenha = findViewById(R.id.campoSenha);
        campoDataNascimento = findViewById(R.id.campoDataNascimento);
        campoTelefone = findViewById(R.id.campoTelefone);
        campoContatoEmergencia = findViewById(R.id.campoContatoEmergencia);
        campoTelefoneEmergencia = findViewById(R.id.campoTelefoneEmergencia);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, campoSexo);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Spinner sp = (Spinner) findViewById(R.id.campoSexo);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void salvarUsuario(View view) {
        CadastroUsuario usuario = new CadastroUsuario();
        usuario.setNome(campoNome.getText().toString());
        usuario.setLogin(campoLogin.getText().toString());
        usuario.setSenha(campoSenha.getText().toString());
        usuario.setDataNascimento(campoDataNascimento.getText().toString());
        usuario.setTelefone(campoTelefone.getText().toString());
        usuario.setContatoEmergencia(campoContatoEmergencia.getText().toString());
        usuario.setTelefoneEmergencia(campoTelefoneEmergencia.getText().toString());
    }

}
