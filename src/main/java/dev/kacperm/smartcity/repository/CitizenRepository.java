package dev.kacperm.smartcity.repository;

import dev.kacperm.smartcity.dao.Citizen;
import org.springframework.data.repository.CrudRepository;

public interface CitizenRepository extends CrudRepository<Citizen, Long> { }
