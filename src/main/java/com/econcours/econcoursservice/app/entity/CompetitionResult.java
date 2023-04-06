package com.econcours.econcoursservice.app.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "competition_result")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionResult extends ECBaseEntity {
    @ManyToOne
    private Candidacy candidacy;
    @ManyToOne
    private Result result;
}
