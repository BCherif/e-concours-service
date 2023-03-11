package com.econcours.econcoursservice.app.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "candidate")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate extends ECBaseEntity {
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "fullname", nullable = false)
    private String fullName;
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "place_of_birth")
    private String placeOfBirth;
}
