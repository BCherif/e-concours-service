package com.econcours.econcoursservice.app.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "attachment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment extends ECBaseEntity {
    @Column(name = "fileName")
    private String fileName;

    @Column(name = "filePath")
    private String filePath;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnoreProperties(value = {"attachments"}, allowSetters = true)
    private Candidacy candidacy;
}
