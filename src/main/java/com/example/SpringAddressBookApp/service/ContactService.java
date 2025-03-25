package com.example.SpringAddressBookApp.service;

import com.example.SpringAddressBookApp.dto.ContactDTO;
import com.example.SpringAddressBookApp.model.Contact;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

public interface ContactService {
    @CacheEvict(value = "contacts", allEntries = true)
    Contact addContact(ContactDTO contactDTO);

    Contact addContact(Contact contact);
    List<Contact> getAllContacts();
    Contact getContactById(Long id);
    Contact updateContact(Long id, Contact contact);

    @CachePut(value = "contacts", key = "#id")
    Contact updateContact(Long id, ContactDTO contactDTO);

    void deleteContact(Long id);
}
