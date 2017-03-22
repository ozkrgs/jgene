/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author sigesa
 */
public class Conexion {
    private String nombre;
    private String URL;
    private String usuario;
    private String contrasena;
    

    public Conexion() {
        
        this.usuario = "";
        this.contrasena = "";
        this.URL = "";
        this.nombre="";
    }
    
    public Conexion(String usuario, String contrasena, String URL) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.URL = URL;
    }   
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
