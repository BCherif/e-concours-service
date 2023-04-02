package com.econcours.econcoursservice.app.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "result")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Results extends ECBaseEntity {
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;
    @Lob
    private String competitionTitle;
    private String competitionUid;
    @Lob
    private String establishmentTitle;
    private String establishmentUid;
    private String candidacyUid;
    @Column(
            nullable = false
    )
    private Long tag;
}
