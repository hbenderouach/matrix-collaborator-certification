package com.example.matrixcollaboratorcertification.dao;

import com.example.matrixcollaboratorcertification.dao.entities.Collaborator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollaboratorDAO extends CrudRepository<Collaborator, Long> {

    Collaborator findByMailAdresse(String mailAdresse);
    List<Collaborator> findAll();

}
