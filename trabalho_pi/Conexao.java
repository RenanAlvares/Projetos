
    // OK
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/projeto_interdisciplinar";
    private static final String user = "root";
    private static final String password = "Renan123";

    private static Connection conn;

    public static Connection getConexao() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, user, password);
            } 
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

