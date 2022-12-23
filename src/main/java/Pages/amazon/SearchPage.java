package Pages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SearchPage {
    private WebDriver driver;

    By allLinks = By.xpath("//a[@class='a-size-base a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']");
    public SearchPage(WebDriver driver){
        this.driver=driver;
    }

    /**
     * click on n-th link at the search result page
     * @return
     */
    public ProductPage clickOnLink(int linkNumber){

        driver.findElements(allLinks).get(linkNumber).click();
        return new ProductPage(driver);
    }

}
