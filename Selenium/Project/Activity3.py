import pytest
from selenium.webdriver.common.by import By

@pytest.mark.usefixtures("setup")
class TestSite:

    def test_verify_url_header_image(self):
        self.driver.get("https://alchemy.hguy.co/jobs/")
        urlHeaderImage = self.driver.find_element(By.XPATH,"//div[contains(@class,'img')]//img") \
            .get_attribute("src")
        print(urlHeaderImage)




