package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.Attachment;
import com.econcours.econcoursservice.app.repository.AttachmentRepository;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import org.springframework.stereotype.Service;

@Service
public class AttachmentService extends ECDefaultBaseService<Attachment, AttachmentRepository> {
    public AttachmentService(AttachmentRepository repository, ECEntityManager manager, ECLogger logger) {
        super(repository, manager, logger);
    }
}
