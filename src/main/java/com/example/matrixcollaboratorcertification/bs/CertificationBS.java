package com.example.matrixcollaboratorcertification.bs;

import com.example.matrixcollaboratorcertification.vo.CertificationVO;

import java.util.List;

public interface CertificationBS {

    boolean createCertification(CertificationVO certificationVO);
    CertificationVO findCertificationByTitle(String certificationTitle);
    List<CertificationVO> findListCertification();

}
