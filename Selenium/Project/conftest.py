import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager

@pytest.fixture(scope="session")
def setup(request):
    service = FirefoxService(GeckoDriverManager().install())
    driver = webdriver.Firefox()
    session = request.node
    for item in session.items:
        cls = item.getparent(pytest.Class)
        setattr(cls.obj,"driver",driver)
    yield
    request.addfinalizer(driver.close)

