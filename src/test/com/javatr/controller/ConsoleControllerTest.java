package com.javatr.controller;

import org.junit.Assert;
import org.junit.Test;


public class ConsoleControllerTest {
    private final  ConsoleController controller = new ConsoleController();

    @Test
    public void shouldGetValidRequestAndReturnStringOfListFlowers(){
        String testString = "[Flower{id=true, name='dandelion', origin=[Europ, Asia], generation=CUTTINGS, soil=SOD_PODZOL, stemColor='yellow', colorLeaves='green', averageSize=200, temperature=15.7, photophilous=false, watering=2016553454}, Flower{id=id2, name='Rose', origin=[Europ, South America, Asia, Australia, North America], generation=CUTTINGS, soil=GROUNDWATER, stemColor='light green', colorLeaves='dark green', averageSize=3000, temperature=24.0, photophilous=true, watering=2500}, Flower{id=id3, name='Вероника', origin=[Asia, Australia], generation=SEEDS, soil=GROUNDWATER, stemColor='light green', colorLeaves='dark green', averageSize=40000000, temperature=45.0, photophilous=true, watering=250}, Flower{id=id4, name='Loosestrife', origin=[Australia, North America], generation=SEEDS, soil=PODZOLIC, stemColor='light green', colorLeaves='dark green', averageSize=2000, temperature=24.0, photophilous=false, watering=500}, Flower{id=id5, name='Marigold', origin=[West Belarus, Poland], generation=SEEDS, soil=GROUNDWATER, stemColor='light green', colorLeaves='dark green', averageSize=300, temperature=24.0, photophilous=false, watering=250}, Flower{id=id6, name='Astra annual', origin=[Europ, South America, Australia, North America], generation=SEEDS, soil=PODZOLIC, stemColor='red', colorLeaves='dark green', averageSize=300, temperature=24.0, photophilous=false, watering=250}, Flower{id=id7, name='Arabis', origin=[Europ, South America, Australia, North America], generation=SEEDS, soil=PODZOLIC, stemColor='light green', colorLeaves='black', averageSize=300, temperature=24.0, photophilous=true, watering=250}, Flower{id=id8, name='lily', origin=[Europ, South America, Asia, Australia, North America], generation=SEEDS, soil=PODZOLIC, stemColor='light green', colorLeaves='dark green', averageSize=300, temperature=24.0, photophilous=false, watering=250}, Flower{id=id9, name='chrysanthemum', origin=[Europ, South America, Asia, Australia, North America], generation=SEEDS, soil=SOD_PODZOL, stemColor='light green', colorLeaves='dark green', averageSize=300, temperature=24.0, photophilous=false, watering=250}, Flower{id=id10, name='Rose', origin=[Europ, South America, Australia, North America], generation=SEEDS, soil=SOD_PODZOL, stemColor='light green', colorLeaves='dark green', averageSize=450, temperature=24.0, photophilous=false, watering=250}, Flower{id=id11, name='cactus', origin=[Europ, South America, Asia, Australia, North America], generation=SEEDS, soil=PODZOLIC, stemColor='light green', colorLeaves='dark green', averageSize=300, temperature=24.0, photophilous=false, watering=250}, Flower{id=id12, name='chamomile', origin=[Europ, South America, Asia, Australia, North America], generation=SEEDS, soil=PODZOLIC, stemColor='light green', colorLeaves='dark green', averageSize=300, temperature=24.0, photophilous=true, watering=250}, Flower{id=id13, name='cornflower', origin=[Europ, South America, Asia, Australia, North America], generation=SEEDS, soil=PODZOLIC, stemColor='light green', colorLeaves='dark green', averageSize=300, temperature=24.0, photophilous=false, watering=250}, Flower{id=id14, name='daisy', origin=[Europ, South America, Asia, Australia, North America], generation=SEEDS, soil=PODZOLIC, stemColor='light green', colorLeaves='dark green', averageSize=300, temperature=24.0, photophilous=false, watering=250}, Flower{id=false, name='ardedea', origin=[Europ, South America, Asia, Australia, North America], generation=SEEDS, soil=PODZOLIC, stemColor='light green', colorLeaves='dark green', averageSize=300, temperature=24.0, photophilous=false, watering=250}, Flower{id=id16, name='tulip', origin=[Europ, South America, Asia, Australia, North America], generation=SEEDS, soil=PODZOLIC, stemColor='light green', colorLeaves='dark green', averageSize=300, temperature=30.5, photophilous=false, watering=500}]";
        String request = "resources/xml/flowers_one.xml SAX PARSE_FLOWER";
        String resultString = controller.parseEntity(request);
        Assert.assertEquals(testString,resultString);
    }

@Test
    public void shouldGetValidRequestAndThrowErrorMessageBecauseNotValidXMLFile(){
        String request = "resources/xml/tariffs_five.xml DOM PARSE_TARIF";
        String errorTestMessage = "org.xml.sax.SAXParseException; systemId: file:///D:/java/projects/XML_TASK_ZINCOU_CHABATAR_SHAIKOUSKI/resources/xml/tariffs_five.xml; lineNumber: 8; columnNumber: 29; cvc-maxInclusive-valid: Value '654' is not facet-valid with respect to maxInclusive '200' for type 'smsprice'.";

        Assert.assertEquals(errorTestMessage,controller.parseEntity(request));
    }



    @Test
    public void shouldGetNotValidRequestAndThrowErrorMessage(){
        String request = "resources/xml/tariffs_five.xml DOM PARSETARIF";
        String errorTestMessage = "Error! you enter incorrect request";
        Assert.assertEquals(errorTestMessage,controller.parseEntity(request));
    }
}