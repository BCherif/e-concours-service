package com.econcours.econcoursservice.base.service;


import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ECEntityManager {

    private EntityManager entityManager;

    public ECEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
