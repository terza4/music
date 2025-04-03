package com.muzika;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class IzvodjacRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("muzikaPU");

    public void save(Izvodjac izvodjac) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(izvodjac);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Izvodjac> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Izvodjac> query = em.createQuery("SELECT i FROM Izvodjac i", Izvodjac.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Izvodjac findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Izvodjac.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Izvodjac izvodjac = em.find(Izvodjac.class, id);
            if (izvodjac != null) {
                em.getTransaction().begin();
                em.remove(izvodjac);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    public void update(Izvodjac izvodjac) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(izvodjac);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}