package br.saojudas.mobile.healthcare.ui.activity;

import android.app.DatePickerDialog;
import android.graphics.MaskFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomUsuarioDAO;
import br.saojudas.mobile.healthcare.model.CadastroUsuario;
import br.saojudas.mobile.healthcare.ui.adapter.CadastroUsuarioAdapter;

public class EditaUsuarioActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR_CADASTRO_USUARIO = "Atualizar dados";
    private EditText campoNome;
    private EditText campoLogin;
    private EditText campoSenha;
    private EditText campoDataNascimento;
    private String[] campoSexo = new String[]{"Feminino", "Masculino"};
    private EditText campoTelefone;
    private EditText campoContatoEmergencia;
    private EditText campoTelefoneEmergencia;
    private EditText campoConfirmaSenha;
    private RoomUsuarioDAO dao;
    private CadastroUsuario cadastroUsuario;
    private CadastroUsuarioAdapter editaUsuarioAdapter;
    private Spinner sp;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_cadastro);
        HaelthCareDatabase database = HaelthCareDatabase.getInstance(this);
        dao = database.getRoomUsuarioDAO();
        setTitle(TITULO_APPBAR_CADASTRO_USUARIO);
        editaUsuarioAdapter = new CadastroUsuarioAdapter(this);

        campoNome = findViewById(R.id.campoNome);
        campoSenha = findViewById(R.id.campoSenha);
        campoDataNascimento = findViewById(R.id.campoDataNascimento);
        campoTelefone = findViewById(R.id.campoTelefone);
        campoTelefone.addTextChangedListener(Mask.insert("(##)# ####-####", campoTelefone));
        campoContatoEmergencia = findViewById(R.id.campoContatoEmergencia);
        campoTelefoneEmergencia = findViewById(R.id.campoTelefoneEmergencia);
        campoTelefoneEmergencia.addTextChangedListener(Mask.insert("(##)# ####-#####", campoTelefoneEmergencia));

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

        if (CadastroUsuario.session() == null)
            return;

        Calendar c = Calendar.getInstance();
        campoNome.setText(CadastroUsuario.session().getNome());
        campoTelefone.setText(CadastroUsuario.session().getTelefone());
        campoContatoEmergencia.setText(CadastroUsuario.session().getContatoEmergencia());
        campoTelefoneEmergencia.setText(CadastroUsuario.session().getTelefoneEmergencia());
        sp.setPrompt(CadastroUsuario.session().getSexo());
        campoDataNascimento.setText(CadastroUsuario.session().getDataNascimento());
//        formatDataNascimento(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR));
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


    public void atualizarUsuario(View view) {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, campoSexo);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            Spinner sp = (Spinner) findViewById(R.id.campoSexo);
            sp.setAdapter(adapter);
            CadastroUsuario usu = CadastroUsuario.session();
            usu.setNome(campoNome.getText().toString());
            usu.setDataNascimento(campoDataNascimento.getText().toString());
            usu.setTelefone(campoTelefone.getText().toString());
            usu.setContatoEmergencia(campoContatoEmergencia.getText().toString());
            usu.setTelefoneEmergencia(campoTelefoneEmergencia.getText().toString());

            int usu_id = usu.getId();

            dao.editarUsuario(usu);
            Toast.makeText(this,"Atualizado com sucesso!",Toast.LENGTH_SHORT).show();

            // pega usuario novamente
            CadastroUsuario.setSession(dao.findById(usu_id));
            System.out.print(CadastroUsuario.session());

            finish();
    }
}