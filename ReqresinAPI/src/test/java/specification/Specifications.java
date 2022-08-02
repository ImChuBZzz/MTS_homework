package specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    private final static String URL = "https://reqres.in";

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder().setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .expectStatusCode(statusCode)
                .build();
    }

    public static void installSpecifications(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
