import pytest
from selenium.webdriver.common.by import By

@pytest.mark.usefixtures("setup")
class TestSite:

    def test_verify_heading(self):
        self.driver.get("https://alchemy.hguy.co/jobs/")
        heading = self.driver.find_element(By.XPATH,"//h1[@class='entry-title']").text
        print(heading)
        assert heading == "Welcome to Alchemy Jobs"




