package Dao;
import DaoImpl.AlunoDaoJDBC;
import DaoImpl.CursoDaoJDBC;
import DaoImpl.DisciplinaDaoJDBC;
import db.DB;

public class FabricaDao {
    public static AlunoDao createAlunoDao() {
        return new AlunoDaoJDBC(DB.getConnection());
    }
    public static CursoDao createCursoDao() {
        return new CursoDaoJDBC();
    }
    public static DisciplinaDao createDisciplinaDao() {
        return new DisciplinaDaoJDBC();
    }
    public static MateriaDao createMatriculaDao() {
        return new MatriculaDaoJDBC();
    }
    public static ProfessorDao createProfessorDao() {
        return new ProfessorDaoJDBC();
    }
    public static TurmaDao createTurmaDao() {
        return new TurmaDaoJDBC();
    }
    
}
