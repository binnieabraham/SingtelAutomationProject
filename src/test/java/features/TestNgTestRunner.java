package features;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepDefinitions"}, tags = "@Sanity",
        plugin ={"pretty","html:target/htmlReports/Automation-report.html","json:target/cucumber.json"},
        monochrome=true)
public class TestNgTestRunner extends AbstractTestNGCucumberTests{

}
