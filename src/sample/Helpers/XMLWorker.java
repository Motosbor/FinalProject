package sample.Helpers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;


public class XMLWorker {

    public static void createXMLFile(Object objects,String xslUrl){

        String url = xslUrl;

        try {

            JAXBContext context = JAXBContext.newInstance(objects.getClass());
            File file = new File("users.xml");
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
           // marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders","<?xml-stylesheet type='text/xsl' href=\"" + url + "\" ?>");
            marshaller.marshal(objects,file);




        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
