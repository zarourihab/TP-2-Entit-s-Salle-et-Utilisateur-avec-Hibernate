package com.example.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<T, ID> {

    protected final EntityManagerFactory emf;
    private final Class<T> entityClass;

    protected AbstractCrudService(EntityManagerFactory emf, Class<T> entityClass) {
        this.emf = emf;
        this.entityClass = entityClass;
    }

    public Optional<T> findById(ID id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(entityClass, id));
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            // JPQL standard: SELECT e FROM Entity e
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            return em.createQuery(jpql, entityClass).getResultList();
        } finally {
            em.close();
        }
    }

    public T save(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public T update(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            T merged = em.merge(entity);
            em.getTransaction().commit();
            return merged;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void deleteById(ID id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if (entity != null) em.remove(entity);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}