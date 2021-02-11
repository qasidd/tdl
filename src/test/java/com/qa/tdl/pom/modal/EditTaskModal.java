package com.qa.tdl.pom.modal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditTaskModal {

	WebDriverWait webDriverWait;

	public EditTaskModal(WebDriver driver) {
		super();
		webDriverWait = new WebDriverWait(driver, 3);
	}
}
