package br.saojudas.mobile.healthcare.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import br.saojudas.mobile.healthcare.ui.activity.CadastroUsuarioActivity;
import br.saojudas.mobile.healthcare.ui.activity.EditaUsuarioActivity;

public class CadastroUsuarioAdapter extends Activity implements AdapterView.OnItemSelectedListener {
    public CadastroUsuarioAdapter(CadastroUsuarioActivity cadastroUsuarioActivity) {
    }
    public CadastroUsuarioAdapter(EditaUsuarioActivity editaUsuarioActivity) {
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
