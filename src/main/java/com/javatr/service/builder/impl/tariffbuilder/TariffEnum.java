package com.javatr.service.builder.impl.tariffbuilder;

public enum TariffEnum {
  TARIFFS("tariffs"),
  ID("id"),
  PRECIOUSNESS("preciousness"),
  NAME("name"),
  TARIFF("tariff"),
  OPERATORNAME("operatorname"),
  PAYROLL("payroll"),
  BILLING("billing");

  String tagName;
  TariffEnum(String tagName){
    this.tagName = tagName;
  }

  public String getTagName() {
    return tagName;
  }
}
