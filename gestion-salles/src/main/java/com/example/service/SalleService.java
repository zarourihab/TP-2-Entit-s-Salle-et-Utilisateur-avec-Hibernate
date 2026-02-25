package com.example.service;

import com.example.model.Salle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class SalleService extends AbstractCrudService<Salle, Long> {

    public SalleService(EntityManagerFactory emf) {
        super(emf, Salle.class);
    }

    public List<Salle> findByDisponible(boolean disponible) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT s FROM Salle s WHERE s.disponible = :dispo",
                            Salle.class
                    ).setParameter("dispo", disponible)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Salle> findByCapaciteMinimum(int capaciteMin) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT s FROM Salle s WHERE s.capacite >= :min",
                            Salle.class
                    ).setParameter("min", capaciteMin)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}

