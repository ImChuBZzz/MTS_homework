package tests.users;

import data.models.UserData;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;
import org.junit.Assert;
import org.junit.Test;
import specification.Specifications;

import java.util.List;

import static data.endpoints.UsersEnpoint.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

public class UserDataTest {
    private final static String URL = "https://reqres.in/";

    /*
    * Проверяем что у пользователей email оканчивается на @reqres.in
    */
    @Test
    public void checkEmailTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        int userPage = 2;
        List<UserData> users = given()
                .when()
                .get(String.format(USERS_PER_PAGE.getURL(), userPage))
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
    }

    /*
     * Проверяем что у пользователей имя аватара начинается с id
     */
    @Test
    public void checkAvatarTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        List<UserData> users = given()
                .when()
                .get(USERS.getURL())
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        //users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        users.forEach(x -> Assert.assertTrue(x.getAvatar().split("/")[5].startsWith(x.getId().toString())));
    }

    /*
    * Проверяем что на шестой странице нет списка пользователей
    */
    @Test
    public void checkUsersOutOfRange() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        int userPage = 6;
        List<UserData> usersOutOfRange= given()
                .when()
                .get(String.format(USERS_PER_PAGE.getURL(), userPage))
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        Assert.assertEquals(0, usersOutOfRange.size());
    }

    /*
    * Проверяем что страница не найдена, т.к. на шестой странице должен быть пустой список пользователей
    */
    @Test
    public void checkCorrectResponseStatus() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_NOT_FOUND));
        int userPage = 6;
        List<UserData> usersOutOfRange= given()
                .when()
                .get(String.format(USERS_PER_PAGE.getURL(), userPage))
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
    }

    /*
    * Проверяем получения пользователя по заданному id
    */
    @Test
    public void correctGetUserTest() {
        Integer userId = 7;
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        UserData user = given()
                .when()
                .get(String.format(USER.getURL(), userId))
                .then().log().all()
                .extract().body().jsonPath().getObject("data", UserData.class);
        Assert.assertEquals(user.getId(), userId);
    }

    /*
     * Проверяем что пользователь не найден
     */
    @Test
    public void notFoundUserTest() {
        Integer userId = 17;
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_NOT_FOUND));
        UserData user = given()
                .when()
                .get(String.format(USER.getURL(), userId))
                .then().log().all()
                .extract().body().jsonPath().getObject("data", UserData.class);
        Assert.assertNull(user);
    }

    /*
    * Отлоденный запрос
    */
    @Test
    public void delayedResponseTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        int delay = 3;
        List<UserData> users = given()
                .get(String.format(DELAY.getURL(), delay) + String.format(USERS_PER_PAGE.getURL(), 2))
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        Response response = given()
                .get(String.format(DELAY.getURL(), delay) + String.format(USERS_PER_PAGE.getURL(), 2))
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int usersPerPage = jsonPath.get("per_page");

        Assert.assertNotNull(users);
        Assert.assertEquals(usersPerPage, users.size());
    }
}
