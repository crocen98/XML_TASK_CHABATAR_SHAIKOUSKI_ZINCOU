package com.javatr.service.builder.impl.tariffbuilder;

import com.javatr.entity.tariff.Billing;
import com.javatr.entity.tariff.Tariff;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TariffHandler extends DefaultHandler {

  private List<Tariff> tariffs = new ArrayList<>();
  private Tariff currentTariff;
  private TariffEnum tariffEnum;
  private EnumSet<TariffEnum>  tariffEnumSet = EnumSet.range(TariffEnum.NAME,TariffEnum.BILLING);

  public List<Tariff> getTariffs(){
    return  tariffs;
  }

  @Override
  public void startDocument() throws SAXException {
    tariffs = new ArrayList<>();
    currentTariff = null;
    tariffEnum = null;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes){
    if(TariffEnum.TARIFF.tagName.equals(localName)){
      currentTariff = new Tariff();
      currentTariff.setId(attributes.getValue(0));
      Optional<String> preciousnessStringAttribute = Optional.of(attributes.getValue(1));
      boolean isPreciousness = Boolean.parseBoolean(preciousnessStringAttribute.get());
      currentTariff.setPreciousness(isPreciousness);
    } else {
      TariffEnum temp = TariffEnum.valueOf(localName.toUpperCase());
      if(tariffEnumSet.contains(temp)){
        tariffEnum = temp;
      }
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if(TariffEnum.TARIFF.tagName.equals(localName)){
      tariffs.add(currentTariff);
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    String tagContent = new String(ch,start,length);
    tagContent = tagContent.trim();

    if(tariffEnum !=null){
      switch (tariffEnum){
        case NAME:
          currentTariff.setName(tagContent);
          break;
        case ID:
          currentTariff.setId(tagContent);
          break;
        case BILLING:
          currentTariff.setBilling(Billing.valueOf(tagContent.toUpperCase()));
          break;
        case PRECIOUSNESS:
          currentTariff.setPreciousness(Boolean.parseBoolean(tagContent));
          break;
        case OPERATORNAME:
          currentTariff.setOperatorName(tagContent);
          break;
        case PAYROLL:
          currentTariff.setPayroll(Integer.parseInt(tagContent));
          break;
        default:
          throw new EnumConstantNotPresentException(
              tariffEnum.getDeclaringClass(), tariffEnum.name());
      }
    }
    tariffEnum = null;
  }
}