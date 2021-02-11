package com.qa.tdl.pom.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditAssigneeModal {
	@FindBy(xpath = "//*[@id=\"editAssigneeModal\"]/div/div/div[3]/button[1]")
	private WebElement editAssigneeModalCloseButton;
	
	WebDriverWait webDriverWait;
	
	public EditAssigneeModal(WebDriver driver) {
		super();
		webDriverWait = new WebDriverWait(driver, 3);
	}
	
	public void closeEditAssigneeModal() {
		webDriverWait.until(ExpectedConditions.visibilityOf(editAssigneeModalCloseButton));
		this.editAssigneeModalCloseButton.click();
	}
}
