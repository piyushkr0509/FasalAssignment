import okhttp3.*;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Helper extends SeleniumBase {


    Properties prop = new Properties();
    InputStream input;

    private int timeOut = 30;

    public Helper() {
        super();
    }


    public static String[] MakeGetRequest(String URL, int expectedCode) {
        String[] responseArray = {};
        OkHttpClient client = new OkHttpClient();

        try {
            Request request = new Request.Builder().url(URL).get().build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String jsonData = response.body().string();
            JSONObject object = new JSONObject(jsonData);
            ReporterOutput.ReporterLog(object.toString());
            int responseCode = response.code();
            Assert.assertEquals(responseCode, expectedCode);




        } catch (IOException e) {
            e.printStackTrace();
        }


        return responseArray;

    }

    public static void MakePostRequest(String url, int expectedCode, String postbody) {
        OkHttpClient client = new OkHttpClient();


        RequestBody post = RequestBody.create(MediaType.parse("application/json"), postbody);
        System.out.println("Hi " + postbody);


        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(post)
                    .build();


            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String jsonData = response.body().string();
            JSONObject object = new JSONObject(jsonData);
            ReporterOutput.ReporterLog(object.toString());
            int responseCode = response.code();
            Assert.assertEquals(responseCode, expectedCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void MakePutRequest(String url, int expectedCode, String putbody) {
        OkHttpClient client = new OkHttpClient();


        RequestBody put = RequestBody.create(MediaType.parse("application/json"), putbody);
        System.out.println("Hi " + putbody);


        try {
            Request request = new Request.Builder()
                    .url(url)
                    .put(put)
                    .build();


            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String jsonData = response.body().string();
            JSONObject object = new JSONObject(jsonData);
            ReporterOutput.ReporterLog(object.toString());
            int responseCode = response.code();
            Assert.assertEquals(responseCode, expectedCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void MakeDeleteRequest(String url, int expectedCode) {
        OkHttpClient client = new OkHttpClient();


        try {
            Request request = new Request.Builder()
                    .url(url)
                    .delete()
                    .build();


            Response response = client.newCall(request).execute();
            assert response.body() != null;
            int responseCode = response.code();
            Assert.assertEquals(responseCode, expectedCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getKeyFromProp(String key) throws IOException {
        String value = "";

        RequestBody body = RequestBody.create(MediaType.parse("raw"), value);
        try {

            input = new FileInputStream("src/main/resources/text.properties");
            prop.load(input);
            value = prop.getProperty(key);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return value;


    }


    public static WebElement select(String text) throws InterruptedException {
        WebElement element = getElementwithText(text);
        element.click();
        Thread.sleep(3000);
        return element;
    }

    public static WebElement getElementwithText(String text) {
        WebElement el = null;


            el = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));

        return el;
    }
    public void waitForElementById(String elementValue) {
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.id(elementValue)));
    }
    public static void scrollToElementText(String txt) throws InterruptedException {

        WebElement  element =driver.findElement(By.xpath("//*[contains(text(),'" + txt + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);

    }

    public void scrolldown(int n) {

        for (int i = 0; i <= n; i++) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,200)");
        }

    }
    public boolean isElementFound(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        }
        catch (NoSuchContextException e) {
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public void waitforElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException ex) {
            System.out.println("Element Not found within Time");
            //ex.printStackTrace();

        }
    }
    public void waitForElmenttoclickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (NoSuchElementException ex) {
            System.out.println("Element Not found within Time");

        }
    }







}
