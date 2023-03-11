package com.econcours.econcoursservice.app.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import com.econcours.econcoursservice.utils.Enumeration;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "candidacy")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidacy extends ECBaseEntity {
    @ManyToOne
    private Candidate candidate;
    @ManyToOne
    private Competition competition;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private Enumeration.STATE_CANDIDACY state;
    @OneToMany(mappedBy = "candidacy")
    @JsonIgnoreProperties(value = {"candidacy"}, allowSetters = true)
    private List<Attachment> attachments;
    @Lob
    private String note;
}
