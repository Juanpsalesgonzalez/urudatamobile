package com.proyecto.urudatamobile;

import android.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;


public class licenciaActivity extends ActionBarActivity {


    String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licencia);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_licencia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cargaFechaFin(View v) {
        TextView t =(TextView)this.findViewById(R.id.editText_fechaIni);
        mostrarDatePicker(v,t);
    }

    public void cargaFechaInicio(View v) {
        TextView t =(TextView)this.findViewById(R.id.editText_fechaFin);
        mostrarDatePicker(v,t);
    }

    public void mostrarDatePicker(View v,TextView t) {
        FechaDialogFragment fechaFragment = new FechaDialogFragment();
        fechaFragment.setActividadPadre(this);
        fechaFragment.setTextoPadre(t);
        fechaFragment.show(getFragmentManager(), "Calendario");
    }

    public void setDate(TextView t, int ano, int mes, int dia){
        t.setText(new StringBuilder().append(dia).append("/")
                .append(mes+1).append("/")
                .append(ano).append(" "));

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_lic_comun:
                if (checked) {
                    comment = "Licencia Reglamentaria";
                    break;
                }
            case R.id.radio_lic_enfermedad:
                if (checked) {
                    comment = "Licencia por Enfermedad";
                    break;
                }
            case R.id.radio_lic_estudio:
                if (checked) {
                    comment = "Licencia por Estudio";
                    break;
                }
            case R.id.radio_lic_otros:
                if (checked) {
                    comment = "Licencia Otros";
                    break;
                }
        }
    }

    public void confirmaLicencia(View v){
        System.out.println(comment);
        TextView t =(TextView)this.findViewById(R.id.editText_fechaFin);
        System.out.println(t.getText());
        TextView s =(TextView)this.findViewById(R.id.editText_fechaFin);
        System.out.println(s.getText());
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_licencia, container, false);
            return rootView;
        }
    }
}
