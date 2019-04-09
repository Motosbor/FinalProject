package sample.Helpers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.*;
import java.io.*;


public class XMLWorker {

    private static File file = new File("table.xml");

    public static void createXMLFile(Object objects,String xslUrl){

        String url = xslUrl;

        try {

            JAXBContext context = JAXBContext.newInstance(objects.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders","<?xml-stylesheet type='text/xsl' href=\"" + url + "\" ?>");
            marshaller.marshal(objects,file);


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void desktopFile(){
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
