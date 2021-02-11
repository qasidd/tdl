package com.qa.tdl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
	
	private static TdlSitePortal page;

	@BeforeAll
	public static void setup() {
		report = new ExtentReports("target/reports/TdlSiteReport.html", true);
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366, 768));
		
		page = PageFactory.initElements(driver, TdlSitePortal.class);
	}
	
	@Test
	public void newTaskTest() {
		test = report.startTest("New task test");
		
		driver.get(URL);
		
		String result = page.newTask();
		
		if (result.isEmpty() || !("Go shopping".equals(result))) {
			test.log(LogStatus.FAIL, "New task test failed");
		} else {
			test.log(LogStatus.PASS, "New task test passed");
		}

		Assertions.assertThat(result).isEqualTo("Go shopping");
	}
	
	@Test
	public void editTaskTest() throws InterruptedException {
		test = report.startTest("Edit task test");
		
//		driver.get(URL);
		
		String result = page.editTask();
		
		if (result.isEmpty() || !("Fix table".equals(result))) {
			test.log(LogStatus.FAIL, "Edit task test failed");
		} else {
			test.log(LogStatus.PASS, "Edit task test passed");
		}

		Assertions.assertThat(result).isEqualTo("Fix table");
	}

	@AfterEach
	public void close() {
		report.endTest(test);
//		driver.close();
	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
		report.flush();
		report.close();
	}
}
