package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static tests.BaseTest.cfg;

public class StartPage {

    private final By ACCEPT_COOKIES = By.xpath("//*[@id='onetrust-accept-btn-handler']");
    private final By SEARCH_CITY_INPUT = By.xpath("//*[@id='ss']");
    private final By CLICK_CHOSEN_CITY = By.xpath("//*[contains(@role, 'listbox')]/*[1]");
    private final By CLICK_PEOPLE_INPUT = By.xpath("//*[@id='xp__guests__toggle']");
    private final By DEC_ADULT_COUNT = By.xpath("//*[@aria-describedby='group_adults_desc'][1]");
    private final By INC_ADULT_COUNT = By.xpath("//*[@aria-describedby='group_adults_desc'][2]");
    private final By DEC_CHILDREN_COUNT = By.xpath("//*[@aria-describedby='group_children_desc'][1]");
    private final By INC_CHILDREN_COUNT = By.xpath("//*[@aria-describedby='group_children_desc'][2]");
    private final String setChildrenAge = "//select[@name='age']";
    private final By DEC_ROOM_COUNT = By.xpath("//*[@aria-describedby='no_rooms_desc'][1]");
    private final By INC_ROOM_COUNT = By.xpath("//*[@aria-describedby='no_rooms_desc'][2]");
    private final By SEARCH_BUTTON = By.xpath("//*[@class='sb-searchbox__button ']");


    /* открываем страницу и убираем всплывающее окно с кукисами */
    public StartPage openPage() {
        open(cfg.baseUrl());
        $(ACCEPT_COOKIES).click();
        return this;
    }

    /* выбираем город */
    public StartPage chooseCity(String city) {
        $(ACCEPT_COOKIES).shouldNot(Condition.visible);
        $(SEARCH_CITY_INPUT).sendKeys(city);
        $(CLICK_CHOSEN_CITY).click();
        return this;
    }

    /* выбираем дату заезда и выезда */
    public StartPage checkInAndCheckOut(String in, String out) {
        ElementsCollection dates = $$x("//*[@data-date]");
        dates.filterBy(Condition.attribute("data-date", in)).first().click();
        dates.filterBy(Condition.attribute("data-date", out)).first().click();
        return this;
    }

    /* выбираем кол-во взрослых */
    public StartPage setAdultCount(int adultCount) {
        $(CLICK_PEOPLE_INPUT).click();
        if (adultCount > 2) {
            for (int i = 2; i < adultCount; i++) {
                $(INC_ADULT_COUNT).click();
            }
        } else if (adultCount < 2) {
            $(DEC_ADULT_COUNT).click();
        } else {
            return this;
        }
        return this;
    }

    public StartPage setChildrenCount(int childrenCount) {
        if (childrenCount == 0) return this;
        Random randomAge = new Random();
        for (int i = 1; i <= childrenCount; i++) {
            $(INC_CHILDREN_COUNT).click();
            $x(String.format("%s[%d]", setChildrenAge, i)).selectOption(randomAge.nextInt(17) + 1);
        }
        return this;
    }

    public StartPage setRoomsCount(int roomCount) {
        if (roomCount == 1) return this;
        for (int i = 1; i < roomCount; i++) {
            $(INC_ROOM_COUNT).click();
        }
        return this;
    }

    public void searchResults() {
        $(SEARCH_BUTTON).click();
    }

}
