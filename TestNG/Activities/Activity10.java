import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Activity10 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NULL");

        driver.get("https://www.training-support.net/selenium/simple-form");
    }

    public static List<List<String>> readExcel(String filePath) {
        List<List<String>> data = new ArrayList<List<String>>();
        try {
            FileInputStream file = new FileInputStream(filePath);

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row cells : sheet) {
                List<String> rowData = new ArrayList<String>();
                for (Cell cell : cells) {
                    if (cell != null) {
                        rowData.add(cell.getStringCellValue());
                    }
                }

                data.add(rowData);
            }
            file.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @DataProvider(name = "Registration")
    public static Object[][] signUpInfo() {
        String filePath = "src/test/resources/sample.xlsx";
        List<List<String>> data = readExcel(filePath);
        return new Object[][]{
                {data.get(1).get(1), data.get(1).get(2),
                        data.get(1).get(3), data.get(1).get(4)},
                {data.get(2).get(1), data.get(2).get(2),
                        data.get(2).get(3), data.get(2).get(4)},
                {data.get(3).get(1), data.get(3).get(2),
                        data.get(3).get(3), data.get(3).get(4)},
        };
    }

    @Test(dataProvider = "Registration")
    public void registerTest(String firstName, String lastName, String email,
                             String phoneNumber) {

        WebElement firstNameField = driver.findElement(By
                .xpath("//input[@id = 'firstName']"));
        WebElement lastNameField = driver.findElement(By
                .xpath("//input[@id = 'lastName']"));
        WebElement emailField = driver.findElement(By
                .xpath("//input[@id = 'email']"));
        WebElement phoneNumberField = driver.findElement(By
                .xpath("//input[@id = 'number']"));

        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneNumberField.clear();

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        phoneNumberField.sendKeys(phoneNumber);

        driver.findElement(By
                .xpath("//input[contains(@class, 'green')]")).click();


        Alert message = driver.switchTo().alert();

        System.out.println("Alert message: " + message.getText());
        message.accept();

        driver.navigate().refresh();
    }

    @AfterClass
    public void tearDown() {

        driver.quit();
    }
}