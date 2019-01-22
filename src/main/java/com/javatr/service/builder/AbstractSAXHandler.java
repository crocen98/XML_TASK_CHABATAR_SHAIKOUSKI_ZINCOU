package com.javatr.service.builder;

import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

public abstract class AbstractSAXHandler<T> extends DefaultHandler {
    public abstract List<T> getEntityList();
}
