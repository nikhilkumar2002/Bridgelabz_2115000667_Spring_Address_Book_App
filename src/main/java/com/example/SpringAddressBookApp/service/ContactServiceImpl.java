package com.example.SpringAddressBookApp.service;

import com.example.SpringAddressBookApp.dto.ContactDTO;
import com.example.SpringAddressBookApp.exception.ContactNotFoundException;
import com.example.SpringAddressBookApp.model.Contact;
import com.example.SpringAddressBookApp.repository.ContactRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @CacheEvict(value = "contacts", allEntries = true)
    @Override
    public Contact addContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        return contactRepository.save(contact);
    }

    @Override
    public Contact addContact(Contact contact) {
        return null;
    }

    @Override
    @Cacheable(value = "contacts")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    @Cacheable(value = "contacts", key = "#id")
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with ID: " + id));
    }

    @Override
    public Contact updateContact(Long id, Contact contact) {
        return null;
    }

    @Override
    @CachePut(value = "contacts", key = "#id")
    public Contact updateContact(Long id, ContactDTO contactDTO) {
        Contact existingContact = getContactById(id);
        existingContact.setName(contactDTO.getName());
        existingContact.setEmail(contactDTO.getEmail());
        existingContact.setPhone(contactDTO.getPhone());
        return contactRepository.save(existingContact);
    }

    @Override
    @CacheEvict(value = "contacts", key = "#id")
    public void deleteContact(Long id) {
        Contact contact = getContactById(id);
        contactRepository.delete(contact);
    }
}
