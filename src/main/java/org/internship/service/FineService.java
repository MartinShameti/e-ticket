package org.internship.service;

import org.internship.entity.Fine;
import org.internship.entity.FineStatus;
import org.internship.repository.FineRepository;

import java.util.List;

public class FineService {

    private final FineRepository fineRepository;

    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public void createFine(Fine fine) {
        fineRepository.save(fine);
        System.out.println("Fine created: " + fine);
    }

    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    public List<Fine> getFinesByCitizen(Long citizenId) {
        return fineRepository.findByCitizenId(citizenId);
    }

    public List<Fine> getFinesByPlateNumber(String plateNumber) {
        return fineRepository.findByPlateNumber(plateNumber);
    }

    public void updateFineReason(Long fineId, String newReason) {
        Fine fine = fineRepository.findById(fineId);
        if (fine != null) {
            fine.setReason(newReason);
            fineRepository.update(fine);
            System.out.println("Fine reason updated: " + fine);
        } else {
            System.out.println("Fine not found with id: " + fineId);
        }
    }

    public void payFine(Long fineId) {
        Fine fine = fineRepository.findById(fineId);
        if (fine == null) {
            System.out.println("Fine not found with id: " + fineId);
            return;
        }
        if (fine.getStatus() == FineStatus.PAID) {
            System.out.println("Fine is already PAID. Cannot pay again.");
            return;
        }
        if (fine.getStatus() == FineStatus.CANCELLED) {
            System.out.println("Fine is CANCELLED. Cannot pay a cancelled fine.");
            return;
        }
        fine.setStatus(FineStatus.PAID);
        fineRepository.update(fine);
        System.out.println("Fine paid successfully: " + fine);
    }

    public void cancelFine(Long fineId) {
        Fine fine = fineRepository.findById(fineId);
        if (fine == null) {
            System.out.println("Fine not found with id: " + fineId);
            return;
        }
        if (fine.getStatus() == FineStatus.PAID) {
            System.out.println("Fine is already PAID. Cannot cancel.");
            return;
        }
        fine.setStatus(FineStatus.CANCELLED);
        fineRepository.update(fine);
        System.out.println("Fine cancelled: " + fine);
    }
}