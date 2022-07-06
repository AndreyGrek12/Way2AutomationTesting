package tests.APItests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CommentTest {

    @Test
    public void createCommentTest() {
        RestAssured.baseURI = "http://localhost:8000/index.php?rest_route=/wp/v2/comments/";
        JSONObject requestBody = new JSONObject();
        requestBody.put("content","Test Comment")
                .put("post","47");

        Response response = given()
                .body(requestBody.toString())
                .auth()
                .preemptive()
                .basic("Andrey Grek", "123-Test")
                .contentType("application/json")
                .when()
                    .post(baseURI)
                .then()
                    .statusCode(201)
                .extract().response();
    }

    @Test
    public void updateCommentTest () {
        RestAssured.baseURI = "http://localhost:8000/index.php?rest_route=/wp/v2/comments/";
        JSONObject requestBody = new JSONObject();
        requestBody.put("content","Test Comment Updated");

        Response response = given()
                .body(requestBody.toString())
                .auth()
                .preemptive()
                .basic("Andrey Grek", "123-Test")
                .contentType("application/json")
                .when()
                .post(baseURI + "37")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test
    public void deleteCommentTest () {
        RestAssured.baseURI = "http://localhost:8000/index.php?rest_route=/wp/v2/comments/";

        Response response = given()
                .auth()
                .preemptive()
                .basic("Andrey Grek", "123-Test")
                .when()
                .delete(baseURI + "37")
                .then()
                .statusCode(200)
                .extract().response();
    }
}
