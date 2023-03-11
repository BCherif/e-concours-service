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
@Table(name = "folder")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Folder extends ECBaseEntity {
    @Column(name = "title")
    private String title;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnoreProperties(value = {"folders"}, allowSetters = true)
    private Competition competition;
}
