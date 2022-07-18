package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.BaseTest.cfg;

public class ExcursionPage {

    private final By ACCEPT_COOKIES =  By.xpath("//*[@id='onetrust-accept-btn-handler']");
    private final By SELECT_EXCURSION_MENU = By.xpath("//*[@class='bui-tab__item'][4]");
    private final By SELECT_PLACE_FOR_EXCURSION = By.xpath("//*[@name='query']");
    private final By CLICK_CHOSEN_PLACE = By.xpath("//*[@class='css-9dv5ti']/*[1]");
    private final By SELECT_PLACES_PER_PAGE_60 = By.xpath("//*[@class='bebf0b2b63 b45d8eb876']/*[3]");
    private final By CHOOSE_MUSEUM_CHECKBOX = By.xpath("//*[@for='__bui-13']/*[2]");
    private final By SELECT_PRICE_TYPE = By.xpath("//*[@class='css-1niqckn']/./*[@class='cb5ebe3ffb']");
    private final By CHOOSE_PRICE_TYPE = By.xpath("//*[@class='ff0ad2a91a']/*[3]");

    public ExcursionPage openPage() {
        open(cfg.baseUrl());
        $(ACCEPT_COOKIES).click();
        $(ACCEPT_COOKIES).shouldNot(Condition.visible);
        $(SELECT_EXCURSION_MENU).click();
        return this;
    }

    public ExcursionPage selectPlaceForExcursion(String place) {
        $(SELECT_PLACE_FOR_EXCURSION).sendKeys(place);
        $(CLICK_CHOSEN_PLACE).click();
        $(SELECT_PLACES_PER_PAGE_60).shouldBe(Condition.visible).click();
        return this;
    }

    public ExcursionPage selectMuseums() {
        $(CHOOSE_MUSEUM_CHECKBOX).click();
        return this;
    }

    public ExcursionPage selectPriceSort() {
        $(SELECT_PRICE_TYPE).click();
        $(CHOOSE_PRICE_TYPE).shouldBe(Condition.visible).click();
        return this;
    }


}
