package com.econcours.econcoursservice.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidacySaveEntity {
    private String candidateId;

    private String competitionId;

    private String phoneNumberToPay;

}
