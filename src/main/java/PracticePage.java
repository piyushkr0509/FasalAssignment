import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.Random;

public class PracticePage extends Helper{

    protected PracticePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "subcategories")
    public  WebElement Subcategories;
    @FindBy(xpath = "//*[contains(@class,'productsSortForm')]")
    public WebElement setfilterdropdown;


    public  void setWomancategory() throws InterruptedException {
        select("Women");
        waitForElementById("subcategories");
        scrolldown(3);




    }

    public String selectFilterRandomFromlist() throws InterruptedException, ElementNotInteractableException, ElementClickInterceptedException {
        String filterapplied="";

        String filterList[]={"Price: Lowest first","Price: Highest first","Product Name: A to Z","Product Name: Z to A","In stock","Reference: Lowest first","Reference: Highest first"};

        Random rand = new Random();
        Thread.sleep(20000);

        filterapplied = filterList[rand.nextInt(filterList.length)];
        ReporterOutput.ReporterLog("Applying filter: -" + filterapplied);


        return filterapplied;
    }




    public void applyFilter(int count) throws InterruptedException, ElementClickInterceptedException{
        int i =1;
        while (i<=count) {
            select("Price: Lowest first");
            select(selectFilterRandomFromlist());

        i+=1;
        }



    }

    public void addItemcartbyPrice(String amount) throws InterruptedException {


         List<WebElement> pricelist = driver.findElements(By.xpath("//*[contains(@class,'price product-price')]"));
         for(WebElement el : pricelist){
            if (el.getText().equalsIgnoreCase(amount))
                el.click();
             Thread.sleep(2000);
         }
        select("Proceed to checkout");
        List<WebElement> cartitem=driver.findElements(By.xpath("//*[contains(@class,'cart_product')]"));
        cartitem.get(7).click();
       List <WebElement> setSize=driver.findElements(By.xpath("//*[contains(@id,'group_1')]"));
       waitforElement(setSize.get(0));
       setSize.get(0).click();
       Thread.sleep(5000);
        select("Add to cart");
        select("Proceed to checkout");
        scrolldown(1);
        List<WebElement> proceedtocheckout=driver.findElements(By.xpath("//*[contains(@class,'icon-chevron-right right')]"));
        //proceedtocheckout.get(0).click();
        /*
        *
        * tODO = ADD NEXT BUTTON
        * */
        WebElement checkoutfirststep=driver.findElement(By.xpath("//*[contains(@href,'http://automationpractice.com/index.php?controller=order&step=1')]"));
        checkoutfirststep.click();
        //select("Proceed to checkout");








    }
    public void setSignIn() throws InterruptedException {
        List<WebElement> entermail=driver.findElements(By.xpath("//*[contains(@id,'email')]"));
        entermail.get(1).sendKeys("kr.piyush0509@gmail.com");
        WebElement enterpwd= driver.findElement(By.id("passwd"));
        enterpwd.sendKeys("test1234");
       WebElement singinbutton=driver.findElement(By.id("SubmitLogin"));
       singinbutton.click();
        Thread.sleep(15000);
        scrolldown(1);
        driver.findElement(By.xpath("//*[contains(@name,'message')]")).sendKeys("hjbhk hjkbjb ");
       WebElement proceedstep2=driver.findElement(By.xpath("//*[contains(@name,'processAddress')]"));
       proceedstep2.click();



    }
    public void setShipping() throws InterruptedException {
        WebElement acceptbox=driver.findElement(By.id("cgv"));
        acceptbox.click();
        WebElement procees3rdstep=driver.findElement(By.xpath("//*[contains(@class,'button btn btn-default standard-checkout button-medium')]"));
        procees3rdstep.click();
        //select("Proceed to checkout");

    }
    public void setPayment(String paymentType) throws InterruptedException {
        select(paymentType);
        select("I confirm my order");
        WebElement confirmationmessage=driver.findElement(By.xpath("//*[contains(@class,'box')]"));
        waitforElement(confirmationmessage);
        ReporterOutput.ReporterLog("Success message to user is "  +confirmationmessage.getText());






    }











}
