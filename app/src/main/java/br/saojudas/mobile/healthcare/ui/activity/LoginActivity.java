package br.saojudas.mobile.healthcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomUsuarioDAO;
import br.saojudas.mobile.healthcare.model.CadastroUsuario;
import br.saojudas.mobile.healthcare.ui.adapter.CadastroUsuarioAdapter;

public class LoginActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR_LOGIN = "LoginActivity";
    private EditText campoLoginLogin;
    private EditText campoSenhaLogin;
    private TextView txtCadastro;
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

        campoLoginLogin = findViewById(R.id.campoLoginLogin);
        campoSenhaLogin = findViewById(R.id.campoSenhaLogin);
        txtCadastro = findViewById(R.id.txtCadastro);
        abreCadastro();
    }

    private void abreCadastro() {
        TextView txtCadastro = findViewById(R.id.txtCadastro);
        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(it);
            }
        });
    }
}