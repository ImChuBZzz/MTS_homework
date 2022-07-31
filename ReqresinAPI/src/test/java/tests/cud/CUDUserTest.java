package tests.cud;

import data.models.CreatedUserData;
import org.junit.Assert;
import org.junit.Test;
import specification.Specifications;

import java.time.Clock;

import static data.endpoints.UsersEnpoint.USER;
import static data.endpoints.UsersEnpoint.USERS;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class CUDUserTest {
    private final static String URL = "https://reqres.in/";

    /*
    * Создание нового пользователя
    */
    @Test
    public void createUserTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_CREATED));
        CreatedUserData userData = new CreatedUserData("morpheus", "leader");
        CreatedUserData createdUser = given()
                .body(userData)
                .when()
                .post(USERS.getURL())
                .then().log().all()
                .extract().as(CreatedUserData.class);
        /*String regex = "(.{5})$";
        String creationTime = Clock.systemUTC().instant().toString();
        Assert.assertEquals(creationTime.replaceAll(regex, ""), createdUser.getCreatedAt().replaceAll(regex, ""));*/
        Assert.assertNotNull(createdUser.getId());
        Assert.assertEquals(createdUser.getName(), userData.getName());
        Assert.assertEquals(createdUser.getJob(), userData.getJob());

    }
    /*
     * Создание пустого пользователя
     */
    @Test
    public void createEmptyUserTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_BAD_REQUEST));
        CreatedUserData userData = new CreatedUserData();
        CreatedUserData createdUser = given()
                .body(userData)
                .when()
                .post(USERS.getURL())
                .then().log().all()
                .extract().as(CreatedUserData.class);
        Assert.assertNotNull(createdUser.getName());
    }

    /*
     * Обновление пользователя по id методом PUT
     */
    @Test
    public void updateUserPutTest() {
        int userId = 3;
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        CreatedUserData userData = new CreatedUserData("morpheus", "zion resident");
        CreatedUserData updateUser = given()
                .body(userData)
                .when()
                .put(String.format(USER.getURL(), userId))
                .then().log().all()
                .extract().as(CreatedUserData.class);
        Assert.assertNotNull(updateUser.getUpdatedAt());
        Assert.assertEquals(updateUser.getName(), userData.getName());
        Assert.assertEquals(updateUser.getJob(), userData.getJob());
    }

    /*
     * Обновление пользователя по id методом PATCH
     */
    @Test
    public void updateUserPatchTest() {
        int userId = 3;
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        CreatedUserData userData = new CreatedUserData("morpheus", "zion resident");
        CreatedUserData updateUser = given()
                .body(userData)
                .when()
                .patch(String.format(USER.getURL(), userId))
                .then().log().all()
                .extract().as(CreatedUserData.class);
        Assert.assertNotNull(updateUser.getUpdatedAt());
        Assert.assertEquals(updateUser.getName(), userData.getName());
        Assert.assertEquals(updateUser.getJob(), userData.getJob());
    }

    /*
     * Обновление пользователя по id методом PATCH c пустым полем "job"
     */
    @Test
    public void updateUserPatchWithEmptyFieldTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        int userId = 3;
        CreatedUserData userData = new CreatedUserData("morpheus");
        CreatedUserData updateUser = given()
                .body(userData)
                .when()
                .patch(String.format(USER.getURL(), userId))
                .then().log().all()
                .extract().as(CreatedUserData.class);
        Assert.assertNotNull(updateUser.getUpdatedAt());
        Assert.assertEquals(updateUser.getName(), userData.getName());
        Assert.assertNotNull("job не должен быть пустым", updateUser.getJob());
    }

    /*
    * Удаление пользователя по id
    */
    @Test
    public void deleteUser() {
        int userId = 3;
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_NO_CONTENT));
        given().when()
                .delete(String.format(USER.getURL(), userId));
    }
}
