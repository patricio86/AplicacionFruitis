package com.example.aplicacionfruitis;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public static String seleccionado;
    public static int seleccpos;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        seleccionado = (String) parent.getAdapter().getItem(pos);
        seleccpos = pos;

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}

