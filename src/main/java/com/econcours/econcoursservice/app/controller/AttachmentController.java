package com.econcours.econcoursservice.app.controller;

import com.econcours.econcoursservice.app.entity.Attachment;
import com.econcours.econcoursservice.app.service.AttachmentService;
import com.econcours.econcoursservice.base.controller.ECDefaultBaseController;
import com.econcours.econcoursservice.utils.UploadLink;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/attachments")
public class AttachmentController extends ECDefaultBaseController<Attachment, AttachmentService> {
    public AttachmentController(AttachmentService service) {
        super(service);
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
}
