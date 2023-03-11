package com.econcours.econcoursservice.auth.service;


import com.econcours.econcoursservice.auth.entity.User;
import com.econcours.econcoursservice.auth.repository.UserRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ECDefaultBaseService<User, UserRepository> {
    public UserService(UserRepository repository, ECEntityManager manager, ECLogger logger) {
        super(repository, manager, logger);
    }

    public ECResponse<User> changePwd() {
        return ECResponse.success(null);
    }
}
