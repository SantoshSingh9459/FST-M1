package project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity3 {
    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://alchemy.hguy.co/jobs/");
    }
    @Test
    public void verifyUrlHeaderImage()
    {
        String urlHeaderImage = driver.findElement(By
                .xpath("//div[contains(@class,'img')]//img"))
                .getAttribute("src");
        System.out.println("Url for header image: "+urlHeaderImage);

    }
    @AfterClass
    public void closeBrowser()
    {
        driver.close();
    }
}
