package com.rao.EasySchool.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Holiday {

    public enum Type{
        FESTIVAL, FEDERAL
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    private final String day;
    private final String reason;
    private final Type type;

    @Override
    public String toString() {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
