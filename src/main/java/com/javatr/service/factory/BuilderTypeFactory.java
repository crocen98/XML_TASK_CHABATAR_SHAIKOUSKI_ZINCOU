package com.javatr.service.factory;

import com.javatr.service.builder.Builder;

public interface BuilderTypeFactory<T> {

    Builder<T> getBuilder(ParserType type);
}
