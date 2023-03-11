package com.econcours.econcoursservice.base.controller;

import com.econcours.econcoursservice.base.entity.ECEntity;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.response.PageData;
import com.econcours.econcoursservice.base.service.ECService;
import com.econcours.econcoursservice.utils.Utils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class ECBaseController<T extends ECEntity, ID, S extends ECService<T, ID>> {
    protected S service;

    public ECBaseController(S service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ECResponse<T>> create(@RequestBody T entity) {
        return service.save(entity).wrap(Utils::created);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ECResponse<T>> update(@PathVariable ID uid, @RequestBody T entity) {
        return service.update(uid, entity).wrap();
    }

    @GetMapping("findOne/{uid}")
    public ResponseEntity<ECResponse<T>> findById(@PathVariable ID uid) {
        return service.findOne(uid).wrap();
    }

    @GetMapping
    public List<T> findAll() {
        return service.findAll();
    }

    @GetMapping("/page")
    public ECResponse<PageData<T>> findAll(Pageable pageable) {
        return ECResponse.success(PageData.fromPage(service.findAll(pageable)));
    }
}
