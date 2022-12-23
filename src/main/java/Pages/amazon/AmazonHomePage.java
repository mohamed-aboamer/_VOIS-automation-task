package Pages.amazon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.browser.BrowserActions;
import utilities.dataHelpers.ConfigReader;
import utilities.element.ElementActions;
import utilities.element.JavaScriptManager;

public class AmazonHomePage {
    private WebDriver driver;
    private static Logger log= LogManager.getLogger();
    By newYearsale = By.linkText("New Year Sale");
    By todayDeals = By.linkText("Today's Deals");
    By searchField = By.className("nav-search-field");
    By searchInput = By.id("twotabsearchtextbox");
    By searchButton = By.id("nav-search-submit-button");

    public AmazonHomePage(WebDriver driver){
        this.driver=driver;
    }

    /**
     * click on search button to show or make search bar visible
     * @return
     */
    public AmazonHomePage clickOnSearch(){
        ElementActions.doClick(driver,searchField);
        return this;
    }

    /**
     * @param input String the text to be entered into search bar
     * @return
     */
    public AmazonHomePage searchFor(String input){
        ElementActions.type(driver,searchInput,input);
        return this;
    }
    public AmazonHomePage loadPage(){
        driver.get(ConfigReader.getStringProperty("task1.baseURL"));
        BrowserActions.refreshPage(driver);
        return this;
    }

    public SearchPage submit(){
        ElementActions.doClick(driver,searchButton);
        return new SearchPage(driver);
    }

    public TodayDealsPage openDealsPage(){
        try {
            JavaScriptManager.focusAndClick(driver,todayDeals);
        }
        catch (Exception e){
            log.error("Today's Deals header changed to New Year Sale");
            JavaScriptManager.focusAndClick(driver,newYearsale);
        }
        return new TodayDealsPage(driver);
    }


}
