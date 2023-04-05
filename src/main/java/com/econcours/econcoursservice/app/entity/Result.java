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
public class Result extends ECBaseEntity {
    @Lob
    @Column(name = "title", nullable = false)
    private String title;
    @Lob
    private String competitionTitle;
    private String competitionUid;
    @Lob
    private String establishmentTitle;
    private String establishmentUid;
}
