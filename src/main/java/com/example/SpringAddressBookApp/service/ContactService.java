package com.example.SpringAddressBookApp.service;

import com.example.SpringAddressBookApp.dto.ContactDTO;
import com.example.SpringAddressBookApp.model.Contact;
import com.example.SpringAddressBookApp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Contact addContact(ContactDTO contactDTO) {
        Contact contact = new Contact(contactDTO);
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, ContactDTO contactDTO) {
        Contact existingContact = getContactById(id);
        if (existingContact != null) {
            existingContact.setName(contactDTO.getName());
            existingContact.setEmail(contactDTO.getEmail());
            existingContact.setPhone(contactDTO.getPhone());
            return contactRepository.save(existingContact);
        }
        return null;
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
