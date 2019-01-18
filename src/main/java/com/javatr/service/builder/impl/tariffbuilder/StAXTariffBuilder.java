package com.javatr.service.builder.impl.tariffbuilder;

import com.javatr.entity.tariff.Billing;
import com.javatr.entity.tariff.Tariff;
import com.javatr.service.builder.Builder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StAXTariffBuilder implements Builder<Tariff> {

  private XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

  @Override
  public List<Tariff> build(XMLValidator validator, String pathToFile)
      throws IOServiceException, XMLParserServiceException {
    List<Tariff> tariffs = new ArrayList<>();
    validator.validate(pathToFile);

    try (FileInputStream inputStream = new FileInputStream(new File(pathToFile))){
      XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(inputStream);
      addAllTariffsToList(reader,tariffs);
    } catch (IOException e) {
      throw new IOServiceException(e);
    } catch (XMLStreamException e) {
      throw new XMLParserServiceException(e);
    }
    return tariffs;
  }

  private void addAllTariffsToList(XMLStreamReader reader, List<Tariff> flowers) throws XMLStreamException {
    String name;
    while (reader.hasNext()) {
      int type = reader.next();
      if (type == XMLStreamConstants.START_ELEMENT) {
        name = reader.getLocalName();
        if (TariffEnum.valueOf(name.toUpperCase()) == TariffEnum.TARIFF) {
          Tariff tariff = buildTariff(reader);
          flowers.add(tariff);
        }
      }
    }
  }

  private Tariff buildTariff(XMLStreamReader reader) throws XMLStreamException {
    Tariff tariff = new Tariff();
    String id = reader.getAttributeValue(null, TariffEnum.ID.getTagName());
    tariff.setId(id);
    String name;
    while (reader.hasNext()) {
      int type = reader.next();

      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          name = reader.getLocalName();
          switch (TariffEnum.valueOf(name.toUpperCase())) {
            case NAME:
              tariff.setName(getXMLText(reader).trim());
              break;
            case OPERATORNAME:
              tariff.setOperatorName(getXMLText(reader).trim());
              break;
            case PAYROLL:
              name = getXMLText(reader).trim();
              int payroll = Integer.parseInt(name);
              tariff.setPayroll(payroll);
              break;
            case SMSPRICE:
              name = getXMLText(reader).trim();
              int smsprice = Integer.parseInt(name);
              tariff.setSmsPrice(smsprice);
              break;
            case BILLING:
              name = getXMLText(reader).trim();
              Billing billing = Billing.valueOf(name.toUpperCase());
              tariff.setBilling(billing);
              break;
            default:
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          name = reader.getLocalName();
          if (isStringEqualsTariffEnum(name,TariffEnum.TARIFF)) {
            return tariff;
          }
          break;
        default:
      }
    }
    throw  new XMLStreamException("Unknown element");
  }

  private boolean isStringEqualsTariffEnum(String tagName , TariffEnum tariffEnum){
    TariffEnum enumFromString = TariffEnum.valueOf(tagName.toUpperCase());
    return  enumFromString == tariffEnum;
  }

  private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
    String text = "";
    if (reader.hasNext()) {
      reader.next();
      text = reader.getText();
    }
    return text;
  }
}
