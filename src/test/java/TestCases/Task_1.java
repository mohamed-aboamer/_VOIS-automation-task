package TestCases;

import Base.BaseTest;
import Pages.amazon.*;
import assertion.Validation;
import org.testng.annotations.Test;
import utilities.dataHelpers.ExcelReader;

public class Task_1 extends BaseTest {

    private static AmazonHomePage amazonHomePage;
    private static SearchPage searchPage;

    private static ProductPage productPage;
    private static CartPage cartPage;
    private static String filePath="resources/testdata/testData.xlsx";
    private static  String sheetName="task1";
    private static TodayDealsPage todayDealsPage;
    @Test
    public void Scenario_1 () {

        amazonHomePage = new AmazonHomePage(getDriver());
        ExcelReader excelReader=new ExcelReader(filePath,sheetName);
        searchPage = amazonHomePage
                .loadPage()
                .clickOnSearch()
                .searchFor((String) excelReader.getCellData(1,0))
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
