package com.example.SpringAddressBookApp.service;

import com.example.SpringAddressBookApp.model.Contact;
import com.example.SpringAddressBookApp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);  // ✅ Saves to MySQL
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();  // ✅ Retrieves from MySQL
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);  // ✅ Finds in MySQL
    }

    @Override
    public Contact updateContact(Long id, Contact contact) {
        Optional<Contact> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {
            Contact updatedContact = existingContact.get();
            updatedContact.setName(contact.getName());
            updatedContact.setEmail(contact.getEmail());
            updatedContact.setPhone(contact.getPhone());
            return contactRepository.save(updatedContact);  // ✅ Updates MySQL
        }
        return null;
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);  // ✅ Deletes from MySQL
    }
}
