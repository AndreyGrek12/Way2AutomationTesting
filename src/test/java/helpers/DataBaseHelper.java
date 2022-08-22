package helpers;

import java.sql.*;

import static java.lang.Integer.valueOf;

public class DataBaseHelper {

    //Запрос на получение поста по его ID
    private static final String preparedPostStatement = "Select * from wp_posts where ID = ?";
    //Запрос на создание поста
    private static final String preparedCreatePostStatement = "insert into wp_posts (post_title, post_content, post_status) " +
            "values (?, ?, 'publish');";
    //Запрос на удаление поста
    private static final String preparedDeletePostStatement = "delete from wp_posts " +
            "where ID=?;";
    //Запрос на получение комментария по его ID
    private static final String preparedCommentStatement = "Select * from wp_comments where comment_ID = ?";
    //Запрос на создание комментария
    private static final String preparedCreateCommentStatement = "insert into wp_comments (comment_post_ID, comment_content) " +
            "values (?, ?);";
    //Запрос на удаление комментария
    private static final String preparedDeleteCommentStatement = "delete from wp_comments " +
            "where comment_ID=?;";
    //Запрос на изменение мода базы данных
    private static final String preparedSetSQLModeStatement = "SET SQL_MODE='ALLOW_INVALID_DATES'";
    //Запрос на получение ID поста по его названия
    private static final String preparedGetPostIDStatement = "select * from wp_posts where post_title = ?";
    //Запрос на получение ID комментария по его содержимому
    private static final String preparedGetCommentIDStatement = "select * from wp_comments where comment_content = ?";

    /**
     * Метод выполняет соединение с базой данных.
     * @return возвращает соединение.
     */

    public static Connection createConnection() {
        try {
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
    public static String selectDataFromPosts(Integer postID, String requiredColumn) {
        try (Connection connection = createConnection();
            PreparedStatement postStmt = connection.prepareStatement(preparedPostStatement)) {
            postStmt.setString(1, postID.toString());
            ResultSet rs = postStmt.executeQuery();
            rs.next();
            return rs.getString(requiredColumn);
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
    public static String selectDataFromComments(Integer commentID, String requiredColumn) {
        try (Connection connection = createConnection();
             PreparedStatement commentStmt = connection.prepareStatement(preparedCommentStatement)) {
            commentStmt.setString(1, commentID.toString());
            ResultSet rs = commentStmt.executeQuery();
            rs.next();
            return rs.getString(requiredColumn);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод отправляет запрос к базе данных на создание поста.
     * @param title Заголовок поста.
     * @param content Текст поста.
     */
    public static void createPost(String title, String content) {
        try (Connection connection = createConnection();
             PreparedStatement createPostStmt = connection.prepareStatement(preparedCreatePostStatement);
             PreparedStatement sQLModeStmt = connection.prepareStatement(preparedSetSQLModeStatement)) {
            sQLModeStmt.executeUpdate();
            createPostStmt.setString(1, title);
            createPostStmt.setString(2, content);
            createPostStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод отправляет запрос к базе данных на удаление поста.
     * @param postID ID поста, который необходимо удалить.
     */
    public static void deletePost(Integer postID) {
        try (Connection connection = createConnection();
             PreparedStatement deletePostStmt = connection.prepareStatement(preparedDeletePostStatement);
             PreparedStatement sQLModeStmt = connection.prepareStatement(preparedSetSQLModeStatement)) {
            sQLModeStmt.executeUpdate();
            deletePostStmt.setString(1, postID.toString());
            deletePostStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод отправляет запрос к базе данных на получение информации о посте по его заголовку.
     * @param postTitle Заголовок поста.
     */
    public static Integer getPostID(String postTitle) {
        try (Connection connection = createConnection();
             PreparedStatement getPostIDStatement = connection.prepareStatement(preparedGetPostIDStatement);
             PreparedStatement sQLModeStmt = connection.prepareStatement(preparedSetSQLModeStatement)) {
            sQLModeStmt.executeUpdate();
            getPostIDStatement.setString(1, postTitle);
            ResultSet rs = getPostIDStatement.executeQuery();
            rs.next();
            return valueOf(rs.getString("ID"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод отправляет запрос к базе данных на создание комментария.
     * @param postID ID поста, под которым создается комментарий.
     * @param commentContent Текст комментария.
     */
    public static void createComment(Integer postID, String commentContent) {
        try (Connection connection = createConnection();
             PreparedStatement createCommentStmt = connection.prepareStatement(preparedCreateCommentStatement);
             PreparedStatement sQLModeStmt = connection.prepareStatement(preparedSetSQLModeStatement)) {
            sQLModeStmt.executeUpdate();
            createCommentStmt.setString(1, postID.toString());
            createCommentStmt.setString(2, commentContent);
            createCommentStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод отправляет запрос к базе данных на удаление комментария.
     * @param commentID ID комментария, который необходимо удалить.
     */
    public static void deleteComment(Integer commentID) {
        try (Connection connection = createConnection();
             PreparedStatement deleteCommentStmt = connection.prepareStatement(preparedDeleteCommentStatement);
             PreparedStatement sQLModeStmt = connection.prepareStatement(preparedSetSQLModeStatement)) {
            sQLModeStmt.executeUpdate();
            deleteCommentStmt.setString(1, commentID.toString());
            deleteCommentStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод отправляет запрос к базе данных на получение информации о комментарии по его содержимому.
     * @param commentContent Содержимое комментария.
     */
    public static Integer getCommentID(String commentContent) {
        try (Connection connection = createConnection();
             PreparedStatement getCommentIDStatement = connection.prepareStatement(preparedGetCommentIDStatement);
             PreparedStatement sQLModeStmt = connection.prepareStatement(preparedSetSQLModeStatement)) {
            sQLModeStmt.executeUpdate();
            getCommentIDStatement.setString(1, commentContent);
            ResultSet rs = getCommentIDStatement.executeQuery();
            rs.next();
            return valueOf(rs.getString("comment_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
