package br.saojudas.mobile.healthcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomUsuarioDAO;
import br.saojudas.mobile.healthcare.model.CadastroUsuario;
import br.saojudas.mobile.healthcare.ui.adapter.CadastroUsuarioAdapter;

public class DashboardActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR_LOGIN = "Dashboard";

    private RoomUsuarioDAO dao;
    private CadastroUsuario cadastroUsuario;
    private CadastroUsuarioAdapter cadastroUsuarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        HaelthCareDatabase database = HaelthCareDatabase.getInstance(this);
        dao = database.getRoomUsuarioDAO();
        setTitle(TITULO_APPBAR_LOGIN);
        abreCadastro();
        abreListaAgendamento();
        abreNovoAgendamento();
    }

    private void abreCadastro() {
        RelativeLayout relativeLayout1 = findViewById(R.id.relativeLayout1);

        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DashboardActivity.this, EditaUsuarioActivity.class);
                startActivity(it);
            }
        });
    }

    private void abreListaAgendamento() {
        RelativeLayout relativeLayout2 = findViewById(R.id.relativeLayout2);

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DashboardActivity.this, ListaAgendamentoActivity.class);
                startActivity(it);
            }
        });
    }

    private void abreNovoAgendamento() {
        RelativeLayout relativeLayout3 = findViewById(R.id.relativeLayout3);
        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DashboardActivity.this, FormularioAgendamentoActivity.class);
                startActivity(it);
            }
        });
    }
}