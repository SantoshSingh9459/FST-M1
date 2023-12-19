import random
import time
import pytest
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.wait import WebDriverWait

@pytest.mark.usefixtures("setup")
class TestSite:

    def test_verify_post_job(self):
        wait = WebDriverWait(self.driver, 20)
        self.driver.get("https://alchemy.hguy.co/jobs/")
        randomNum = random.randint(100, 999)
        emailId = "test"+str(randomNum)+"@gmail.com"
        self.driver.find_element(By.XPATH, "//a[contains(@href,'post-a')]").click()
        self.driver.find_element(By.XPATH, "//input[@id='create_account_email']") \
            .send_keys(emailId)
        self.driver.find_element(By.XPATH, "//input[@id='job_title']") \
            .send_keys("Quality Analyst")
        jobType = self.driver.find_element(By.XPATH, "//select[@id='job_type']")
        select = Select(jobType)
        select.select_by_visible_text("Freelance")
        self.driver.switch_to.frame("job_description_ifr")
        descriptionBox = self.driver.find_element(By.XPATH, "//body[@id='tinymce']")
        self.driver.execute_script("arguments[0].scrollIntoView(true);", descriptionBox)
        descriptionBox.click()
        description = "This is a test job"
        actions = ActionChains(self.driver)
        actions.send_keys(description).perform()
        self.driver.switch_to.default_content()
        self.driver.find_element(By.XPATH, "//input[@id='application']") \
            .send_keys("testexample@gmail.com")
        self.driver.find_element(By.XPATH, "//input[@id='company_name']").send_keys("STest Job")
        self.driver.find_element(By.XPATH, "//input[@value='Preview']").click()
        expected = ["Quality Analyst", "STest Job", "Freelance"]
        time.sleep(5)
        wait.until(expected_conditions
               .visibility_of_element_located((By.XPATH,
                                               "//input[@value='Submit Listing']")))
        self.driver.find_element(By.XPATH, "//input[@value='Submit Listing']").click()
        self.driver.find_element(By.XPATH, "//a[contains(@href,'jobs/jobs')]").click()
        time.sleep(5)
        wait.until(expected_conditions
               .visibility_of_element_located((By.XPATH,
                                               "(//ul[contains(@class,'listings')]//li//div//h3)[1]")))
        firstJobListing = self.driver.find_element(By.XPATH, "(//ul[contains(@class,'listings')]//li//div//h3)[1]")
        print("Posted job is "+firstJobListing.text)
        actual = [firstJobListing.text,
              self.driver.find_element(By.XPATH, "(//ul[contains(@class,'listings')]//li//div//strong)[1]").text,
              self.driver.find_element(By.XPATH, "(//ul[contains(@class,'listings')]//li//div//following-sibling::ul//li)[1]").text]
        print(actual)
        print(expected)
        assert actual == expected



