package com.rao.EasySchool.service;

import com.rao.EasySchool.constants.ContactConstant;
import com.rao.EasySchool.model.Contact;
import com.rao.EasySchool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(ContactConstant.OPEN);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy(ContactConstant.ANONYMOUS);
        log.info(contact.toString());
        int saveMsg = contactRepository.saveContactMsg(contact, true);
        if (saveMsg > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMessageWithOpenStatus() {
        return contactRepository.findMessagesWithStatus(ContactConstant.OPEN);
    }

    public boolean updateMsgStatus(int contactId, String updatedBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId, ContactConstant.CLOSE, updatedBy);
        if (result > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
