package project;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
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
import java.util.List;

public class projectActivity1 {

    // Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;

    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.google.android.apps.tasks");
        options.setAppActivity(".ui.TaskListsActivity");
        options.noReset();

        // Server Address
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Test method
    @Test
    public void addActivities() {
        //List of Activities
        String[] activitynames = {"Complete Activity with Google Tasks", "Complete Activity with Google Keep", "Complete the second Activity Google Keep"};
        for (String ac : activitynames) {
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Create new task"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("add_task_title"))).sendKeys(ac);
            driver.findElement(AppiumBy.id("add_task_done")).click();
        }
        //Verify there are 3 Activities on the List
        List<WebElement> activityList = driver.findElements(AppiumBy.xpath("(//android.view.View[@content-desc='Mark as complete'])"));
        System.out.println("No of Activities are :" +activityList.size());
        Assert.assertEquals(activityList.size(),3);
        //Clear all Activities
        for(WebElement e : activityList) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//android.view.View[@content-desc='Mark as complete'])[1]"))).click();
        }
        //Verify the clear Activities
        List<WebElement> activityListAfterClear = driver.findElements(AppiumBy.xpath("(//android.view.View[@content-desc='Mark as complete'])"));
        System.out.println("No of Activities are after clear :" +activityListAfterClear.size());
        Assert.assertEquals(activityListAfterClear.size(),1);

    }

    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }


}
