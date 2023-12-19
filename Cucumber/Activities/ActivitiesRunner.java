package testRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepDefinitions"},
        //tags = "@activity1"
        //tags = "@activity2"
        /*tags = "@SmokeTest"
        tags = "@SimpleTest", "@ConfirmAlert"
        tags = "@PromptAlert"*/
        //tags = "@activity3"
        //tags = "@activity4"
        //tags = "@activity5"
        tags = "@SmokeTest",
        //plugin = {"pretty"},
        //plugin = {"html: test-reports"},
        //tags = "@SimpleAlert",
        //plugin = {"json: test-reports1/json-report.json"},
        plugin = {"junit:test-reports1/Cucumber.xml"},
        monochrome = true

)

public class ActivitiesRunner {

}