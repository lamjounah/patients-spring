package com.example.patient;

import com.example.patient.entite.Patient;
import com.example.patient.repositorises.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientApplication {

    public static void main(String[] args) {

        SpringApplication.run(PatientApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient( null,"hamza",new Date(),false,122));
            patientRepository.save(
                    new Patient( null,"nabil",new Date(),true,182));
            patientRepository.save(
                    new Patient( null,"mohamed",new Date(),false,302));
            patientRepository.save(
                    new Patient( null,"otaman",new Date(),true,162));
            patientRepository.findAll().forEach(p->
                    System.out.println(p.getNom()));
        };
    }
}
