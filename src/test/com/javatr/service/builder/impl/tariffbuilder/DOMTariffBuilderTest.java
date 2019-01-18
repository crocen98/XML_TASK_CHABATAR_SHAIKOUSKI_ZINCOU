package com.javatr.service.builder.impl.tariffbuilder;

import com.javatr.entity.tariff.Billing;
import com.javatr.entity.tariff.Tariff;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class DOMTariffBuilderTest {
  private static final DOMTariffBuilder domTariffBuilder = new DOMTariffBuilder();
  private static final String pathToXSD = "resources/xsd/tariffs.xsd";

  @Test
  public void shouldReturnValidInformation() throws IOServiceException, XMLParserServiceException {
    List<Tariff> tariffList = domTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),"resources/xml/tariffs_one.xml");
    Assert.assertEquals(17,tariffList.size());
  }

  @Test
  public void allEntitiesShouldBeEqual() throws IOServiceException, XMLParserServiceException {
    Tariff firstTariff = new Tariff("AAAAA", "string", "operator",Billing.valueOf("MINUTE"),138,66);
    Tariff secondTariff = new Tariff("AAAAB", "coolTariff", "coolOperator",Billing.valueOf("TWELVE_SECOND"),42,69);
    Tariff thirdTariff = new Tariff("AAAAC", "AnotherCoolName", "rer",Billing.valueOf("TWELVE_SECOND"),78,152);
    Tariff foursTariff = new Tariff("AAAAD", "kgpig", "poipoi",Billing.valueOf("TWELVE_SECOND"),82,188);
    Tariff fivesTariff = new Tariff("AAAAE", "string", "string",Billing.valueOf("MINUTE"),119,180);
    List<Tariff> tariffs = new ArrayList<>(Arrays.asList(firstTariff,secondTariff,thirdTariff,foursTariff,fivesTariff));
    List<Tariff> tariffListFromFile = domTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),"resources/xml/tariffs_four.xml");
    Assert.assertEquals(tariffListFromFile,tariffs);
  }

  @Test(expected = XMLParserServiceException.class)
  public void shouldThrowExceptionIfFileNotValid()
      throws IOServiceException, XMLParserServiceException {
    List<Tariff> tariffListFromFile = domTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),"resources/xml/tariffs_five.xml");
  }

  @Test
  public void shouldReturnEmptyCollection() throws IOServiceException, XMLParserServiceException {
    List<Tariff> tariffListFromFile = domTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),"resources/xml/tariff_three.xml");
    Assert.assertEquals(tariffListFromFile,new ArrayList<>(Collections.emptyList()));
  }

  @Test
  public void shouldTrimAllStrings() throws IOServiceException, XMLParserServiceException {
    List<Tariff> tariffListFromFile = domTariffBuilder.build(new XMLValidatorByXSD(pathToXSD),"resources/xml/tariffs_two.xml");
    Tariff tariffFromFile = tariffListFromFile.get(0);
    Assert.assertTrue(tariffFromFile.getName().equals("der"));
  }


}