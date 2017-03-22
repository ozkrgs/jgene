/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author sigesa
 */
public class Utilitarios {
    
    public static String firstUpper(String texto) {//recibe un string y delvuelve el mismo string con la primera letra en mayuscula y las demas en minuscula
        if (texto.length() > 0) {
            texto = texto.replace(texto.substring(0, 1), texto.substring(0, 1).toUpperCase());
            texto = texto.replace(texto.substring(1, texto.length()), texto.substring(1, texto.length()).toLowerCase());
        }
        return texto;
    }

    public static String firstLower(String texto) {//recibe un string y delvuelve el mismo estring con la primera letra en minuscula 
        if (texto.length() > 0) {
            texto = texto.replace(texto.substring(0, 1), texto.substring(0, 1).toLowerCase());
            texto = texto.replace(texto.substring(1, texto.length()), texto.substring(1, texto.length()).toLowerCase());
        }
        return texto;
    }

    public static String firstLetterUpper(String texto) {//recibe un string y delvuelve el mismo string con la primera letra en mayuscula y las demas en el mismo estado
        if (texto.length() > 0) {
            String letraM = texto.substring(0, 1).toUpperCase();
            texto = letraM.concat(texto.substring(1, texto.length()));
        }
        return texto;
    }

    public static String firstLetterLower(String texto) {//recibe un string y delvuelve el mismo string con la primera letra en mayuscula y las demas en el mismo estado
        if (texto.length() > 0) {
            String letraM = texto.substring(0, 1).toLowerCase();
            texto = letraM.concat(texto.substring(1, texto.length()));
        }
        return texto;
    }

    public  static String convierteNombre(String frase) {//despues de cada "_" convierte la siguiente letra en mayuscula y elimina los "_"
        String frase2 = "";
        int indice = 0;
        int indicepunto = 0;

        indicepunto = frase.indexOf('_', indice);
        while (indicepunto >= 0) {
            // El primer carácter es en mayúsculas
            frase2 = frase2 + frase.substring(indice, indice + 1).toUpperCase();
            // Pegamos el resto hasta el primer punto      
            frase2 = frase2 + frase.substring(indice + 1, indicepunto + 1);

            indice = indicepunto + 1;
            indicepunto = frase.indexOf('_', indice);
        }
        // Incluimos el final de la frase
        frase2 = frase2 + frase.substring(indice, indice + 1).toUpperCase();
        frase2 = frase2 + frase.substring(indice + 1, frase.length());
        frase2 = frase2.replaceAll("_", "");
        frase2 = frase2.replace(frase2.substring(0, 1), frase2.substring(0, 1).toLowerCase());
        return frase2;
    }
    
    public  static String convierteNombreTitulo(String frase) {//despues de cada "_" convierte la siguiente letra en mayuscula y remplaza los _ por espacios en blanco
        String frase2 = "";
        int indice = 0;
        int indicepunto = 0;

        indicepunto = frase.indexOf('_', indice);
        while (indicepunto >= 0) {
            // El primer carácter es en mayúsculas
            frase2 = frase2 + frase.substring(indice, indice + 1).toUpperCase();
            // Pegamos el resto hasta el primer punto      
            frase2 = frase2 + frase.substring(indice + 1, indicepunto + 1);

            indice = indicepunto + 1;
            indicepunto = frase.indexOf('_', indice);
        }
        // Incluimos el final de la frase
        frase2 = frase2 + frase.substring(indice, indice + 1).toUpperCase();
        frase2 = frase2 + frase.substring(indice + 1, frase.length());
        frase2 = frase2.replaceAll("_", " ");
        frase2 = frase2.replace(frase2.substring(0, 1), frase2.substring(0, 1).toLowerCase());
        return frase2;
    }


    public static String tipoDato(String tipo) {
        String texto;
        switch (tipo) {
            case "VARCHAR2":
                texto = "String";
                break;
            case "VARCHAR":
                texto = "String";
                break;
            case "CHAR":
                texto = "String";
                break;
            case "NCHAR":
                texto = "String";
                break;
            case "NVARCHAR":
                texto = "String";
                break;
            case "NUMBER":
                texto = "int";
                break;
            case "LONG":
                texto = "double";
                break;
            case "DATE":
                texto = "Date";
            default:
                texto = firstUpper(tipo);
                break;
        }
        return texto;
    }

    public static String tipoDatoSTM(String tipo) {
        String texto;
        switch (tipo) {
            case "VARCHAR2":
                texto = "String";
                break;
            case "VARCHAR":
                texto = "String";
                break;
            case "CHAR":
                texto = "String";
                break;
            case "NCHAR":
                texto = "String";
                break;
            case "NVARCHAR":
                texto = "String";
                break;
            case "NUMBER":
                texto = "Int";
                break;
            case "LONG":
                texto = "Double";
                break;
            case "DATE":
                texto = "Timestamp";
            default:
                texto = firstUpper(tipo);
                break;
        }
        return texto;
    }

    
}
