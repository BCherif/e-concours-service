package com.econcours.econcoursservice.app.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import com.econcours.econcoursservice.utils.Enumeration;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "notification")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends ECBaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private Enumeration.NOTIFICATION_TYPE type;

    @Column(name = "reading")
    private Enumeration.NOTIFICATION_READING reading = Enumeration.NOTIFICATION_READING.NonRead;

    @ManyToOne
    @JoinColumn(name="competition")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name="candidate")
    private Candidate candidate;

//    @ManyToOne
//    @JoinColumn(name = "candidateResultat")
//    private CandidateResultat candidateResultat;
}
