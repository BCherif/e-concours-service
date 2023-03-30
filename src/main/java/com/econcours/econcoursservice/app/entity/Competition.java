package com.econcours.econcoursservice.app.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import com.econcours.econcoursservice.utils.CustomJsonDateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "competition")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition extends ECBaseEntity {
    @Lob
    @Column(name = "title")
    private String title;
    private String imageUrl;
    @JsonSerialize(using = CustomJsonDateUtils.JsonDateSerializer.class)
    @JsonDeserialize(using = CustomJsonDateUtils.JsonDateDeserializer.class)
    private Date deadline;
    @JsonSerialize(using = CustomJsonDateUtils.JsonDateSerializer.class)
    @JsonDeserialize(using = CustomJsonDateUtils.JsonDateDeserializer.class)
    private Date competitionDate;
    @Column(name = "quotation")
    private int quotation;
    private boolean state;
    @Lob
    private String description;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
    @OneToMany(mappedBy = "competition")
    @JsonIgnoreProperties(value = {"competition"}, allowSetters = true)
    private List<Folder> folders;
}
