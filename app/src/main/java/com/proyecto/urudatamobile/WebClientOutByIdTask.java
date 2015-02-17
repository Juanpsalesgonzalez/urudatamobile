package com.proyecto.urudatamobile;

/**
 * Created by juan on 15/02/15.
 */
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.os.AsyncTask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class WebClientOutByIdTask extends AsyncTask <String, String, OutsourcerWebClient> {

    OutNameActivity actividad;

    public WebClientOutByIdTask(Activity a){
        actividad = (OutNameActivity) a;
    }


    @Override
    protected OutsourcerWebClient doInBackground(String... urls){
        try{
            String url=urls[0];
            RestTemplate rT = new RestTemplate();
            rT.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            OutsourcerWebClient o = rT.getForObject(url,OutsourcerWebClient.class);
            return o;
        }  catch(Exception e) { e.printStackTrace();}
        return null;
    }

    @Override
    protected void onPostExecute(OutsourcerWebClient o) {

        String nombre=o.getNombre();
        String id=o.getId();
        actividad.setName(nombre);
        actividad.setId(id);
        actividad.setStatus("Marca realizada");
    }
}

