package com.example.SpringAddressBookApp.controller;

import com.example.SpringAddressBookApp.model.Contact;
import com.example.SpringAddressBookApp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactRepository.save(contact), HttpStatus.CREATED);
    }
}
