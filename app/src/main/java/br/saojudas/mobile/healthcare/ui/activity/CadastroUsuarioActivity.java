package br.saojudas.mobile.healthcare.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomUsuarioDAO;
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
    private RoomUsuarioDAO dao;
    private CadastroUsuario cadastroUsuario;
    private CadastroUsuarioAdapter cadastroUsuarioAdapter;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        HaelthCareDatabase database = HaelthCareDatabase.getInstance(this);
        dao = database.getRoomUsuarioDAO();
        setTitle(TITULO_APPBAR_CADASTRO_USUARIO);
        cadastroUsuarioAdapter = new CadastroUsuarioAdapter(this);

        campoNome = findViewById(R.id.campoNome);
        campoLogin = findViewById(R.id.campoLogin);
        campoSenha = findViewById(R.id.campoSenha);
        campoDataNascimento = findViewById(R.id.campoDataNascimento);
        campoTelefone = findViewById(R.id.campoTelefone);
        campoTelefone.addTextChangedListener(Mask.insert("(##)#####-####", campoTelefone));
        campoContatoEmergencia = findViewById(R.id.campoContatoEmergencia);
        campoTelefoneEmergencia = findViewById(R.id.campoTelefoneEmergencia);
        campoTelefoneEmergencia.addTextChangedListener(Mask.insert("(##)#####-#####", campoTelefoneEmergencia));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, campoSexo);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Spinner sp = (Spinner) findViewById(R.id.campoSexo);
        sp.setAdapter(adapter);

        Calendar c = Calendar.getInstance();
        formatDataNascimento(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR));

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void formatDataNascimento(int dia, int mes, int ano){
        campoDataNascimento.setText(String.format("%d/%d/%d", dia, mes, ano));
    }

    public void showDatePicker(View view){
        String[] date = campoDataNascimento.getText().toString().split("/");

        int ano = Integer.parseInt(date[2]);
        int mes = Integer.parseInt(date[1]);
        int dia = Integer.parseInt(date[0]);

        formatDataNascimento(dia, mes, ano);

        DatePickerDialog a = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                formatDataNascimento(day, month + 1, year);
            }
        }, dia, mes, ano);
        a.show();
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
        if (validarFormulario().equals(true)) {
            dao.salvarUsuario(usuario);
            Toast.makeText(this,"Seja bem vindo(a)!",Toast.LENGTH_SHORT).show();
            Button btnSalvarUsuario = findViewById(R.id.btnSalvarUsuario);
            btnSalvarUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }
    }

    public Boolean validarFormulario(){
        Boolean validado = false;

        if (campoNome.getText().toString().isEmpty()){
            Toast.makeText(this,"Insira seu nome completo",Toast.LENGTH_SHORT).show();
        } else if (campoLogin.getText().toString().isEmpty() || campoLogin.getText().toString().length() < 4) {
            Toast.makeText(this,"Login não pode ser em branco ou menor que 4 caracteres",Toast.LENGTH_SHORT).show();
        } else if (campoSenha.getText().toString().isEmpty() || campoLogin.getText().toString().length() < 5) {
            Toast.makeText(this,"Senha não pode ser em branco ou menor que 5 caracteres",Toast.LENGTH_SHORT).show();
        } else if (campoDataNascimento.getText().toString().isEmpty()) {
            Toast.makeText(this,"Insira sua data de nascimento",Toast.LENGTH_SHORT).show();
        } else if (campoContatoEmergencia.getText().toString().isEmpty()) {
            Toast.makeText(this,"Insira seu contato de emergência",Toast.LENGTH_SHORT).show();
        } else if (campoTelefoneEmergencia.getText().toString().isEmpty()) {
            Toast.makeText(this,"Insira o telefone do seu contato de emergência",Toast.LENGTH_SHORT).show();
        }
        else {
            validado = true;
        }
        return validado;

    }
}
