package TestUtilities;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class GenericUtility {

    public static WebDriver webDriver;
    public static final String CHROMEBROWSER="chrome";
    public static final String  USERDIR=System.getProperty("user.dir");
    public static final int ACTIVE_STATUS=200;
    public static final int INACTIVE_STATUS=404;

    //Gets property value from Config.properties file
    public static String getPropValue(String keyName) throws Exception
    {

        Properties properties=new Properties();
        InputStream inputStream = null;
        try{
            inputStream=new FileInputStream(USERDIR+"\\src\\main\\resources\\config.properties");
        } catch (Exception e)
        {
            System.out.println("Exception occurred"+e.getMessage());

        }

        properties.load(inputStream);
        return (properties.getProperty(keyName).toString());
    }

    //Setup the browser and initiates it
    public static void setupBrowser() throws Exception
    {
        String browserName=getPropValue("browserName");
        String driverPath=getPropValue("driverPath");

        switch (browserName)
        {
            case CHROMEBROWSER: {

                System.setProperty("webdriver.chrome.driver",driverPath);
                webDriver=new ChromeDriver();
                webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            }
        }


    }

    //Navigates to specific URL
    public static void navigatetoURL(String url)
    {
        webDriver.get(url);
    }

    //Checks active links on webpage
    public static void chkActiveLinks() {

        try {

            //fetch all <A> tag elements
            List<WebElement> listATags = webDriver.findElements(By.tagName("a"));

            //Repeat until all elements in list
            for (WebElement webelement : listATags) {

                String urlLink;

                if (webelement.getAttribute("href") != null) {
                    urlLink=webelement.getAttribute("href");
                    if(urlLink.contains("http"))
                    {
                        checkURLStatus(urlLink);
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    //Checks URL status i.e 200 or 404 by making the request to specific URL
    public static void checkURLStatus(String URL) {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGetRequest = new HttpGet(URL);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGetRequest);
            int httpStatus;
            httpStatus= httpResponse.getStatusLine().getStatusCode();

            if ( httpStatus == INACTIVE_STATUS)
                System.out.println("THIS URL IS INACTIVE->"+URL +", STATUS CODE IS->"+httpStatus);
            else if( httpStatus==ACTIVE_STATUS)
                System.out.println("THIS URL IS ACTIVE->"+URL +", STATUS CODE IS->"+httpStatus);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
