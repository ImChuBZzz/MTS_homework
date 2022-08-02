package api;

import data.models.ErrorData;
import data.models.LoginRegisterRequestData;
import data.models.LoginRegisterResponseData;
import specification.Specifications;

import static data.endpoints.ReqresInEndpoints.REGISTER;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;

public class LoginAPI {
    public static LoginRegisterResponseData successLogin(LoginRegisterRequestData loginData) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_OK));
        return given()
                .body(loginData)
                .when()
                .post(REGISTER.getURL())
                .then()
                .extract().as(LoginRegisterResponseData.class);
    }

    public static ErrorData loginError(LoginRegisterRequestData loginData) {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(SC_BAD_REQUEST));
        return given()
                .body(loginData)
                .when()
                .post(REGISTER.getURL())
                .then()
                .extract().as(ErrorData.class);
    }
}
