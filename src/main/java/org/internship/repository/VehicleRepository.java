package org.internship.repository;

import jakarta.persistence.*;
import org.internship.entity.Vehicle;
import java.util.List;

public class VehicleRepository {
    private final EntityManagerFactory emf;

    public VehicleRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Vehicle vehicle) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(vehicle);
        em.getTransaction().commit();
        em.close();
    }


    public List<Vehicle> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Vehicle> list = em.createQuery("SELECT v FROM Vehicle v", Vehicle.class).getResultList();
        em.close();
        return list;
    }
}