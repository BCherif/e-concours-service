package com.econcours.econcoursservice.auth.repository;

import com.econcours.econcoursservice.auth.entity.User;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;

import java.util.Optional;

public interface UserRepository extends ECDefaultBaseRepository<User> {
    User findByUsername(String username);
    Optional<User> getByUsername(String username);
//    User findByUsernameOrEmail(String username, String email);
}
