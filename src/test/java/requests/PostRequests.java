package requests;

import io.restassured.response.Response;
import pOJO.RequestBody;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * Класс содержит методы, вызвав которые отправляется определенный запрос связанный с постами
 */
public class PostRequests extends BaseRequest{

    /**
     * Этот метод отправляет запрос на создание поста с некторыми данными:
     * @param title отвечает за заголовок поста,
     * @param content отвечает за текст поста.
     * @return возвращает ответ на запрос.
     */
    public static Response createPost(String title, String content) {
        RequestBody requestBody = RequestBody.builder()
                .title(title)
                .content(content)
                .status("publish")
                .build();

        return given()
                    .spec(baseRequest)
                    .body(requestBody)
               .when()
                    .post(baseURI+"posts/")
               .then()
               .extract().response();
    }

    /**
     * Этот метод отправляет запрос на редактирование поста с некторыми данными:
     * @param newTitle отвечает за новый заголовок поста,
     * @param newContent отвечает за новый текст поста,
     * @param postID отвечает за id поста, который необходимо отредактировать.
     * @return возвращает ответ на запрос.
     */
    public static Response updatePost(String newTitle, String newContent, int postID) {
        RequestBody requestBody = RequestBody.builder()
                .title(newTitle)
                .content(newContent)
                .build();

        return given()
                    .spec(baseRequest)
                    .body(requestBody)
                .when()
                    .post(baseURI + "posts/" + postID)
                .then()
                .extract().response();
    }

    /**
     * Этот метод отправляет запрос на удаление поста.
     * @param postID отвечает за id поста, который необходимо удалить.
     * @return возвращает ответ на запрос.
     */
    public static Response deletePost(int postID) {
        return given()
                    .spec(baseRequest)
               .when()
                    .delete(baseURI + "posts/" + postID)
               .then()
               .extract().response();
    }

    /**
     * Этот метод отправляет запрос на получение информации о посте.
     * @return возвращает ответ на запрос.
     */
    public static Response getPostInfo(Integer postID) {
        return given()
                .spec(baseRequest)
        .when()
                .get(baseURI + "posts/" + postID)
        .then()
                .extract().response();
    }
}
