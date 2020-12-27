import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;



public class SeleniumBase {

    public static WebDriver driver;


    public static void startBrowser(String BrowserName, String URL) {

        try {


            if (BrowserName.equalsIgnoreCase("Chrome")) {

                driver = new ChromeDriver();


            } else if (BrowserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();

            } else if (BrowserName.equalsIgnoreCase("safari")) {
                driver = new SafariDriver();


            } else {
                System.out.println("Not able to read browser, Please check Test.xml file");
            }
            driver.manage().window().maximize();
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }
        catch (Exception e){
            e.printStackTrace();
        }


    }


    public SeleniumBase() {

        switch (System.getProperty("os.name")) {
            case "Windows 10":
                System.setProperty("webdriver.chrome.driver", "chromedriver-windows.exe");
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
                break;
            case "Linux":
                System.setProperty("webdriver.chrome.driver", "chromedriver-linux");

                break;
            case "Mac OS X":
                System.setProperty("webdriver.chrome.driver", "chromedriver-mac");
               System.setProperty("webdriver.gecko.driver", "");
                break;
            default:
                Assert.fail("Unable to detect current os: " + System.getProperty("os.name"));
        }
    }






}
