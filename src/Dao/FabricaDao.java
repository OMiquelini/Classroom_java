package Dao;
import DaoImpl.AlunoDaoJDBC;
import DaoImpl.CursoDaoJDBC;
import DaoImpl.MateriaDaoJDBC;
import db.DB;

public class FabricaDao {
    public static AlunoDao createAlunoDao() {
        return new AlunoDaoJDBC(DB.getConnection());
    }
    public static CursoDao createCursoDao() {
        return new CursoDaoJDBC(DB.getConnection());
    }
    public static MateriaDao createMateriaDao() {
        return new MateriaDaoJDBC(DB.getConnection());
    }
    
}
