package com.qa.tdl.pom.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewTaskModal {
	
	@FindBy(id = "newTaskTitle")
	private WebElement newTaskTitleInput;
	
	@FindBy(xpath = "//*[@id=\"newTaskModal\"]/div/div/div[3]/button[2]")
	private WebElement newTaskSaveButton;
	
	@FindBy(xpath = "//*[@id=\"newTaskModal\"]/div/div/div[3]/button[1]")
	private WebElement newTaskModalCloseButton;
	
	WebDriverWait webDriverWait;

	public NewTaskModal(WebDriver driver) {
		super();
		webDriverWait = new WebDriverWait(driver, 3);
	}
	
	public void newTask() {
		webDriverWait.until(ExpectedConditions.visibilityOf(newTaskTitleInput));
		this.newTaskTitleInput.sendKeys("Go shopping");
		this.newTaskSaveButton.click();
	}
	
	public void closeNewTaskModal() {
		webDriverWait.until(ExpectedConditions.visibilityOf(newTaskModalCloseButton));
		this.newTaskModalCloseButton.click();
	}

}
