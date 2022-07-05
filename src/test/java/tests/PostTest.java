package tests;

import io.restassured.response.Response;
import org.json.JSONObject;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostTest {

    @Test
    public void createPostTest(){
        RestAssured.baseURI = "http://localhost:8000/index.php";
        JSONObject requestBody = new JSONObject();
        requestBody.put("title","Test Post");
        requestBody.put("content","Test Post");
        requestBody.put("status","publish");

         Response response = given()
                .body(requestBody.toString())
                .auth()
                .preemptive()
                .basic("Andrey Grek", "123-Test")
                .contentType("application/json")
                .queryParam("rest_route","/wp/v2/posts")
                .when()
                    .post()
                .then()
                    .statusCode(201)
                .extract().response();
    }

    @Test
    public void updatePostTest () {
        RestAssured.baseURI = "http://localhost:8000/index.php";
        JSONObject requestBody = new JSONObject();
        requestBody.put("title","Test Update");
        requestBody.put("content","Test Update");

        Response response = given()
                .body(requestBody.toString())
                .auth()
                .preemptive()
                .basic("Andrey Grek", "123-Test")
                .contentType("application/json")
                .queryParam("rest_route","wp/v2/posts/45")
                .log()
                .all()
                .when()
                    .post()
                .then()
                    .statusCode(404)
                .extract().response();
        response.prettyPrint();
    }

    @Test
    public void deletePostTest() {
        RestAssured.baseURI = "http://localhost:8000/index.php";

        Response response = given()
                .auth()
                .preemptive()
                .basic("Andrey Grek", "123-Test")
                .queryParam("rest_route","wp/v2/posts/45")
                .log()
                .all()
                .when()
                    .delete()
                .then()
                    .statusCode(404)
                .extract().response();
        response.prettyPrint();
    }
}
