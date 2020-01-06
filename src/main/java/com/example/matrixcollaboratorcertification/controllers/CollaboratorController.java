package com.example.matrixcollaboratorcertification.controllers;

import com.example.matrixcollaboratorcertification.bs.CollaboratorBS;
import com.example.matrixcollaboratorcertification.exceptions.CertificationException;
import com.example.matrixcollaboratorcertification.exceptions.CollaboratorException;
import com.example.matrixcollaboratorcertification.vo.CollaboratorVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CollaboratorController {

    @Autowired
    private CollaboratorBS collaboratorBS;

    @Operation(summary = "Create Collaborator", description = " Create new collaborator by firstname, lastname ...", tags = { "collaborator" })
    @RequestMapping(value="/collaborator", method= RequestMethod.POST,consumes={ "application/json"})
    @ResponseBody
    public ResponseEntity createCollaborator(@RequestBody CollaboratorVO collaborator) throws CollaboratorException {
        Optional<Object> findCollaborator = Optional.ofNullable(collaboratorBS.findCollaboratorByMailAdresse(collaborator.getMailAdresse()));

        if(findCollaborator.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body("This User is Founded");
        }
        collaboratorBS.createCollaborator(collaborator);
        return ResponseEntity.status(HttpStatus.CREATED).body(collaborator);
    }

    @Operation(summary = "Create certification", description = " Create new certification by email", tags = { "collaborator" })
    @RequestMapping(value="/collaborator/certifications", method=RequestMethod.POST,consumes={ "application/json"})
    @ResponseBody
    public ResponseEntity addCertificationsCollaborator(@RequestParam(value="email", defaultValue="robot@sqli.com") String email,@RequestBody List<Integer> certifications ) throws CertificationException {
        Optional<Object> findCollaborator = Optional.ofNullable(collaboratorBS.addCertificationsCollaborator(email,certifications));

        if(findCollaborator.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden Certifications Information");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(findCollaborator.get());
    }

    @Operation(summary = "Find certifications collaborator by email", description = "Certifications Collaborator search by %email% format", tags = { "collaborator" })
    @RequestMapping(value="/collaborator/certifications", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCollaboratorInfo(@RequestParam(value="email", defaultValue="robot@sqli.com") String mailAdresse){
        Optional<CollaboratorVO> findCollaborator = Optional.ofNullable(collaboratorBS.findCollaboratorByMailAdresse(mailAdresse));
        if(findCollaborator.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findCollaborator.get().getCertificationVOList());
    }

    @Operation(summary = "Find all collaborators", description = "Find all collaborators", tags = { "collaborator" })
    @RequestMapping(value="/collaborator/all", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllCollaboratorInfo(){
        List<CollaboratorVO> findCollaborators = collaboratorBS.findListCollaborator();

        if(findCollaborators.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findCollaborators);
    }


}
