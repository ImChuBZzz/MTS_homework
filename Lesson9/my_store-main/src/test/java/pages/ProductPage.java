package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    private final By ADD_TO_BUCKET_BUTTON = By.cssSelector(".js-store-prod-popup-buy-btn-txt");
    private final By SELECT_TEST_PRODUCT = By.xpath("//*[@data-product-uid='837522649341']");

    public ProductPage clickAddToBucketButton() {
        $(SELECT_TEST_PRODUCT).click();
        $(ADD_TO_BUCKET_BUTTON).click();
        return this;
    }
}
