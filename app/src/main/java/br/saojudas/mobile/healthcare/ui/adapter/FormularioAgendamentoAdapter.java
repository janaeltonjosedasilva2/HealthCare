package br.saojudas.mobile.healthcare.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import br.saojudas.mobile.healthcare.R;
import br.saojudas.mobile.healthcare.ui.activity.FormularioAgendamentoActivity;

public class FormularioAgendamentoAdapter extends Activity implements AdapterView.OnItemSelectedListener {
    Context context;
    Spinner spinner;

    public FormularioAgendamentoAdapter(Context context) {
        this.context = context;
    }

    public void criaSpinner(FormularioAgendamentoActivity viewCriada) {
        spinner = (Spinner) viewCriada.findViewById(R.id.activity_formulario_agendamento_spinner_frequencia_remedio);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.frequencia_remedio,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void setPosition (FormularioAgendamentoActivity viewCriada, int position){
        spinner = (Spinner) viewCriada.findViewById(R.id.activity_formulario_agendamento_spinner_frequencia_remedio);
        spinner.setSelection(position);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
