package com.example.matrixcollaboratorcertification.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of= {"certificationTitle","certificationDescription"})
public class CertificationVO {
    String certificationTitle;
    String certificationDescription;
}
