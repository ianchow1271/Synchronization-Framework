import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class JDBCa {
    private Connection connection;
    private Connection connection1;


    public JDBCa() throws ClassNotFoundException, SQLException {
    		Class.forName("com.mysql.cj.jdbc.Driver");
        }


    public void writeServerData(String serverData) {
    	try {
    		//if(connection1 != null) {
    			Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/server_database", "root", "#Thankyou7303");
    			String insertSql = "INSERT INTO server(data, record) VALUES (?, ?)";
    			PreparedStatement pstmt = connection1.prepareStatement(insertSql);
    			pstmt.setString(1, serverData);
    			pstmt.setString(2, serverData);
    			pstmt.executeUpdate();
    		//}
    	}
     catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void writeClientData(String clientData) {
    	try {
    			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/client_database", "root", "#Thankyou7303");
    			String insertSql = "INSERT INTO client(data, record) VALUES (?, ?)";
    			PreparedStatement pstmt = connection.prepareStatement(insertSql);
    			pstmt.setString(1, clientData);
    			pstmt.setString(2, clientData);
    			pstmt.executeUpdate();
    	}
     catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    public void clearClientDataColumn() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/client_database", "root", "#Thankyou7303");
            String updateSql = "UPDATE client SET data = NULL";
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void clearServerDataColumn() {
        try {
        	Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/server_database", "root", "#Thankyou7303");
            String updateSql = "UPDATE server SET data = NULL";
            PreparedStatement pstmt = connection1.prepareStatement(updateSql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public String getClient() throws SQLException {
        String result = null;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/client_database", "root", "#Thankyou7303");
        String sql = "SELECT data  FROM client ORDER BY data DESC LIMIT 1";
        try (Statement statement = connection.createStatement()) {
            boolean hasResult = statement.execute(sql);
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    result = resultSet.getString("data");
                }
                resultSet.close();
            }
        }

        return result;
    }

    public String getServer() throws SQLException {
        String result = null;
        Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/server_database", "root", "#Thankyou7303");
        String sql = "SELECT data  FROM server ORDER BY data DESC LIMIT 1";
        try (Statement statement = connection1.createStatement()) {
            boolean hasResult = statement.execute(sql);
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    result = resultSet.getString("data");
                }
                resultSet.close();
            }
        }

        return result;
    }

}

