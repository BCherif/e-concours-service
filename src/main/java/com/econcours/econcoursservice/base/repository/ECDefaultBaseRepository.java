package com.econcours.econcoursservice.base.repository;

import com.econcours.econcoursservice.base.entity.ECEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ECDefaultBaseRepository<T extends ECEntity> extends ECBaseRepository<T, String> {
}
