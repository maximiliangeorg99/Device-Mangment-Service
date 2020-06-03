package com.benmccann.db;

import com.google.inject.Inject;

import javax.persistence.EntityManager;

public class ExamplePersistedClassDao {

    protected EntityManager entityManager;

    @Inject
    public ExamplePersistedClassDao(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveInNewTransaction(final ExamplePersistedClass object) {
        entityManager.getTransaction().begin();
        save(object);
        entityManager.getTransaction().commit();
    }

    public void save(final ExamplePersistedClass object) {
        entityManager.persist(object);
    }

    public ExamplePersistedClass getByOtherField(final String otherField) {
        return (ExamplePersistedClass) entityManager
                .createQuery("select e from ExamplePersistedClass e where e.otherField=:otherField")
                .setParameter("otherField", otherField)
                .getSingleResult();
    }

}