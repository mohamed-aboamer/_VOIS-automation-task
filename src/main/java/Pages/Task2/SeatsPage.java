package Pages.Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.element.ElementActions;
import utilities.element.JavaScriptManager;

public class SeatsPage {
    By makePaymentButton=By.id("PgBtn");
    By  passengerDetails= By.linkText("Passenger Details");
    By availableSeats =By.cssSelector(".availSeatClassS");
    By selectSeats=By.cssSelector("input[name='SrvcSelectBtnForward']");
    By BoardingPoint=By.id("Forwardboarding-tab");
    By DroppingPoint=By.id("Forwarddroping-tab");
    By customerDetails=By.cssSelector("a#Forwardprofile-tab");
    By mobileNumber=By.cssSelector("input#mobileNo");
    By emailId=By.cssSelector("input#email");
    By genderDropDown=By.name("genderCodeId");
    By availableDroppingPoints=By.cssSelector("li.p-2.Forward-droping");
    private WebDriver driver;
    public SeatsPage(WebDriver driver){
        this.driver=driver;
    }
    public SeatsPage clickOnSeat(){
        driver.findElements(selectSeats).get(1).click();
        return this;
    }
    public SeatsPage setDroppingPoint(){
        driver.findElement(DroppingPoint).click();
        driver.findElements(availableDroppingPoints).get(0).click();
        return this;
    }
    public SeatsPage setMobileNumber(String number){

        ElementActions.type(driver,mobileNumber,number);
        return this;
    }

    public SeatsPage setEmail(String email){
        ElementActions.type(driver,emailId,email);
        return this;
    }
    public SeatsPage selectSeat(int num){
        driver.findElements(availableSeats).get(num-1).click();
        return this;
    }
    public SeatsPage selectGender(String gender){
        JavaScriptManager.scrollToElement(driver,passengerDetails);
        ElementActions.doClick(driver,passengerDetails);
        ElementActions.selectByVisibleText(driver,genderDropDown,gender.toUpperCase());
        return this;
    }
    public SeatsPage makePayment(){
        ElementActions.doClick(driver,makePaymentButton);
        return this;
    }



}
