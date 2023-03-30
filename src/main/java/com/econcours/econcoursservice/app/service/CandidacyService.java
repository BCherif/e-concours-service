package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.core.UploadFileService;
import com.econcours.econcoursservice.app.entity.Attachment;
import com.econcours.econcoursservice.app.entity.Candidacy;
import com.econcours.econcoursservice.app.entity.Candidate;
import com.econcours.econcoursservice.app.entity.Competition;
import com.econcours.econcoursservice.app.repository.AttachmentRepository;
import com.econcours.econcoursservice.app.repository.CandidacyRepository;
import com.econcours.econcoursservice.app.repository.CompetitionRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.utils.Enumeration;
import com.econcours.econcoursservice.utils.UploadLink;
import com.econcours.econcoursservice.wrapper.CandidacySaveEntity;
import com.econcours.econcoursservice.wrapper.CandidateSaveEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CandidacyService extends ECDefaultBaseService<Candidacy, CandidacyRepository> {
    private final CompetitionRepository competitionRepository;
    private final CandidateService candidateService;
    private final AttachmentRepository attachmentRepository;

    private final UploadFileService uploadFileService;

    public CandidacyService(CandidacyRepository repository, ECEntityManager manager, ECLogger logger, CompetitionRepository competitionRepository, CandidateService candidateService, AttachmentRepository attachmentRepository, UploadFileService uploadFileService) {
        super(repository, manager, logger);
        this.competitionRepository = competitionRepository;
        this.candidateService = candidateService;
        this.attachmentRepository = attachmentRepository;
        this.uploadFileService = uploadFileService;
    }

  /*  @Transactional
    public ECResponse<Candidacy> create(CandidacySaveEntity candidacySaveEntity, MultipartFile[] files) {
        try {
            CandidateSaveEntity candidateSaveEntity = CandidateSaveEntity
                    .builder()
                    .lastName(candidacySaveEntity.getLastName())
                    .firstName(candidacySaveEntity.getFirstName())
                    .fullName(candidacySaveEntity.getFirstName() + " " + ( candidacySaveEntity.getLastName()))
                    .address(candidacySaveEntity.getAddress())
                    .email(candidacySaveEntity.getEmail())
                    .phone(candidacySaveEntity.getPhone())
                    .placeOfBirth(candidacySaveEntity.getPlaceOfBirth())
                    .username(candidacySaveEntity.getUsername())
                    .password(candidacySaveEntity.getPassword())
                    .build();
            Candidate candidateSaved = candidateService.save(candidateSaveEntity);
            Optional<Competition> optionalCompetition = competitionRepository.findById(candidacySaveEntity.getCompetitionId());
            if (optionalCompetition.isPresent()) {
                Candidacy candidacy = Candidacy
                        .builder()
                        .competition(optionalCompetition.get())
                        .state(Enumeration.STATE_CANDIDACY.Instance)
                        .candidate(candidateSaved)
                        .build();
                Candidacy candidacySaved = repository.saveAndFlush(candidacy);
                for (MultipartFile file : files) {
                    String[] tab = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
                    Attachment attachment = Attachment
                            .builder()
                            .filePath(uploadFileService.uploadFile(file, UploadLink.ECONCOURS_LINK))
                            .fileName(tab[0])
                            .candidacy(candidacySaved)
                            .build();
                    attachmentRepository.save(attachment);
                }
                return ECResponse.success(candidacySaved, "Ajouter avec succès !");
            } else {
                return ECResponse.error(String.format("Entity N°%s not found", candidacySaveEntity.getCompetitionId()));
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }*/
}
