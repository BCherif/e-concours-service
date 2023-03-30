package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Notification;
import com.econcours.econcoursservice.app.service.NotificationService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController extends ECDefaultBaseController<Notification, NotificationService> {
    public NotificationController(NotificationService service) {
        super(service);
    }

}
