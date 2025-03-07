package com.example.SpringAddressBookApp.service;

import com.example.SpringAddressBookApp.model.Contact;
import java.util.List;

public interface ContactService {
    Contact addContact(Contact contact);
    List<Contact> getAllContacts();
    Contact getContactById(Long id);
    Contact updateContact(Long id, Contact contact);
    void deleteContact(Long id);
}
