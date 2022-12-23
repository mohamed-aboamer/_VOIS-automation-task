package Pages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.browser.JavaScriptWait;
import utilities.dataHelpers.ExcelReader;
import utilities.element.ElementActions;
import utilities.element.JavaScriptManager;
import utilities.element.WebElementWait;

public class TodayDealsPage {

    private static String filePath="resources/testdata/testData.xlsx";
    private static  String sheetName="task1";
    By filtersBoxes = By.className("CheckboxFilter-module__gridFilterOption_hdG5xZdR2ZvDkQKkl_d49");
    By filtersLinks = By.className("LinkFilterOption-module__linkFilterOptionListElement_AzC4LFMfeFF1CkwveJM01");

    By randomItem=By.partialLinkText("Gogailen Bone Conduction Headphones");
    private WebDriver driver;
    public TodayDealsPage(WebDriver driver){
        this.driver=driver;
    }

    public TodayDealsPage applyFilters(){
        ExcelReader excelReader=new ExcelReader(filePath,sheetName);
        ElementActions.selectFilter(driver,filtersBoxes,(String) excelReader.getCellData(1,1));
            ElementActions.selectFilter(driver,filtersBoxes,(String) excelReader.getCellData(2,1));
            ElementActions.selectFilter(driver,filtersLinks,(String) excelReader.getCellData(3,1));
        return this;
    }

    public TodayDealsPage selectPage(int page){

        select_Page(page);
        //JavaScriptManager.scrollToTopPage(driver);
        return this;
    }
    private void select_Page(int page){
        By paging= By.cssSelector("ul.a-pagination");
        By elem=By.xpath("//li[starts-with(@aria-label,'Page "+page+"')]");
        while (!WebElementWait.isPresent(driver,elem,10)){

            JavaScriptManager.scrollToElement(driver,paging);
            WebElementWait.waitUntilElementBeClickable(driver,By.xpath("//li[starts-with(@aria-label,'Page 1')]"));
            ElementActions.doClick(driver,By.cssSelector("li.a-last>a"));
        }
        ElementActions.doClick(driver,elem);
    }
    public ProductPage selectRandomItem(){
        JavaScriptManager.scrollToElement(driver,randomItem);
        driver.findElement(randomItem).click();
        return new ProductPage(driver);
    }
}
