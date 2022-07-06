package requests;

import helpers.PropertiesProvider;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CommentRequests {

    public static Response createComment(String content, int postID) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("content", content)
                .put("post", postID);

        return given()
                    .body(requestBody.toString())
                    .auth()
                    .preemptive()
                    .basic(PropertiesProvider.getProperty("baseAuthLogin"),
                            PropertiesProvider.getProperty("baseAuthPassword"))
                    .contentType("application/json")
               .when()
                    .post(baseURI + "comments/")
               .then()
               .extract().response();
    }

    public static Response updateComment(String newContent, int commentID) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("content", newContent);

        return given()
                    .body(requestBody.toString())
                    .auth()
                    .preemptive()
                    .basic(PropertiesProvider.getProperty("baseAuthLogin"),
                            PropertiesProvider.getProperty("baseAuthPassword"))
                    .contentType("application/json")
               .when()
                    .post(baseURI + "comments/" + commentID)
               .then()
               .extract().response();
    }

    public static Response deleteComment(int commentID) {
        return given()
                    .auth()
                    .preemptive()
                    .basic(PropertiesProvider.getProperty("baseAuthLogin"),
                            PropertiesProvider.getProperty("baseAuthPassword"))
               .when()
                    .delete(baseURI + "comments/" + commentID)
               .then()
               .extract().response();
    }
}
