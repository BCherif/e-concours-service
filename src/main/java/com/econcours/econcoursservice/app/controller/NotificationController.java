package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Notification;
import com.econcours.econcoursservice.app.service.NotificationService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.base.response.ECResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController extends ECDefaultBaseController<Notification, NotificationService> {
    public NotificationController(NotificationService service) {
        super(service);
    }

    @GetMapping("/{candidateUid}")
    public ECResponse<?> getNotificationsByCandidate(@PathVariable String candidateUid, Pageable pageable) {
        return service.getNotificationsByCandidate(candidateUid, pageable);
    }

    @GetMapping("/read/{notifUid}")
    public ECResponse<?> readNotification(@PathVariable String notifUid) {
        return service.readNotification(notifUid);
    }

    @GetMapping("/number/{candidateUid}")
    public ECResponse<?> getNotifUnReadNumber(@PathVariable String candidateUid) {
        return service.getNotifUnReadNumber(candidateUid);
    }
}
