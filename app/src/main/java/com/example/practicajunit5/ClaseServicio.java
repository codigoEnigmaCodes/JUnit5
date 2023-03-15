package com.example.practicajunit5;

import android.content.Context;
import android.os.Build;

public class ClaseServicio {


    public String obtenerNOmbreId(Integer id) {
        String nombre = "";
        /*
        codigo que ejecuta servicio y obtiene nombre por id
        * */

        System.out.println("llamada metodo nombreid");

        return nombre;
    }

    public void validacion(int val) {

        System.out.println("metodo extra");

        //return"";
    }

    public void codigoTerceros() {

        System.out.println("llamada externa");
    }

    public String concatenacion1() {
        System.out.println("llamada concatenacion1");
        return "concatenacion1";
    }

    public String concatenacion2() {

        return "concatenacion2";
    }
}
