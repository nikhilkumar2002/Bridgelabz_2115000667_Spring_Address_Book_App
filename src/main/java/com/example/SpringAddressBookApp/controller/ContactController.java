package com.example.SpringAddressBookApp.controller;

import com.example.SpringAddressBookApp.dto.ContactDTO;
import com.example.SpringAddressBookApp.model.Contact;
import com.example.SpringAddressBookApp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Create a new contact
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody ContactDTO contactDTO) {
        Contact contact = new Contact(contactDTO);  // Convert DTO to Entity
        return new ResponseEntity<>(contactService.addContact(contact), HttpStatus.CREATED);
    }


    // Get all contacts
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // Get contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    // Update a contact
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        Contact updatedContact = contactService.updateContact(id, contactDTO);
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

    // Delete a contact
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>("Contact deleted successfully!", HttpStatus.NO_CONTENT);
    }
}
