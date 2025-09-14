package com.rao.EasySchool.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class Holiday {

    public enum Type{
        FESTIVAL, FEDERAL
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    private final String day;
    private final String reason;
    private final Type type;

    public Holiday(String day, String reason, Type type) {
        this.day = day;
        this.reason = reason;
        this.type = type;
    }

    @Override
    public String toString() {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
