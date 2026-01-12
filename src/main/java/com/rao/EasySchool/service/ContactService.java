package com.rao.EasySchool.service;

import com.rao.EasySchool.constants.GlobalConstant;
import com.rao.EasySchool.model.Contact;
import com.rao.EasySchool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//    public List<Contact> findMessageWithOpenStatus() {
//        return contactRepository.findByStatus(GlobalConstant.OPEN);
//    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(
                GlobalConstant.OPEN, pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        int rows = contactRepository.updateMsgStatusNative(GlobalConstant.CLOSE, contactId);
        if(rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
