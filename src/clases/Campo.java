/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

public class Campo {
    
    private Boolean selected;
    private String nombreCampo;
    private String tipoCampo;
    private Integer tamanno;
    private String nombreAtributo;
    private String tipoAtributo;
    private String tipoRelacion;
    private String componente;
    private String etiqueta;
    

    public Campo() {
        this.selected=false;
        this.nombreCampo = "";
        this.tipoCampo = "";
        this.nombreAtributo = "";
        this.tipoAtributo = "";
        this.tipoRelacion = "";
        this.componente = "";
        this.tamanno=0;
        this.etiqueta="";
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombre) {
        this.nombreCampo = nombre;
    }

    public String getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(String tipo) {
        this.tipoCampo = tipo;
    }

    public String getNombreAtributo() {
        return nombreAtributo;
    }

    public void setNombreAtributo(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }

    public String getTipoAtributo() {
        return tipoAtributo;
    }

    public void setTipoAtributo(String tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public Integer getTamanno() {
        return tamanno;
    }

    public void setTamanno(Integer tamanno) {
        this.tamanno = tamanno;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    

}
