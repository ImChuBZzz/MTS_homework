package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BookingPage {

    //private final By PROPERTY_FOUND = By.xpath("//h1[@class='e1f827110f d3a14d00da']");
    //private final By HOTEL_ADDRESS = By.xpath("//span[@data-testid='address' and contains(text(), 'Барселона')]");
    private final By HOTELS_CARDS = By.xpath("//div[@data-testid='property-card']");
    private final By FIVE_STAR_CHECKBOX = By.xpath("//div[@data-filters-item='class:class=5']");
    private final By DAMN_OVERLAY_SPINNER = By.xpath("//div[@data-testid='overlay-card']");
    private final By FIVE_STAR_PROPERTY_FOUND = By.xpath("//div[@data-filters-item='class:class=5']//span[@class='d8eab2cf7f a414c2b280']");
    private final By STAR_ELEMENT_1 = By.xpath("//span[@class='b6dc9a9e69 adc357e4f1 fe621d6382']");
    private final By STAR_ELEMENT_2 = By.cssSelector(".b6dc9a9e69.adc357e4f1.fe621d6382");


    public BookingPage setFiveStarCheckBox() {
        $(FIVE_STAR_CHECKBOX).shouldBe(exist).click();
        $(DAMN_OVERLAY_SPINNER).shouldBe(visible).shouldBe(not(visible));
        return this;
    }

    public BookingPage checkPropertyCount() {
        $$(HOTELS_CARDS).shouldHave(sizeGreaterThan(0));
        return this;
    }

    public BookingPage checkFiveStarsHotelsCount() {
        int fiveStarsCheckBoxCount = Integer.parseInt($(FIVE_STAR_PROPERTY_FOUND).getText());
        $$(HOTELS_CARDS).shouldHave(size(fiveStarsCheckBoxCount));
        return this;
    }

    public BookingPage checkHotelsRating(int starsCount) {
        ElementsCollection hotels = $$(HOTELS_CARDS);
        for (SelenideElement hotel : hotels) {
            hotel.$$(STAR_ELEMENT_2).shouldHave(size(starsCount));
        }
        return this;
    }
}
