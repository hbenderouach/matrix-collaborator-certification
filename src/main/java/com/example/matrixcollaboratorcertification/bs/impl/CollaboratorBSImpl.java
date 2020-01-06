package com.example.matrixcollaboratorcertification.bs.impl;

import com.example.matrixcollaboratorcertification.bs.CollaboratorBS;
import com.example.matrixcollaboratorcertification.dao.CertificationDAO;
import com.example.matrixcollaboratorcertification.dao.CollaboratorDAO;
import com.example.matrixcollaboratorcertification.dao.entities.Certification;
import com.example.matrixcollaboratorcertification.dao.entities.Collaborator;
import com.example.matrixcollaboratorcertification.exceptions.CertificationException;
import com.example.matrixcollaboratorcertification.exceptions.CollaboratorException;
import com.example.matrixcollaboratorcertification.vo.CertificationVO;
import com.example.matrixcollaboratorcertification.vo.CollaboratorVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class CollaboratorBSImpl implements CollaboratorBS {


    @Autowired
    private CollaboratorDAO collaboratorDAO;

    @Autowired
    private CertificationDAO certificationDAO;

    @Override
    @Transactional(rollbackFor = CollaboratorException.class)
    public boolean createCollaborator(CollaboratorVO collaboratorVO) throws CollaboratorException {
        String email =  collaboratorVO.getMailAdresse();

        Collaborator collaboratorToSave = Collaborator.builder()
                .mailAdresse(collaboratorVO.getMailAdresse())
                .build();
        return  collaboratorDAO.save(collaboratorToSave)!=null;
    }

    @Override
    @Transactional(rollbackFor = CertificationException.class)
    public boolean addCertificationsCollaborator(String email,List<Integer> certifications) throws CertificationException {
        Collaborator collaboratorToUpdate = collaboratorDAO.findByMailAdresse(email);
        if(null == collaboratorToUpdate || null == email || StringUtils.isEmpty(email)){
            throw new CertificationException();
        }
        Set<Certification> collected = certifications.stream().map(id -> certificationDAO.findByCertificationId(id)).collect(Collectors.toSet());
        Set<Certification> collaboratorCertifications = collaboratorToUpdate.getCertifications();
        if(!collaboratorCertifications.isEmpty()){
            collaboratorCertifications.addAll(collected);
        }else{
            collaboratorToUpdate.setCertifications(collected);
        }
        return collaboratorDAO.save(collaboratorToUpdate)!=null;
    }

    @Override
    public CollaboratorVO findCollaboratorByMailAdresse(String mailAdresse) {
        Optional<Collaborator> collaboratorFinded = Optional.ofNullable(collaboratorDAO.findByMailAdresse(mailAdresse));
        if(collaboratorFinded.isPresent()) {
            return  CollaboratorVO.builder()
                    .mailAdresse(collaboratorFinded.get().getMailAdresse())
                    .certificationVOList(collaboratorFinded.get().getCertifications().stream()
                    .map(certification ->  CertificationVO.builder()
                    .certificationTitle(certification.getCertificationTitle())
                    .certificationDescription(certification.getCertificationDescription()).build())
                    .collect(Collectors.toList()))
                    .build();
        }
        return null;
    }

    @Override
    public List<CollaboratorVO> findListCollaborator() {
        return collaboratorDAO.findAll()
                .stream()
                .map(collaboratorFinded -> CollaboratorVO.builder()
                .mailAdresse(collaboratorFinded.getMailAdresse())
                .certificationVOList(collaboratorFinded.getCertifications().stream()
                .map(certification ->  CertificationVO.builder()
                .certificationTitle(certification.getCertificationTitle())
                .certificationDescription(certification.getCertificationDescription()).build())
                .collect(Collectors.toList()))
                .build())
                .collect(Collectors.toList());
    }

}
