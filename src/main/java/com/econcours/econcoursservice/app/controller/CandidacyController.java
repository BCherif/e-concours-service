package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Candidacy;
import com.econcours.econcoursservice.app.service.CandidacyService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.base.response.ECResponse;
import com.econcours.econcoursservice.utils.UploadLink;
import com.econcours.econcoursservice.wrapper.CandidacySaveEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/candidacies")
public class CandidacyController extends ECDefaultBaseController<Candidacy, CandidacyService> {
    public CandidacyController(CandidacyService service) {
        super(service);
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestParam("candidacy") String newCandidacySaveEntity,
                                  @RequestParam("files") MultipartFile[] files) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        CandidacySaveEntity candidacySaveEntity = objectMapper.readValue(newCandidacySaveEntity, CandidacySaveEntity.class);
        return ResponseEntity.ok(service.create(candidacySaveEntity, files));
    }

    @GetMapping("/get_candidacy/{candidateUid}")
    public ECResponse<?> getCandidacyByCandidate(@PathVariable String candidateUid, Pageable pageable) {
        return service.getCandidacyByCandidate(candidateUid, pageable);
    }

    @ResponseBody
    @GetMapping("/download/{file}")
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable("file") String image) {
        String path = UploadLink.ECONCOURS_LINK;
        try {
            Path fileName = Paths.get(path, image);
            byte[] buffer = Files.readAllBytes(fileName);
            ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
            return ResponseEntity.ok()
                    .contentLength(buffer.length)
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(byteArrayResource);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/candidacies-accept/{competitionUid}")
    public ResponseEntity<?> findCandidaciesAccept(@PathVariable String competitionUid, Pageable pageable) {
        return ResponseEntity.ok(service.findCandidaciesAccept(competitionUid, pageable));
    }

}
