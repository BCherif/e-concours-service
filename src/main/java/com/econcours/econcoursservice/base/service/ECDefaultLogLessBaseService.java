package com.econcours.econcoursservice.base.service;

import com.econcours.econcoursservice.base.entity.ECEntity;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;

public abstract class ECDefaultLogLessBaseService<T extends ECEntity, R extends ECDefaultBaseRepository<T>> extends ECBaseService<T, String, R> {
    public ECDefaultLogLessBaseService(R repository, ECEntityManager manager) {
        super(repository, manager);
    }
}
