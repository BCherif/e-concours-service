package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Result;
import com.econcours.econcoursservice.app.service.ResultsService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.wrapper.ResultSaveEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
