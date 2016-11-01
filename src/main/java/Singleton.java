import model.Cinema;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by asus-pc on 01.11.2016.
 */
public class Singleton {

    public static Cinema getCinema() throws JAXBException {
        File file = new File("doc.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Cinema.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Cinema cinema = (Cinema) jaxbUnmarshaller.unmarshal(file);
        return cinema;
    }

    public static void saveToFile(Cinema cinema) throws JAXBException {
        File file = new File("doc.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Cinema.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(cinema, file);
        marshaller.marshal(cinema, System.out);
    }

}
