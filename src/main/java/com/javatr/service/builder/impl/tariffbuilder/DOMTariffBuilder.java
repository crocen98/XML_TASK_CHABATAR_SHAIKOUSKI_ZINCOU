package com.javatr.service.builder.impl.tariffbuilder;

import com.javatr.entity.tariff.Billing;
import com.javatr.entity.tariff.Tariff;
import com.javatr.service.builder.Builder;
import com.javatr.service.exception.IOServiceException;
import com.javatr.service.exception.XMLParserServiceException;
import com.javatr.service.validation.XMLValidator;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DOMTariffBuilder implements Builder<Tariff> {

  private final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

  @Override
  public List<Tariff> build(XMLValidator validator, String pathToFile)
      throws IOServiceException, XMLParserServiceException {
    Document doc;
    validator.validate(pathToFile);
    try {
      DocumentBuilder  docBuilder = documentBuilderFactory.newDocumentBuilder();
      doc = docBuilder.parse(pathToFile);
    } catch (SAXException | ParserConfigurationException e) {
      throw new XMLParserServiceException(e);
    } catch (IOException e) {
      throw  new IOServiceException(e);
    }
    List<Tariff> tariffs = new ArrayList<>();
    NodeList tariffsList = doc.getElementsByTagName("tariff");
    for(int i = 0; i < tariffsList.getLength(); ++i){
      Element tariffElement = (Element) tariffsList.item(i);
      Tariff tariff = buildTariff(tariffElement);
      tariffs.add(tariff);
    }
    return tariffs;
  }

  private Tariff buildTariff(Element tariffElement) {
    Tariff tariff = new Tariff();
    tariff.setName(getElementTextContent(tariffElement,"name"));
    tariff.setPayroll(Integer.parseInt(getElementTextContent(tariffElement,"payroll")));
    tariff.setOperatorName(getElementTextContent(tariffElement,"operatorname"));
    tariff.setId(tariffElement.getAttribute("id"));
    tariff.setPreciousness(Boolean.parseBoolean(tariffElement.getAttribute("preciousness").toString()));
    initBilling(tariffElement,tariff);
    return tariff;
  }

  private void initBilling(Element billingType, Tariff tariff){
    String billing = getElementTextContent(billingType,TariffEnum.BILLING.tagName);
    tariff.setBilling(Billing.valueOf(billing.toUpperCase()));
  }

  private static String getElementTextContent(Element element, String elementName) {
    NodeList nList = element.getElementsByTagName(elementName);
    Node node = nList.item(0);
    return node.getTextContent();
  }
}
