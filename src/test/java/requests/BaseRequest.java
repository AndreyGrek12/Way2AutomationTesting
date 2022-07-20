package requests;

import helpers.PropertiesProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

abstract class BaseRequest {

    static {
        RestAssured.baseURI = PropertiesProvider.getProperty("baseURI");
    }

    static RequestSpecification baseRequest =
            given()
                    .contentType(ContentType.JSON)
                    .auth()
                    .preemptive()
                    .basic(PropertiesProvider.getProperty("baseAuthLogin"),
                            PropertiesProvider.getProperty("baseAuthPassword"));
}
