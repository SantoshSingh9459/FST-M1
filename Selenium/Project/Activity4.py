import pytest
from selenium.webdriver.common.by import By

@pytest.mark.usefixtures("setup")
class TestSite:

    def test_verify_second_heading(self):
        self.driver.get("https://alchemy.hguy.co/jobs/")
        secondHeading = self.driver.find_element(By.XPATH, "//h2").text
        print(secondHeading)
        assert secondHeading == "Quia quis non"




