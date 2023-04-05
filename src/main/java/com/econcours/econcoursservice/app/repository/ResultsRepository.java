package com.econcours.econcoursservice.app.repository;

import com.econcours.econcoursservice.app.entity.Result;
import com.econcours.econcoursservice.base.repository.ECDefaultBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ResultsRepository extends ECDefaultBaseRepository<Result> {

   /* @Query(
            value = "SELECT DISTINCT tag FROM result order by tag desc limit 1",
            nativeQuery = true
    )
    Long getLastTagForList();

    Optional<Result> findByCandidacyUid(String candidacyUid);*/
}
