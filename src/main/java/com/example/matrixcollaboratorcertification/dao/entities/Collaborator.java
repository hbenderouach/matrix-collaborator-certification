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
@ToString(of= {"mailAdresse"})
@Entity
public class Collaborator {

    @Id
    @Column(unique = true)
    String mailAdresse;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "collaborator_certification",
            joinColumns = { @JoinColumn(name = "mail_adresse") },
            inverseJoinColumns = { @JoinColumn(name = "certification_id") })
    Set<Certification> certifications = new HashSet<>();

}
