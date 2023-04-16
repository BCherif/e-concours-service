package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.CompetitionResult;
import com.econcours.econcoursservice.app.entity.Result;
import com.econcours.econcoursservice.app.repository.CompetitionResultRepository;
import com.econcours.econcoursservice.app.repository.ResultsRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.response.PageData;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.wrapper.ResultSaveEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class ResultsService extends ECDefaultBaseService<Result, ResultsRepository> {
    private final CompetitionResultRepository competitionResultRepository;

    public ResultsService(ResultsRepository repository, ECEntityManager manager, ECLogger logger, CompetitionResultRepository competitionResultRepository) {
        super(repository, manager, logger);
        this.competitionResultRepository = competitionResultRepository;
    }

    @Transactional
    public ECResponse<?> saveResult(ResultSaveEntity resultSaveEntity) {
        try {
            Result result = Result
                    .builder()
                    .title(resultSaveEntity.getTitle())
                    .competitionTitle(resultSaveEntity.getCompetitionTitle())
                    .competitionUid(resultSaveEntity.getCompetitionUid())
                    .establishmentTitle(resultSaveEntity.getEstablishmentTitle())
                    .establishmentUid(resultSaveEntity.getEstablishmentUid())
                    .build();
            Result resultSaved = repository.save(result);
            if (resultSaveEntity.getCandidacies() != null && !resultSaveEntity.getCandidacies().isEmpty()) {
                resultSaveEntity.getCandidacies()
                        .forEach(candidacy -> {
                            CompetitionResult competitionResult = CompetitionResult
                                    .builder()
                                    .result(resultSaved)
                                    .candidacy(candidacy)
                                    .build();
                            competitionResultRepository.save(competitionResult);
                        });
                return ECResponse.success(resultSaved, "Résultats soumis avec succès");
            }
            return ECResponse.error("Une erreur inconnue est survenue");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }

    public ECResponse<?> getesultByCandidate(String candidateUid, Pageable pageable) {
        try {
            PageData<CompetitionResult> results = PageData.fromPage(competitionResultRepository.findAllByCandidacyCandidateUid(candidateUid, pageable));
            return ECResponse.success(results, "Résultats soumis avec succès");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }
}
