package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Candidate;
import com.econcours.econcoursservice.app.service.CandidateService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.wrapper.CandidateSaveEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
public class CandidateController extends ECDefaultBaseController<Candidate, CandidateService> {
    public CandidateController(CandidateService service) {
        super(service);
    }

    @PostMapping("create")
    public ResponseEntity<?> save(@RequestBody CandidateSaveEntity candidateSaveEntity){
        return ResponseEntity.ok(service.create(candidateSaveEntity));
    }
}
