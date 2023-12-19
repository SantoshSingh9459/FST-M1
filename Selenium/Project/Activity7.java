package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Activity7 {
    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://alchemy.hguy.co/jobs/");
    }
    @Test
    public void verifyPostJob() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Random rand = new Random();
        int randomNum = rand.nextInt((999 - 100) + 1) + 100;
        String emailId = "test"+randomNum+"@gmail.com";
        //Click on post a job
        driver.findElement(By
                .xpath("//a[contains(@href,'post-a')]")).click();
        //Enter required details
        driver.findElement(By.xpath("//input[@id='create_account_email']"))
                .sendKeys(emailId);
        driver.findElement(By.xpath("//input[@id='job_title']"))
                .sendKeys("Quality Analyst");
        WebElement jobType = driver.findElement(By.xpath("//select[@id='job_type']"));
        Select select = new Select(jobType);
        select.selectByVisibleText("Freelance");
        driver.switchTo().frame("job_description_ifr");

        WebElement descriptionBox = driver.findElement(By.xpath("//body[@id='tinymce']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", descriptionBox);
        descriptionBox.click();
        String description = "This is a test job";
        Actions actions = new Actions(driver);
        actions.sendKeys(description).build().perform();
        driver.switchTo().defaultContent();
        driver.findElement(By
                .xpath("//input[@id='application']"))
                .sendKeys("testexample@gmail.com");
        driver.findElement(By
                .xpath("//input[@id='company_name']")).sendKeys("STest Job");
        driver.findElement(By.xpath("//input[@value='Preview']")).click();
        List<String> expected = new ArrayList<>();
        //expected.add(emailId);
        expected.add("Quality Analyst");
        expected.add("STest Job");
        expected.add("Freelance");
        //expected.add("This is a test job");
        //expected.add("testexample@gmail.com");

        //click on submit listing
        driver.findElement(By
                .xpath("//input[@value='Submit Listing']")).click();
        //Click on jobs
        driver.findElement(By
                .xpath("//a[contains(@href,'jobs/jobs')]")).click();
        //verify job listing
        By byFirstJobListing = By.xpath("(//ul[contains(@class,'listings')]" +
                        "//li//div//h3)[1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(byFirstJobListing));
        WebElement firstJobListing = driver.findElement(By
                .xpath("(//ul[contains(@class,'listings')]" +
                        "//li//div//h3)[1]"));
        //firstJobListing.click();
        System.out.println("Posted job is "+firstJobListing.getText());
        List<String> actual = new ArrayList<>();
        actual.add(firstJobListing.getText());
        actual.add(driver.findElement(By
                .xpath("(//ul[contains(@class,'listings')]" +
                        "//li//div//strong)[1]")).getText());
        actual.add(driver.findElement(By
                .xpath("(//ul[contains(@class,'listings')]" +
                        "//li//div//following-sibling::ul//li)[1]")).getText());
        System.out.println(actual);
        System.out.println(expected);
        Assert.assertEquals(actual, expected);
    }
    @AfterClass
    public void closeBrowser()
    {
        driver.close();
    }
}
