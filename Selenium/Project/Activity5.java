package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Activity5 {
    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://alchemy.hguy.co/jobs/");
    }
    @Test
    public void verifyJobsTitle()
    {
        List<WebElement> listOfNavigation = driver.findElements(By
                .xpath("//ul[contains(@id,'primary-menu')]//li//a"));
        for(WebElement we : listOfNavigation)
        {
            if(we.getText().equals("Jobs"))
            {
                we.click();
                break;
            }
        }
        String jobsTitle = driver.getTitle();
        Assert.assertEquals(jobsTitle,"Jobs – Alchemy Jobs");
    }
    @AfterClass
    public void closeBrowser()
    {
        driver.close();
    }
}
