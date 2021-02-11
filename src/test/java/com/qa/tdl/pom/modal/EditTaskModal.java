package com.qa.tdl.pom.modal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditTaskModal {

	private WebDriverWait webDriverWait;
	private WebDriver driver;
	private WebElement targ;

	public EditTaskModal(WebDriver driver) {
		super();
		webDriverWait = new WebDriverWait(driver, 3);
		this.driver = driver;
	}
	
	public void editTask() {
		// edit task title input
		targ = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".show #editTaskTitle")));
		targ.sendKeys("Fix table");
		// edit task save button
		targ = driver.findElement(By.id("editTaskSubmit"));
		targ.click();
	}
}
