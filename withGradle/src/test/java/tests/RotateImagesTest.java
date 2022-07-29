package tests;


import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;
import pages.HomePage;

public class RotateImagesTest extends BaseTest{
    @Test
    @Owner("Александр imchubzzz Черняев")
    @Description("Проверка ротации изображений на главной странице сайта")
    public void rotateImagesTest() {
        HomePage homePage = new HomePage();
        homePage.openPage("https://moskva.mts.ru/personal")
                .checkRotateOfImages();
    }
}
