package com.example.JpaDTO_02.controller;

import com.example.JpaDTO_02.dto.CursoDTORequest;
import com.example.JpaDTO_02.dto.CursoDTOResponse;
import com.example.JpaDTO_02.entity.Curso;
import com.example.JpaDTO_02.entity.Professor;
import com.example.JpaDTO_02.repository.CursoRepository;
import com.example.JpaDTO_02.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTOResponse> getById(@PathVariable Long id){
        Optional<CursoDTOResponse> cursoDTO = cursoService.getById(id);
        if (cursoDTO.isPresent()){
            return ResponseEntity.ok(cursoDTO.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity<CursoDTOResponse> create(@RequestBody CursoDTORequest cursoDTORequest){
        CursoDTOResponse cursoDTO = cursoService.saveDto(cursoDTORequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDTO);
    }
}
