package com.econcours.econcoursservice.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateSaveEntity {
    private String lastName;

    private String firstName;

    private String fullName;
    private String email;

    private String phone;

    private String address;

    private String placeOfBirth;

    private String username;

    private String password;
}
