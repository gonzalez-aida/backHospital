package com.hospital.hospital.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Converter
public class ListToStringConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return attribute == null ? "" : String.join(",", attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(dbData.split(",")));
    }
}