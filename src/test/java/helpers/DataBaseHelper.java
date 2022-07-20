package helpers;

import java.sql.*;

public class DataBaseHelper {

    private static final Connection connection = createConnection();

    //Запрос на получение поста по его ID
    private static final String preparedPostStatement = "Select * from wp_posts where ID = ?";
    //Запрос на получение комментария по его ID
    private static final String preparedCommentStatement = "Select * from wp_comments where comment_ID = ?";

    private static PreparedStatement postStmt = null;

    static {
        try {
            postStmt = connection.prepareStatement(preparedPostStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static PreparedStatement commentStmt = null;

    static {
        try {
            commentStmt = connection.prepareStatement(preparedCommentStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод выполняет соединение с базой данных.
     * @return возвращает соединение.
     */
    public static Connection createConnection() {
        try{
            return DriverManager
                    .getConnection(PropertiesProvider.getProperty("dataBaseURI"),
                            PropertiesProvider.getProperty("dataBaseLogin"),
                            PropertiesProvider.getProperty("dataBasePassword"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод отправляет запрос к базе данных на получение информации о посте с заданным ID.
     * @param postID ID поста, информацию о котором нобходимо получить.
     * @return Возвращает результат запроса.
     */
    public static ResultSet selectDataFromPosts(Integer postID) {
        try {
            postStmt.setString(1, postID.toString());
            ResultSet rs = postStmt.executeQuery();
            rs.next();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод отправляет запрос к базе данных на получение информации о комментарии с заданным ID.
     * @param commentID ID комментария, информацию о котором нобходимо получить.
     * @return Возвращает результат запроса.
     */
    public static ResultSet selectDataFromComments(Integer commentID) {
        try {
            commentStmt.setString(1, commentID.toString());
            ResultSet rs = commentStmt.executeQuery();
            rs.next();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод определяет наличие записей в результате запроса.
     * @param rs Результат запроса, в котором необходимо узнать сущствование строк.
     * @return возвращает истинность существования.
     */
    public static Boolean isRawExist (ResultSet rs) {
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
