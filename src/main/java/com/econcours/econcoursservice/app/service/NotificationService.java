package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.Notification;
import com.econcours.econcoursservice.app.repository.NotificationRepository;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends ECDefaultBaseService<Notification, NotificationRepository> {
    public NotificationService(NotificationRepository repository, ECEntityManager manager, ECLogger logger) {
        super(repository, manager, logger);
    }
}
