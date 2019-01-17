package com.javatr.service;

import com.javatr.service.builder.impl.tariffbuilder.DOMTariffBuilder;
import com.javatr.service.builder.impl.tariffbuilder.SAXTariffBuilder;
import com.javatr.service.builder.impl.tariffbuilder.StAXTariffBuilder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import org.xml.sax.SAXException;

public class ParserService {

  public static void main(String[] args)
      throws SAXException, IOServiceException, XMLParserServiceException {
    SAXTariffBuilder saxTariffBuilder = new SAXTariffBuilder();
    DOMTariffBuilder domTariffBuilder = new DOMTariffBuilder();
    StAXTariffBuilder stAXTariffBuilder = new StAXTariffBuilder();
    String pathToXSD = "resources/xsd/tariff.xsd";
    String pathToXML = "resources/xml/tariffs.xml";
    System.out.println(saxTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),pathToXML));
    System.out.println(domTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),pathToXML));
    System.out.println(saxTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),pathToXML));
  }
}
