import time
import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

@pytest.mark.usefixtures("setup")
class TestSite:

    def test_verify_job_listing_using_backend(self):
        wait = WebDriverWait(self.driver, 20)
        self.driver.get("https://alchemy.hguy.co/jobs/wp-admin/")
        user = "root"
        position = "Software Analyst"
        username = self.driver.find_element(By.XPATH,"//input[@id='user_login']")
        password = self.driver.find_element(By.XPATH,"//input[@id='user_pass']")
        login = self.driver.find_element(By.XPATH,"//input[@id='wp-submit']")
        username.send_keys(user)
        password.send_keys("pa$$w0rd")
        login.click()
        self.driver.find_element(By.XPATH, "//div[text()='Job Listings']").click()
        self.driver.find_element(By.XPATH, "//a[text()='Add New' and @class='page-title-action']").click()
        try:
            self.driver.find_element(By.XPATH,"//button[@aria-label='Close dialog']").click()
        except:
            print("No close dialog")
        textArea = self.driver.find_element(By.XPATH, "//textarea[@class='editor-post-title__input']")
        textArea.click()
        textArea.send_keys(position)
        self.driver.find_element(By.XPATH, "//input[@id='in-job_listing_type-5']").click()
        self.driver.find_element(By.XPATH, "//input[@id='_company_tagline']").send_keys("This is a test job")
        self.driver.find_element(By.XPATH, "//input[@id='_company_name']").send_keys("STest Job")
        self.driver.find_element(By.XPATH, "//button[contains(@class,'publish-button')]").click()
        time.sleep(10)
        wait.until(expected_conditions
               .visibility_of_element_located((By.XPATH,
                                               "//div[contains(@class,'publish')]//button[contains(@class,'publish-button')]")))
        publishButton = self.driver.find_element(By.XPATH,
                                        "//div[contains(@class,'publish')]//button[contains(@class,'publish-button')]")
        publishButton.click()
        time.sleep(20)
        wait.until(expected_conditions
               .visibility_of_element_located((By.XPATH,
                                               "//div[contains(text(),'Published')]")))
        self.driver.find_element(By.XPATH, "//a[contains(text(),'View Job')]").click()
        actual = self.driver.find_element(By.XPATH, "//h1[@class='entry-title']").text
        print("Position posted: "+actual)
        assert actual == position



