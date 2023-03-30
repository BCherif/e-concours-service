package com.econcours.econcoursservice.app.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends ECBaseEntity {
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "job", nullable = false)
    private String job;

    @OneToOne
    private Establishment establishment;
}
