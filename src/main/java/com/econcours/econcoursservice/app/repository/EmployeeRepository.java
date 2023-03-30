package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.Employee;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;

import java.util.List;

public interface EmployeeRepository extends ECDefaultBaseRepository<Employee> {
    List<Employee> findAllByEstablishmentUid(String establishment_uid);
}
