package com.econcours.econcoursservice.base.service;


import com.econcours.econcoursservice.base.response.ECResponse;

public interface EntityValidator<T> {
    ECResponse<T> validate(T entity);
}
