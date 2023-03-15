package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.core.UploadFileService;
import com.econcours.econcoursservice.app.entity.Competition;
import com.econcours.econcoursservice.app.entity.Folder;
import com.econcours.econcoursservice.app.repository.CompetitionRepository;
import com.econcours.econcoursservice.app.repository.FolderRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.utils.UploadLink;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class CompetitionService extends ECDefaultBaseService<Competition, CompetitionRepository> {
    private final FolderRepository folderRepository;
    private final UploadFileService uploadFileService;

    public CompetitionService(CompetitionRepository repository, ECEntityManager manager, ECLogger logger, FolderRepository folderRepository, UploadFileService uploadFileService) {
        super(repository, manager, logger);
        this.folderRepository = folderRepository;
        this.uploadFileService = uploadFileService;
    }

    @Transactional
    public ECResponse<Competition> create(Competition competition, MultipartFile imageUrl) {
        try {
            competition.setImageUrl(uploadFileService.uploadFile(imageUrl, UploadLink.ECONCOURS_LINK));
            Competition newCompetition = repository.save(competition);
            if (!competition.getFolders().isEmpty()) {
                competition.getFolders()
                        .forEach(folder -> {
                            Folder newFolder = Folder.builder()
                                    .title(folder.getTitle())
                                    .competition(newCompetition)
                                    .build();
                            folderRepository.save(newFolder);
                        });
            }
            return ECResponse.success(newCompetition, "Concours ajouter avec succès");
        } catch (Exception e) {
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }
}
