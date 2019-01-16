package com.javatr.service.builder.impl.gembuilder;

import com.javatr.entity.gem.Gem;
import com.javatr.entity.gem.Preciousness;
import com.javatr.entity.gem.VisualParameters;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class GemHandler extends DefaultHandler {
    private List<Gem> gems;
    private Gem current;
    private VisualParameters visualParameters;
    private GemEnum currentEnum;
    private EnumSet<GemEnum> withText;

    public List<Gem> getGems() {
        return gems;
    }


    @Override
    public void startDocument() {
        gems = new ArrayList<>();
        withText = EnumSet.range(GemEnum.NAME, GemEnum.CUTTINGMETHOD);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if ("gem".equals(localName)) {
            current = new Gem();
            current.setId(attributes.getValue(0));
        } else if ("visualParameters".equals(localName)) {
            visualParameters = new VisualParameters();
        } else {
            GemEnum temp = GemEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if ("gem".equals(localName)) {
            gems.add(current);
        }else if ("visualParameters".equals(localName)){
            current.setVisualParameters(visualParameters);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case PRECIOUSNESS:
                    current.setPreciousness(Preciousness.fromValue(s));
                    break;
                case ORIGIN:
                    current.setOrigin(s);
                    break;
                case VALUE:
                    current.setValue(Double.parseDouble(s));
                    break;
                case COLOR:
                    visualParameters.setColor(s);
                    break;
                case TRANSPARENCY:
                    visualParameters.setTransparency(Double.parseDouble(s));
                    break;
                case CUTTINGMETHOD:
                    visualParameters.setCuttingMethod(Integer.parseInt(s));
                    break;
                default: throw new EnumConstantNotPresentException(
                        currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
    }
}
