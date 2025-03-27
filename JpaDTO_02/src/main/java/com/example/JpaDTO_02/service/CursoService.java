package com.example.JpaDTO_02.service;

import com.example.JpaDTO_02.dto.CursoDTORequest;
import com.example.JpaDTO_02.dto.CursoDTOResponse;
import com.example.JpaDTO_02.entity.Curso;
import com.example.JpaDTO_02.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Curso fromDTO(CursoDTORequest cursoDTORequest){
        Curso curso = new Curso();
        curso.setNome(cursoDTORequest.getNome());
        curso.setNumeroSala(cursoDTORequest.getNumeroSala());
        curso.setProfessor(cursoDTORequest.getProfessor());

        return curso;
    }

    public CursoDTOResponse toDTO(Curso curso){
        CursoDTOResponse cursoDTOResponse = new CursoDTOResponse();
        cursoDTOResponse.setId(curso.getIdCurso());
        cursoDTOResponse.setNome(curso.getNome());
        cursoDTOResponse.setNumeroSala(curso.getNumeroSala());
        cursoDTOResponse.setProfessor(curso.getProfessor());
        cursoDTOResponse.setAlunos(curso.getAlunos());

        return cursoDTOResponse;
    }

    public List<Curso> getAll(){
        return cursoRepository.findAll();
    }

    public Optional<CursoDTOResponse> getById(Long id){
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if(optionalCurso.isPresent()){// verifica se encontrou algum professor
            return Optional.of(this.toDTO(optionalCurso.get()));
        }else {
            return Optional.empty(); // um objeto Optional vazio.
        }
            //return professorRepository.findById(id).map(this::toDTO);
    }

    public CursoDTOResponse saveDto(CursoDTORequest cursoDTORequest){
        Curso curso = this.fromDTO(cursoDTORequest);
        Curso cursoBd = cursoRepository.save(curso);
        return this.toDTO(cursoBd);
    }

    public Optional<CursoDTOResponse> updateCurso(Long id, CursoDTORequest cursoDTORequest){
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if(optionalCurso.isPresent()){
            Curso curso = optionalCurso.get();
            curso.setNome(cursoDTORequest.getNome());
            curso.setNumeroSala(cursoDTORequest.getNumeroSala());
            curso.setProfessor(cursoDTORequest.getProfessor());

            Curso cursoUpdate = cursoRepository.save(curso);

            return Optional.of(this.toDTO(cursoUpdate));
        }else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        if(cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

}
