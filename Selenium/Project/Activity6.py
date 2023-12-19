import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

@pytest.mark.usefixtures("setup")
class TestSite:

    def test_verify_search_job(self):
        wait = WebDriverWait(self.driver, 10)
        self.driver.get("https://alchemy.hguy.co/jobs/")
        self.driver.find_element(By.XPATH,"//a[contains(@href,'jobs/jobs')]").click()
        self.driver.find_element(By.XPATH,"//input[@id='search_keywords']").send_keys("Banking")
        self.driver.find_element(By.XPATH,"//input[@value='Search Jobs']").click()
        wait.until(expected_conditions
               .visibility_of_element_located((By.XPATH,
                                               "(//ul[contains(@class,'listings')]//li//div//h3)[1]")))
        firstJobListing = self.driver \
            .find_element(By.XPATH, "(//ul[contains(@class,'listings')]//li//div//h3)[1]")
        firstJobListing.click()
        wait.until(expected_conditions
               .visibility_of_element_located((By.XPATH,
                                               "//input[contains(@class,'application')]")))
        applyForJob = self.driver \
            .find_element(By.XPATH,"//input[contains(@class,'application')]")
        applyForJob.click()
        wait.until(expected_conditions
               .visibility_of_element_located((By.XPATH,"//a[contains(@class,'job')]")))
        jobEmail = self.driver.find_element(By.XPATH, "//a[contains(@class,'job')]")
        print("Email for applying job is "+jobEmail.text)



