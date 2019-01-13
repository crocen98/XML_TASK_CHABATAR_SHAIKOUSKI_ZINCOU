package com.javatr.service.builder.impl.flowerbuilder;

import com.javatr.entity.flower.Flower;
import com.javatr.service.builder.Builder;
import com.javatr.service.validation.XMLValidator;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SAXFlowerBuilder implements Builder<Flower> {
        private static final Logger LOGGER = LogManager.getLogger(SAXFlowerBuilder.class);


        private FlowerHandler flowerHandler = new FlowerHandler();
        private XMLReader reader ;

        public SAXFlowerBuilder() throws SAXException {
                try {
                        reader = XMLReaderFactory.createXMLReader();
                        reader.setContentHandler(flowerHandler);
                } catch (SAXException e){
                        LOGGER.error(e);
                        throw e;
                }
        }
@Override
public List<Flower> build(XMLValidator validator, String pathToFile) throws IOException, SAXException {
        validator.validate(pathToFile);
        reader.parse(pathToFile);
        return flowerHandler.getFlowers();
        }


        public static void main(String ... args) throws SAXException, IOException {
                SAXFlowerBuilder builder = new SAXFlowerBuilder();
                System.out.println(builder.build(new XMLValidatorByXSD("resources/xsd/Flowers.xsd"),"resources/xml/Flowers.xml"));


                System.out.println(builder.build(new XMLValidatorByXSD("resources/xsd/Flowers.xsd"),"resources/xml/Flowers.xml"));

        }

}
