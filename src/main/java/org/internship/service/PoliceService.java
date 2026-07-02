package org.internship.service;

import org.internship.entity.Police;
import org.internship.repository.PoliceRepository;
import java.util.List;

public class PoliceService {

    private final PoliceRepository policeRepository;

    public PoliceService(PoliceRepository policeRepository) {
        this.policeRepository = policeRepository;
    }

    public void createPolice(Police police) {
        policeRepository.save(police);
        System.out.println("Officer created: " + police);
    }

    public List<Police> getAllPolice() {
        return policeRepository.findAll();
    }
}