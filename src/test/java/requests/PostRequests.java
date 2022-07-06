package requests;

import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostRequests {
    
    public static Response createPost (String title, String content) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title)
                .put("content", content)
                .put("status","publish");

        return given()
                    .body(requestBody.toString())
                    .auth()
                    .preemptive()
                    .basic("Andrey Grek", "123-Test")
                    .contentType("application/json")
               .when()
                    .post(baseURI)
               .then()
               .extract().response();
    }

    public static Response updatePost (String newTitle, String newContent, int id) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", newTitle)
                .put("content", newContent);

        return given()
                .body(requestBody.toString())
                .auth()
                .preemptive()
                .basic("Andrey Grek", "123-Test")
                .contentType("application/json")
        .when()
                .post(baseURI + id)
        .then()
        .extract().response();
    }

    public static Response deletePost (int id) {
        return given()
                    .auth()
                    .preemptive()
                    .basic("Andrey Grek", "123-Test")
               .when()
                    .delete(baseURI + id)
               .then()
               .extract().response();
    }
}
