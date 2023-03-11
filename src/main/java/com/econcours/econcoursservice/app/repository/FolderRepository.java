package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
