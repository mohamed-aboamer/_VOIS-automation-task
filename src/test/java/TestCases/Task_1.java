package TestCases;

import Base.BaseTest;
import Pages.amazon.*;
import assertion.Validation;
import org.testng.annotations.Test;

public class Task_1 extends BaseTest {

    private static AmazonHomePage amazonHomePage;
    private static SearchPage searchPage;

    private static ProductPage productPage;
    private static CartPage cartPage;

    private static TodayDealsPage todayDealsPage;
    @Test
    public void Scenario_1 () {
        amazonHomePage = new AmazonHomePage(getDriver());
        searchPage = amazonHomePage
                .loadPage()
                .clickOnSearch()
                .searchFor("car accessories")
                .submit();
        productPage = searchPage.clickOnLink(0);
        cartPage =  productPage.addToCart();
        cartPage.goToCart();
        Validation.using(getDriver()).verifyText(cartPage.cart,"1");
        Validation.using(getDriver()).verifyText(cartPage.subTotal,"Subtotal (1 item):");
    }
    @Test
    public void Scenario_2() {
        amazonHomePage = new AmazonHomePage(getDriver())
                            .loadPage();
        todayDealsPage= amazonHomePage.openDealsPage();
        todayDealsPage.applyFilters();
        todayDealsPage.selectPage(4);
        productPage=todayDealsPage.selectRandomItem();
        cartPage=productPage.addToCart();
        cartPage.goToCart();
        Validation.using(getDriver()).verifyText(cartPage.cart,"1");
        Validation.using(getDriver()).verifyText(cartPage.subTotal,"Subtotal (1 item):");
    }
}
