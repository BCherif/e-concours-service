package com.econcours.econcoursservice.logger.controller;


import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.logger.entity.Log;
import com.econcours.econcoursservice.logger.service.LogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class LogController extends ECDefaultBaseController<Log, LogService> {
    public LogController(LogService service) {
        super(service);
    }
}
