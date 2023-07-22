import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

public class Activity2 {
    WebDriver driver;
    @BeforeClass
    public void beforeClass()
    {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://www.training-support.net/selenium/target-practice");
    }
    @Test
    public void verifyPageTitle() {

        String title = driver.getTitle();
        System.out.println("Title is: " + title);
        Assert.assertEquals(title, "Target Practice");
    }
    @Test
    public void verifyException() {

        WebElement blackButton = driver.findElement(By
                .xpath("//button[text()='Black']"));
        Assert.assertEquals(blackButton.getText(), "black");
    }
    @Test(enabled = false)
    public void verifySkipTest() {
        System.out.println("Test is skipped");
    }
    @Test
    public void verifySkipException() {

        throw new SkipException("Skip test case");
    }
    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
