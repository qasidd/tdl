package com.qa.tdl.pom.modal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditAssigneeModal {
	
	private WebDriverWait webDriverWait;
	private WebDriver driver;
	private WebElement targ;
	
	public EditAssigneeModal(WebDriver driver) {
		super();
		webDriverWait = new WebDriverWait(driver, 3);
		this.driver = driver;
	}
	
	public void closeEditAssigneeModal() {
		// edit assignee modal close button
		targ = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id=\"editAssigneeModal\"]/div/div/div[3]/button[1]")));
		targ.click();
	}
}
