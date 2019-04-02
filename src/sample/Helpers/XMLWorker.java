package sample.Helpers;

import sample.Entitys.AllUsers;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;


public class XMLWorker {

    public static void createXMLFile(AllUsers allUsers){
        try {
            JAXBContext context = JAXBContext.newInstance(AllUsers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(allUsers,new File("users.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
