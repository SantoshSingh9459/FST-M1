import pytest
from selenium.webdriver.common.by import By

@pytest.mark.usefixtures("setup")
class TestSite:

    def test_verify_jobs_title(self):
        self.driver.get("https://alchemy.hguy.co/jobs/")
        listNavigation = self.driver.find_elements(By.XPATH,
                                          "//ul[contains(@id,'primary-menu')]//li//a")
        for we in listNavigation:
            if we.text == "Jobs":
                we.click()
                break
        jobsTitle = self.driver.title
        print(jobsTitle)
        assert jobsTitle == "Jobs – Alchemy Jobs"



