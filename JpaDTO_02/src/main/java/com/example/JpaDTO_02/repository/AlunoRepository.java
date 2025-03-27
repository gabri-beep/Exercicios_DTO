package com.example.JpaDTO_02.repository;

import com.example.JpaDTO_02.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
