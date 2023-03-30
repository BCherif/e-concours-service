package com.econcours.econcoursservice.app.entity;

import com.econcours.econcoursservice.base.entity.ECBaseEntity;
import com.econcours.econcoursservice.utils.Enumeration;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mailVerification")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailVerification extends ECBaseEntity {
String mail;
String confirmationCode;
}
