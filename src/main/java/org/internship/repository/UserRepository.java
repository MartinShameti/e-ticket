package org.internship.repository;

import jakarta.persistence.*;
import org.internship.entity.User;
import java.util.List;

public class UserRepository {

    private final EntityManagerFactory emf;

    public UserRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public List<User> findAll() {
        EntityManager em = emf.createEntityManager();
        List<User> list = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.close();
        return list;
    }
}