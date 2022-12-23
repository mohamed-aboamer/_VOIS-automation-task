package Pages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.element.ElementActions;


public class CartPage {

    public By subTotal = By.id("sc-subtotal-label-activecart");
    By goToCartLink= By.linkText("Go to Cart");
    public By cart=By.id("nav-cart-count");
    private WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver=driver;
    }

    public CartPage goToCart(){
        ElementActions.doClick(driver,goToCartLink);
        return this;
    }



}
