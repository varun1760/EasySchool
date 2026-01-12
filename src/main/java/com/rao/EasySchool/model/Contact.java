package com.rao.EasySchool.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
@Data annotation is provided by Lombok library which generates getter, setter,
equals(), hashCode(), toString() methods & Constructor at compile time.
This makes our code short and clean.
* */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "contact_msg")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
})
@NamedQueries({
        @NamedQuery(name = "Contact.findOpenMsgs",
                query = "SELECT c FROM Contact c WHERE c.status = :status"),
        @NamedQuery(name = "Contact.updateMsgStatus",
                query = "UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Contact.findOpenMsgsNative",
                query = "SELECT * FROM contact_msg c WHERE c.status = :status"
                ,resultClass = Contact.class),
        @NamedNativeQuery(name = "Contact.findOpenMsgsNative.count",
                query = "select count(*) as cnt from contact_msg c where c.status = :status",
                resultSetMapping = "SqlResultSetMapping.count"),
        /*Spring Data JPA doesn’t support dynamic sorting for native queries.
        Doing that would require Spring Data to analyze the provided statement and generate
        the ORDER BY clause in the database-specific dialect. This would be a very complex operation
        and is currently not supported by Spring Data JPA.*/
        @NamedNativeQuery(name = "Contact.updateMsgStatusNative",
                query = "UPDATE contact_msg c SET c.status = ?1 WHERE c.contact_id = ?2")
})
public class Contact extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide valid email address")
    private String email;

    @NotBlank(message = "Subject must not be blank")
    @Size(min = 5, max = 20, message = "Subject must be between 5-20 characters long")
    private String subject;

    @NotBlank(message = "Message must not be blank")
    @Size(min = 10, message = "Message must be at least 10 characters long")
    private String message;

    private String status;

    private static final ObjectMapper mapper = new ObjectMapper();

//    @Override
//    public String toString() {
//        mapper.registerModule(new JavaTimeModule()); // this line is required
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        try {
//            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
