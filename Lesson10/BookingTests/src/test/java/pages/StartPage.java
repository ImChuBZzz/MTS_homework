package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static tests.BaseTest.cfg;

public class StartPage {

    private final By ACCEPT_COOKIES =  By.xpath("//*[@id='onetrust-accept-btn-handler']");
    private final By SEARCH_CITY_INPUT = By.xpath("//*[@id='ss']");
    private final By CLICK_CHOSEN_CITY = By.xpath("//*[contains(@role, 'listbox')]/*[1]");
    private final By CLICK_PEOPLE_INPUT = By.xpath("//*[@id='xp__guests__toggle']");
    private final By DEC_ADULT_COUNT = By.xpath("//*[@aria-describedby=\"group_adults_desc\"][1]");
    private final By INC_ADULT_COUNT = By.xpath("//*[@aria-describedby=\"group_adults_desc\"][2]");
    private final By SEARCH_BUTTON = By.xpath("//*[@class='sb-searchbox__button ']");


    //private final By CHECK_IN = By.xpath("//*[contains(@class, 'bui-calendar__date--today')]");
    //private final By CHECK_OUT = By.xpath("//*[contains(@class, 'bui-calendar__date--today')]/../../../../following-sibling::*/*/*/following-sibling::*/*/*");

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
            for(int i = 2; i < adultCount; i++) {
                $(INC_ADULT_COUNT).click();
            }
        }
        else {
            $(DEC_ADULT_COUNT).click();
        }
        return this;
    }

    public void searchResults() {
        $(SEARCH_BUTTON).click();
    }

    /*
    public StartPage checkInAndCheckOut() {
        $(CHECK_IN).click();
        $(CHECK_OUT).click();
        return this;
    }*/

}
