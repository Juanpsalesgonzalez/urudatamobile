package com.proyecto.urudatamobile;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by juan on 26/04/15.
 */
class WSLicenceTask extends AsyncTask <String, String, OutsourcerWebClient>  {

    private LicenceConnectActivity actividad;

    public WSLicenceTask(Activity a){
        System.out.println(a.toString());
        actividad = (LicenceConnectActivity) a;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        System.out.println("hola");
    }
    @Override
    protected OutsourcerWebClient doInBackground(String... params) {

        String user, pass,endDate,initDate,comment, cookie;


        user = params[0];
        pass = params[1];
        endDate = params[2];
        initDate = params[3];
        comment = params[4];

        cookie=WSServices.getCookie(WSServices.loginToWS(user, pass));
        if (cookie ==null){
            return null;
        }

        OutsourcerWebClient outsourcer = WSServices.setLicense(user,cookie,initDate,endDate,comment);
        if (outsourcer ==null){
            return null;
        }
        return outsourcer;

    }
    @Override
    protected void onPostExecute(OutsourcerWebClient outsourcer) {
       actividad.confirmMessage(outsourcer);

    }
}

