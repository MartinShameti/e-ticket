package org.internship.repository;

import jakarta.persistence.*;
import org.internship.entity.Police;
import java.util.List;

public class PoliceRepository {

    private final EntityManagerFactory emf;

    public PoliceRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public void save(Police police) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(police);
        em.getTransaction().commit();
        em.close();
    }

    public List<Police> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Police> list = em.createQuery("SELECT p FROM Police p", Police.class).getResultList();
        em.close();
        return list;
    }
}