import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity5 {
    WebDriver driver;
    @BeforeClass(alwaysRun = true)
    public void beforeClass()
    {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://www.training-support.net/selenium/target-practice");
    }
    @Test(groups = {"HeaderTests", "ButtonTests"})
    public void verifyPageTitle() {

        String title = driver.getTitle();
        System.out.println("Title is: " + title);
        Assert.assertEquals(title, "Target Practice");
    }
    @Test(dependsOnMethods = {"verifyPageTitle"}, groups = {"HeaderTests"})
    public void verifyHeader1() {

        WebElement header3 = driver.findElement(By
                .xpath("//h3[@id='third-header']"));
        Assert.assertEquals(header3.getText(), "Third header");
    }
    @Test(dependsOnMethods = {"verifyPageTitle"}, groups = {"HeaderTests"})
    public void verifyHeader2() {

        WebElement header5 = driver.findElement(By
                .xpath("//h5[text()='Fifth header']"));
        System.out.println(header5.getAttribute("class"));
        Assert.assertEquals(header5.getCssValue("color"),
                "rgb(33, 186, 69)");
    }
    @Test(dependsOnMethods = {"verifyPageTitle"}, groups = {"ButtonTests"})
    public void verifyButton1() {

        WebElement button1 = driver.findElement(By
                .xpath("//button[text()='Olive']"));
        Assert.assertEquals(button1.getText(), "Olive");
    }
    @Test(dependsOnMethods = {"verifyPageTitle"}, groups = {"ButtonTests"})
    public void verifyButton2() {

        WebElement button2 = driver.findElement(By.xpath(("//button[text()='Brown']")));
        Assert.assertEquals(button2.getCssValue("color"),
                "rgb(255, 255, 255)");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {

        driver.quit();
    }
}
