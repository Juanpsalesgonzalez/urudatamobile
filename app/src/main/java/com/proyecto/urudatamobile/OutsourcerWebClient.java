package com.proyecto.urudatamobile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by juan on 15/02/15.
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class OutsourcerWebClient {



    private String id;
    private String nombre;

    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setId(String i){
        id=i;
    }

    public void setNombre(String n){
        nombre=n;
    }

}

