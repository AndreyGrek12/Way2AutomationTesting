package requests;

import helpers.PropertiesProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseRequest {

    public static RequestSpecification baseRequest() {
        RestAssured.baseURI = PropertiesProvider.getProperty("baseURI");
        return given()
                    .contentType(ContentType.JSON)
                    .auth()
                    .preemptive()
                    .basic(PropertiesProvider.getProperty("baseAuthLogin"),
                        PropertiesProvider.getProperty("baseAuthPassword"))
                .log()
                .all();
    }
}
