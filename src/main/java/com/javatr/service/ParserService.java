package com.javatr.service;

import com.javatr.service.builder.impl.tariffbuilder.DOMTariffBuilder;
import com.javatr.service.builder.impl.tariffbuilder.SAXTariffBuilder;
import com.javatr.service.builder.impl.tariffbuilder.StAXTariffBuilder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.impl.XMLValidatorByXSD;

public class ParserService {

  public static void main(String[] args)
      throws IOServiceException, XMLParserServiceException {
    SAXTariffBuilder saxTariffBuilder = new SAXTariffBuilder();
    DOMTariffBuilder domTariffBuilder = new DOMTariffBuilder();
    StAXTariffBuilder stAXTariffBuilder = new StAXTariffBuilder();
    String pathToXSD = "resources/xsd/tariffs.xsd";
    String pathToXML = "resources/xml/tariff_three.xml";
    String pathToXML2 = "resources/xml/tariffs_two.xml";
    System.out.println(domTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),pathToXML));
    System.out.println();
    System.out.println(stAXTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),pathToXML2));
  }
}
