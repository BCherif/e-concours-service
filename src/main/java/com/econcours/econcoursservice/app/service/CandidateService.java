package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.Candidate;
import com.econcours.econcoursservice.app.repository.CandidateRepository;
import com.econcours.econcoursservice.auth.entity.User;
import com.econcours.econcoursservice.auth.repository.UserRepository;
import com.econcours.econcoursservice.auth.service.UserService;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.wrapper.CandidateSaveEntity;
import com.econcours.econcoursservice.wrapper.CandidateWithToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class CandidateService extends ECDefaultBaseService<Candidate, CandidateRepository> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository repository, ECEntityManager manager, ECLogger logger, PasswordEncoder passwordEncoder, UserRepository userRepository, UserService userService, CandidateRepository candidateRepository) {
        super(repository, manager, logger);
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
        this.candidateRepository = candidateRepository;
    }

//    @Transactional
    public ECResponse<CandidateWithToken> create(CandidateSaveEntity candidateSaveEntity) {
        try {
            Candidate candidate = Candidate
                    .builder()
                    .address(candidateSaveEntity.getAddress())
                    .phone(candidateSaveEntity.getPhone())
                    .firstName(candidateSaveEntity.getFirstName())
                    .lastName(candidateSaveEntity.getLastName())
                    .fullName(candidateSaveEntity.getFirstName() + " " + (candidateSaveEntity.getLastName()))
                    .email(candidateSaveEntity.getEmail())
                    .placeOfBirth(candidateSaveEntity.getPlaceOfBirth())
                    .build();
            Candidate candidateSaved = repository.save(candidate);
            User user = User
                    .builder()
                    .username(candidateSaveEntity.getUsername())
                    .password(passwordEncoder.encode(candidateSaveEntity.getPassword()))
                    .candidate(candidateSaved)
                    .active(true)
                    .admin(false)
                    .build();
            userRepository.save(user);

            String token = userService.createToken(user.getUsername());

            CandidateWithToken candidateWithToken = CandidateWithToken.builder()
                    .__ac__(token)
                    .candidate(candidateSaved)
                    .build();

            return ECResponse.success(candidateWithToken, "Ajouter avec succès !");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }

    public CandidateWithToken signInCandidate(String mail) {
        try {
            Optional<User> userOptional = userRepository.getByUsername(mail);
            if (userOptional.isPresent()) {
                Optional<Candidate> candidateOptional = candidateRepository.findByEmail(mail);
                if (candidateOptional.isPresent()) {
                    // USER PRESENT
                    Candidate candidate = candidateOptional.get();

                    CandidateWithToken candidateWithToken = CandidateWithToken.builder()
                            .candidate(candidate)
                            .__ac__(userService.createToken(mail))
                            .build();
                    return candidateWithToken;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
           return null;
        }
    }

    @Transactional
    public Candidate save(CandidateSaveEntity candidateSaveEntity) {
        Candidate candidate = Candidate
                .builder()
                .address(candidateSaveEntity.getAddress())
                .phone(candidateSaveEntity.getPhone())
                .firstName(candidateSaveEntity.getFirstName())
                .lastName(candidateSaveEntity.getLastName())
                .fullName(candidateSaveEntity.getFirstName().concat(candidateSaveEntity.getLastName()))
                .email(candidateSaveEntity.getEmail())
                .placeOfBirth(candidateSaveEntity.getPlaceOfBirth())
                .build();
        Candidate candidateSaved = repository.saveAndFlush(candidate);
        User user = User
                .builder()
                .username(candidateSaveEntity.getUsername())
                .password(passwordEncoder.encode(candidateSaveEntity.getPassword()))
                .candidate(candidateSaved)
                .active(true)
                .admin(false)
                .build();
        userRepository.save(user);
        return candidateSaved;
    }
}
