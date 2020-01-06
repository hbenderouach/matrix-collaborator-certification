package com.example.matrixcollaboratorcertification.dao;

import com.example.matrixcollaboratorcertification.dao.entities.Certification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CertificationDAO extends CrudRepository<Certification, Long> {

    List<Certification> findAll();
    Certification findByCertificationId(long certificationId);
    Certification findBycertificationTitle(String certificationTitle);

}