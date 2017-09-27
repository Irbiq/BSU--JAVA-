package utils;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

/**
 * <p>Class for marshaling</p>
 *
 * @author Maxim Karzhavin
 * @version 1.0
 */
public class JAXBParser implements utils.Parser {

    /**
     * <p>Method which create object by unmarshalling file</p>
     * @throws NullPointerException if <code>schema</code> is null.
     * @return ready object form xml
     */
    @Override
    public Object getObject(File file, Class c) throws JAXBException {


        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(file);
        return object;
    }

    /**
     * <ul>
     *     <li><p>Marshalling and saving object into xml file</p></li>
     *     <li><p>Uses schema to validate document {@link Schema}</p></li>
     * </ul>
     *
     * @param file in which object will be saved
     * @param o object which should be saved
     * @throws NullPointerException  if <code>schema</code> is null.
     */
    @Override
    public void saveObject(File file, Object o) throws JAXBException, SAXException {

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("utils/schema.xsd"));

        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setSchema(schema);

        marshaller.marshal(o,file);

    }
}
