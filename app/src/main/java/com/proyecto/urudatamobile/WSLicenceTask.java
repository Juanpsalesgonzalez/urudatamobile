package com.proyecto.urudatamobile;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by juan on 26/04/15.
 */
public class WSLicenceTask extends AsyncTask <String, String, OutsourcerWebClient>  {

    LicenceActivity actividad;

    public WSLicenceTask(Activity a){
        actividad = (LicenceActivity) a;
    }

    @Override
    protected OutsourcerWebClient doInBackground(String... params) {

        String user, pass,endDate,initDate,comment, cookie;

        user = params[0];
        pass = params[1];
        initDate = params[2];
        endDate = params[3];
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

    }

}  */

}
