package tests.unknown;

import api.ResourceAPI;
import data.models.ResourceData;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;


public class ResourceDataTest {

    /*
    * Проверяем что список отсортирован по возрастанию года
    */
    @Test
    public void checkIncYearTest() {
        List<ResourceData> resources = ResourceAPI.getResorces();
        List<Integer> actualData = resources.stream().map(ResourceData::getYear).toList();
        Assert.assertEquals(actualData, actualData.stream().sorted().toList());
    }

    /*
    * Проверяем получение объекта по id
    */
    @Test
    public void correctGetUnknownTest() {
        Integer id = 3;
        ResourceData resource = ResourceAPI.getResourceByIdSuccess(id);
        Assert.assertEquals(id, resource.getId());
    }

    /*
     * Проверяем отсутствие объекта по несуществующему id
     */
    @Test
    public void singleResourceNotFoundTest() {
        int resourceWrongId = 23;
        ResourceAPI.resourceNotFoundSuccess(resourceWrongId);
    }
}
