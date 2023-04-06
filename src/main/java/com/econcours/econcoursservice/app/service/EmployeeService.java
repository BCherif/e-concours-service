package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.Employee;
import com.econcours.econcoursservice.app.entity.Establishment;
import com.econcours.econcoursservice.app.repository.EmployeeRepository;
import com.econcours.econcoursservice.app.repository.EstablishmentRepository;
import com.econcours.econcoursservice.auth.entity.User;
import com.econcours.econcoursservice.auth.repository.UserRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.wrapper.EmployeeSaveEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService extends ECDefaultBaseService<Employee, EmployeeRepository> {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EstablishmentRepository establishmentRepository;

    public EmployeeService(EmployeeRepository repository, ECEntityManager manager, ECLogger logger, PasswordEncoder passwordEncoder, UserRepository userRepository, EstablishmentRepository establishmentRepository) {
        super(repository, manager, logger);
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.establishmentRepository = establishmentRepository;
    }


    public ECResponse<Employee> create(EmployeeSaveEntity employeeSaveEntity) {
        try {
            Optional<Establishment> establishmentOptional = establishmentRepository.findById(employeeSaveEntity.getEstablishmentId());
            if (establishmentOptional.isPresent()) {
                Establishment establishment = establishmentOptional.get();
                Employee employee = Employee
                        .builder()
                        .firstName(employeeSaveEntity.getFirstName())
                        .lastName(employeeSaveEntity.getLastName())
                        .email(employeeSaveEntity.getEmail())
                        .job(employeeSaveEntity.getJob())
                        .phone(employeeSaveEntity.getPhone())
                        .establishment(establishment)
                        .build();
                Employee employeeSaved = repository.save(employee);
                User user = User
                        .builder()
                        .username(employeeSaveEntity.getUsername())
                        .password(passwordEncoder.encode(employeeSaveEntity.getPassword()))
                        .employee(employeeSaved)
                        .active(true)
                        .build();
                userRepository.save(user);
                return ECResponse.success(employeeSaved, "Ajouter avec succès !");
            }
            return ECResponse.error("Cet établissement n'existe pas");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }

    public ECResponse<List<Employee>> findEmployeesByEstablishmentUid(String uid) {
        try {
            return ECResponse.success(repository.findAllByEstablishmentUid(uid), "Les employées lié à cet établissement");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }

}
