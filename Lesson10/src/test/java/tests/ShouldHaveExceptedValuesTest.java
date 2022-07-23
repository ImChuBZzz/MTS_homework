package tests;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.StartPage;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;


public class ShouldHaveExceptedValuesTest extends BaseTest {
    private final String CITY = "Барселона";
    private final LocalDate CHECK_IN = LocalDate.now();
    private final LocalDate CHECK_OUT = LocalDate.now().plusDays(7);
    private final Integer ADULT_COUNT = 1;
    private final Integer CHILDREN_COUNT = 0;
    private final Integer ROOM_COUNT = 1;

    // делает дату из ГГГГ-ММ-ДД в читаемый вид
    private String readableDate(LocalDate date) {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(new Locale("ru"))
                .format(date);
    }

    @Before
    public void letsGo() {
        StartPage startPage = new StartPage();
        startPage.openPage()
                .chooseCity(CITY)
                .checkInAndCheckOut(CHECK_IN.toString(), CHECK_OUT.toString())
                .setAdultCount(ADULT_COUNT)
                .searchResults();
    }

    @Test
    public void shouldHaveExceptedValuesTest() {

        //letsGo();

        By propertyFound = By.xpath("//h1[@class='e1f827110f d3a14d00da']");                                                      // локатор на кол-во предложений
        ElementsCollection propertyCards = $$x("//*[@data-testid='property-card']");                           // коллекция карточек с отелями на странице
        int propertyCount = Integer.parseInt($(propertyFound).getText().replaceAll("[^0-9]", ""));          // достаем кол-во доступных вариантов для бронирования в ввиде числа
        int propertyCardsCount = propertyCards.size();
        Assert.assertNotEquals(0, propertyCardsCount);
        Assert.assertNotEquals(0, propertyCount);

        By cityName = By.xpath("//*[@name='ss']");                                                                          // локатор на поле города
        Assert.assertEquals(CITY, $(cityName).getValue());

        By checkIn = By.xpath("//*[@data-testid='date-display-field-start']");                                              // локаторы для заезда и отъезда
        By checkOut = By.xpath("//*[@data-testid='date-display-field-end']");
        String dateIn = $(checkIn).getText().substring($(checkIn).getText().indexOf(" ")).trim() + " г.";                   // достаем даты, удаляя текст до первого пробела,
        String dateOut = $(checkOut).getText().substring($(checkOut).getText().indexOf(" ")).trim() + " г.";                // т.к. я не знаю как вытаскивать текст только из родителя
        String exceptedDayIn = readableDate(CHECK_IN);
        String exceptedDayOut = readableDate(CHECK_OUT);
        Assert.assertEquals(exceptedDayIn.substring(exceptedDayIn.indexOf(" ")).trim(), dateIn);                            // убираем название дня недели
        Assert.assertEquals(exceptedDayOut.substring(exceptedDayOut.indexOf(" ")).trim(), dateOut);

        By peopleInput = By.xpath("//*[@data-testid=\"occupancy-config\"]");                                                // локатор для кол-ва людей и комнат
        String exceptedOccupancyInfo = ($(peopleInput).getText().replaceAll("[^\\w]", ""));                 // делаем из 1 взрослые . 0 дети . 1 комната строку 101 PS первое чт опришло в голову
        String testOccupancyInfo = ADULT_COUNT.toString() + CHILDREN_COUNT.toString() + ROOM_COUNT.toString();
        Assert.assertEquals(exceptedOccupancyInfo, testOccupancyInfo);
    }

}
