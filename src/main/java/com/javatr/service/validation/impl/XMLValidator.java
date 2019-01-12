package com.javatr.service.validation.impl;

import com.javatr.service.validation.Validator;

/**
 * Validator for all XML files should get XSD in constructor
 */
public class XMLValidator implements Validator {

    public XMLValidator(String pathToXSDScheme) {
        /// add xsdScheme
    }

    @Override
    public boolean isValid(String pathToFile) {
        // should add validation b xsd
        return false;
    }
}
