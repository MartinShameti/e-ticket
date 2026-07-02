package org.internship.repository;

import jakarta.persistence.*;
import org.internship.entity.Citizen;
import java.util.List;

public class CitizenRepository {

    private final EntityManagerFactory emf;

    public CitizenRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public void save(Citizen citizen) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(citizen);
        em.getTransaction().commit();
        em.close();
    }
    public List<Citizen> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Citizen> list = em.createQuery("SELECT c FROM Citizen c", Citizen.class).getResultList();
        em.close();
        return list;
    }
}