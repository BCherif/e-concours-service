package com.econcours.econcoursservice.base.service;


import com.econcours.econcoursservice.base.entity.ECEntity;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;
import com.econcours.econcoursservice.logger.ECLogger;

public abstract class ECDefaultBaseService<T extends ECEntity, R extends ECDefaultBaseRepository<T>> extends ECBaseService<T, String, R> {
    public ECDefaultBaseService(R repository, ECEntityManager manager, ECLogger logger) {
        super(repository, manager, logger);
    }
}
