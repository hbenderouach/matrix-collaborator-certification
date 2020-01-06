package com.example.matrixcollaboratorcertification.controllers;

import com.example.matrixcollaboratorcertification.bs.CertificationBS;
import com.example.matrixcollaboratorcertification.vo.CertificationVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CertificationController {

    @Autowired
    private CertificationBS certificationBS;


    @Operation(summary = "Create Certification", description = " Create new Certification by title, description, ...", tags = { "certification" })
    @RequestMapping(value="/certification", method= RequestMethod.POST,consumes={ "application/json"})
    @ResponseBody
    public ResponseEntity createCertification(@RequestBody CertificationVO certification) {
        Optional<Object> findCertification = Optional.ofNullable(certificationBS.findCertificationByTitle(certification.getCertificationTitle()));

        if(findCertification.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body("This Certification is Founded");
        }
        certificationBS.createCertification(certification);
        return ResponseEntity.status(HttpStatus.CREATED).body(certification);
    }

    @Operation(summary = "Find Certification by title", description = "Certification search by %title% format", tags = { "certification" })
    @RequestMapping(value="/certification", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCertificationInfo(@RequestParam(value="title", defaultValue="Certification title") String title){
        Optional<CertificationVO> findCertification = Optional.ofNullable(certificationBS.findCertificationByTitle(title));
        if(findCertification.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This Certification not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(findCertification.get());
    }

    @Operation(summary = "Find all certifications", description = "Find all certifications", tags = { "certification" })
    @RequestMapping(value="/certification/all", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllCertificationtInfo(){
        List<CertificationVO> findCertifications = certificationBS.findListCertification();

        if(findCertifications.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findCertifications);
    }



}
