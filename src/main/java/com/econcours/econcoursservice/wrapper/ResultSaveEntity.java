package com.econcours.econcoursservice.wrapper;

import com.econcours.econcoursservice.app.entity.Candidacy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultSaveEntity {
    private String competitionTitle;
    private String competitionUid;
    private String establishmentTitle;
    private String establishmentUid;
    private List<Candidacy> candidacies;

}
