package com.example.patient.repositorises;

import com.example.patient.entite.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

   public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient>  findByNomContains(String key , Pageable pageable);
}
