package tests.unknown;

import data.models.ResourceData;
import org.junit.Assert;
import org.junit.Test;
import specification.Specifications;

import java.util.List;

import static data.endpoints.UnknownEndpoint.UNKNOWN;
import static data.endpoints.UnknownEndpoint.UNKNOWN_LIST;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

public class ResourceDataTest {
    private final static String URL = "https://reqres.in/";

    /*
    * Проверяем что список отсортирован по возрастанию года
    */
    @Test
    public void checkIncYearField() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        List<ResourceData> resources = given()
                .when()
                .get(UNKNOWN_LIST.getURL())
                .then().log().all()
                .extract().body().jsonPath().getList("data", ResourceData.class);
        List<Integer> actualData = resources.stream().map(ResourceData::getYear).toList();
        Assert.assertEquals(actualData, actualData.stream().sorted().toList());
    }

    /*
    * Проверяем получение пользователя по id
    */
    @Test
    public void correctGetUnknownTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_OK));
        Integer id = 3;
        ResourceData resource = given()
                .when()
                .get(String.format(UNKNOWN.getURL(), id))
                .then().log().all()
                .extract().body().jsonPath().getObject("data", ResourceData.class);
        Assert.assertEquals(id, resource.getId());
    }

    /*
     * Проверяем отсутствие объекта по несуществующему id
     */
    @Test
    public void singleResourceNotFoundTest() {
        Specifications.installSpecifications(Specifications.requestSpec(URL), Specifications.responseSpec(SC_NOT_FOUND));
        Integer id = 23;
        ResourceData resource = given()
                .when()
                .get(String.format(UNKNOWN.getURL(), id))
                .then().log().all()
                .extract().body().jsonPath().getObject("data", ResourceData.class);
        Assert.assertNull(resource);
    }
}
