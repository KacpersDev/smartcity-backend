package dev.kacperm.smartcity.controller;

import dev.kacperm.smartcity.dao.Citizen;
import dev.kacperm.smartcity.repository.CitizenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/citizen")
public class CitizenController {

    private final CitizenRepository citizenRepository;

    public CitizenController(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Citizen> findById(@PathVariable Long id) {
        Optional<Citizen> optionalCitizen = citizenRepository.findById(id);
        if (optionalCitizen.isPresent()) {
            return ResponseEntity.ok(optionalCitizen.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Citizen> createCitizen(@RequestBody Citizen citizen) {
        Citizen createdCitizen = citizenRepository.save(citizen);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCitizen);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Citizen> login(@RequestBody Citizen citizen) {}
}
