package com.example.matrixcollaboratorcertification.bs.impl;

import com.example.matrixcollaboratorcertification.bs.CertificationBS;
import com.example.matrixcollaboratorcertification.dao.CertificationDAO;
import com.example.matrixcollaboratorcertification.dao.entities.Certification;
import com.example.matrixcollaboratorcertification.vo.CertificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CertificationBSImpl implements CertificationBS {

    @Autowired
    private CertificationDAO certificationDAO;

    @Override
    public boolean createCertification(CertificationVO certificationVO) {
        Certification typeToSave =  Certification.builder()
                .certificationTitle(certificationVO.getCertificationTitle())
                .certificationDescription(certificationVO.getCertificationDescription())
                .build();
        return certificationDAO.save(typeToSave) != null;
    }

    @Override
    public CertificationVO findCertificationByTitle(String certificationTitle) {
        Optional<Certification> certificationFinded = Optional.ofNullable(certificationDAO.findBycertificationTitle(certificationTitle));
        if(certificationFinded.isPresent()) {
            return  CertificationVO.builder()
                    .certificationTitle(certificationFinded.get().getCertificationTitle())
                    .certificationDescription(certificationFinded.get().getCertificationDescription())
                    .build();
        }
        return null;
    }

    @Override
    public List<CertificationVO> findListCertification() {
        return certificationDAO.findAll()
                .stream()
                .map(certificationFinded ->  CertificationVO.builder()
                        .certificationTitle(certificationFinded.getCertificationTitle())
                        .certificationDescription(certificationFinded.getCertificationDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
