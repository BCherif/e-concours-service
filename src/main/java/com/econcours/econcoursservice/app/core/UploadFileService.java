package com.econcours.econcoursservice.app.core;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.Objects;

@Service
public class UploadFileService {
    public String uploadFile(MultipartFile photo, String link) throws IOException {
        Path path = Paths.get(link);
        Calendar cal = Calendar.getInstance();
        long millisDate = cal.getTimeInMillis();

        String newImageName = Long.toString(millisDate).concat(Objects.requireNonNull(photo.getOriginalFilename())) ;
        InputStream inputStream = photo.getInputStream();
        Files.copy(inputStream, path.resolve(newImageName), StandardCopyOption.REPLACE_EXISTING);
        return newImageName ;

    }

    public String updateImage(MultipartFile photo, String link, String fileName) throws IOException {
        Path path = Paths.get(link);
        InputStream inputStream = photo.getInputStream();
        Files.copy(inputStream, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}
