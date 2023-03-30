package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Employee;
import com.econcours.econcoursservice.app.service.EmployeeService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.wrapper.EmployeeSaveEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController extends ECDefaultBaseController<Employee, EmployeeService> {
    public EmployeeController(EmployeeService service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody EmployeeSaveEntity employeeSaveEntity) {
        return ResponseEntity.ok(service.create(employeeSaveEntity));
    }

    @GetMapping("/by-establishmentUid/{uid}")
    public ResponseEntity<?> findEstablishmentsByUid(@PathVariable String uid) {
        return ResponseEntity.ok(service.findEmployeesByUid(uid));
    }
}
