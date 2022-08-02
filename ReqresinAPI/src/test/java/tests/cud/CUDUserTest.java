package tests.cud;

import api.UsersPageAPI;
import data.models.CreatedUserData;

import org.junit.Assert;
import org.junit.Test;



import java.time.Clock;


import static data.endpoints.UsersEnpoints.USER;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class CUDUserTest {

    /*
    * Создание нового пользователя
    */
    @Test
    public void createUserTest() {
        CreatedUserData userData = new CreatedUserData("morfeus", "leader");
        CreatedUserData createdUser = UsersPageAPI.createUserSuccess(userData);
        Assert.assertNotNull(createdUser.getId());
        Assert.assertEquals(createdUser.getName(), userData.getName());
        Assert.assertEquals(createdUser.getJob(), userData.getJob());

    }
    /*
     * Создание пользователя только по имени
     */
    @Test
    public void createUserOnlyNameTest() {
        String user = "Ololosha";
        UsersPageAPI.createdUserOnlyNameFail(user);
    }

    /*
     * Создание пустого пользователя
     */
    @Test
    public void createEmptyUserTest() {
        UsersPageAPI.createdEmptyUserFail();
    }

    /*
     * Обновление пользователя по id методом PUT
     */
    @Test
    public void updateUserPutTest() {
        CreatedUserData userData = new CreatedUserData("morpheus", "zion resident");
        CreatedUserData updateUser = UsersPageAPI.updateUserByPut(userData);
        Assert.assertNotNull(updateUser.getUpdatedAt());
        Assert.assertEquals(updateUser.getName(), userData.getName());
        Assert.assertEquals(updateUser.getJob(), userData.getJob());
    }

    /*
     * Обновление пользователя по id методом PATCH
     */
    @Test
    public void updateUserPatchTest() {
        CreatedUserData userData = new CreatedUserData("morpheus", "zion resident");
        CreatedUserData updateUser = UsersPageAPI.updateUserByPatch(userData);
        Assert.assertNotNull(updateUser.getUpdatedAt());
        Assert.assertEquals(updateUser.getName(), userData.getName());
        Assert.assertEquals(updateUser.getJob(), userData.getJob());
    }

    /*
     * Обновление пользователя по id методом PATCH c пустым полем "job"
     */
    @Test
    public void updateUserPatchWithEmptyFieldTest() {

        CreatedUserData userData = new CreatedUserData("morpheus", "");
        CreatedUserData updateUser = UsersPageAPI.updateUserByPatch(userData);
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
        UsersPageAPI.deleteUserById(userId);
    }
}
