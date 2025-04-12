package com.muzika;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class SongRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("muzikaPU");

    public void save(Song song) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(song);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    public Song findByNameAndArtist(String name, Long izvodjacId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Song> query = em.createQuery(
                    "SELECT s FROM Song s WHERE s.songName = :name AND s.izvodjac.id = :id", Song.class);
            query.setParameter("name", name);
            query.setParameter("id", izvodjacId);
            List<Song> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    public List<Song> findAllByIzvodjacId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Song> query = em.createQuery(
                    "SELECT s FROM Song s WHERE s.izvodjac.id = :id", Song.class);
            query.setParameter("id", id);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Song findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Song.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(Long id, String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Song> query = em.createQuery(
                    "SELECT s FROM Song s WHERE s.izvodjac.id = :id AND s.songName = :name", Song.class);
            query.setParameter("id", id);
            query.setParameter("name", name);
            List<Song> results = query.getResultList();
            if (!results.isEmpty()) {
                Song result = results.get(0);
                em.getTransaction().begin();
                em.remove(em.contains(result) ? result : em.merge(result));
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    public void update(Song song) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(song);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}
