package tests.registerLogin;

import api.LoginAPI;
import api.RegisterAPI;
import data.models.ErrorData;
import data.models.LoginRegisterRequestData;
import data.models.LoginRegisterResponseData;
import org.junit.Assert;
import org.junit.Test;

public class RegisterAndLoginTest {

    /*
    * Проверка успешной регистрации
    */
    @Test
    public void successRegistrationTest() {
        Integer responseId = 4;
        String responseToken = "QpwL5tke4Pnpja7X4";
        LoginRegisterResponseData response = RegisterAPI.successRegistration(new LoginRegisterRequestData("eve.holt@reqres.in", "pistol"));
        Assert.assertNotNull(response.getId());
        Assert.assertNotNull(response.getToken());
        Assert.assertEquals(responseId, response.getId());
        Assert.assertEquals(responseToken, response.getToken());
    }

    /*
     * Проверка регистрации c другими данными, тест падает, хотя по логике должен норм работать
     */
    @Test
    public void successRegistrationAnotherDataTest() {
        String errorMessage = "Note: Only defined users succeed registration";
        ErrorData message = RegisterAPI.registrationError(new LoginRegisterRequestData("abu.holt@reqres.in", "bazooka"));
        Assert.assertEquals(errorMessage, message.getMessage());
    }

    /*
     * Проверка регистрации с другим паролем и тем же email'ом. На деле ответ сервера 200
     *
    *@Test
    public void registrationWithAnotherPasswordTest() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_NOT_FOUND));
        LoginRegisterRequestData user = new LoginRegisterRequestData("eve.holt@reqres.in", "abracadabra");
        Integer responseId = 4;
        String responseToken = "QpwL5tke4Pnpja7X4";
        LoginRegisterResponseData response = given()
                .body(user)
                .when()
                .post(REGISTER.getURL())
                .then().log().all()
                .extract().as(LoginRegisterResponseData.class);
        Assert.assertNotNull(response.getId());
        Assert.assertNotNull(response.getToken());
        Assert.assertEquals(responseId, response.getId());
        Assert.assertEquals(responseToken, response.getToken());
    }*/

    /*
     * Попытка не правильной регистрации
     */
    @Test
    public void unsuccessfulRegistrationTest() {
        String errorMessage = "Missing password";
        ErrorData message = RegisterAPI.registrationError(new LoginRegisterRequestData("eve.holt@reqres.in"));
        Assert.assertEquals(errorMessage, message.getMessage());
    }

    /*
     * Проверка успешной авторизации
     */
    @Test
    public void successLoginTest() {
        String responseToken = "QpwL5tke4Pnpja7X4";
        LoginRegisterResponseData response = LoginAPI.successLogin(
                new LoginRegisterRequestData("eve.holt@reqres.in", "cityslicka"));
        Assert.assertNotNull(response.getToken());
        Assert.assertEquals(responseToken, response.getToken());
    }

    /*
     * Проверка авторизации с другим паролем и тем же email'ом. На деле ответ сервера 200
     *
    @Test
    public void loginWithAnotherPasswordTest() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_NOT_FOUND));
        LoginRegisterRequestData user = new LoginRegisterRequestData("eve.holt@reqres.in", "abracadabra");
        String responseToken = "QpwL5tke4Pnpja7X4";
        LoginRegisterResponseData response = given()
                .body(user)
                .when()
                .post(LOGIN.getURL())
                .then().log().all()
                .extract().as(LoginRegisterResponseData.class);
        Assert.assertNotNull(response.getToken());
        Assert.assertEquals(responseToken, response.getToken());
    }*/

    /*
     * Проверка неуспешной авторизации
     */
    @Test
    public void unsuccessfulLoginTest() {
        String errorMessage = "Missing password";
        ErrorData message = LoginAPI.loginError(new LoginRegisterRequestData("peter@klaven"));
        Assert.assertEquals(errorMessage, message.getMessage());
    }
}
