package com.rao.EasySchool.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "holidays")
public class Holiday {

    public enum Type{
        FESTIVAL, FEDERAL
    }

//    private static final ObjectMapper mapper = new ObjectMapper();

    @Id
    private String day;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Type type;

//    @Override
//    public String toString() {
//        try {
//            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
