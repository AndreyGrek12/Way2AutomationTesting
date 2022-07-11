package requests;

import io.restassured.response.Response;
import pOJO.RequestBody;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static requests.BaseRequest.baseRequest;

public class PostRequests {
    
    public static Response createPost(String title, String content) {
        RequestBody requestBody = RequestBody.builder()
                .title(title)
                .content(content)
                .status("publish")
                .build();

        return given()
                    .spec(baseRequest())
                    .body(requestBody)
               .when()
                    .post(baseURI+"posts/")
               .then()
               .extract().response();
    }

    public static Response updatePost(String newTitle, String newContent, int postID) {
        RequestBody requestBody = RequestBody.builder()
                .title(newTitle)
                .content(newContent)
                .build();

        return given()
                    .spec(baseRequest())
                    .body(requestBody)
                .when()
                    .post(baseURI + "posts/" + postID)
                .then()
                .extract().response();
    }

    public static Response deletePost(int postID) {
        return given()
                    .spec(baseRequest())
               .when()
                    .delete(baseURI + "posts/" + postID)
               .then()
               .extract().response();
    }
}
