package com.proyecto.urudatamobile;

/**
 * Created by juan on 15/02/15.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class WebClientOutById extends AsyncTask<String, String, String> {

    private OutNameActivity actividad;

    public WebClientOutById(OutNameActivity a){
        actividad=a;
    }

    protected void onPreExecute () {};

    @Override
    protected String doInBackground(String... urls){

        String u = urls[0];
        HttpClient httpclient = new DefaultHttpClient();
        StringBuilder builder = new StringBuilder();
        HttpGet httprequest = new HttpGet(u);
        System.out.println("URL: " + u);
        try {
            HttpResponse respuesta = httpclient.execute(httprequest);
            StatusLine statusLine = respuesta.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            System.out.println("Status : " + statusCode);
            if (statusCode == 200){
                HttpEntity entity = respuesta.getEntity();
                InputStream contenido = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(contenido));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    System.out.println("Linea recibida : " + line);
                }
            }
        } catch (Exception e){
            System.out.println("Primer catch");
            e.printStackTrace();

        }
        return builder.toString();
    }

//{"id":25,"nombre":"Juan Pablo","direccion":null,"proyecto":null,"celular":null,"status":null,"markIn":null,"markOut":null}


    protected void onPostExecute(String result){

        String nombre = "Su ID es Invalida";
        try {
            JSONObject jsonObject = new JSONObject(result);
            nombre=jsonObject.getString("nombre");
            System.out.println("Nombre WebService " + nombre);
        } catch (JSONException e) {
            System.out.println("Segundo catch");
            e.printStackTrace();
        }
        actividad.setName(nombre);

    }

}

