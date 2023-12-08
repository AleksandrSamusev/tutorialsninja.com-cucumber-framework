package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features = {".//features/Login.feature"},
                //features = "@target/rerun.txt",
                glue = "stepDefinitions",
                plugin = {"pretty", "html:reports/myreport.html", "json:reports/myreport.json", "rerun:target/rerun.txt"},
                dryRun = false, // if true when run TestRunner shows in console
                // steps and corresponding methods, for cross-check
                //monochrome = true, // removes junk characters for console if any
                tags = "@regression"
        )
public class TestRunner {
}
