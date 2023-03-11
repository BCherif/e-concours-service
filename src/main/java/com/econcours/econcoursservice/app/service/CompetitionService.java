package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.entity.Competition;
import com.econcours.econcoursservice.app.entity.Folder;
import com.econcours.econcoursservice.app.repository.CompetitionRepository;
import com.econcours.econcoursservice.app.repository.FolderRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CompetitionService extends ECDefaultBaseService<Competition, CompetitionRepository> {
    private final FolderRepository folderRepository;

    public CompetitionService(CompetitionRepository repository, ECEntityManager manager, ECLogger logger, FolderRepository folderRepository) {
        super(repository, manager, logger);
        this.folderRepository = folderRepository;
    }

    @Transactional
    public ECResponse<Competition> create(Competition competition) {
        try {
            Competition newComopetition = repository.save(competition);
            if (!competition.getFolders().isEmpty()) {
                competition.getFolders()
                        .forEach(folder -> {
                            Folder newFolder = Folder.builder()
                                    .title(folder.getTitle())
                                    .competition(newComopetition)
                                    .build();
                            folderRepository.save(newFolder);
                        });
            }
            return ECResponse.success(newComopetition, "Concours ajouter avec succès");
        } catch (Exception e) {
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }
}
