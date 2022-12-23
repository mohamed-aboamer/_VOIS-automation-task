package Pages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.element.ElementActions;

public class ProductPage {
    private WebDriver driver;
    By addToCart = By.id("add-to-cart-button");
    public ProductPage(WebDriver driver){
        this.driver=driver;
    }

    public CartPage addToCart(){
        ElementActions.doClick(driver,addToCart);
        return new CartPage(driver);
    }

}
