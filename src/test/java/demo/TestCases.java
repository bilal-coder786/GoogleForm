package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import demo.wrappers.Wrappers;

public class TestCases {
    static ChromeDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
    }

    @Test
    public static void testCase01() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        WebElement nameElement = driver.findElement(By.xpath("//div[@class='rFrNMe k3kHxc RdH0ib yqQS1 zKHdkd']/div/div/div/input"));
       //Thread.sleep(3000);
       Wrappers.enterText(nameElement, "Crio Learner");
       System.out.println("Wait 1");

      // Thread.sleep(3000);
        WebElement autoElement = driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"));
        String txt = "I want to be the best QA Engineer! ";
        String Epochtime = Wrappers.getEpochTimeString();
        Wrappers.enterText(autoElement, txt + " "+ Epochtime );
        System.out.println("Wait 2");


        Wrappers.radioButton(driver, "0 - 2");

        // Using explicit wait for the checkBox method in Wrappers
        Wrappers.checkBox(driver, "Java");
        Wrappers.checkBox(driver, "Selenium");
        Wrappers.checkBox(driver, "TestNG");

        System.out.println("Wait 3");
        WebElement dropDownWebElement = driver.findElement(By.xpath("//div[contains(@class,'DEh1R')]"));
        Wrappers.clickonElement(driver, dropDownWebElement);
        Wrappers.dropDownClick(driver, "Mr");

        WebElement dateInput = driver.findElement(By.xpath("//input[@type='date']"));
        String sevenDaysAgoDate = Wrappers.getdateSevenDaysAgo();
        Wrappers.enterText(dateInput, sevenDaysAgoDate);

        WebElement hoursElement = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement mintElement = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        WebElement submitBtn = driver.findElement(By.xpath("//span[text()='Submit']"));
        Wrappers.enterText(hoursElement, "10");
        Wrappers.enterText(mintElement, "30");
        Wrappers.clickonElement(driver, submitBtn);

        WebElement successMsg = driver.findElement(By.xpath("//div[@class='vHW8K']"));
        String expectedMsg = "Thanks for your response, Automation Wizard!";
        Assert.assertEquals(successMsg.getText().trim(), expectedMsg);
    }

    @AfterTest
    public void endTest() throws InterruptedException {
        driver.close();
        driver.quit();
    }
}
