package com.econcours.econcoursservice.logger.service;


import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultLogLessBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.entity.Log;
import com.econcours.econcoursservice.logger.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService extends ECDefaultLogLessBaseService<Log, LogRepository> {
    public LogService(LogRepository repository, ECEntityManager manager) {
        super(repository, manager);
    }

    @Override
    public ECResponse<Log> save(Log entity) {
        return ECResponse.error("not implemented");
    }

    @Override
    public ECResponse<Log> saveAndCommit(Log entity) {
        return ECResponse.error("not implemented");
    }

    @Override
    public Log validationLessSave(Log entity) {
        return entity;
    }

    @Override
    public Log validationLessSaveAndCommit(Log entity) {
        return entity;
    }
}
