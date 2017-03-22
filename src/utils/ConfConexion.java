/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import clases.Conexion;
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
public class ConfConexion {

    public ConfConexion() {

    }

    public void creaArchivo() throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "conexiones", null);

        Element raiz = document.getDocumentElement();
        Element nodoConexion = document.createElement("conexion"); //creamos un nuevo elemento
        nodoConexion.setAttribute("nombre", "conexion3c");
        raiz.appendChild(nodoConexion);
        Element nodoURL = document.createElement("url"); //creamos un nuevo elemento
        Text nodoValorCampo = document.createTextNode("jdbc:oracle:thin:@sigesa-desa.una.ac.cr:1521:DESA01"); //Ingresamos la info				
        nodoURL.appendChild(nodoValorCampo);
        nodoConexion.appendChild(nodoURL); //pegamos el elemento a la raiz "Documento"

        Element nodoUsuario = document.createElement("usuario"); //creamos un nuevo elemento
        nodoValorCampo = document.createTextNode("www_sigesa"); //Ingresamos la info				
        nodoUsuario.appendChild(nodoValorCampo);
        nodoConexion.appendChild(nodoUsuario); //pegamos el elemento a la raiz "Documento"

        Element nodoPsw = document.createElement("contrasena"); //creamos un nuevo elemento
        nodoValorCampo = document.createTextNode("sigesa"); //Ingresamos la info				
        nodoPsw.appendChild(nodoValorCampo);
        nodoConexion.appendChild(nodoPsw); //pegamos el elemento a la raiz "Documento"

        Source source = new DOMSource(document);
        Result result = new StreamResult(new java.io.File("jgeneconf.xml"));//nombre del archivo

        Transformer transformer;
        transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

    public ArrayList<Conexion> listaConexiones() {
        ArrayList<Conexion> listaConex = new ArrayList<Conexion>();
        try {
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            Document documento = creadorDocumento.parse("jgeneconf.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento.getDocumentElement();
            //Obtener la lista de nodos que tienen etiqueta "conexion"
            NodeList listaConexiones = raiz.getElementsByTagName("conexion");
            //Recorrer la lista de empleados
            for (int i = 0; i < listaConexiones.getLength(); i++) {
                //Obtener de la lista una conexion tras otra    
                Node conexionNodo = listaConexiones.item(i);
                //Obtener la lista de los datos que contiene esa conexion

                NodeList datosConexion = conexionNodo.getChildNodes();
                //Recorrer la lista de los datos que contiene la conexion
                Conexion conexion = new Conexion();
                conexion.setNombre(conexionNodo.getAttributes().getNamedItem("nombre").getNodeValue());
                for (int j = 0; j < datosConexion.getLength(); j++) {

                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosConexion.item(j);
                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if (dato.getNodeType() == Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();

                        switch (dato.getNodeName()) {
                            case "url":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    conexion.setURL(datoContenido.getNodeValue());
                                }
                                break;
                            case "usuario":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    conexion.setUsuario(datoContenido.getNodeValue());
                                }
                                break;
                            case "contrasena":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    conexion.setContrasena(datoContenido.getNodeValue());
                                    break;
                                }
                        }
                    }
                }
                listaConex.add(conexion);
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
        return listaConex;
    }

    public void nuevaConexion(Conexion conexion) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException, IOException {

        DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
        DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
        Document documento = creadorDocumento.parse("jgeneconf.xml");
        //Obtener el elemento raíz del documento
        Element raiz = documento.getDocumentElement();

        Element nodoConexion = documento.createElement("conexion"); //creamos un nuevo elemento
        nodoConexion.setAttribute("nombre", conexion.getNombre());
        raiz.appendChild(nodoConexion);
        Element nodoURL = documento.createElement("url"); //creamos un nuevo elemento
        Text nodoValorCampo = documento.createTextNode(conexion.getURL()); //Ingresamos la info				
        nodoURL.appendChild(nodoValorCampo);
        nodoConexion.appendChild(nodoURL); //pegamos el elemento a la raiz "Documento"

        Element nodoUsuario = documento.createElement("usuario"); //creamos un nuevo elemento
        nodoValorCampo = documento.createTextNode(conexion.getUsuario()); //Ingresamos la info				
        nodoUsuario.appendChild(nodoValorCampo);
        nodoConexion.appendChild(nodoUsuario); //pegamos el elemento a la raiz "Documento"

        Element nodoPsw = documento.createElement("contrasena"); //creamos un nuevo elemento
        nodoValorCampo = documento.createTextNode(conexion.getContrasena()); //Ingresamos la info				
        nodoPsw.appendChild(nodoValorCampo);
        nodoConexion.appendChild(nodoPsw); //pegamos el elemento a la raiz "Documento"

        Source source = new DOMSource(documento);
        Result result = new StreamResult(new java.io.File("jgeneconf.xml"));//nombre del archivo

        Transformer transformer;
        transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

    public void eliminarConexion(int conexion) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("jgeneconf.xml");

            // Get employee by tag name
            //use item(0) to get the first node with tage name "employee"
            Node conex = (Element) document.getElementsByTagName("conexion").item(conexion);
            conex.getParentNode().removeChild(conex);


            // write the DOM object to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File("jgeneconf.xml"));
            transformer.transform(domSource, streamResult);

            System.out.println("The XML File was ");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
    
     public void modificarConexion(Conexion conexion, int conexionIndex) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("jgeneconf.xml");

            // Get employee by tag name
            //use item(0) to get the first node with tage name "employee"
            Node nodoConexion = document.getElementsByTagName("conexion").item(conexionIndex);

            // update employee , set the id to 10        
            NamedNodeMap attribute = nodoConexion.getAttributes();
            Node nodeAttr = attribute.getNamedItem("nombre");
            nodeAttr.setTextContent(conexion.getNombre());           
            
            nodoConexion.getChildNodes().item(0).setTextContent(conexion.getURL());
            nodoConexion.getChildNodes().item(1).setTextContent(conexion.getUsuario());
            nodoConexion.getChildNodes().item(2).setTextContent(conexion.getContrasena());
            
                    
            // write the DOM object to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File("jgeneconf.xml"));
            transformer.transform(domSource, streamResult);

            System.out.println("The XML File was ");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

     

}
