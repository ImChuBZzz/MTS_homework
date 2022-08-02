package api;

import data.models.ResourceData;
import specification.Specifications;

import java.util.List;


import static data.endpoints.UnknownEndpoints.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

public class ResourceAPI {
    public static List<ResourceData> getResorces(){
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        return given()
                .when()
                .get(UNKNOWN_LIST.getURL())
                .then()
                .extract().body().jsonPath().getList("data", ResourceData.class);
    }

    public static List<ResourceData> getResorcesPerPage(int pageNumber){
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        return given()
                .when()
                .get(UNKNOWN_LIST.getURL() + String.format("?page=%s",pageNumber))
                .then()
                .extract().body().jsonPath().getList("data", ResourceData.class);
    }

    public static ResourceData getResourceByIdSuccess(int id) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        return given()
                .when()
                .get(String.format(UNKNOWN.getURL(), id))
                .then()
                .extract().body().jsonPath().getObject("data", ResourceData.class);
    }

    public static void resourceNotFoundSuccess(int id) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_NOT_FOUND));
        given()
                .when()
                .get(String.format(UNKNOWN.getURL(), id))
                .then();
    }
}
