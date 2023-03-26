package com.econcours.econcoursservice.wrapper;

import com.econcours.econcoursservice.app.entity.Candidate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateWithToken {
    public Candidate candidate;
    public String __ac__;
    public String uid;
}
