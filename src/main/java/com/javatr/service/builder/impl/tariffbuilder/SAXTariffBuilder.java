package com.javatr.service.builder.impl.tariffbuilder;

import com.javatr.entity.tariff.Tariff;
import com.javatr.service.builder.AbstractSAXBuilder;
import com.javatr.service.builder.AbstractSAXHandler;

public class SAXTariffBuilder extends AbstractSAXBuilder<Tariff> {

  private AbstractSAXHandler<Tariff> handler = new TariffHandler();

  @Override
  public AbstractSAXHandler<Tariff> getEntityHandler() {
    return handler;
  }
}
