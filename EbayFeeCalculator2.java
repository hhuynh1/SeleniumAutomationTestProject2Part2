/**
 *
 *  ITEC 4260
 *  Dr. Im
 *  Georgia Gwinnett College
 *
 *  Project 2: Real World System Testing with Selenium
 *
 *  Created by Henry Huynh
 *  Date: 4/09/2018
 */

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EbayFeeCalculator2 {

    // Declaring instance of WebElement and WebDriver objects
    public static WebDriver driver;
    public static WebElement category1;
    public static WebElement category2;
    public static WebElement category3;
    public static WebElement businessIndustrial;
    public static WebElement printingAndGraphics;
    public static WebElement commercialPrintingPresses;
    public static WebElement sellingFormat;
    public static WebElement fixedPrice;
    public static WebElement buyItNow;
    public static WebElement valuePack;
    public static WebElement calculate;
    public static WebElement estimatedTotal;

    @BeforeClass
    public static void initializeDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Software Development\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.fees.ebay.com/feeweb/feecalculator");

        setupWebElement();
        executeBehavior();
    }

    // Implementing test method and creating a initialize Driver method
    public static void setupWebElement() {
        // Initializing WebElement objects to avoid null pointer exceptions
        category1 = driver.findElement(By.xpath("//*[@id=\"btn_cat-level1\"]"));
        businessIndustrial = driver.findElement(By.xpath("//*[@id=\"cat-level1_4\"]"));
        category2 = driver.findElement(By.xpath("//*[@id=\"btn_cat-level2\"]"));
        sellingFormat = driver.findElement(By.id("dropdown_dd_listing_format"));
        fixedPrice = driver.findElement(By.id("dd_listing_format_1"));
        buyItNow = driver.findElement(By.id("finalsaleprice"));
        valuePack = driver.findElement(By.xpath("//*[@id=\"feetype\"]/ul/li[4]/span[1]"));
        estimatedTotal = driver.findElement(By.id("estimated_total"));
        calculate = driver.findElement(By.id("calcbutton"));
    }

    public static void executeBehavior() throws InterruptedException {
        // Implementing behavior
        category1.click();
        businessIndustrial.click();

        category2.click();

        // Web Driver must be slowed down
        // Implementing WebDriverWait class to slow down action
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"options_cat-level2\"]/li[18]"))));
        printingAndGraphics = driver.findElement(By.xpath("//*[@id=\"options_cat-level2\"]/li[20]"));
        Thread.sleep(1000);
        printingAndGraphics.click();

        // Web Driver must be slowed down
        // Implementing WebDriverWait class to slow down action
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"btn_cat-level3\"]"))));
        category3 = driver.findElement(By.xpath("//*[@id=\"dropdown_cat-level3\"]"));
        category3.click();

        // Web Driver must be slowed down
        // Implementing WebDriverWait class to slow down action
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"btn_cat-level3\"]"))));
        commercialPrintingPresses = driver.findElement(By.xpath("//*[@id=\"options_cat-level3\"]/li[3]\n"));
        commercialPrintingPresses.click();

        // Implementing behavior
        sellingFormat.click();
        fixedPrice.click();
        buyItNow.click();
        buyItNow.sendKeys("5000");
        valuePack.click();

        // Web Driver must be slowed down
        // Implementing WebDriverWait class to slow down action
        wait.until(ExpectedConditions.elementToBeClickable((By.id("calcbutton"))));
        calculate = driver.findElement(By.id("calcbutton"));
        Thread.sleep(4000);
        calculate.click();
    }

    // Testing method with Asset.equals
    @Test
    public void testEbayFees() throws InterruptedException {
        Thread.sleep(3000);
        String totalStr = estimatedTotal.getText();
        Double total = Double.parseDouble(totalStr);

        double expected = 212.30;
        double actual = total;
        double delta = actual - expected;

        Assert.assertEquals(expected,actual,delta);
    }

}
