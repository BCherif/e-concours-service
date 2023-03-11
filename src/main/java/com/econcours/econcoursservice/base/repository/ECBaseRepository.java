package com.econcours.econcoursservice.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ECBaseRepository<T, ID> extends JpaRepository<T, ID> {
}
