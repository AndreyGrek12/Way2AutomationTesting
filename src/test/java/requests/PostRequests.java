package requests;

import helpers.PropertiesProvider;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostRequests {
    
    public static Response createPost(String title, String content) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title)
                .put("content", content)
                .put("status","publish");

        return given()
                    .body(requestBody.toString())
                    .auth()
                    .preemptive()
                    .basic(PropertiesProvider.getProperty("baseAuthLogin"),
                            PropertiesProvider.getProperty("baseAuthPassword"))
                    .contentType("application/json")
               .when()
                    .post(baseURI+"posts/")
               .then()
               .extract().response();
    }

    public static Response updatePost(String newTitle, String newContent, int postID) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", newTitle)
                .put("content", newContent);

        return given()
                .body(requestBody.toString())
                .auth()
                .preemptive()
                .basic(PropertiesProvider.getProperty("baseAuthLogin"),
                        PropertiesProvider.getProperty("baseAuthPassword"))
                .contentType("application/json")
        .when()
                .post(baseURI + "posts/" + postID)
        .then()
        .extract().response();
    }

    public static Response deletePost(int postID) {
        return given()
                    .auth()
                    .preemptive()
                    .basic(PropertiesProvider.getProperty("baseAuthLogin"),
                            PropertiesProvider.getProperty("baseAuthPassword"))
               .when()
                    .delete(baseURI + "posts/" + postID)
               .then()
               .extract().response();
    }
}
