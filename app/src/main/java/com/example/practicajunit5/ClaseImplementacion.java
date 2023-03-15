package com.example.practicajunit5;

import java.util.regex.Pattern;

public class ClaseImplementacion {

    ClaseServicio claseServicio;

    ClaseImplementacion(ClaseServicio claseServicio) {
        this.claseServicio = claseServicio;
    }


    public String concatenacion(Integer id) {
        System.out.println("llamada metodo implementacion");

        try {
            String nombre = claseServicio.obtenerNOmbreId(id);
            return "el nombre es: " + nombre;
        } catch (NullPointerException e) {
            return "";
        }

    }

    public String validacionesExtra(int val) {
        String valor = "algun valor";
        //logica

        //llamada metodo void
        claseServicio.validacion(val);
        claseServicio.codigoTerceros();

        return valor;
    }


    public String concatenaciones() {
        String val1 = claseServicio.concatenacion1();
        String val2 = claseServicio.concatenacion2();
        return val1 + val2 + "";
    }

    public boolean validacionNumero(String numero) {

        String cadena = claseServicio.concatenacion1();
        boolean banderaCadena = false;
        boolean bandera = false;

        if (cadena.equals("")) {
            banderaCadena = true;
        }
        String NUMEROS = "[0-9]+$";
        Pattern pattern = Pattern.compile(NUMEROS);

        bandera = pattern.matcher(numero).matches();

        return banderaCadena && bandera;
    }

}
