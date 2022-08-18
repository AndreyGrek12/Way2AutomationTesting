package requests;

import io.restassured.response.Response;
import pOJO.RequestBody;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * Класс содержит методы, вызвав которые отправляется определенный запрос связанный с комментариями
 */
public class CommentRequests extends BaseRequest {

    /**
     * Этот метод отправляет запрос на создание комментария с некторыми данными:
     * @param content отвечает за текст комментария,
     * @param postID отвечает за id поста, к которому комментарий будет относиться.
     * @return возвращает ответ на запрос
     */
    public static Response createComment(String content, int postID) {
        RequestBody requestBody = RequestBody.builder()
                .content(content)
                .post(postID)
                .build();

        return given()
                    .spec(baseRequest)
                    .body(requestBody)
               .when()
                    .post(baseURI + "comments/")
               .then()
               .extract().response();
    }

    /**
     * Этот метод отправляет запрос на редактирование комментария с некторыми данными:
     * @param newContent отвечает за новый текст комментария,
     * @param commentID отвечает за id комментария, который необходимо отредактировать.
     * @return возвращает ответ на запрос.
     */
    public static Response updateComment(String newContent, int commentID) {
        RequestBody requestBody = RequestBody.builder()
                .content(newContent)
                .build();

        return given()
                    .spec(baseRequest)
                    .body(requestBody)
               .when()
                    .post(baseURI + "comments/" + commentID)
               .then()
               .extract().response();
    }

    /**
     * Этот метод отправляет запрос на удаление комментария.
     * @param commentID отвечает за id комментария, который необходимо удалить.
     * @return возвращает ответ на запрос.
     */
    public static Response deleteComment(Integer commentID) {
        return given()
                    .spec(baseRequest)
               .when()
                    .delete(baseURI + "comments/" + commentID)
               .then()
               .extract().response();
    }

    /**
     * Этот метод отправляет запрос на получение информации о комментарии.
     * @param commentID ID комментария
     * @return возвращает ответ на запрос.
     */
    public static Response getCommentInfo(Integer commentID) {
        return given()
                .spec(baseRequest)
        .when()
                .get(baseURI + "comments/" + commentID)
        .then()
                .extract().response();
    }
}
