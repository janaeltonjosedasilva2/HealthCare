package br.saojudas.mobile.healthcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomUsuarioDAO;
import br.saojudas.mobile.healthcare.model.CadastroUsuario;
import br.saojudas.mobile.healthcare.ui.adapter.CadastroUsuarioAdapter;

public class LoginActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR_LOGIN = "LoginActivity";
    private EditText editLogin;
    private EditText editSenha;
    private Button btnEntrar;
    private Button btnCadastro;
    private Button btnEsqueceuSenha;
    private RoomUsuarioDAO dao;
    private CadastroUsuario cadastroUsuario;
    private CadastroUsuarioAdapter cadastroUsuarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        HaelthCareDatabase database = HaelthCareDatabase.getInstance(this);
        dao = database.getRoomUsuarioDAO();
        setTitle(TITULO_APPBAR_LOGIN);
        carregarCampos();
        abreEsqueciSenha();
        abreCadastro();
    }

    public void setBtnEntrar(View view){
        if (validarLogin()){
            String loginView = editLogin.getText().toString();
            String senhaView =  editSenha.getText().toString();
            cadastroUsuario = dao.findByLoginAndSenha(loginView,senhaView);
            if (cadastroUsuario != null
                    && cadastroUsuario.getLogin().equals(loginView)
                    && cadastroUsuario.getSenha().equals(senhaView)){
                Toast.makeText(LoginActivity.this, "Login realizado com sucesso !", Toast.LENGTH_SHORT).show();
                abreDashboard();
            } else{
                Toast.makeText(LoginActivity.this, "Login e senha invalido !", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Boolean validarLogin(){
        Boolean validado = false;

        String nomeLogin = editLogin.getText().toString();
        String senhaLogin = editSenha.getText().toString();

        if (nomeLogin.isEmpty() || senhaLogin.isEmpty()){
            Toast.makeText(this,"Insira o Login e sua Senha",Toast.LENGTH_SHORT).show();
        } else {
            validado = true;
        }
        return validado;

    }

    private void carregarCampos(){
        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnEsqueceuSenha = findViewById(R.id.btnEsqueceuSenha);
    }

    private void abreCadastro() {
        Button btnCadastro = findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(it);
            }
        });
    }
    private void abreDashboard() {
        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(it);
            }
        });
    }
    private void abreEsqueciSenha() {
        Button btnEsqueceuSenha = findViewById(R.id.btnEsqueceuSenha);
        btnEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, EsqueceuSenhaActivity.class);
                startActivity(it);
            }
        });
    }
}