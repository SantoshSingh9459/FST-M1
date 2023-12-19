import pytest

@pytest.mark.usefixtures("setup")
class TestSite:

    def test_verify_title(self):
        self.driver.get("https://alchemy.hguy.co/jobs/")
        title = self.driver.title
        print(title)
        assert title == "Alchemy Jobs – Job Board Application"




