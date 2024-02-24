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

import Dao.MateriaDao;
import db.DB;
import db.DbException;
import entities.Curso;
import entities.Materia;

public class MateriaDaoJDBC implements MateriaDao {
        private Connection conn;

        public MateriaDaoJDBC(Connection conn) {
            this.conn = conn;
        }

        public void insert(Materia obj) {
            PreparedStatement st = null;
            
            try
            {
                st = conn.prepareStatement("insert into materias (nome, materia_id, Curso_id) values (?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
                st.setString(1, obj.getName());
                st.setInt(2, obj.getMateriaId());
                st.setInt(3, obj.getCursoId());
                
                int rowsAffected = st.executeUpdate();
                if (rowsAffected >0)
                {
                    ResultSet rs = st.getGeneratedKeys();
                    if(rs.next())
                    {
                        int id = rs.getInt(1);
                        obj.setMateriaId(id);
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

        public void update(Materia obj) {
            PreparedStatement st = null;
            
            try
            {
                st = conn.prepareStatement("UPDATE materias SET nome = ?, curso_id = ? WHERE materia_id = ?");
                st.setString(1, obj.getName());
                st.setInt(2, obj.getCursoId());
                st.setInt(3, obj.getMateriaId());
        
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
                st = conn.prepareStatement("DELETE FROM materias WHERE materia_id = ?");
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
        
        public Materia findById(Integer id) {
            PreparedStatement st = null;
            ResultSet rs = null;
            try
            {
                st = conn.prepareStatement("SELECT * FROM materias WHERE materia_id = ?");
                st.setInt(1, id);
                rs = st.executeQuery();
                if(rs.next())
                {
                    Materia obj = new Materia();
                    obj.setMateriaId(rs.getInt("materia_id"));
                    obj.setName(rs.getString("nome"));
                    obj.setCursoId(rs.getInt("curso_id"));
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

        public List<Materia> findAll()
        {
            PreparedStatement st = null;
            ResultSet rs = null;
            try
            {
                st = conn.prepareStatement("SELECT * FROM materias");
                rs = st.executeQuery();
                List<Materia> list = new ArrayList<>();
                Map<Integer, Materia> map = new HashMap<>();
                while(rs.next())
                {
                    Materia obj = map.get(rs.getInt("materia_id"));
                    if(obj == null)
                    {
                        obj = new Materia();
                        obj.setMateriaId(rs.getInt("materia_id"));
                        obj.setName(rs.getString("nome"));
                        obj.setCursoId(rs.getInt("curso_id"));
                        map.put(rs.getInt("materia_id"), obj);
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

        public List<Materia> findByCurso(Curso curso)
        {
            PreparedStatement st = null;
            ResultSet rs = null;
            try
            {
                st = conn.prepareStatement("SELECT * FROM materias WHERE curso_id = ?");
                st.setInt(1, curso.getCursoId());
                rs = st.executeQuery();
                List<Materia> list = new ArrayList<>();
                Map<Integer, Materia> map = new HashMap<>();
                while(rs.next())
                {
                    Materia obj = map.get(rs.getInt("materia_id"));
                    if(obj == null)
                    {
                        obj = new Materia();
                        obj.setMateriaId(rs.getInt("materia_id"));
                        obj.setName(rs.getString("nome"));
                        obj.setCursoId(rs.getInt("curso_id"));
                        map.put(rs.getInt("materia_id"), obj);
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