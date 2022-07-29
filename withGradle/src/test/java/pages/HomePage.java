package pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;


import java.util.HashSet;


import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private final ElementsCollection rotateImages = $$x("//a[not(@class='promo-cover__item js__promo-content-item slick-slide slick-cloned')]//div[@class='promo-cover__title']");
    private final SelenideElement unhiddenImage = $x("//a[@aria-hidden='false']");
    private final SelenideElement nextButton = $x("//*[@class='loader-circle']/ancestor::button");

    public HomePage() {}
    @Step("Открываем сайт {0}")
    public HomePage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Проверяем что происходить ротация изображений при переключении")
    public void checkRotateOfImages() {
        int imagesCount = rotateImages.shouldBe(sizeGreaterThan(0)).size();
        HashSet<String> hrefs = new HashSet<>();
        while (imagesCount != 0) {
            Selenide.sleep(1000);
            String hrefUnhiddenImage = unhiddenImage.shouldBe(exist).getAttribute("href");
            hrefs.add(hrefUnhiddenImage);
            nextButton.shouldBe(exist).click();
            imagesCount--;
        }
        Assertions.assertEquals(rotateImages.size(), hrefs.size());
    }
}
