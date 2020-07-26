package com.tumblbug.project.common.converters;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, Character> {
    @Override
    public Character convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? 'y' : 'n';
    }

    @Override
    public Boolean convertToEntityAttribute(Character dbData) {
        return dbData.equals('y');
    }
}
