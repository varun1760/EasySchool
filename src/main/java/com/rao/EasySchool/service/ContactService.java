package com.rao.EasySchool.service;

import com.rao.EasySchool.constants.GlobalConstant;
import com.rao.EasySchool.model.Contact;
import com.rao.EasySchool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(GlobalConstant.OPEN);
        log.info(contact.toString());
        Contact savedContact = contactRepository.save(contact);
        if (savedContact.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMessageWithOpenStatus() {
        return contactRepository.findByStatus(GlobalConstant.OPEN);
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        Optional<Contact> contactData = contactRepository.findById(contactId);
        contactData.ifPresent(contact -> {
            contact.setStatus(GlobalConstant.CLOSE);
        });
        Contact updateContact = contactRepository.save(contactData.get());
        if (updateContact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
