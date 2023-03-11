package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Competition;
import com.econcours.econcoursservice.app.service.CompetitionService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/competitions")
public class CompetitionController extends ECDefaultBaseController<Competition, CompetitionService> {
    public CompetitionController(CompetitionService service) {
        super(service);
    }

    @PostMapping("create")
    public ResponseEntity<?> save(@RequestBody Competition competition) {
        return ResponseEntity.ok(service.create(competition));
    }
}
