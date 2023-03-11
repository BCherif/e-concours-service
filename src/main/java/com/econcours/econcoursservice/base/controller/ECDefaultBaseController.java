package com.econcours.econcoursservice.base.controller;


import com.econcours.econcoursservice.base.entity.ECEntity;
import com.econcours.econcoursservice.base.service.ECService;

public abstract class ECDefaultBaseController<T extends ECEntity, S extends ECService<T, String>> extends ECBaseController<T, String, S> {
    public ECDefaultBaseController(S service) {
        super(service);
    }
}
