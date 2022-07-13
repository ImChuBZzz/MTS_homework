package tests;

import org.junit.Test;
import pages.BucketPage;
import pages.HomePage;
import pages.ProductPage;

public class AddProductToBucketAndMakeOrderTest extends BaseTest {

    @Test
    public void addProductToBucket() {
        HomePage homePage = new HomePage();
        homePage.openPage()
                .clickGoToWear();

        ProductPage productPage = new ProductPage();
        productPage.clickAddToBucketButton();

        BucketPage bucketPage = new BucketPage();
        String TEST_OFFICE_INPUT = "1";
        String TEST_HOUSE_INPUT = "108";
        String TEST_STREET_INPUT = "ул Профсоюзная";
        String TEST_RECEIVER_NAME_INPUT = "Кузмичев Кузьма Кузьмич";
        String TEST_ADDRESS_INPUT = "Москва, Красная площадь, 1";
        String TEST_REGION_INPUT = "Москва";
        String TEST_PHONE_INPUT = "0000000000";
        String TEST_NAME_INPUT = "Тестов Тест Тестович";
        bucketPage.sendName(TEST_NAME_INPUT)
                .sendPhone(TEST_PHONE_INPUT)
                .sendRegion(TEST_REGION_INPUT)
                .sendAddress(TEST_ADDRESS_INPUT)
                .sendReceiverName(TEST_RECEIVER_NAME_INPUT)
                .sendStreet(TEST_STREET_INPUT)
                .sendHouse(TEST_HOUSE_INPUT)
                .sendOffice(TEST_OFFICE_INPUT)
                .clickOrderButton()
                .checkMainErrorText("Укажите, пожалуйста, корректный номер телефона")
                .checkPhoneErrorText("Укажите, пожалуйста, корректный номер телефона");

    }

}
