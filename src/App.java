import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import db.DB;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql;
        Scanner sc = new Scanner(System.in);
        try
        {
            conn = DB.getConnection();
            System.out.println("Digite a sua insertion");
            sql = sc.nextLine();
            DB.updateSet(conn, st, sql);
            System.out.println("Digite a sua seleção");
            sql = sc.nextLine();
            rs = DB.selecSet(conn, st, rs, sql);
            while(rs.next())
            {
                System.out.println(rs.getString("Nome") + ", " + rs.getInt("Curso_id"));
            }
        }
        catch(SQLException e)
        {
            throw new db.DbException(e.getMessage());
        }
        finally
        {
            sc.close();
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();

        }
    }
}
