package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.ProjectConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Before;


public abstract class BaseTest {

    public static final ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    @Before
    public void init() {
        WebDriverManager.chromedriver().cachePath("D:\\Apps\\drivers\\chrome").setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.headless = false;
        Configuration.browserSize = "1280x960";
    }

    @After
    public void shutDown() {
        Selenide.closeWebDriver();
    }

}
