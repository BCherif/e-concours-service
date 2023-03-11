package com.econcours.econcoursservice.auth.controller;


import com.econcours.econcoursservice.auth.entity.User;
import com.econcours.econcoursservice.auth.service.UserService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends ECDefaultBaseController<User, UserService> {

    public UserController(UserService service) {
        super(service);
    }
}
