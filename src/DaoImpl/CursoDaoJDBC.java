package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dao.CursoDao;
import db.DB;
import db.DbException;
import entities.Curso;

public class CursoDaoJDBC implements CursoDao {
    private Connection conn;

    public CursoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public void insert(Curso obj) {
        PreparedStatement st = null;

        try
        {
            st = conn.prepareStatement("insert into cursos (nome) values (?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected >0)
            {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next())
                {
                    int id = rs.getInt(1);
                    obj.setCursoId(id);
                }
                DB.closeResultSet(rs);
            }
            else
            {
                throw new DbException("Erro inesperado! Nenhuma linha afetada!");
            }
        }
        catch(SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        finally
        {
            DB.closeStatement(st);
        }
    }

    public void update(Curso obj) {
        PreparedStatement st = null;

        try
        {
            st = conn.prepareStatement("update cursos set nome = ? where id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getCursoId());

            st.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        finally
        {
            DB.closeStatement(st);
        }
    }

    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try
        {
            st = conn.prepareStatement("delete from cursos where id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        finally
        {
            DB.closeStatement(st);
        }
    }

    public Curso findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try
        {
            st = conn.prepareStatement("select * from cursos where id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next())
            {
                Curso obj = new Curso();
                obj.setCursoId(rs.getInt("id"));
                obj.setName(rs.getString("nome"));
                return obj;
            }
            return null;
        }
        catch(SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        finally
        {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    public List<Curso> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try
        {
            st = conn.prepareStatement("select * from cursos");
            rs = st.executeQuery();

            List<Curso> list = new ArrayList<>();
            Map<Integer, Curso> map = new HashMap<>();

            while(rs.next())
            {
                Curso obj = map.get(rs.getInt("id"));

                if(obj == null)
                {
                    obj = new Curso();
                    obj.setCursoId(rs.getInt("id"));
                    obj.setName(rs.getString("nome"));
                    map.put(rs.getInt("id"), obj);
                }
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
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
