package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Establishment;
import com.econcours.econcoursservice.app.service.EstablishmentService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/establishments")
public class EstablishmentController extends ECDefaultBaseController<Establishment, EstablishmentService> {
    public EstablishmentController(EstablishmentService service) {
        super(service);
    }
}
