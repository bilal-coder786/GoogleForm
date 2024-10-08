package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {

    public static void enterText(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEpochTimeString() {
        try {
            long epochTime = System.currentTimeMillis() / 1000;
            return String.valueOf(epochTime);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void radioButton(ChromeDriver driver, String radioButtonText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'OvPDhc') and contains(text(),'"
                    + radioButtonText + "')]/../../..//div[@class='vd3tt']")));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkBox(ChromeDriver driver, String checkboxText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'n5vBHf') and contains(text(),'"
                    + checkboxText + "')]/../../preceding-sibling::div[contains(@id,'i')]")));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dropDownClick(ChromeDriver driver, String dropDownText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'QXL7Te')]//span[text()='"
                    + dropDownText + "']")));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickonElement(ChromeDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getdateSevenDaysAgo() {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateMinus7Days = currentDate.minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateMinus7Days.format(formatter);
    }

    public static boolean handleAlert(ChromeDriver driver) {
        boolean status = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
        status = true;
        return status;
    }
}
