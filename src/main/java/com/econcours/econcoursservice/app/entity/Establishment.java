package com.econcours.econcoursservice.app.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "establishment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Establishment extends ECBaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Lob
    @Column(name = "description")
    private String description;
    private String imageUrl;
}
