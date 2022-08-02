package tests.users;

import api.UsersPageAPI;
import data.models.UserData;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class UserDataTest {

    /*
    * Проверяем что у пользователей email оканчивается на @reqres.in
    */
    @Test
    public void checkEmailTest() {
        int userPage = 2;
        List<UserData> users = UsersPageAPI.getUsersOnPage(userPage);
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
    }

    /*
     * Проверяем что у пользователей имя аватара начинается с id
     */
    @Test
    public void checkAvatarTest() {
        List<UserData> users = UsersPageAPI.getUsers();
        users.forEach(x -> Assert.assertTrue(x.getAvatar().split("/")[5].startsWith(x.getId().toString())));
    }

    /*
    * Проверяем что на шестой странице нет списка пользователей
    */
    @Test
    public void checkUsersOutOfRange() {
        int userPage = 6;
        List<UserData> usersOutOfRange= UsersPageAPI.getUsersOnPage(userPage);
        Assert.assertEquals(0, usersOutOfRange.size());
    }

    /*
    * Проверяем что на шестой странице должен быть пустой список пользователей
    */
    @Test
    public void checkCorrectResponseStatus() {
        int userPage = 6;
        List<UserData> usersOutOfRange= UsersPageAPI.getUsersOnPage(userPage);
        Assert.assertEquals(0, usersOutOfRange.size());
    }

    /*
    * Проверяем получения пользователя по заданному id
    */
    @Test
    public void correctGetUserTest() {
        Integer userId = 7;
        UserData user = UsersPageAPI.getUserByIdSuccess(userId);
        Assert.assertEquals(user.getId(), userId);
    }

    /*
     * Проверяем что пользователь не найден
     */
    @Test
    public void notFoundUserTest() {
        Integer userId = 17;
        UserData user = UsersPageAPI.getUserByIdNotFound(userId);
        Assert.assertNull(user);
    }

    /*
    * Отложенный запрос
    */
    @Test
    public void delayedResponseTest() {
        int delay = 3;
        List<UserData> users = UsersPageAPI.delayedGetUsers(delay);
        Map<String, Integer> pagesInfo = UsersPageAPI.pagesInfo();
        Assert.assertNotNull(users);
        Assert.assertEquals(users.size(), pagesInfo.get("per_page").intValue());
    }
}
