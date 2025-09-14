package com.rao.EasySchool.repository;

import com.rao.EasySchool.model.Contact;
import com.rao.EasySchool.utilities.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContactRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert contactInsert;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.contactInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("CONTACT_MSG")
                .usingGeneratedKeyColumns("CONTACT_ID");
    }

    public int saveContactMsg(Contact contact, boolean simpleJdbcInsert) {
        if (simpleJdbcInsert) {
            Map<String, Object> params = new HashMap<>();
            params.put("NAME", contact.getName());
            params.put("MOBILE_NUM", contact.getMobileNum());
            params.put("EMAIL", contact.getEmail());
            params.put("SUBJECT", contact.getSubject());
            params.put("MESSAGE", contact.getMessage());
            params.put("STATUS", contact.getStatus());
            params.put("CREATED_AT", contact.getCreatedAt());
            params.put("CREATED_BY", contact.getCreatedBy());
            return contactInsert.executeAndReturnKey(params).intValue();
        } else {
            String sqlQuery = """
                    INSERT INTO CONTACT_MSG
                    (NAME, MOBILE_NUM, EMAIL, SUBJECT, MESSAGE, STATUS, CREATED_AT, CREATED_BY)
                    VALUES (?,?,?,?,?,?,?,?)""";
            return jdbcTemplate.update(sqlQuery, contact.getName(), contact.getMobileNum(), contact.getEmail(),
                    contact.getSubject(), contact.getMessage(), contact.getStatus(), contact.getCreatedAt(),
                    contact.getCreatedBy());
        }
    }

    public List<Contact> findMessagesWithStatus(String status) {
        String sqlQuery = "SELECT * FROM CONTACT_MSG WHERE STATUS = ?";
        return jdbcTemplate.query(
                sqlQuery,
                ps -> ps.setString(1, status),
                new ContactMapper());
    }

    public int updateMsgStatus(int contactId, String status, String updatedBy) {
        String sql = "UPDATE CONTACT_MSG SET STATUS = ?, UPDATED_BY = ?, UPDATED_AT =? WHERE CONTACT_ID = ?";
        return jdbcTemplate.update(
                sql,
                preparedStatement -> {
                    preparedStatement.setString(1, status);
                    preparedStatement.setString(2, updatedBy);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                    preparedStatement.setInt(4, contactId);
                });
    }
}
