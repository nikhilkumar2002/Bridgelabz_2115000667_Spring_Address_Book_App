package com.example.SpringAddressBookApp.model;

import com.example.SpringAddressBookApp.dto.ContactDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact3")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    // Constructor to convert ContactDTO to Contact
    public Contact(ContactDTO dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
    }
}
