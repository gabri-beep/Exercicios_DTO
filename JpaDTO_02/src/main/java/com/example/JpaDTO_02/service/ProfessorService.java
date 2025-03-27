package com.example.JpaDTO_02.service;

import com.example.JpaDTO_02.dto.ProfessorDTO;
import com.example.JpaDTO_02.entity.Professor;
import com.example.JpaDTO_02.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;


    public Professor fromDTO(ProfessorDTO professorDTO){
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());
        return professor;
    }

    public ProfessorDTO toDTO(Professor professor){
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getIdProfessor());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());

        return professorDTO;
    }

    public List<Professor> getAll(){
        return professorRepository.findAll();
    }

    //buscar pelo id
    public Optional<ProfessorDTO> getById(Long id){
        Optional<Professor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isPresent()){
            return Optional.of(this.toDTO(professorOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    public  ProfessorDTO save(ProfessorDTO professorDTO){
        Professor professor = this.fromDTO(professorDTO);
        Professor professorBd = professorRepository.save(professor);
        return this.toDTO(professorBd);
    }

    public Optional<ProfessorDTO> updateProfessor(Long id, ProfessorDTO professorDTO){
        Optional<Professor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isPresent()) {
            Professor professor = professorOptional.get();

            professor.setNome(professorDTO.getNome());
            professor.setCpf(professorDTO.getCpf());

            Professor professorUpdate = professorRepository.save(professor);

            return Optional.of(this.toDTO(professorUpdate));
        } else {
            return Optional.empty();
        }
    }

    public boolean  delete(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
