package com.qa.tdl.pom.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAssigneeModal {

	@FindBy(xpath = "//*[@id=\"newAssigneeModal\"]/div/div/div[3]/button[1]")
	private WebElement newAssigneeModalCloseButton;
	
	private WebDriverWait webDriverWait;
	
	public NewAssigneeModal(WebDriver driver) {
		super();
		webDriverWait = new WebDriverWait(driver, 3);
	}
	
	public void closeNewAssigneeModal() {
		webDriverWait.until(ExpectedConditions.visibilityOf(newAssigneeModalCloseButton));
		this.newAssigneeModalCloseButton.click();
	}
}
