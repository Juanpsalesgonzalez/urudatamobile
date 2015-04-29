package com.proyecto.urudatamobile;

/**
 * Created by juan on 15/02/15.
 */


import android.app.Activity;
import android.os.AsyncTask;


public class WSOutsourcerTask extends AsyncTask <String, String, OutsourcerWebClient> {

    OutNameActivity actividad;

    public WSOutsourcerTask(Activity a){
        actividad = (OutNameActivity) a;
    }


    @Override
    protected OutsourcerWebClient doInBackground(String... params){

        String cookie;
        String url, user, pass;

        user=params[0];
        pass=params[1];

        cookie=WSServices.getCookie(WSServices.loginToWS(user, pass));
        if (cookie ==null){

            return null;
        }
        OutsourcerWebClient outsourcer = WSServices.outByName(cookie, user);
        if (outsourcer ==null){
            return null;
        }
        return outsourcer;

    } /*

/*        response= WSServices.loginToWS(user, pass);
        if (response == null){
            return null;
        }
        cookie=WSServices.getCookie(response);
        if (response ==null){
            return null;
        }
        OutsourcerWebClient outsourcer = WSServices.outByName(url, cookie, user);
        if (outsourcer ==null){
            return null;
        }
        return outsourcer;

    } /*

  /*  public OutsourcerWebClient outByName(String url, String cookie ,String user){

        OutsourcerWebClient t= null;
        url=url + "?username=" + user;
        RestTemplate rT=new RestTemplate(true);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        headers.set("Content-type", "application/x-www-form-urlencoded");
        headers.setContentEncoding(ContentCodingType.ALL);
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<String> response=null;
        rT.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            response = rT.exchange(url, HttpMethod.GET, requestEntity, String.class);
            // TO DO  JACKSON Converter
        }catch(Exception e) {
            e.printStackTrace();
        };

        if (response==null){
            return null;
        }
        String s = response.getBody();
        JSONObject j = null;
        String n=null;
        String i=null;
        try {
            j = new JSONObject(s);
            n=j.get("nombre").toString();
            i=j.get("id").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        OutsourcerWebClient o = new OutsourcerWebClient(n,i);

        return o;
    } */

   /* public ResponseEntity loginToWS(String user, String pass) {

            String  url="http://192.168.1.2:8080/urudata/loginProcess";
            RestTemplate rT = new RestTemplate(true); //Construye con Default Content Handlers

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-type", "application/x-www-form-urlencoded");
            LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
            body.add("username", user);
            body.add("password", pass);

            HttpEntity entity = new HttpEntity(body,headers);
            ResponseEntity<HttpResponse> response = null;


        try {
            response = rT.exchange(url, HttpMethod.POST, entity, HttpResponse.class);
        }catch (Exception e){e.printStackTrace();}

        String s=response.toString();
        System.out.println(s);
        if (response == null) {
            return null;
        }
        HttpStatus httpstatus = response.getStatusCode();
        if (httpstatus.value() != 302) {
            return null;
        }
        return response;
    }
*/
 /*   public String getCookie(ResponseEntity response) {

        String cookieField, cookieValue;
        String [] cookieSubFields, cookieLastFields;

        try {
            HttpHeaders headers = response.getHeaders();
            System.out.println(headers.get("Set-Cookie").toString());
            List<String> headerFieldValue = headers.get("Set-Cookie");
            cookieField = headerFieldValue.get(0);
            cookieSubFields = cookieField.split(";");
            //cookieLastFields = cookieSubFields[0].split("=");
            //cookieValue = cookieLastFields[1];
            cookieValue = cookieSubFields[0];  // Devuelve en formato JSESSIONID=xxxxxxxxx
            return cookieValue;
        }catch (Exception e){e.printStackTrace();}

        return null;

    }

*/

//    curl -i -H "Content-type: application/x-www-form-urlencoded" -c cookies.txt -X POST http://localhost:8080/urudata/loginProcess -d "username=nmoraes&password=123456"

    @Override
    protected void onPostExecute(OutsourcerWebClient outsourcer) {
        if (outsourcer == null) {
            actividad.setStatus("Error de Login");
        }else {
            String nombre = outsourcer.getNombre();
            String id = outsourcer.getId();
            actividad.setName(nombre);
            actividad.setId(id);
            actividad.setStatus("Marca realizada");

        }

    }
}

