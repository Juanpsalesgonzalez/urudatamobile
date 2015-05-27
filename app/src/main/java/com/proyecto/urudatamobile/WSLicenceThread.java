package com.proyecto.urudatamobile;

/**
 * Created by juan on 10/05/15.
 */
public class WSLicenceThread extends Thread {

    private LicenceActivity parent;
    private String user, pass, initDate, endDate, comment;
    private String cookie;

    public WSLicenceThread() {
        super();
    }

    public WSLicenceThread(LicenceActivity a) {
        this.parent = a;
    }

    public void setParent(LicenceActivity parent) {
        this.parent = parent;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

     public void run(){

        cookie=WSServices.getCookie(WSServices.loginToWS(user, pass));
        if (cookie ==null){
            parent.loginError();
        }else {
            OutsourcerWebClient outsourcer = WSServices.setLicense(user, cookie, initDate, endDate, comment);
            if (outsourcer == null) {
                parent.loginError();
            } else {
                parent.confirmMessage(outsourcer);
            }
        }
    }

}
