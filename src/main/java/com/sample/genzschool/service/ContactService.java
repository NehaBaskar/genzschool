package com.sample.genzschool.service;

import com.sample.genzschool.Constants.GenZSchoolConstants;
import com.sample.genzschool.Model.Contact;
import com.sample.genzschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service

public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactService() {
        System.out.println("Contact Service Initiated");
    }

    public boolean saveContact(Contact contact){
        System.out.println("Entered ContactService....");
        boolean isSaved = false;
        contact.setStatus(GenZSchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId() > 0){
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findContactMsgswithStatusOpen() {
        List<Contact> contactMsgs = contactRepository.findByStatus(GenZSchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateStatus(int id) {
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(id);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(GenZSchoolConstants.CLOSE);
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy() != null){
            isUpdated = true;
        }
        return  isUpdated;
    }
}
