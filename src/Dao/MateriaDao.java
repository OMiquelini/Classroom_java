package Dao;

import java.util.List;
import entities.Materia;
import entities.Curso;

public interface MateriaDao {
    void insert(Materia obj);
    void update(Materia obj);
    void deleteById(Integer id);
    Materia findById(Integer id);
    List<Materia> findAll();
    List<Materia> findByCurso(Curso curso);   
}
