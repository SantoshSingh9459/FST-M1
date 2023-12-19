package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity2 {
    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://alchemy.hguy.co/jobs/");
    }
    @Test
    public void verifyHeading()
    {
        String heading = driver.findElement(By.xpath("//h1[@class='entry-title']")).getText();
        Assert.assertEquals(heading,"Welcome to Alchemy Jobs");
    }
    @AfterClass
    public void closeBrowser()
    {
        driver.close();
    }
}
