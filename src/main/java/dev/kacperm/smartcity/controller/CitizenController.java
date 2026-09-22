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

    @PostMapping("/auth/register")
    public ResponseEntity<Citizen> registerCitizen(@RequestBody Citizen citizen) {
        Optional<Citizen>  optionalCitizen = citizenRepository.findByEmail(citizen.getEmail());

        if (optionalCitizen.isPresent()) {
            return ResponseEntity.badRequest().body(optionalCitizen.get());
        }

        Citizen createdCitizen = citizenRepository.save(citizen);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCitizen);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Citizen> loginCitizen(@RequestBody Citizen citizen) {
        Optional<Citizen> optionalCitizen = citizenRepository.findByEmail(citizen.getEmail());

        if (optionalCitizen.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // TODO hash passwords
        if (!citizen.getPassword().equals(optionalCitizen.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(optionalCitizen.get());
    }
}
