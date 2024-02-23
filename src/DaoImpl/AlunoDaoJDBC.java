package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dao.AlunoDao;
import db.DB;
import db.DbException;
import entities.Aluno;
import entities.Curso;

public class AlunoDaoJDBC implements AlunoDao {
    
    private Connection conn;

    public AlunoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public void insert(Aluno obj) {
        // TODO Auto-generated method stub
    }

    public void update(Aluno obj) {
        // TODO Auto-generated method stub
    }

    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
    }

    public Aluno findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            st = conn.prepareStatement("select alunos.*,cursos.nome as CurName from alunos inner join cursos on alunos.Curso_id = cursos.Curso_id where alunos.matricula = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next())
            {
                Curso cur = instantiateCurso(rs);
                Aluno obj = instantiateAluno(rs, cur);
                return obj;
            }
            else
            {
                return null;
            }
        }
        catch(SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        finally
        {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public List<Aluno> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Aluno> findByCurso(Curso curso) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            st = conn.prepareStatement("select alunos.*,cursos.nome as CurName from alunos inner join cursos on alunos.Curso_id = cursos.Curso_id where Curso_id = ? order by nome");
            st.setInt(1,curso.getCursoId());
            rs = st.executeQuery();
            List<Aluno> list = new ArrayList<>();
            Map<Integer, Curso> map = new HashMap<>();
            while(rs.next())
            {
                Curso cur = map.get(rs.getInt("Curso_id"));
                if(cur == null)
                {
                    cur = instantiateCurso(rs);
                    map.put(rs.getInt("Curso_id"), cur);
                }
                Aluno obj = instantiateAluno(rs, cur);
                list.add(obj);
            }
            return list;
        }
        catch(SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        finally
        {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Aluno instantiateAluno(ResultSet rs, Curso cur) throws SQLException {
        Aluno obj = new Aluno();
        obj.setMatricula(rs.getInt("matricula"));
        obj.setName(rs.getString("nome"));
        obj.setEmail(rs.getString("email"));
        obj.setCursoId(cur);
        return obj;
    }

    private Curso instantiateCurso(ResultSet rs) throws SQLException {
        Curso cur = new Curso();
        cur.setCursoId(rs.getInt("Curso_id"));
        cur.setName(rs.getString("CurName"));
        return cur;
    }
}
