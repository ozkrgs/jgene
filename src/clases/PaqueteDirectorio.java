/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sigesa
 */
public class PaqueteDirectorio {
    private String nombre;
    private List<Directorio> directorios;

    public PaqueteDirectorio() {
        directorios = new ArrayList<Directorio>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Directorio> getDirectorios() {
        return directorios;
    }

    public void setDirectorios(List<Directorio> directorios) {
        this.directorios = directorios;
    }
    
}
