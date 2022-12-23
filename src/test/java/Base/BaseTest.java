package Base;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.reporters.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.dataHelpers.ConfigReader;

import java.time.Duration;

@Listeners({ExtentReporterNG.class})
public class BaseTest  {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    @BeforeMethod
    @Parameters({"browser"})
    public void startDriver( @Optional("chrome") String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--window-size=1024,768");
            chromeOptions.setHeadless(ConfigReader.getBooleanProperty("headless"));
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver(chromeOptions));
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setHeadless(ConfigReader.getBooleanProperty("headless"));
            firefoxOptions.addArguments("--window-size=1024,768");
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver(firefoxOptions));
        }

        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getIntegerProperty("pageLoadTimeOut")));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getIntegerProperty("implicitlyWait")));

    }
    public static WebDriver getDriver()
    {
        return driver.get();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()){
            Shutterbug.shootPage(getDriver())
                    .withName(result.getMethod().getMethodName())
                    .save("ExtentReports/");
        }
        getDriver().quit();
    }
}
