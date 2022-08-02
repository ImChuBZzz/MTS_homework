package api;

import data.endpoints.UsersEnpoints;
import data.models.CreatedUserData;
import data.models.UserData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import specification.Specifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static data.endpoints.UsersEnpoints.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class UsersPageAPI {

    public static UserData getUserByIdSuccess(Integer id) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        return given()
                .when()
                .get(String.format(USER.getURL(), id))
                .then()
                .extract().body().jsonPath().getObject("data", UserData.class);
    }

    public static UserData getUserByIdNotFound(Integer id) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_NOT_FOUND));
        return given()
                .when()
                .get(String.format(USER.getURL(), id))
                .then()
                .extract().body().jsonPath().getObject("data", UserData.class);
    }

    public static List<UserData> getUsersOnPage(int pageNumber) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        return given()
                .when()
                .get(String.format(USERS_PER_PAGE.getURL(), pageNumber))
                .then()
                .extract().body().jsonPath().getList("data", UserData.class);
    }

    public static List<UserData> getUsers() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        return given()
                .when()
                .get(USERS.getURL())
                .then()
                .extract().body().jsonPath().getList("data", UserData.class);
    }

    public static List<UserData> delayedGetUsers(int delay) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        return given()
                .get(String.format(DELAY.getURL(), delay))
                .then()
                .extract().body().jsonPath().getList("data", UserData.class);
    }

    public static Map<String, Integer> pagesInfo() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        Response response = given()
                .get(USERS.getURL())
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        return new HashMap<>(){{
            put("page", jsonPath.get("page"));
            put("per_page", jsonPath.get("per_page"));
            put("total", jsonPath.get("total"));
            put("total_pages", jsonPath.get("total_pages"));
        }};
    }

    public static CreatedUserData createUserSuccess(CreatedUserData userData) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_CREATED));
        return given()
                .body(userData)
                .when()
                .post(USERS.getURL())
                .then()
                .extract().as(CreatedUserData.class);
    }

    public static void createdUserOnlyNameFail(String name) {
        //Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_BAD_REQUEST));
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_CREATED));
        given()
                .body(String.format("{\"name\":\"%s\"}", name))
                .when()
                .post(USERS.getURL())
                .then();
    }
    public static void createdEmptyUserFail() {
        //Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_BAD_REQUEST));
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_CREATED));
        given()
                .when()
                .post(USERS.getURL())
                .then();
    }

    public static CreatedUserData updateUserByPut(CreatedUserData userData) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        int userId = 2;
        return given()
                .body(userData)
                .when()
                .put(String.format(USER.getURL(), userId))
                .then()
                .extract().as(CreatedUserData.class);
    }

    public static CreatedUserData updateUserByPatch(CreatedUserData userData) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        int userId = 2;
        return given()
                .body(userData)
                .when()
                .patch(String.format(USER.getURL(), userId))
                .then()
                .extract().as(CreatedUserData.class);
    }

    public static void deleteUserById(int userId) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_NO_CONTENT));
        given().when()
                .delete(String.format(USER.getURL(), userId));
    }
}
