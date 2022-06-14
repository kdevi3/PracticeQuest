package runner;

import org.junit.Rule;
import org.junit.runner.RunWith;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
 features = {"Features"} // path of your feature folder
 ,glue={"stepDefinition"} // path of Step Definition
 ,monochrome= true
  
 ,tags= {"@Mock"}
 )


public class TestRunner {
 
  
}


