package com.qa.tdl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.tdl.persistence.repos.AssigneeRepo;
import com.qa.tdl.persistence.repos.TaskRepo;
import com.qa.tdl.pom.TdlSitePortal;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SpringBootTest
public class UserAcceptanceTest {

	private static final String URL = "http://localhost:8080";
	private static WebDriver driver;
	private static ExtentReports report;
	private static ExtentTest test;
	private static TdlSitePortal page;
	
	private TaskRepo taskRepo;
	private AssigneeRepo assigneeRepo;
	
	@Autowired
	public UserAcceptanceTest(TaskRepo taskRepo, AssigneeRepo assigneeRepo) {
		this.taskRepo = taskRepo;
		this.assigneeRepo = assigneeRepo;
	}

	@BeforeAll
	public static void setup() {
		report = new ExtentReports("target/reports/TdlSiteReport.html", true);
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366, 768));
		
		page = PageFactory.initElements(driver, TdlSitePortal.class);
		
		driver.get(URL);
	}
	
	@BeforeEach
	public void refresh() {
//		driver.get(URL);
//		page.refresh();
	}
	
	@Test
	public void newTaskTest() {
		test = report.startTest("New Task");
		
		String result = page.newTask();
		
		if (result.isEmpty() || !("Go shopping".equals(result))) {
			test.log(LogStatus.FAIL, "Test failed; new task doesn't show on interface");
		} else {
			test.log(LogStatus.PASS, "Test passed; new task shows on interface");
		}

		Assertions.assertThat(result).isEqualTo("Go shopping");
	}
	
	@Test
	public void updateTaskTest() throws InterruptedException {
		test = report.startTest("Update Task");
		
		String result = page.updateTask();
		
		if (result.isEmpty() || !("Fix table".equals(result))) {
			test.log(LogStatus.FAIL, "Test failed; edited task doesn't show on interface");
		} else {
			test.log(LogStatus.PASS, "Test passed; edited task shows on interface");
		}

		Assertions.assertThat(result).isEqualTo("Fix table");
	}
	
	@Test
	public void readAllTest() {
		test = report.startTest("Read All Task");
		
		int result = page.readAllTask();
		int actual = (int) taskRepo.count();
		
		if (result != actual) {
			test.log(LogStatus.FAIL, "Test failed; incorrect number of tasks being shown");
		} else {
			test.log(LogStatus.PASS, "Test passed; correct number of tasks being shown");
		}
		
		Assertions.assertThat(result).isEqualTo(actual);
	}
	
	@Test
	public void deleteTaskTest() {
		test = report.startTest("Delete Task");
		
		int result = page.deleteTask();
		int actual = (int) taskRepo.count();
		
		if (result != actual) {
			test.log(LogStatus.FAIL, "Test failed; incorrect number of tasks being shown after deletion");
		} else {
			test.log(LogStatus.PASS, "Test passed; correct number of tasks being shown after deletion");
		}
		
		Assertions.assertThat(result).isEqualTo(actual);
	}
	
	@Test
	public void markAsCompletedTest() {
		test = report.startTest("Mark as Completed Task");
		
		boolean result = page.markAsCompleted();
		
		if (!result) {
			test.log(LogStatus.FAIL, "Test failed; task not marked as completed as it should be");
		} else {
			test.log(LogStatus.PASS, "Test passed; task marked as completed");
		}
		
		Assertions.assertThat(result).isTrue();
	}
	
	@Test
	public void addAssigneeTest() {
		test = report.startTest("Add Assignee to Task");
		
		boolean result = page.addAssignee();
		
		if (!result) {
			test.log(LogStatus.FAIL, "Test failed; added assignee not visible on task");
		} else {
			test.log(LogStatus.PASS, "Test passed; added assignee visible on task");
		}
		
		Assertions.assertThat(result).isTrue();
	}
	
	@Test
	public void removeAssigneeTest() {
		test = report.startTest("Remove Assignee from Task");
		
		boolean result = page.removeAssignee();
		
		if (!result) {
			test.log(LogStatus.FAIL, "Test failed; removed assignee still visible on task");
		} else {
			test.log(LogStatus.PASS, "Test passed; removed assignee not visible on task");
		}
		
		Assertions.assertThat(result).isTrue();
	}
	
	@Test
	public void createAssigneeTest() {
		test = report.startTest("Create Assignee");
	}
	
	@Test
	public void updateAssigneeTest() {
		test = report.startTest("Update Assignee");
	}
	
	@Test
	public void readAllAssigneeTest() {
		test = report.startTest("Read All Assignees");
	}
	
	@Test
	public void deleteAssigneeTest() {
		test = report.startTest("Delete Assignee");
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
