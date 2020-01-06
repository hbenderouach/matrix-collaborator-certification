package com.example.matrixcollaboratorcertification.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of= {"mailAdresse","certificationVOList"})
public class CollaboratorVO {
     String mailAdresse;
     List<CertificationVO> certificationVOList;
}
