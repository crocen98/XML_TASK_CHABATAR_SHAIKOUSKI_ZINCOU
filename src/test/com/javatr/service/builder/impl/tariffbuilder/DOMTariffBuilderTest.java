package com.javatr.service.builder.impl.tariffbuilder;

import com.javatr.entity.tariff.Tariff;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class DOMTariffBuilderTest {
  private static final DOMTariffBuilder domTariffBuilder = new DOMTariffBuilder();
  private static final String pathToXSD = "resources/xsd/tariffs.xsd";

  @Test
  public void shouldReturnValidInformation() throws IOServiceException, XMLParserServiceException {
    List<Tariff> tariffList = domTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),"resources/xml/tariffs_one.xml");
    Assert.assertEquals(tariffList.size(),17);
  }

  @Test
  public void allEntitiesShouldBeEqual(){

  }
}