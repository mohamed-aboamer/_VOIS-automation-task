package TestCases;
import Base.BaseTest;
import Pages.Task2.*;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class Task2 extends BaseTest {

    private static HomePage homePage;
    private static SeatsPage seatsPage;
    @Description("Booking a seats bus")
    @Test
    public void bookingSeat(){
        homePage=new HomePage(getDriver());
        seatsPage=homePage.loadPage()
                .chooseFromPopularRoutes()
                .setArrivalDate(3)
                .searchForBus();

        seatsPage.clickOnSeat()
                .setDroppingPoint()
                .setEmail("seaf256@example.com")
                .setMobileNumber("6789125987")
                .selectSeat(2)
                .selectGender("male")
                .makePayment();

    }



}
