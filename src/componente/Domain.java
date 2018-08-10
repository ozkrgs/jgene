package componente;

import clases.Campo;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;
import utils.Utilitarios;

/**
 *
 * @author sigesa
 */
public class Domain {

    public void GenerarEntidad(String nombreEntidad, String directorio, String paquete, String nombreTabla, ArrayList<Campo> listaCampos) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        String ruta = "";
        try {
            ruta = directorio + nombreEntidad + ".java";
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.println("package " + paquete + ".domain;");
            pw.println("import cr.ac.una.cgi.sdkuna.domain.CampoConfigurable;");
            pw.println("import cr.ac.una.cgi.sdkuna.domain.Usuario;");
            pw.println("import cr.ac.una.cgi.sdkuna.generic.BaseEntity;");
            pw.println("import javax.persistence.AttributeOverride;");
            pw.println("import javax.persistence.Column;");
            pw.println("import javax.persistence.Entity;");
            pw.println("import javax.persistence.SequenceGenerator;");
            pw.println("import javax.persistence.Table;");
            pw.println("import javax.persistence.FetchType;");
            pw.println("import javax.persistence.JoinColumn;");
            pw.println("import javax.persistence.ManyToOne;");
            pw.println("import javax.persistence.OneToOne;");
            pw.println("import javax.persistence.OneToMany;");
            pw.println("import javax.persistence.Transient;");
            pw.println("@Entity");
            pw.println("@Table(name = \"" + nombreTabla + "\")");
            pw.println("@AttributeOverride(name = \"id\", column =@Column(name = \"ID_" + nombreTabla + "\"))");
            pw.println("@SequenceGenerator(name = \"sequence\", sequenceName = \"SQ_" + nombreTabla + "\", allocationSize = 1)");
            
            pw.println("");

            pw.println("public class " + nombreEntidad + " extends BaseEntity<Usuario, CampoConfigurable> {");
            if (!listaCampos.isEmpty()) {
                Iterator iterador = listaCampos.listIterator(); //Le solicito a la lista que me devuelva un iterador con todos los el elementos contenidos en ella
                while (iterador.hasNext()) {
                    Campo c = (Campo) iterador.next(); //Obtengo el elemento contenido                     

                    switch (c.getTipoRelacion()) {
                        case "@OneToOne":
                            pw.println("@OneToOne");
                            pw.println("@JoinColumn(name=\"" + c.getNombreCampo().toUpperCase() + "\")");
                            pw.println("private " + c.getTipoAtributo() + " " + c.getNombreAtributo() + ";");
                            break;
                        case "@OneToMany":
                            pw.println("@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)");
                            pw.println("@JoinColumn(name = \"" + nombreTabla.toUpperCase() + "\", nullable = false)");
                            pw.println("private List<" + c.getTipoAtributo() + "> " + c.getNombreAtributo() + ";");
                            break;
                        case "@ManyToOne":
                            pw.println("@ManyToOne");
                            pw.println("@JoinColumn(name=\"" + c.getNombreCampo().toUpperCase() + "\")");
                            pw.println("private " + c.getTipoAtributo() + " " + c.getNombreAtributo() + ";");
                            break;
                        case "@ManyToOne(Mestro)":
                            pw.println("@ManyToOne(fetch = FetchType.LAZY, optional = false)");
                            pw.println("@JoinColumn(name=\"" + c.getNombreCampo().toUpperCase() + "\",referencedColumnName=\"ID_" + c.getNombreCampo().toUpperCase() + "\",nullable = false,insertable = false,updatable = false)");
                            pw.println("private " + c.getTipoAtributo() + " " + c.getNombreAtributo() + ";");
                            break;
                        case "@Transient":
                            pw.println("@Transient");
                            pw.println("private " + c.getTipoAtributo() + " " + c.getNombreAtributo() + ";");
                            break;
                        default:
                            pw.println("@Column(name=\"" + c.getNombreCampo().toUpperCase() + "\")");
                            pw.println("private " + c.getTipoAtributo() + " " + c.getNombreAtributo() + ";");
                            break;
                    }
                    pw.println("");
                }
                pw.println("");
                pw.println("public " + nombreEntidad + "() {}");//
                pw.println("");
                iterador = listaCampos.listIterator();
                while (iterador.hasNext()) {
                    Campo c = (Campo) iterador.next(); //Obtengo el elemento contenido                     
                    pw.println("public " + c.getTipoAtributo() + " get" + Utilitarios.firstLetterUpper(c.getNombreAtributo()) + "(){");
                    pw.println("return " + c.getNombreAtributo() + ";");
                    pw.println("}");
                    pw.println("");
                    pw.println("public void set" + Utilitarios.firstLetterUpper(c.getNombreAtributo()) + "(" + c.getTipoAtributo() + " " + c.getNombreAtributo() + "){");
                    pw.println("this." + c.getNombreAtributo() + "=" + c.getNombreAtributo() + ";");
                    pw.println("}");
                    pw.println("");
                }
            }
            pw.println("}");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, e2.toString());
            }
        }
    }

}
