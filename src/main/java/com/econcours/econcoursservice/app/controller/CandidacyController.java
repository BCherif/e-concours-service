package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Candidacy;
import com.econcours.econcoursservice.app.service.CandidacyService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.wrapper.CandidacySaveEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/candidacies")
public class CandidacyController extends ECDefaultBaseController<Candidacy, CandidacyService> {
    public CandidacyController(CandidacyService service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestParam("candidacy") String newCandidacySaveEntity,
                                  @RequestParam("files") MultipartFile[] files) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        CandidacySaveEntity candidacySaveEntity = objectMapper.readValue(newCandidacySaveEntity, CandidacySaveEntity.class);
        return ResponseEntity.ok(service.create(candidacySaveEntity, files));
    }

}
