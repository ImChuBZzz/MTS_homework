package tests.register_login;

import data.models.ErrorData;
import data.models.LoginRegisterRequestData;
import data.models.LoginRegisterResponseData;
import org.junit.Assert;
import org.junit.Test;
import specification.Specifications;

import static data.endpoints.LoginEndpoint.LOGIN;
import static data.endpoints.RegisterEndpoint.REGISTER;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class RegisterAndLoginTest {
    private static final String URL = "https://reqres.in/";

    /*
    * Проверка успешной регистрации
    */
    @Test
    public void successRegistrationTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        LoginRegisterRequestData user = new LoginRegisterRequestData("eve.holt@reqres.in", "pistol");
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
    }

    /*
     * Проверка регистрации c другими данными, тест падает, хотя по логике должен норм работать
     */
    @Test
    public void successRegistrationAnotherDataTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_BAD_REQUEST));
        LoginRegisterRequestData user = new LoginRegisterRequestData("abu.holt@reqres.in", "bazooka");
        String errorMessage = "Note: Only defined users succeed registration";
        ErrorData message = given()
                .body(user)
                .when()
                .post(REGISTER.getURL())
                .then().log().all()
                .extract().as(ErrorData.class);
        Assert.assertEquals(errorMessage, message.getMessage());
    }

    /*
     * Проверка регистрации с другим паролем и тем же email'ом. На деле ответ сервера 200
     */
    @Test
    public void registrationWithAnotherPasswordTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_NOT_FOUND));
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
    }

    /*
     * Попытка не правильной регистрации
     */
    @Test
    public void unsuccessfulRegistrationTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_BAD_REQUEST));
        LoginRegisterRequestData user = new LoginRegisterRequestData("eve.holt@reqres.in");
        String errorMessage = "Missing password";
        ErrorData message = given()
                .body(user)
                .when()
                .post(REGISTER.getURL())
                .then().log().all()
                .extract().as(ErrorData.class);
        Assert.assertEquals(errorMessage, message.getMessage());
    }

    /*
     * Проверка успешной авторизации
     */
    @Test
    public void successLoginTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        LoginRegisterRequestData user = new LoginRegisterRequestData("eve.holt@reqres.in", "cityslicka");
        String responseToken = "QpwL5tke4Pnpja7X4";
        LoginRegisterResponseData response = given()
                .body(user)
                .when()
                .post(LOGIN.getURL())
                .then().log().all()
                .extract().as(LoginRegisterResponseData.class);
        Assert.assertNotNull(response.getToken());
        Assert.assertEquals(responseToken, response.getToken());
    }

    /*
     * Проверка авторизации с другим паролем и тем же email'ом. На деле ответ сервера 200
     */
    @Test
    public void loginWithAnotherPasswordTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_NOT_FOUND));
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
    }

    /*
     * Проверка неуспешной авторизации
     */
    @Test
    public void unsuccessfulLoginTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_BAD_REQUEST));
        LoginRegisterRequestData user = new LoginRegisterRequestData("peter@klaven");
        String errorMessage = "Missing password";
        ErrorData message = given()
                .body(user)
                .when()
                .post(LOGIN.getURL())
                .then().log().all()
                .extract().as(ErrorData.class);
        Assert.assertEquals(errorMessage, message.getMessage());
    }
}
