
import org.testng.annotations.*;


public class AutomationPracticeTest extends SeleniumBase {
    PracticePage pc = new PracticePage(driver);
    public  String browsername="chrome";

@BeforeClass(alwaysRun = true)
public static void startserver(){
     String browsername="chrome";
    startBrowser(browsername,"http://automationpractice.com/index.php");

}

    @Test
    public  void shopforItem() throws InterruptedException {

        pc.setWomancategory();
        pc.applyFilter(3);
        pc.addItemcartbyPrice("$27.00");
        pc.setSignIn();
        pc.setShipping();
        pc.setPayment("Pay by bank wire ");



    }







}
