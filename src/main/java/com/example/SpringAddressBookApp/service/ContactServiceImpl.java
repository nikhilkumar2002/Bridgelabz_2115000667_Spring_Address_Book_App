package com.example.SpringAddressBookApp.service;

import com.example.SpringAddressBookApp.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ContactServiceImpl implements ContactService {

    private final List<Contact> contactList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Contact addContact(Contact contact) {
        contact.setId(idCounter.incrementAndGet());
        contactList.add(contact);
        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactList;
    }

    @Override
    public Contact getContactById(Long id) {
        return contactList.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Contact updateContact(Long id, Contact contact) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getId().equals(id)) {
                contact.setId(id);
                contactList.set(i, contact);
                return contact;
            }
        }
        return null;
    }

    @Override
    public void deleteContact(Long id) {
        contactList.removeIf(contact -> contact.getId().equals(id));
    }
}
