package com.qa.tdl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.tdl.pom.TdlSitePortal;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UserAcceptanceTest {

	private final String URL = "http://localhost:8080";

	private static WebDriver driver;
	private static ExtentReports report;
	private static ExtentTest test;

	@BeforeAll
	public static void setup() {
		report = new ExtentReports("target/reports/TdlSiteReport.html", true);
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366, 768));
	}

	@Test
	public void test() throws InterruptedException {
		test = report.startTest("Demo test");
		
		driver.get(URL);

		TdlSitePortal page = PageFactory.initElements(driver, TdlSitePortal.class);
		
		boolean result = page.test();
		
		if (result) {
			test.log(LogStatus.PASS, "Demo test passed");
		} else {
			test.log(LogStatus.FAIL, "Demo test failed");
		}

		Assertions.assertTrue(result);
	}

	@AfterEach
	public void close() {
		report.endTest(test);
		driver.close();
	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
		report.flush();
		report.close();
	}
}
