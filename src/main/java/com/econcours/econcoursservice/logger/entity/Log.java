package com.econcours.econcoursservice.logger.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import com.econcours.econcoursservice.logger.enumeration.LogType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log extends ECBaseEntity {
    private String user;

    private String message;

    @Enumerated(EnumType.STRING)
    private LogType logType;
}
