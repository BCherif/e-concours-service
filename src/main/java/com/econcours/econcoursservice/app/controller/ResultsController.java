package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Candidacy;
import com.econcours.econcoursservice.app.entity.Results;
import com.econcours.econcoursservice.app.service.ResultsService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultsController extends ECDefaultBaseController<Results, ResultsService> {
    public ResultsController(ResultsService service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveResult(@RequestBody List<Candidacy> candidacies) {
        return ResponseEntity.ok(service.saveResult(candidacies));
    }
}
