package Pages.Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.dataHelpers.ConfigReader;
import utilities.element.ElementActions;
import utilities.element.JavaScriptManager;

import java.util.List;

public class HomePage {

    By boardingInputField=By.id("fromPlaceName");
    By droppingInputField=By.id("toPlaceName");
    By chikkamagaluruBengaluru =By.linkText("Chikkamagaluru - Bengaluru");
    By nextButtonCalender=By.cssSelector("a.ui-datepicker-next.ui-corner-all");
    By prevButtonCalender=By.cssSelector("a.ui-datepicker-prev.ui-corner-all");
    By dayDate=By.cssSelector("a.ui-state-default");
    By datePicker=By.id("ui-datepicker-div");
    By searchForBusButton=By.cssSelector("button.btn.btn-primary.btn-lg.btn-block.btn-booking");
    By popularRoutes=By.cssSelector("section.popular-section>div>div>div");
    private WebDriver driver;
    public HomePage (WebDriver driver){
        this.driver=driver;
    }
    public HomePage loadPage(){
        driver.get(ConfigReader.getStringProperty("task2.baseURL"));
        return this;
    }
    public HomePage setBoardingPoint(String boardingPoint){
        ElementActions.type(driver,boardingInputField,boardingPoint);
        return this;
    }
    public HomePage setDroppingPoint(String droppingPoint){

        ElementActions.type(driver,droppingInputField,droppingPoint);
        return this;
    }
    public HomePage chooseFromPopularRoutes(){
        JavaScriptManager.scrollToElement(driver,popularRoutes);
        ElementActions.doClick(driver,chikkamagaluruBengaluru);
        return this;
    }

    public HomePage setArrivalDate(int day){
        ElementActions.doClick(driver,nextButtonCalender);
        JavaScriptManager.scrollToElement(driver,datePicker);
        List<WebElement> list=driver.findElements(dayDate);
        list.get(day-1).click();
        return this;
    }
    public SeatsPage searchForBus(){
        ElementActions.doClick(driver,searchForBusButton);
        return new SeatsPage(driver);
    }


}