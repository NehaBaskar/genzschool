package com.sample.genzschool.service;

import com.sample.genzschool.Constants.GenZSchoolConstants;
import com.sample.genzschool.Model.Contact;
import com.sample.genzschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Contact> findContactMsgswithStatusOpen(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum-1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatusWithQuery(GenZSchoolConstants.OPEN, pageable);
        return msgPage;
    }

    public boolean updateStatus(int id) {
        boolean isUpdated = false;
        /*Optional<Contact> contact = contactRepository.findById(id);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(GenZSchoolConstants.CLOSE);
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy() != null){
            isUpdated = true;
        }  */
        int rows = contactRepository.updateStatusById(GenZSchoolConstants.CLOSE, id);
        if(rows > 0){
            isUpdated = true;
        }
        return  isUpdated;
    }
}
