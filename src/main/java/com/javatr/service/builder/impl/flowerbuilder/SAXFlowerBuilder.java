package com.javatr.service.builder.impl.flowerbuilder;
import com.javatr.entity.flower.Flower;
import com.javatr.service.builder.Builder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SAXFlowerBuilder implements Builder<Flower> {
        private final FlowerHandler flowerHandler = new FlowerHandler();

@Override
public List<Flower> build(XMLValidator validator, String pathToFile) throws XMLParserServiceException, IOServiceException {
                validator.validate(pathToFile);
        try {
                XMLReader reader ;
                reader = XMLReaderFactory.createXMLReader();
                reader.setContentHandler(flowerHandler);
                reader.parse(pathToFile);
        } catch (SAXException e ){
                throw  new XMLParserServiceException(e);
        }catch (IOException e){
                throw  new IOServiceException(e);
        }
        return flowerHandler.getFlowers();
        }


        public static void main(String ... args) throws XMLParserServiceException, IOServiceException {
                SAXFlowerBuilder builder = new SAXFlowerBuilder();
                System.out.println(builder.build(new XMLValidatorByXSD("resources/xsd/Flowers.xsd"),"resources/xml/flowers_five.xml").size());
                System.out.println(builder.build(new XMLValidatorByXSD("resources/xsd/Flowers.xsd"),"resources/xml/flowers_one.xml").size());

        }

}
