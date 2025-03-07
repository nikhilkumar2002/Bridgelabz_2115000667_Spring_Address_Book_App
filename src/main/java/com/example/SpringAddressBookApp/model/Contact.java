package com.example.SpringAddressBookApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data  // Includes getters, setters, toString, etc.
@Table(name = "contact")
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
}
