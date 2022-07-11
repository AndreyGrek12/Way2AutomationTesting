package requests;

import io.restassured.response.Response;
import pOJO.RequestBody;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static requests.BaseRequest.baseRequest;

public class CommentRequests {

    public static Response createComment(String content, int postID) {
        RequestBody requestBody = RequestBody.builder()
                .content(content)
                .post(postID)
                .build();

        return given()
                    .spec(baseRequest())
                    .body(requestBody)
               .when()
                    .post(baseURI + "comments/")
               .then()
               .extract().response();
    }

    public static Response updateComment(String newContent, int commentID) {
        RequestBody requestBody = RequestBody.builder()
                .content(newContent)
                .build();

        return given()
                    .spec(baseRequest())
                    .body(requestBody)
               .when()
                    .post(baseURI + "comments/" + commentID)
               .then()
               .extract().response();
    }

    public static Response deleteComment(Integer commentID) {
        return given()
                    .spec(baseRequest())
               .when()
                    .delete(baseURI + "comments/" + commentID)
               .then()
               .extract().response();
    }
}
