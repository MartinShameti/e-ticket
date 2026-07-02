package org.internship.service;

import org.internship.entity.Citizen;
import org.internship.repository.CitizenRepository;
import java.util.List;

public class CitizenService {

    private final CitizenRepository citizenRepository;

    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public void createCitizen(Citizen citizen) {
        citizenRepository.save(citizen);
        System.out.println("Citizen created: " + citizen);
    }

    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }
}