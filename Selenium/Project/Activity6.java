package project;

import com.sun.jdi.request.DuplicateRequestException;
import io.github.bonigarcia.wdm.WebDriverManager;
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

public class Activity6 {
    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://alchemy.hguy.co/jobs/");
    }
    @Test
    public void verifySearchJob()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Click on jobs
        driver.findElement(By
                .xpath("//a[contains(@href,'jobs/jobs')]")).click();
        //search with Banking
        driver.findElement(By
                .xpath("//input[@id='search_keywords']")).sendKeys("Banking");
        //click on search jobs
        driver.findElement(By
                .xpath("//input[@value='Search Jobs']")).click();
        //click on first listing
        By byFirstJobListing = By.xpath("(//ul[contains(@class,'listings')]" +
                        "//li//div//h3)[1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(byFirstJobListing));
        WebElement firstJobListing = driver.findElement(By
                .xpath("(//ul[contains(@class,'listings')]" +
                        "//li//div//h3)[1]"));
        firstJobListing.click();
        //click on Apply job
        By byApplyForJob = By.xpath("//input[contains(@class,'application')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(byApplyForJob));
        WebElement applyForJob = driver.findElement(By
                .xpath("//input[contains(@class,'application')]"));
        applyForJob.click();
        //get Text from Email
        By byJobEmail = By
                .xpath("//a[contains(@class,'job')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(byJobEmail));
        WebElement jobEmail = driver.findElement(By
                .xpath("//a[contains(@class,'job')]"));
        System.out.println("Email for applying job is "+jobEmail.getText());
    }
    @AfterClass
    public void closeBrowser()
    {
        driver.close();
    }
}
