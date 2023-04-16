package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Result;
import com.econcours.econcoursservice.app.service.ResultsService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.wrapper.ResultSaveEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/results")
public class ResultsController extends ECDefaultBaseController<Result, ResultsService> {
    public ResultsController(ResultsService service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveResult(@RequestBody ResultSaveEntity resultSaveEntity) {
        return ResponseEntity.ok(service.saveResult(resultSaveEntity));
    }

    @GetMapping("/liste-by-candidate/{candidateUid}")
    public ResponseEntity<?> getesultByCandidate(@PathVariable String candidateUid, Pageable pageable) {
        return ResponseEntity.ok(service.getesultByCandidate(candidateUid, pageable));
    }
}
