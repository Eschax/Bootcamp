package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
        plugin = {"pretty",                                   // Pretty console output
                "html:target/cucumber-report.html",          // HTML report
                "json:target/cucumber-report.json",          // JSON report
                "junit:target/cucumber-report.xml"           // JUnit XML report
        }
)
public class TestRunner {

}
