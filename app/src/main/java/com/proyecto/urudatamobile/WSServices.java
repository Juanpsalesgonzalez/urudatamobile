package com.proyecto.urudatamobile;

import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Autor: JPS - 26/04/15.
 * Servicios de cliente Web Services
 */


class WSServices {


    public static OutsourcerWebClient outByName(String cookie, String user) {

        String url;

        url = Constants.URL_CONFIRM;
        url = url + "?username=" + user;
        RestTemplate rT = new RestTemplate(true);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        headers.set("Content-type", "application/x-www-form-urlencoded");
        headers.setContentEncoding(ContentCodingType.ALL);
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        rT.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            response = rT.exchange(url, HttpMethod.GET, requestEntity, String.class);
            // TO DO  JACKSON Converter
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response == null) {
            return null;
        }
        String s = response.getBody();
        String n = null;
        String i = null;
        try {
            JSONObject j = new JSONObject(s);
            n = j.get("nombre").toString();
            i = j.get("id").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new OutsourcerWebClient(n, i);
    }

    public static ResponseEntity loginToWS(String user, String pass) {

        String url = Constants.URL_LOGIN_PROCESS;
        RestTemplate rT = new RestTemplate(true); //Construye con Default Content Handlers

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/x-www-form-urlencoded");
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("username", user);
        body.add("password", pass);

        HttpEntity entity = new HttpEntity(body, headers);
        ResponseEntity<HttpResponse> response = null;

        try {
                    response = rT.exchange(url, HttpMethod.POST, entity, HttpResponse.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        if (response == null) {
            return null;
        }
        HttpStatus httpstatus = response.getStatusCode();
        if (httpstatus.value() != 302) {
            return null;
        }
        return response;
    }

    public static String getCookie(ResponseEntity response) {

        String cookieField, cookieValue;
        String[] cookieSubFields;

        try {
            HttpHeaders headers = response.getHeaders();
            System.out.println(headers.get("Set-Cookie").toString());
            List<String> headerFieldValue = headers.get("Set-Cookie");
            cookieField = headerFieldValue.get(0);
            cookieSubFields = cookieField.split(";");//
            //cookieLastFields = cookieSubFields[0].split("=");
            //cookieValue = cookieLastFields[1];
            cookieValue = cookieSubFields[0];  // Devuelve en formato JSESSIONID=xxxxxxxxx
            return cookieValue;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public static OutsourcerWebClient setLicense(String user, String cookie, String initDate, String endDate, String comment) {
        {
            String url;
            String url_user, url_idate, url_edate, url_comm;
            String sep = "?";    //url_separator
            String fsep = "&"; //url_field_separator
            url_user = "username=" + user;
            url_idate = "fechaini=" + initDate;
            url_edate = "fechafin=" + endDate;
            url_comm = "comm=" + comment;

            url = Constants.URL_SET_LICENSE;
            url = url + sep + url_user + fsep + url_idate + fsep + url_edate + fsep + url_comm;
            System.out.println("URL " + url);
            RestTemplate rT = new RestTemplate(true);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Cookie", cookie);
            headers.set("Content-type", "application/x-www-form-urlencoded");
            headers.setContentEncoding(ContentCodingType.ALL);
            HttpEntity requestEntity = new HttpEntity(headers);
            ResponseEntity<String> response = null;
            rT.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                try {
                    response = rT.exchange(url, HttpMethod.GET, requestEntity, String.class);
                   } catch (Exception e) {
                    e.printStackTrace();
                   }

            if (response == null) {
                System.out.println("Response NULL");
                return null;
            }
            System.out.println(response.toString());
            String s = response.getBody();
            String n = null;
            String i = null;
            try {
                JSONObject j = new JSONObject(s);
                n = j.get("nombre").toString();
                i = j.get("id").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            OutsourcerWebClient o = new OutsourcerWebClient(n, i);

            return o;
        }
    }
}