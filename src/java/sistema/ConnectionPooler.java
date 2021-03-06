package sistema;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author reddo
 */
public final class ConnectionPooler {
    
    private static ConnectionPooler cpl = null;
    private BasicDataSource dataSource;
    
    public void openSource () {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/webdois?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("bancoweb");
        dataSource.setPassword("123123");
    }
    
    public static Connection getConnection () throws SQLException {
        ConnectionPooler result = cpl;
        if (result == null) {
            synchronized (ConnectionPooler.class) {
                result = cpl;
                if (result == null) {
                    cpl = result = new ConnectionPooler();
                    result.openSource();
                }
            }
        }
        return result.dataSource.getConnection();
    }
    
    public static void closeResultset (ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            
        }
    }
    
    public static void closeStatement (Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            // Log error on close
        }
    }
    
    public static void closeConnection (Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // Log error on close
        }
    }
    
}
