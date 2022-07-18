package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.ExcursionPage;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;


public class ExcursionTest extends BaseTest {
    @Test
    public void excursionSortedAndNotEmpty() {
        ExcursionPage excursionPage = new ExcursionPage();
        excursionPage.openPage()
                .selectPlaceForExcursion("Париж")
                .selectMuseums()
                .selectPriceSort();
        // кол-во у кнопки выбора "музеи"
        int museumsCount = Integer.parseInt($(By.xpath("//*[@class='css-18yal0d']/./*[text()='Музеи']/following-sibling::*")).getText().replaceAll("[^\\w]", ""));

        // это карточки музеев на странице, а точнее я взял часть карточки, которая отвечает за цену экскурсии, потому что если взять всю карточку я не знаю как из той кучи текста достать именно цену
        //ElementsCollection museums = $$x("//*[@class='css-1bmkeys']").shouldBe(CollectionCondition.sizeGreaterThan(0));
        ElementsCollection museums = $$(".css-qu86ds  .css-pori7h .ac78a73c96").shouldBe(CollectionCondition.sizeGreaterThan(0));
        // здесь делаем проверку что кол-во карточек совпадает с тем, что написано около чекбокса, а если больше, то просто смотрим что не нулевое значение
        if(museumsCount <= 60) {
            Assert.assertEquals(museumsCount, museums.size());
        } else {
            Assert.assertNotEquals(0, museums.size());
        }
        // тут делаем список из цен, попутно превращяя их в числовой тип
        List<Integer> prices = museums.stream().map(x -> Integer.parseInt(x.getText().replaceAll("[^0-9]", ""))).collect(Collectors.toList());
        // проверяем что полученые цены и которые мы взяли с сайта отсортированы по возрастанию
        Assert.assertEquals(prices, prices.stream().sorted().collect(Collectors.toList()));
    }
}
