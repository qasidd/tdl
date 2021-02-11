package com.qa.tdl.pom.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditTaskModal {
	
	@FindBy(id = "editTaskTitle")
	private WebElement editTaskTitleInput;
	
	@FindBy(id = "editTaskSubmit")
	private WebElement editTaskSaveButton;

	WebDriverWait webDriverWait;

	public EditTaskModal(WebDriver driver) {
		super();
		webDriverWait = new WebDriverWait(driver, 3);
	}
	
	public void editTask() {
		webDriverWait.until(ExpectedConditions.visibilityOf(editTaskTitleInput));
		this.editTaskTitleInput.sendKeys("Fix table");
		this.editTaskSaveButton.click();
	}
}
