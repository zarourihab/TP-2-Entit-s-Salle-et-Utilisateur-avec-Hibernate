package com.example.service;

import com.example.model.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class UtilisateurService extends AbstractCrudService<Utilisateur, Long> {

    public UtilisateurService(EntityManagerFactory emf) {
        super(emf, Utilisateur.class);
    }

    public Optional<Utilisateur> findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Utilisateur> result = em.createQuery(
                            "SELECT u FROM Utilisateur u WHERE u.email = :email",
                            Utilisateur.class
                    )
                    .setParameter("email", email)
                    .getResultList();

            return result.stream().findFirst();
        } finally {
            em.close();
        }
    }
}