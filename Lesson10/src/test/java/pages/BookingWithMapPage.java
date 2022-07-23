package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BookingWithMapPage {
    private final By ANOTHER_DAMN_SPINNER = By.xpath("//div[@class='map_left_cards__loading-spinner']");
    private final By MAP_ICON = By.xpath("//button[@data-testid='map-trigger']");
    private final By FIVE_STAR_CHECKBOX = By.xpath("//div[@class='map_left_filters__container']//div[@data-filters-item='class:class=5']");
    private final By HOTEL_CARD = By.xpath("//div[@class='map-card__content-container']");
    private final By STAR_ELEMENT = By.cssSelector(".bui-icon.bui-rating__item.bui-icon--medium");

    public BookingWithMapPage clickMapIcon() {
        $(MAP_ICON).click();
        $(ANOTHER_DAMN_SPINNER).shouldBe(visible).shouldBe(not(visible));
        return this;
    }

    public BookingWithMapPage setFiveStarCheckBox() {
        $(FIVE_STAR_CHECKBOX).shouldBe(visible).click();
        $(ANOTHER_DAMN_SPINNER).shouldBe(visible).shouldBe(not(visible));
        return this;
    }

    public BookingWithMapPage checkHotelRating(int hotelRating) {
        ElementsCollection hotels = $$(HOTEL_CARD);
        for (SelenideElement hotel : hotels) {
            hotel.$$(STAR_ELEMENT).shouldHave(size(hotelRating));
        }
        return this;
    }
}
