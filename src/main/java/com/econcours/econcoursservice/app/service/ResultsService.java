package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.Candidacy;
import com.econcours.econcoursservice.app.entity.Results;
import com.econcours.econcoursservice.app.repository.ResultsRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.econcours.econcoursservice.utils.Utils.removeDuplicates;

@Service
@Slf4j
public class ResultsService extends ECDefaultBaseService<Results, ResultsRepository> {
    public ResultsService(ResultsRepository repository, ECEntityManager manager, ECLogger logger) {
        super(repository, manager, logger);
    }

    @Transactional
    public ECResponse<?> saveResult(List<Candidacy> candidacies) {
        try {
            Long lastTag = repository.getLastTagForList();
            List<Results> items = new ArrayList<>();
            if (candidacies != null && !candidacies.isEmpty()) {
                candidacies.forEach(candidacy -> {
                    Optional<Results> optionalResults = repository.findByCandidacyUid(candidacy.getUid());
                    if (optionalResults.isEmpty()) {
                        Results results = new Results();
                        if (lastTag == null) results.setTag(1L);
                        else results.setTag(lastTag + 1);
                        results.setFirstName(candidacy.getCandidate().getFirstName());
                        results.setLastName(candidacy.getCandidate().getLastName());
                        results.setEmail(candidacy.getCandidate().getEmail());
                        results.setPhone(candidacy.getCandidate().getPhone());
                        results.setCompetitionTitle(candidacy.getCompetition().getTitle());
                        results.setCompetitionUid(candidacy.getCompetition().getUid());
                        results.setEstablishmentTitle(candidacy.getCompetition().getEstablishment().getName());
                        results.setEstablishmentUid(candidacy.getCompetition().getEstablishment().getUid());
                        results.setCandidacyUid(candidacy.getUid());
                        items.add(results);
                    }
                });
                return ECResponse.success(repository.saveAll(removeDuplicates(items)), "Résultats soumis avec succès");
            }
            return ECResponse.error("La liste des candidatures !");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }
}
