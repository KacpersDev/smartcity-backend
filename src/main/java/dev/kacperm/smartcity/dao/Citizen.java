package dev.kacperm.smartcity.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String firstName;
    private String lastName;
    private String[] middleNames;
    private String email;
    private String password;
    private int ssn;

    public Citizen() {

    }
}