package com.example.practicajunit5;

import java.util.regex.Pattern;

public class Utils {


    public boolean numero(String numero) {

        String NUMEROS = "[0-9]$";
        Pattern pattern = Pattern.compile(NUMEROS);

        return pattern.matcher(numero).matches();
    }

    public boolean numeros(String numero) {

        String NUMEROS = "[0-9]+$";
        Pattern pattern = Pattern.compile(NUMEROS);

        return pattern.matcher(numero).matches();
    }

    public boolean decimales(String numero) {
        String NUMEROS_DECIMALES = "[0-9]+[.]?[0-9]+$";
        Pattern pattern = Pattern.compile(NUMEROS_DECIMALES);
        return pattern.matcher(numero).matches();

    }

    public boolean moneda(String numero) {
        String MONEDA = "[$]([0]|[1-9]([0-9]{2})?|[1-9]([0-9]{2})?[,][0-9]{3}|[1-9]([0-9]{2})?[,][0-9]{3}[,][0-9]{3})([.][0-9]+)?$";
        Pattern pattern = Pattern.compile(MONEDA);

        return pattern.matcher(numero).matches();

    }


    public boolean caracteres(String cadena) {
        String expresion = "(([A-Z]+|[a-z]+|[áéíóú]+)|[ñÑ]+)+$";
        Pattern pattern = Pattern.compile(expresion);

        return pattern.matcher(cadena).matches();
    }

    public boolean curp(String curp) {
        String REGULAR_EXPRESSION_VALIDATE_CURP = "[A-Z]{1}[AEIOUX]{1}[A-Z]{2}"
                + "[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])"
                + "[HM]{1}"
                + "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)"
                + "[B-DF-HJ-NP-TV-Z]{3}"
                + "[0-9A-Z]{1}[0-9]{1}$";

        Pattern pattern = Pattern.compile(REGULAR_EXPRESSION_VALIDATE_CURP);

        return pattern.matcher(curp).matches();

    }

    public void validaciones() {

        String CARACTERES_SEGUIDOS = ".*1(?!2).*$";
        Pattern pattern = Pattern.compile(CARACTERES_SEGUIDOS);
        System.out.println("cadena dd12fg " + pattern.matcher("dd12fg").matches());
        System.out.println("cadena dd13fg " + pattern.matcher("dd13fg").matches());

        String CANTIDAD_CARACTERES = "[A-Z]{5,10}$";
        pattern = Pattern.compile(CANTIDAD_CARACTERES);
        System.out.println("cadena AAFZZZZ " + pattern.matcher("AAFZZZZ").matches());
    }
}
