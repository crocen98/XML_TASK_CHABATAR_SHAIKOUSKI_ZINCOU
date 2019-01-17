package com.javatr.service.builder.impl.tariffbuilder;

public enum TariffEnum {
  TARIFFS("tariffs"),
  ID("id"),
  NAME("name"),
  TARIFF("tariff"),
  OPERATORNAME("operatorname"),
  PAYROLL("payroll"),
  BILLING("billing"),
  SMSPRICE("smsprice");

  String tagName;
  TariffEnum(String tagName){
    this.tagName = tagName;
  }

  public String getTagName() {
    return tagName;
  }
}
