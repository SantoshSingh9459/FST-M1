import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait

@pytest.mark.usefixtures("setup")
class TestSite:

    def test_verify_back_end_login(self):
        wait = WebDriverWait(self.driver, 10)
        self.driver.get("https://alchemy.hguy.co/jobs/wp-admin/")
        user = "root"
        username = self.driver.find_element(By.XPATH,"//input[@id='user_login']")
        password = self.driver.find_element(By.XPATH,"//input[@id='user_pass']")
        login = self.driver.find_element(By.XPATH,"//input[@id='wp-submit']")
        username.send_keys(user)
        password.send_keys("pa$$w0rd")
        login.click()
        actualUser = self.driver.find_element(By.XPATH, "//a[contains(@href,'profile') and @aria-haspopup='true']//span").text
        print(actualUser)
        assert actualUser == user



