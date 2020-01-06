package com.example.matrixcollaboratorcertification.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"certificationId","certificationTitle","certificationDescription"})
@Entity
public class Certification {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long certificationId;
    String certificationTitle;
    String certificationDescription;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "certifications")
    Set<Collaborator> collaborators = new HashSet<>();

}
