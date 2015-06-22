package com.proyecto.urudatamobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Calendar;


public class LicenceActivity extends ActionBarActivity {


    String comment;
    String user, pass;
    OutsourcerWebClient outsourcer;

    String initDate, endDate;

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
        TextView t = (TextView) this.findViewById(R.id.editText_fechaIni);
        mostrarDatePicker(v, t);
    }

    public void cargaFechaInicio(View v) {
        TextView t = (TextView) this.findViewById(R.id.editText_fechaFin);
        mostrarDatePicker(v, t);
    }

    public void mostrarDatePicker(View v, TextView t) {
        t.setText(HttpUtils.currDate());
        //FechaDialogFragment fechaFragment = new FechaDialogFragment();
        //fechaFragment.setActividadPadre(this);
        //fechaFragment.setTextoPadre(t);
        //fechaFragment.show(getFragmentManager(), "Calendario");
    }

    public void setDate(TextView t, int ano, int mes, int dia) {
        t.setText(new StringBuilder().append(dia).append("/")
                .append(mes + 1).append("/")
                .append(ano).append(" "));

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_lic_comun:
                if (checked) {
                    comment = "Reglamentaria";
                    break;
                }
            case R.id.radio_lic_enfermedad:
                if (checked) {
                    comment = "Enfermedad";
                    break;
                }
            case R.id.radio_lic_estudio:
                if (checked) {
                    comment = "Estudio";
                    break;
                }
            case R.id.radio_lic_otros:
                if (checked) {
                    comment = "Otros";
                    break;
                }
        }
    }

    public void loginError() {
        System.out.println("Login Error");
    }

    public void confirmError() {
        System.out.println("Error al confirmar licencia");
    }

    public void setOutsourcer(OutsourcerWebClient o) {
        if (outsourcer != null) {
            outsourcer = o;
        }
    }
    public void confirmaLicencia(View v){

        TextView endDateTV, initDateTV;
        String initDate, endDate;

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        endDateTV = (TextView)this.findViewById(R.id.editText_fechaIni);
        initDateTV = (TextView)this.findViewById(R.id.editText_fechaFin);
        initDate = initDateTV.getText().toString();
        endDate = endDateTV.getText().toString();
        if (initDate == null) {
            initDate = day + "/" + month + "/" + year;
        }
        if (endDate == null) {
            endDate = day + "/" + month + "/" + year;
        }
        if (comment == null){
            comment="Licencia por Enfermedad";
        }

        Intent intent = getIntent();
        user = intent.getStringExtra("name_outsourcer");
        pass = intent.getStringExtra("pass_outsourcer");
        // Es necesario crear un Intent y un Activity para poder llamar
        // al Async Task. El Async Task solo se puede llamar del main/

        Intent conIntent = new Intent(this,com.proyecto.urudatamobile.LicenceConnectActivity.class);
        conIntent.putExtra("init",initDate);
        conIntent.putExtra("end",endDate);
        conIntent.putExtra("name",user);
        conIntent.putExtra("pass",pass);
        conIntent.putExtra("comment",comment);
        startActivity(conIntent);
        finish();
    }


//        new WSLicenceTask(this).execute(user, pass, endDate, initDate, comment);


    public void confirmMessage(OutsourcerWebClient o) {
        System.out.println(o.getNombre());
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


