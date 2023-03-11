package com.econcours.econcoursservice.base.service;


import com.econcours.econcoursservice.base.response.ECResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ECService<T, ID> {
    ECResponse<T> save(T entity);

    ECResponse<T> saveAndCommit(T entity);

    ECResponse<T> findOne(ID id);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    ECResponse<T> update(ID uid, T entity);
}
