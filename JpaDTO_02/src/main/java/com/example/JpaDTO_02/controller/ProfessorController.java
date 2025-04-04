package com.example.JpaDTO_02.controller;

import com.example.JpaDTO_02.dto.ProfessorDTO;
import com.example.JpaDTO_02.entity.Professor;
import com.example.JpaDTO_02.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;


    @GetMapping
    public ResponseEntity<List<Professor>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable Long id){
        Optional<ProfessorDTO> professorDTO = professorService.getById(id);
        if (professorDTO.isPresent()){
            return ResponseEntity.ok(professorDTO.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDTO professorDTO){
        ProfessorDTO professorBd = professorService.save(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorBd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO){
        Optional<ProfessorDTO> professorDTOOptional = professorService.updateProfessor(id, professorDTO);
        if (professorDTOOptional.isPresent()){
            return ResponseEntity.ok(professorDTOOptional.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfessorDTO> delete(@PathVariable Long id){
       if (professorService.delete(id)){
           return ResponseEntity.noContent().build();
       } else {
           return ResponseEntity.notFound().build();
       }
    }

}
