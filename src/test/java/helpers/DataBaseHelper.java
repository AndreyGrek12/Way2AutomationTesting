package helpers;

import java.sql.*;

public class DataBaseHelper {

    private static Connection connection = null;

    static {
        try {
            connection = createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager
                .getConnection(PropertiesProvider.getProperty("dataBaseURI"), "wordpress", "wordpress");
    }

    public static ResultSet selectDataFromPosts(Integer postID) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from wp_posts " +
                "where ID = "  + postID);
        rs.next();
        return rs;
    }

    public static ResultSet selectDataFromComments(Integer commentID) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from wp_comments " +
                "where comment_ID = "  + commentID);
            rs.next();
            return rs;
    }

    public static int getRawCount (ResultSet rs) throws SQLException {
        int rawCount = 0;
        while (rs.next()) {
            rawCount ++;
        }
        return rawCount;
    }
}
