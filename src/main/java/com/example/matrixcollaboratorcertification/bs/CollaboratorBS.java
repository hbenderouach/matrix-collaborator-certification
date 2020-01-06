package com.example.matrixcollaboratorcertification.bs;

import com.example.matrixcollaboratorcertification.exceptions.CertificationException;
import com.example.matrixcollaboratorcertification.exceptions.CollaboratorException;
import com.example.matrixcollaboratorcertification.vo.CollaboratorVO;

import java.util.List;

public interface CollaboratorBS {

    boolean createCollaborator(CollaboratorVO collaboratorVO) throws CollaboratorException;
    boolean addCertificationsCollaborator(String email, List<Integer> certifications) throws CertificationException;
    CollaboratorVO findCollaboratorByMailAdresse(String mailAdresse);
    List<CollaboratorVO> findListCollaborator();

}
