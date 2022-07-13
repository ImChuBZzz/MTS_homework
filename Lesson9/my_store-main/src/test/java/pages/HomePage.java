package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.BaseTest.cfg;

public class HomePage {

    private final By GO_TO_WEAR = By.xpath("//*[text()='перейти в каталог']");

    public HomePage openPage() {
        open(cfg.baseUrl());
        return this;
    }

    public HomePage clickGoToWear() {
        $(GO_TO_WEAR).shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
        return this;
    }
}
