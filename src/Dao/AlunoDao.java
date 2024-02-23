package Dao;

import java.util.List;

import entities.Aluno;
import entities.Curso;

public interface AlunoDao {
    void insert(Aluno obj);
    void update(Aluno obj);
    void deleteById(Integer id);
    Aluno findById(Integer id);
    List<Aluno> findAll();
    List<Aluno> findByCurso(Curso curso);
}
