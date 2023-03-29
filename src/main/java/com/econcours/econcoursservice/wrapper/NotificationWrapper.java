package com.econcours.econcoursservice.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationWrapper {
    public String title;
    public String description;
    public String competitionUid;
    public String candidateUid;

}
