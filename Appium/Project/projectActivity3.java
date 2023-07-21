package project;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class projectActivity3 {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();

        URL serverURL = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://v1.training-support.net/selenium");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @Test
    public void chromeTest() throws InterruptedException {
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";

        driver.findElement(AppiumBy.xpath(
                "//android.widget.Button[@text='Get Started!']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=" +
                        "\"Tab Opener Launch new tabs with the click of a button.\"]")));
        driver.findElement(AppiumBy.androidUIAutomator(UiScrollable +
                ".scrollTextIntoView(\"To-Do List\")")).click();
        Thread.sleep(2000L);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=' Clear List']"))
                .click();
        Thread.sleep(2000L);
        List<String> expectedTasks = new ArrayList<>();
        expectedTasks.add("Add tasks to list");
        expectedTasks.add("Get number of tasks");
        expectedTasks.add("Clear the list");
        for (String task : expectedTasks) {
            driver.findElement(AppiumBy
                    .xpath("//android.widget.EditText[@resource-id='taskInput']")).click();
            driver.findElement(AppiumBy
                            .xpath("//android.widget.EditText[@resource-id='taskInput']"))
                    .sendKeys(task);
            Thread.sleep(2000L);
            driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Add Task']"))
                    .click();
            Thread.sleep(2000L);

        }
        List<WebElement> tasksActual = driver.findElements(AppiumBy
                .xpath("//android.view.View[@resource-id='tasksList']//android.view.View"));
        for(WebElement we : tasksActual)
        {
            we.click();
            Thread.sleep(2000L);
        }
        List<String> actualTasks = new ArrayList<>();
        for (WebElement webElement : tasksActual)
        {
            actualTasks.add(webElement.getText());
        }

        System.out.println(actualTasks);
        System.out.println(expectedTasks);

        Assert.assertEquals(actualTasks, expectedTasks);

        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=' Clear List']"))
                .click();
        Thread.sleep(2000L);
        String clearList = driver.findElement(AppiumBy
                .xpath("//android.view.View[@resource-id='tasksCard']" +
                        "//android.view.View//android.view.View")).getText();
        System.out.println(clearList);
        Assert.assertEquals(clearList, "\uF1F8");

    }

    @AfterClass
    public void tearDown()
    {
        //driver.quit();
    }
}
