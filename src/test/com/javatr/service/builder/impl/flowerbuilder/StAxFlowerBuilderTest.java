package com.javatr.service.builder.impl.flowerbuilder;

import com.javatr.entity.flower.Flower;
import com.javatr.entity.flower.Generation;
import com.javatr.entity.flower.Soil;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class StAxFlowerBuilderTest {
  private static final StAxFlowerBuilder stAxFlowerBuilder = new StAxFlowerBuilder();
  private static final String PATH_TO_XSD = "resources/xsd/Flowers.xsd";

  @Test
  public void shouldReturnValidInformation() throws IOServiceException, XMLParserServiceException {
    List<Flower> tariffList = stAxFlowerBuilder.build(new XMLValidatorByXSD(PATH_TO_XSD),"resources/xml/flowers_one.xml");
    Assert.assertEquals(16,tariffList.size());
  }

  @Test
  public void allEntitiesShouldBeEqual() throws IOServiceException, XMLParserServiceException {
    Set<String> set = new HashSet<>();
    set.add("string");
    Flower firstFlower = new Flower("AAAAA", "string", set, Generation.valueOf("CUTTINGS"),
        Soil.valueOf("GROUNDWATER"),"string", "string",927727968,41.9839058039,true,1045562543);
    Flower secondFlower = new Flower("AAAAB", "string", set, Generation.valueOf("CUTTINGS"),
        Soil.valueOf("GROUNDWATER"),"string", "string",204255760,22.4101058039,false,111707804);
    Flower thirdFlower = new Flower("AAAAC", "string",set , Generation.valueOf("CUTTINGS"),
        Soil.valueOf("GROUNDWATER"),"string", "string",1026263108,117.1185058039,true,1011050333);
    set = new HashSet<>();
    set.add("string");
    set.add("anotherString");
    Flower foursFlower = new Flower("AAAAD", "string",set , Generation.valueOf("SEEDS"),
        Soil.valueOf("GROUNDWATER"),"string", "string",1041443669,76.8965058039,true,1475946183);

    List<Flower> flowers = new ArrayList<>(
        Arrays.asList(firstFlower,secondFlower,thirdFlower,foursFlower));
    List<Flower> flowersListFromFile = stAxFlowerBuilder.build(new XMLValidatorByXSD(PATH_TO_XSD),"resources/xml/flowers_five.xml");

    Assert.assertEquals(flowersListFromFile,flowers);
  }

  @Test(expected = XMLParserServiceException.class)
  public void shouldThrowExceptionIfFileNotValid()
      throws IOServiceException, XMLParserServiceException {
    stAxFlowerBuilder.build(new XMLValidatorByXSD(PATH_TO_XSD),"resources/xml/flowers_two.xml");
  }

  @Test
  public void shouldReturnEmptyCollection() throws IOServiceException, XMLParserServiceException {
    List<Flower> flowerListFromFile = stAxFlowerBuilder.build(new XMLValidatorByXSD(PATH_TO_XSD),"resources/xml/flowers_four.xml");
    Assert.assertEquals(flowerListFromFile,new ArrayList<>(Collections.emptyList()));
  }

  @Test
  public void shouldTrimAllStrings() throws IOServiceException, XMLParserServiceException {
    List<Flower> flowerListFromFile = stAxFlowerBuilder.build(new XMLValidatorByXSD(PATH_TO_XSD),"resources/xml/flowers_three.xml");
    Flower flowerFromFile = flowerListFromFile.get(0);
    Assert.assertEquals("dandelion", flowerFromFile.getName());
  }
}