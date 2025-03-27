package com.example.JpaDTO_02.controller;

import com.example.JpaDTO_02.entity.Aluno;
import com.example.JpaDTO_02.entity.Professor;
import com.example.JpaDTO_02.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

}
