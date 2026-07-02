package org.internship;

import jakarta.persistence.EntityManagerFactory;
import org.internship.entity.*;
import org.internship.repository.*;
import org.internship.service.*;
import org.internship.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();

        PoliceService policeService = new PoliceService(new PoliceRepository(emf));
        CitizenService citizenService = new CitizenService(new CitizenRepository(emf));
        VehicleService vehicleService = new VehicleService(new VehicleRepository(emf));
        FineService fineService = new FineService(new FineRepository(emf));
        UserService userService = new UserService(new UserRepository(emf));

        // create officers
        Police p1 = new Police("John Smith", "B001");
        Police p2 = new Police("Jane Doe", "B002");
        policeService.createPolice(p1);
        policeService.createPolice(p2);

        // create citizens
        Citizen c1 = new Citizen("Alice", "Brown", "ID001");
        Citizen c2 = new Citizen("Bob", "Wilson", "ID002");
        Citizen c3 = new Citizen("Charlie", "Davis", "ID003");
        citizenService.createCitizen(c1);
        citizenService.createCitizen(c2);
        citizenService.createCitizen(c3);

        // register vehicles
        Vehicle v1 = new Vehicle("AA-111-BB");
        c1.addVehicle(v1);
        vehicleService.registerVehicle(v1);

        Vehicle v2 = new Vehicle("CC-222-DD");
        c2.addVehicle(v2);
        vehicleService.registerVehicle(v2);

        Vehicle v3 = new Vehicle("EE-333-FF");
        c3.addVehicle(v3);
        vehicleService.registerVehicle(v3);

        // create users
        userService.createUser(new User("admin", "admin123", Role.ADMIN));
        userService.createUser(new User("jsmith", "pass123", Role.OFFICER));
        userService.createUser(new User("alice", "pass456", Role.CITIZEN));

        System.out.println("-- Users --");
        userService.getAllUsers().forEach(System.out::println);

        // create fines
        Fine f1 = new Fine("Speeding", 100.0, v1, p1);
        Fine f2 = new Fine("Illegal Parking", 50.0, v1, p2);
        Fine f3 = new Fine("Running Red Light", 200.0, v2, p1);
        Fine f4 = new Fine("No Seatbelt", 75.0, v3, p2);
        fineService.createFine(f1);
        fineService.createFine(f2);
        fineService.createFine(f3);
        fineService.createFine(f4);

        // print all fines
        System.out.println("-- All fines --");
        fineService.getAllFines().forEach(System.out::println);

        // search by citizen
        System.out.println("-- Fines for Alice --");
        fineService.getFinesByCitizen(c1.getId()).forEach(System.out::println);

        // search by plate
        System.out.println("-- Fines for plate CC-222-DD --");
        fineService.getFinesByPlateNumber("CC-222-DD").forEach(System.out::println);

        // update reason
        fineService.updateFineReason(f1.getId(), "Excessive Speeding");

        // pay fine
        fineService.payFine(f1.getId());

        // try paying same fine again
        fineService.payFine(f1.getId());

        // cancel unpaid fine
        fineService.cancelFine(f2.getId());

        // try paying cancelled fine
        fineService.payFine(f2.getId());

        // final statuses
        System.out.println("-- Final statuses --");
        for (Fine f : fineService.getAllFines()) {
            System.out.println("Fine #" + f.getId() + " | " + f.getReason() + " | " + f.getStatus());
        }

        // shutdown
        HibernateUtil.shutdown();
        System.out.println("Done.");
    }
}