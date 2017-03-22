/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

/**
 *
 * @author i111910530
 */
public class UsuarioBean {
   private String usuario;
   private String password;

    public UsuarioBean(){
    }//constructor
    
    public UsuarioBean(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
   
}
