package helpers;

import java.sql.*;

public class DataBaseHelper {

    private static final Connection connection = createConnection();

    //Запрос на получение поста по его ID
    private static final String postStatement = "Select * from wp_posts where post_ID = ";

    //Запрос на получение комментария по его ID
    private static final String commentStatement = "Select * from wp_comments where comment_ID = ";

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
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(postStatement  + postID);
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
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(commentStatement + commentID);
            rs.next();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод считает количество строк в результате запроса.
     * @param rs Результат запроса, в котором необходимо посчитать количество строк.
     * @return возвращает количество строк.
     */
    public static Object getRawCount (ResultSet rs) {
        try {
            int rawCount = 0;
            while (rs.next()) {
                rawCount++;
            }
            return rawCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
