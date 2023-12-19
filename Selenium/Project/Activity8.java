package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Activity8 {
    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://alchemy.hguy.co/jobs/wp-admin/");
    }
    @Test
    public void verifyBackEndLogin() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String user = "root";
        //Enter username and password
        WebElement username = driver.findElement(By.xpath("//input[@id='user_login']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='user_pass']"));
        WebElement login = driver.findElement(By.xpath("//input[@id='wp-submit']"));
        //Enter login details
        username.sendKeys(user);
        password.sendKeys("pa$$w0rd");
        login.click();
        String actualUser = driver.findElement(By.
                xpath("//a[contains(@href,'profile') and @aria-haspopup='true']//span"))
                .getText();
        Assert.assertEquals(actualUser, user);
    }
    @AfterClass
    public void closeBrowser()
    {
        driver.close();
    }
}
