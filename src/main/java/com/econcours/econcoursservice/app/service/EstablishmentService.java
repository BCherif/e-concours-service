package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.Establishment;
import com.econcours.econcoursservice.app.repository.EstablishmentRepository;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import org.springframework.stereotype.Service;

@Service
public class EstablishmentService extends ECDefaultBaseService<Establishment, EstablishmentRepository> {
    public EstablishmentService(EstablishmentRepository repository, ECEntityManager manager, ECLogger logger) {
        super(repository, manager, logger);
    }
}
