package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Competition;
import com.econcours.econcoursservice.app.service.CompetitionService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.utils.UploadLink;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/competitions")
public class CompetitionController extends ECDefaultBaseController<Competition, CompetitionService> {
    public CompetitionController(CompetitionService service) {
        super(service);
    }

    @PostMapping("create")
    public ResponseEntity<?> save(@RequestParam("competition") String newCompetition,
                                    @RequestParam("imageUrl") MultipartFile imageUrl) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Competition competition = objectMapper.readValue(newCompetition, Competition.class);
        return ResponseEntity.ok(service.create(competition, imageUrl));
    }

    @ResponseBody
    @GetMapping("/download/{imageUrl}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("imageUrl") String image) {
        String path = UploadLink.ECONCOURS_LINK;
        try {
            Path fileName = Paths.get(path, image);
            byte[] buffer = Files.readAllBytes(fileName);
            ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
            return ResponseEntity.ok()
                    .contentLength(buffer.length)
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(byteArrayResource);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);
        }
        return ResponseEntity.badRequest().build();

    }
}
