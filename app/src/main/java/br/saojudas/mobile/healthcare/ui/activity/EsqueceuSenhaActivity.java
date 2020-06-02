package br.saojudas.mobile.healthcare.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomUsuarioDAO;
import br.saojudas.mobile.healthcare.model.CadastroUsuario;

public class EsqueceuSenhaActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_ESQUECEU_SENHA = "Forgot Password";
    private Button btnAlterarSenha;
    private Button btnVoltarLogin;
    private EditText editLogin;
    private EditText editNovaSenha;
    private EditText editConfirmaSenha;
    private EditText editDataNasc;

    private RoomUsuarioDAO dao;
    private CadastroUsuario cadastroUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);
        HaelthCareDatabase database = HaelthCareDatabase.getInstance(this);
        dao = database.getRoomUsuarioDAO();
        setTitle(TITULO_APPBAR_ESQUECEU_SENHA);
        carregarCampos();
        alterarSenha();
        setBtnVoltarLogin();
        Calendar c = Calendar.getInstance();
        formatDataNascimento(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR));
    }

    private void formatDataNascimento(int dia, int mes, int ano){
        editDataNasc.setText(String.format("%d/%d/%d", dia, mes, ano));
    }

    public void showDatePicker(View view){
        String[] date = editDataNasc.getText().toString().split("/");

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

    private void carregarCampos(){
        btnAlterarSenha = findViewById(R.id.btnAlterarSenha);
        btnVoltarLogin = findViewById(R.id.btnVoltarLogin);
        editLogin = findViewById(R.id.editLogin);
        editNovaSenha = findViewById(R.id.editNovaSenha);
        editConfirmaSenha = findViewById(R.id.editConfirmaSenha);
        editDataNasc = findViewById(R.id.editDataNasc);
    }


    public void alterarSenha() {
        Button btnAlterarSenha = findViewById(R.id.btnAlterarSenha);
        btnAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validacao()){
                    String login = editLogin.getText().toString();
                    String dataNascimento = editDataNasc.getText().toString();
                    cadastroUsuario =  dao.findByLoginAndDataNasc(login,dataNascimento);
                    if (cadastroUsuario != null
                            && cadastroUsuario.getLogin().equals(login)
                            && cadastroUsuario.getDataNascimento().equals(dataNascimento)){
                        String novaSenha = editNovaSenha.getText().toString();
                        String confimarSenha = editConfirmaSenha.getText().toString();
                        if (confimarSenha.equals(novaSenha)){
                            cadastroUsuario.setSenha(novaSenha);
                            dao.editarUsuario(cadastroUsuario);
                            Toast.makeText(EsqueceuSenhaActivity.this,"Senha alterada !", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(EsqueceuSenhaActivity.this,"As senhas precisam ser iguais !", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(EsqueceuSenhaActivity.this,"Usuario não encontrado !", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    private Boolean validacao(){
        Boolean validado = false;
        String login = editLogin.getText().toString();
        String dataNascimento = editDataNasc.getText().toString();
        if (login.isEmpty() || dataNascimento.isEmpty()){
            Toast.makeText(this,"Insira o Login e sua Data de Nascimento",Toast.LENGTH_SHORT).show();
        } else {
            validado = true;
        }
        return validado;

    }
    public void setBtnVoltarLogin() {
        Button btnVoltarLogin = findViewById(R.id.btnVoltarLogin);
        btnVoltarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(EsqueceuSenhaActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });
    }
}
