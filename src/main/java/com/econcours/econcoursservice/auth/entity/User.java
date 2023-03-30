package com.econcours.econcoursservice.auth.entity;

import com.econcours.econcoursservice.app.entity.Candidate;
import com.econcours.econcoursservice.app.entity.Employee;
import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends ECBaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "is_admin")
    private boolean admin;

    @OneToOne
    private Candidate candidate;

    @OneToOne
    private Employee employee;
}
