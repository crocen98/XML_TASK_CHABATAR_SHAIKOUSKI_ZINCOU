package com.javatr.service.builder.impl.gembuilder;

import com.javatr.entity.gem.Gem;
import com.javatr.entity.gem.Preciousness;
import com.javatr.entity.gem.VisualParameters;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import com.javatr.service.validation.impl.XMLValidatorByXSD;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class StAXGemBuilderTest {
    private static final String[] pathToXML = new String[]{
            "resources/xml/gems_one.xml",
            "resources/xml/gems_two.xml",
            "resources/xml/gems_three.xml",
            "resources/xml/gems_four.xml",
            "resources/xml/gems_five.xml"};
    private static final  String PATH_TO_XSD = "resources/xsd/gems.xsd";
    private XMLValidator validator;
    private StAXGemBuilder stAXGemBuilder;
    @Before
    public void setUp() {
        stAXGemBuilder = new StAXGemBuilder();
        validator = new XMLValidatorByXSD(PATH_TO_XSD);
    }

    @Test
    public void buildFirstXML() throws IOServiceException, XMLParserServiceException {
        List<Gem> gems = stAXGemBuilder.build(validator,pathToXML[0]);
        assertEquals(16,gems.size());
    }
    @Test
    public void buildSecondXML() throws IOServiceException, XMLParserServiceException {
        List<Gem> gems = stAXGemBuilder.build(validator,pathToXML[1]);
        assertEquals(gems,new ArrayList<>(Collections.emptyList()));
    }
    @Test
    public void buildThirdXML() throws IOServiceException, XMLParserServiceException {
        List<Gem> gems = stAXGemBuilder.build(validator,pathToXML[2]);
        assertEquals("Onyx",gems.get(1).getName());
    }
    @Test(expected = XMLParserServiceException.class)
    public void buildFourthXML() throws IOServiceException, XMLParserServiceException {
        stAXGemBuilder.build(validator,pathToXML[3]);
    }
    @Test
    public void buildFifthXML() throws IOServiceException, XMLParserServiceException {
        List<Gem> gems = stAXGemBuilder.build(validator,pathToXML[4]);
        Gem gem1 = new Gem(new VisualParameters("vsmi",50.0,8),
                Preciousness.SEMIPRECIOUS, Arrays.asList("Ty"),14590.4349058039,"I16","AAAAA");
        Gem gem2 = new Gem(new VisualParameters("pqf",64.0,5),
                Preciousness.PRECIOUS, Arrays.asList("XTG","WGZZVK"),6521.7749058039,"Hg6FTm4L47LfLkD9z4Zs7","AAAAB");
        Gem gem3 = new Gem(new VisualParameters("Cwfehms",64.0,13),
                Preciousness.PRECIOUS, Arrays.asList("XpMx","NrU"),15553.4549058039,"M70c81O","AAAAC");
        Gem gem4 = new Gem(new VisualParameters("Efy",85.0,9),
                Preciousness.PRECIOUS, Arrays.asList("TE","NR"),1774.8549058039,"QTG","AAAAD");
        Gem gem5 = new Gem(new VisualParameters("vfk",47.0,7),
                Preciousness.SEMIPRECIOUS, Arrays.asList("FlMD","Gpd"),1711.4149058039,"MP1N3ix6m957YU754XpJ","AAAAE");
        List<Gem> expected = Arrays.asList(gem1,gem2,gem3,gem4,gem5);

        assertEquals(gems, expected);
    }

    @After
    public void tearDown() {
        stAXGemBuilder = null;
        validator = null;
    }
}