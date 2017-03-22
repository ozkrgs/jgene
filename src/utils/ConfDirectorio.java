/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import clases.Directorio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import org.xml.sax.SAXException;

/**
 *
 * @author sigesa
 */
public class ConfDirectorio {

    public ConfDirectorio() {

    }

   
    public ArrayList<Directorio> listaConexiones() {
        ArrayList<Directorio> listaDir = new ArrayList<Directorio>();
        try {
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            Document documento = creadorDocumento.parse("directoriosConfig.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento.getDocumentElement();
            //Obtener la lista de nodos que tienen etiqueta "conexion"
            NodeList listaDirectorios = raiz.getElementsByTagName("directorio");
            //Recorrer la lista de empleados
            for (int i = 0; i < listaDirectorios.getLength(); i++) {
                //Obtener de la lista una conexion tras otra    
                Node conexionNodo = listaDirectorios.item(i);
                //Obtener la lista de los datos que contiene esa conexion

                NodeList datosConexion = conexionNodo.getChildNodes();
                //Recorrer la lista de los datos que contiene la conexion
                Directorio directorio = new Directorio();                
                for (int j = 0; j < datosConexion.getLength(); j++) {                    
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosConexion.item(j);
                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if (dato.getNodeType() == Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();

                        switch (dato.getNodeName()) {
                            case "domain":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre(datoContenido.getNodeValue());
                                }
                                break;
                            case "repository":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre(datoContenido.getNodeValue());
                                }
                                break;
                            case "service":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre(datoContenido.getNodeValue());
                                    break;
                                }
                        }
                    }
                }
                listaDir.add(directorio);
            }
        } catch (SAXException ex) {
            System.out.println("ERROR: El formato XML del fichero no es correcto\n" + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Se ha producido un error el leer el fichero\n" + ex.getMessage());
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n" + ex.getMessage());
            ex.printStackTrace();
        }
        return listaDir;
    }

    
     

}
