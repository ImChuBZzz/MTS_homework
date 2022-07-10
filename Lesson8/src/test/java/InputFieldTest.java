import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class InputFieldTest {

    @Test
    public void searchFieldTest() throws InterruptedException {

        String TEST_FIELD_INPUT = "ФУТБОЛКА ПОЛО ЧЕРНАЯ (М)";

        System.setProperty("webdriver.edge.driver", "D:\\Apps\\drivers\\edge\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().setSize(new Dimension(1280, 960));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            driver.get("https://homebrandofficial.ru/wear");
//            driver.findElement(By.cssSelector("input[name='query']")).sendKeys(TEST_FIELD_INPUT + Keys.ENTER);
//            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".js-store-filters-prodsnumber")));
//            String foundResult = driver.findElement(By.cssSelector(".t-store__card__title")).getText();

            driver.findElement(By.xpath("//*[contains(@name, 'query')]")).sendKeys(TEST_FIELD_INPUT + Keys.ENTER);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@class, 'js-store-filters-prodsnumber')]/span")));
            String foundResult = driver.findElement(By.xpath("//*[contains(@class, 't-store__card__textwrapper')]/div[1]")).getText();

            Assert.assertEquals(TEST_FIELD_INPUT, foundResult);
        } finally {
            Thread.sleep(2500);
            driver.close();
        }

    }
}
