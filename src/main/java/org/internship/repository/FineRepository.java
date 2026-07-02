package org.internship.repository;

import jakarta.persistence.*;
import org.internship.entity.Fine;
import java.util.List;

public class FineRepository {

    private final EntityManagerFactory emf;

    public FineRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public void save(Fine fine) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(fine);
        em.getTransaction().commit();
        em.close();
    }

    public Fine findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Fine fine = em.find(Fine.class, id);
        em.close();
        return fine;
    }
    public List<Fine> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Fine> list = em.createQuery("SELECT f FROM Fine f", Fine.class).getResultList();
        em.close();
        return list;
    }
    public List<Fine> findByCitizenId(Long citizenId) {
        EntityManager em = emf.createEntityManager();
        List<Fine> list = em.createQuery(
                        "SELECT f FROM Fine f WHERE f.vehicle.citizen.id = :citizenId", Fine.class)
                .setParameter("citizenId", citizenId)
                .getResultList();
        em.close();
        return list;
    }
    public List<Fine> findByPlateNumber(String plateNumber) {
        EntityManager em = emf.createEntityManager();
        List<Fine> list = em.createQuery(
                        "SELECT f FROM Fine f WHERE f.vehicle.plateNumber = :plate", Fine.class)
                .setParameter("plate", plateNumber)
                .getResultList();
        em.close();
        return list;
    }

    public void update(Fine fine) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(fine);
        em.getTransaction().commit();
        em.close();
    }
}