package com.econcours.econcoursservice.base.service;


import com.econcours.econcoursservice.base.entity.ECEntity;
import com.econcours.econcoursservice.base.repository.ECBaseRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.response.ECResponses;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.logger.LogPayload;
import com.econcours.econcoursservice.logger.enumeration.LogType;
import com.econcours.econcoursservice.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
public abstract class ECBaseService<T extends ECEntity, ID, R extends ECBaseRepository<T, ID>> implements ECService<T, ID>, EntityValidator<T> {
    protected R repository;
    protected ECEntityManager manager;
    protected ECLogger logger;
    protected Sort defaultSort = Sort.by(Sort.Direction.DESC, "createdAt");

    public ECBaseService(R repository, ECEntityManager manager, ECLogger logger) {
        this.repository = repository;
        this.manager = manager;
        this.logger = logger;
    }

    public ECBaseService(R repository, ECEntityManager manager) {
        this(repository, manager, null);
    }

    @Override
    public ECResponse<T> validate(T entity) {
        if (Objects.isNull(entity)) return ECResponse.error("entity is null");
        return ECResponse.success(entity);
    }

    @Override
    public ECResponse<T> save(T entity) {
        return persist(entity, this::validationLessSave);
    }

    @Override
    public ECResponse<T> saveAndCommit(T entity) {
        return persist(entity, this::validationLessSaveAndCommit);
    }

    public T validationLessSave(T entity) {
        boolean isUid = Utils.isCorrectUid(entity.getUid());
        LogType type = isUid ? LogType.UPDATE : LogType.CREATE;
        entity.setUid(isUid ? entity.getUid() : Utils.uid());
        T t = repository.save(entity);
        log(type, String.format("%s entity", type.isUpdate() ? "update" : "create"));
        return t;
    }

    public T validationLessSaveAndCommit(T entity) {
        boolean isUid = Utils.isCorrectUid(entity.getUid());
        LogType type = isUid ? LogType.UPDATE : LogType.CREATE;
        entity.setUid(isUid ? entity.getUid() : Utils.uid());
        T t = repository.saveAndFlush(entity);
        log(type, String.format("%s entity", type.isUpdate() ? "update" : "create"));
        return t;
    }

    @Override
    public ECResponse<T> update(ID uid, T entity) {
        return repository.findById(uid)
                .map(__ -> persist(entity, this::validationLessSaveAndCommit))
                .orElse(ECResponses.notFound());
    }

    public void log(LogType type, String message) {
        if (Objects.isNull(logger)) return;
        logger.log(LogPayload.from(type, message));
    }

    private ECResponse<T> persist(T entity, Function<T, T> func) {
        try {
            ECResponse<T> validationResponse = validate(entity);
            if (validationResponse.isKo()) return validationResponse;
            return ECResponse.success(func.apply(entity));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public ECResponse<T> findOne(ID id) {
        Optional<T> opt = repository.findById(id);
        return opt.map(ECResponse::success).orElseGet(() -> ECResponse.error(String.format("Entity N°%s not found", id)));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(getDefaultPageable(pageable));
    }

    protected Pageable getDefaultPageable(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), defaultSort);
    }

    public R getRepository() {
        return repository;
    }
}
