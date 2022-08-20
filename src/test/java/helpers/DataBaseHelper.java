package helpers;

import java.sql.*;

import static java.lang.Integer.valueOf;

public class DataBaseHelper {

    private static final Connection connection = createConnection();

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

    private static PreparedStatement getPostIDStatement = null;

    static {
        try {
            getPostIDStatement = connection.prepareStatement(preparedGetPostIDStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static PreparedStatement getCommentIDStatement = null;

    static {
        try {
            getCommentIDStatement = connection.prepareStatement(preparedGetCommentIDStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static PreparedStatement sQLModeStmt = null;

    static {
        try {
            sQLModeStmt = connection.prepareStatement(preparedSetSQLModeStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static PreparedStatement postStmt = null;

    static {
        try {
            postStmt = connection.prepareStatement(preparedPostStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static PreparedStatement createPostStmt = null;

    static {
        try {
            createPostStmt = connection.prepareStatement(preparedCreatePostStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static PreparedStatement deletePostStmt = null;

    static {
        try {
            deletePostStmt = connection.prepareStatement(preparedDeletePostStatement);
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

    private static PreparedStatement createCommentStmt = null;

    static {
        try {
            createCommentStmt = connection.prepareStatement(preparedCreateCommentStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static PreparedStatement deleteCommentStmt = null;

    static {
        try {
            deleteCommentStmt = connection.prepareStatement(preparedDeleteCommentStatement);
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
    public static Boolean isRawExist(ResultSet rs) {
        try {
            return rs.next();
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
        try {
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
        try {
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
        try {
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
        try {
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
        try{
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
        try {
            getCommentIDStatement.setString(1, commentContent);
            ResultSet rs = getCommentIDStatement.executeQuery();
            rs.next();
            return valueOf(rs.getString("comment_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setSQLMode() {
        try {
            sQLModeStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
