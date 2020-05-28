package br.saojudas.mobile.healthcare.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.database.HaelthCareDatabase;
import br.saojudas.mobile.healthcare.database.dao.RoomAgendamentoDAO;
import br.saojudas.mobile.healthcare.model.Agendamento;
import br.saojudas.mobile.healthcare.ui.adapter.FormularioAgendamentoAdapter;
import br.saojudas.mobile.healthcare.ui.helper.TimePickerHelper;
import br.saojudas.mobile.healthcare.ui.receiver.AlertReceiver;

import static br.saojudas.mobile.healthcare.ui.activity.ConstantesActivities.CHAVE_AGENDAMENTO;

public class FormularioAgendamentoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_AGENDAMENTO = "Novo agendamento";
    private static final String TITULO_APPBAR_EDITA_AGENDAMENTO = "Editar agendamento";
    private EditText campoNomeMedicamento;
    private EditText campoDose;
    private EditText campoNomeMedico;
    private Spinner campoFrequenciaMedicamento;
    private EditText campoHoraDose;
    private TimePickerDialog picker;
    private RoomAgendamentoDAO dao;
    private Agendamento agendamento;
    private FormularioAgendamentoAdapter adapter;
    private TextView alarme;

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
        displayTimePicker();
    }

    private void displayTimePicker() {
        campoHoraDose.setInputType(InputType.TYPE_NULL);
        campoHoraDose.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(FormularioAgendamentoActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, sHour);
                                c.set(Calendar.MINUTE, sMinute);
                                c.set(Calendar.SECOND, 0);
                                iniciarAlarme(c);
                                inserirTimeText(c);
                                campoHoraDose.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
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
        campoHoraDose.setText(agendamento.getHoraPrimeiraDose());
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
        alarme = findViewById(R.id.alarme);
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
        agendamento.setHoraPrimeiraDose(primeiraDose);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void iniciarAlarme(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        boolean a = c.before(Calendar.getInstance());
        if (a) {
            c.add(Calendar.DATE, 1);
        }
        c.getTimeInMillis();
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void inserirTimeText(Calendar c) {
        String timeText = "Dose agendada para: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        alarme.setText(timeText);
    }
    private void cancelaAlarme() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
        alarme.setText("Alarm canceled");
    }
}
