package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Activity9 {
    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://alchemy.hguy.co/jobs/wp-admin/");
    }
    @Test
    public void verifyJobListingUsingBackEnd() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String user = "root";
        String position = "Software Analyst";
        //Enter username and password
        WebElement username = driver.findElement(By.xpath("//input[@id='user_login']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='user_pass']"));
        WebElement login = driver.findElement(By.xpath("//input[@id='wp-submit']"));
        //Enter login details
        username.sendKeys(user);
        password.sendKeys("pa$$w0rd");
        login.click();
        //Click on Job Listings
        driver.findElement(By.xpath("//div[text()='Job Listings']")).click();
        driver.findElement(By.
                xpath("//a[text()='Add New' and @class='page-title-action']")).click();
        try
        {
            driver.findElement(By.xpath("//button[@aria-label='Close dialog']")).click();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //Enter Position
        WebElement textArea = driver.findElement(By
                .xpath("//textarea[@class='editor-post-title__input']"));
        textArea.click();
        textArea.sendKeys(position);
        //Enter Job Type
        driver.findElement(By.xpath("//input[@id='in-job_listing_type-5']")).click();
        //Enter Tagline
        driver.findElement(By.xpath("//input[@id='_company_tagline']"))
                .sendKeys("This is a test job");
        //Enter company name
        driver.findElement(By.xpath("//input[@id='_company_name']"))
                .sendKeys("STest Job");
        //Click on Publish
        driver.findElement(By.xpath("//button[contains(@class,'publish-button')]"))
                .click();
        By byPublishButton = By.xpath("//div[contains(@class,'publish')]" +
                "//button[contains(@class,'publish-button')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(byPublishButton));
        WebElement publishButton = driver.findElement(By
                .xpath("//div[contains(@class,'publish')]" +
                        "//button[contains(@class,'publish-button')]"));
        publishButton.click();
        By byPublished = By.xpath("//div[contains(text(),'Published')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(byPublished));
        driver.findElement(By.xpath("//a[contains(text(),'View Job')]")).click();
        String actual = driver.findElement(By
                .xpath("//h1[@class='entry-title']")).getText();
        Assert.assertEquals(actual, position);


    }
    @AfterClass
    public void closeBrowser()
    {
        driver.close();
    }
}
