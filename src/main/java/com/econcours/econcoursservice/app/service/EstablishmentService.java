package com.econcours.econcoursservice.app.service;

import com.econcours.econcoursservice.app.core.UploadFileService;
import com.econcours.econcoursservice.app.entity.Establishment;
import com.econcours.econcoursservice.app.repository.EstablishmentRepository;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.base.service.ECDefaultBaseService;
import com.econcours.econcoursservice.base.service.ECEntityManager;
import com.econcours.econcoursservice.logger.ECLogger;
import com.econcours.econcoursservice.utils.UploadLink;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class EstablishmentService extends ECDefaultBaseService<Establishment, EstablishmentRepository> {
    private final UploadFileService uploadFileService;
    public EstablishmentService(EstablishmentRepository repository, ECEntityManager manager, ECLogger logger, UploadFileService uploadFileService) {
        super(repository, manager, logger);
        this.uploadFileService = uploadFileService;
    }

    @Transactional
    public ECResponse<Establishment> create(Establishment establishment, MultipartFile imageUrl) {
        try {
            establishment.setImageUrl(uploadFileService.uploadFile(imageUrl, UploadLink.ECONCOURS_LINK));
            Establishment newCompetition = repository.save(establishment);
            return ECResponse.success(newCompetition, "Etablissement ajouter avec succès");
        } catch (Exception e) {
            return ECResponse.error("Une erreur inconnue est survenue");
        }
    }
}
