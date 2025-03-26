package com.example.Jpa_02.controller;

import com.example.Jpa_02.dto.ProfessorDTO;
import com.example.Jpa_02.entity.Professor;
import com.example.Jpa_02.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody ProfessorDTO professorDTO){
        Professor professorBd = professorService.save(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorBd);
    }



}
