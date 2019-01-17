package com.javatr.service.builder.impl.tariffbuilder;

import com.javatr.entity.tariff.Tariff;
import com.javatr.service.builder.Builder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import java.io.IOException;
import java.util.List;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXTariffBuilder implements Builder<Tariff> {

  private TariffHandler tariffHandler = new TariffHandler();

  @Override
  public List<Tariff> build(XMLValidator validator, String pathToFile)
      throws IOServiceException, XMLParserServiceException {
    validator.validate(pathToFile);
    try {
      XMLReader reader ;
      reader = XMLReaderFactory.createXMLReader();
      reader.setContentHandler(tariffHandler);
      reader.parse(pathToFile);
    } catch (SAXException e ){
      throw  new XMLParserServiceException(e);
    }catch (IOException e){
      throw  new IOServiceException(e);
    }
    return tariffHandler.getTariffs();
  }

}
