package com.econcours.econcoursservice.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSaveEntity {

    private String lastName;

    private String firstName;

    private String email;

    private String job;

    private String phone;

    private String username;

    private String password;

    private String establishmentId;
}
