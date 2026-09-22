package dev.kacperm.smartcity.repository;

import dev.kacperm.smartcity.dao.Citizen;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CitizenRepository extends CrudRepository<Citizen, Long> {

    Optional<Citizen> findByEmail(String email);
}
