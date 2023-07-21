package project;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class projectActivity2 {

    AndroidDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void setUp() throws MalformedURLException, InterruptedException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.google.android.keep");
        options.setAppActivity(".activities.BrowseActivity");
        options.noReset();

        URL serverURL = new URL("http://localhost:4723/wd/hub");

        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        Thread.sleep(10000L);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void addTasksTest()
    {

        String expectedTitle = "TestTask";
        String expectedDesc = "This is for appium project";
        driver.findElement(AppiumBy.accessibilityId("New text note")).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("editable_title")));
        driver.findElement(AppiumBy.id("editable_title")).sendKeys(expectedTitle);
        driver.findElement(AppiumBy.id("edit_note_text")).sendKeys(expectedDesc);
        driver.findElement(AppiumBy.accessibilityId("Navigate up")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy
                .id("browse_note_interior_content")));
        String actualTitle = driver.findElement(AppiumBy.id("index_note_title")).getText();
        String actualDesc = driver.findElement(AppiumBy.id("index_note_text_description")).getText();;
        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertEquals(actualDesc, expectedDesc);

    }

    @AfterClass
    public void tearDown()
    {
        //driver.quit();
    }
}
