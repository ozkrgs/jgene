/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import clases.Directorio;
import clases.PaqueteDirectorio;
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

    public ArrayList<PaqueteDirectorio> listaPaqueteDirectorio() {
        ArrayList<PaqueteDirectorio> ListaPaqueteDirectorios = new ArrayList<>();
        
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
                                
                PaqueteDirectorio paqueteDirectorio = new PaqueteDirectorio();
                
                paqueteDirectorio.setNombre(conexionNodo.getAttributes().getNamedItem("nombre").getNodeValue());

                ArrayList<Directorio> directorios = new ArrayList<>();
                for (int j = 0; j < datosConexion.getLength(); j++) {

                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosConexion.item(j);
                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if (dato.getNodeType() == Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        Directorio directorio = new Directorio();
                        switch (dato.getNodeName()) {
                            case "domain":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre("domain");
                                    directorio.setRuta(datoContenido.getNodeValue());
                                }
                                break;
                            case "repository":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre("repository");
                                    directorio.setRuta(datoContenido.getNodeValue());
                                }
                                break;
                            case "service":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre("service");
                                    directorio.setRuta(datoContenido.getNodeValue());
                                    break;
                                }
                            case "bean":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre("bean");
                                    directorio.setRuta(datoContenido.getNodeValue());
                                    break;
                                }
                            case "LOVBean":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre("LOVBean");
                                    directorio.setRuta(datoContenido.getNodeValue());
                                    break;
                                }
                            case "editForm":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre("editForm");
                                    directorio.setRuta(datoContenido.getNodeValue());
                                    break;
                                }
                            case "listForm":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre("listForm");
                                    directorio.setRuta(datoContenido.getNodeValue());
                                    break;
                                }
                            case "SQL":
                                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                                    directorio.setNombre("SQL");
                                    directorio.setRuta(datoContenido.getNodeValue());
                                    break;
                                }                                
                        }
                        directorios.add(directorio);
                    }

                }
                paqueteDirectorio.setDirectorios(directorios);
               ListaPaqueteDirectorios.add(paqueteDirectorio);
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

        return ListaPaqueteDirectorios;
    }
    
    public void nuevoPaqueteDirectorio(PaqueteDirectorio paqueteDirectorio) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException, IOException {

        DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
        DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
        Document documento = creadorDocumento.parse("directoriosConfig.xml");
        //Obtener el elemento raíz del documento
        Element raiz = documento.getDocumentElement();

        Element nodoDirectorio = documento.createElement("directorio"); //creamos un nuevo elemento
        nodoDirectorio.setAttribute("nombre", paqueteDirectorio.getNombre());
        raiz.appendChild(nodoDirectorio);
        
        Element nodo = documento.createElement("domain"); //creamos un nuevo elemento
        Text nodoValorCampo = documento.createTextNode(paqueteDirectorio.getDirectorios().get(0).getRuta()); //Ingresamos la info				
        nodo.appendChild(nodoValorCampo);
        nodoDirectorio.appendChild(nodo); 
        
        nodo = documento.createElement("repository"); //creamos un nuevo elemento
        nodoValorCampo = documento.createTextNode(paqueteDirectorio.getDirectorios().get(1).getRuta()); //Ingresamos la info				
        nodo.appendChild(nodoValorCampo);
        nodoDirectorio.appendChild(nodo); 

        nodo = documento.createElement("service"); //creamos un nuevo elemento
        nodoValorCampo = documento.createTextNode(paqueteDirectorio.getDirectorios().get(2).getRuta()); //Ingresamos la info				
        nodo.appendChild(nodoValorCampo);
        nodoDirectorio.appendChild(nodo); 

        nodo = documento.createElement("bean"); //creamos un nuevo elemento
        nodoValorCampo = documento.createTextNode(paqueteDirectorio.getDirectorios().get(3).getRuta()); //Ingresamos la info				
        nodo.appendChild(nodoValorCampo);
        nodoDirectorio.appendChild(nodo); 

        nodo = documento.createElement("LOVBean"); //creamos un nuevo elemento
        nodoValorCampo = documento.createTextNode(paqueteDirectorio.getDirectorios().get(4).getRuta()); //Ingresamos la info				
        nodo.appendChild(nodoValorCampo);
        nodoDirectorio.appendChild(nodo); 

        nodo = documento.createElement("listForm"); //creamos un nuevo elemento
        nodoValorCampo = documento.createTextNode(paqueteDirectorio.getDirectorios().get(5).getRuta()); //Ingresamos la info				
        nodo.appendChild(nodoValorCampo);
        nodoDirectorio.appendChild(nodo); 

        nodo = documento.createElement("editForm"); //creamos un nuevo elemento
        nodoValorCampo = documento.createTextNode(paqueteDirectorio.getDirectorios().get(6).getRuta()); //Ingresamos la info				
        nodo.appendChild(nodoValorCampo);
        nodoDirectorio.appendChild(nodo); 

        nodo = documento.createElement("SQL"); //creamos un nuevo elemento
        nodoValorCampo = documento.createTextNode(paqueteDirectorio.getDirectorios().get(7).getRuta()); //Ingresamos la info				
        nodo.appendChild(nodoValorCampo);
        nodoDirectorio.appendChild(nodo); 

        Source source = new DOMSource(documento);
        Result result = new StreamResult(new java.io.File("directoriosConfig.xml"));//nombre del archivo

        Transformer transformer;
        transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }
    
    public void eliminarPaqueteDirectorio(int indice) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("directoriosConfig.xml");

            // Get employee by tag name
            //use item(0) to get the first node with tage name "employee"
            Node conex = (Element) document.getElementsByTagName("directorio").item(indice);
            conex.getParentNode().removeChild(conex);


            // write the DOM object to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File("directoriosConfig.xml"));
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
    
  public void modificarPaqueteDirectorio(PaqueteDirectorio paqueteDirectorio, int conexionIndex) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("directoriosConfig.xml");

            // Get employee by tag name
            //use item(0) to get the first node with tage name "employee"
            Node nodoConexion = document.getElementsByTagName("directorio").item(conexionIndex);

            // update employee , set the id to 10        
            NamedNodeMap attribute = nodoConexion.getAttributes();
            Node nodeAttr = attribute.getNamedItem("nombre");
            nodeAttr.setTextContent(paqueteDirectorio.getNombre());           
            
            nodoConexion.getChildNodes().item(0).setTextContent(paqueteDirectorio.getDirectorios().get(0).getRuta());
            nodoConexion.getChildNodes().item(1).setTextContent(paqueteDirectorio.getDirectorios().get(1).getRuta());
            nodoConexion.getChildNodes().item(2).setTextContent(paqueteDirectorio.getDirectorios().get(2).getRuta());
            nodoConexion.getChildNodes().item(3).setTextContent(paqueteDirectorio.getDirectorios().get(3).getRuta());
            nodoConexion.getChildNodes().item(4).setTextContent(paqueteDirectorio.getDirectorios().get(4).getRuta());
            nodoConexion.getChildNodes().item(5).setTextContent(paqueteDirectorio.getDirectorios().get(5).getRuta());
            nodoConexion.getChildNodes().item(6).setTextContent(paqueteDirectorio.getDirectorios().get(6).getRuta());
            nodoConexion.getChildNodes().item(7).setTextContent(paqueteDirectorio.getDirectorios().get(7).getRuta());            
                    
            // write the DOM object to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File("directoriosConfig.xml"));
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
