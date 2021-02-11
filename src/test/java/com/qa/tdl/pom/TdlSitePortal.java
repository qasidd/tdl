package com.qa.tdl.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.tdl.pom.modal.AddAssigneeModal;
import com.qa.tdl.pom.modal.EditAssigneeModal;
import com.qa.tdl.pom.modal.EditTaskModal;
import com.qa.tdl.pom.modal.NewAssigneeModal;
import com.qa.tdl.pom.modal.NewTaskModal;

public class TdlSitePortal {

	@FindBy(xpath = "/html/body/div/div/div[1]/div/div[1]/button")
	private WebElement newTaskButton;

	@FindBy(xpath = "/html/body/div/div/div[1]/div/div[2]/button")
	private WebElement newAssigneeButton;

	@FindBy(xpath = "/html/body/div/div/div[1]/div/div[3]/button")
	private WebElement editAssigneeButton;

	@FindBy(css = "body[class=\"\"] #flush-heading4 .task-title")
	private WebElement newTaskTitle;
	
	@FindBy(xpath = "//*[@id=\"flush-heading0\"]/button")
	private WebElement firstTaskElement;
	
	@FindBy(css = "#flush-collapse0.show")
	private WebElement firstTaskExpanded;
	
	@FindBy(xpath = "//*[@id=\"flush-collapse0\"]/div/div/button[1]")
	private WebElement editTaskButton;
	
	@FindBy(css = "body[class=\"\"] #flush-heading0 .task-title")
	private WebElement editTaskTitle;

	NewTaskModal newTaskModal;
	NewAssigneeModal newAssigneeModal;
	EditAssigneeModal editAssigneeModal;
	EditTaskModal editTaskModal;
	AddAssigneeModal addAssigneeModal;

	WebDriverWait webDriverWait;

	public TdlSitePortal(WebDriver driver) {
		super();
		newTaskModal = PageFactory.initElements(driver, NewTaskModal.class);
		newAssigneeModal = PageFactory.initElements(driver, NewAssigneeModal.class);
		editAssigneeModal = PageFactory.initElements(driver, EditAssigneeModal.class);
		editTaskModal = PageFactory.initElements(driver, EditTaskModal.class);
		addAssigneeModal = PageFactory.initElements(driver, AddAssigneeModal.class);

		webDriverWait = new WebDriverWait(driver, 3);
	}

	public String newTask() {
		this.newTaskButton.click();
		this.newTaskModal.newTask();
		
		webDriverWait.until(ExpectedConditions.visibilityOf(newTaskTitle));
		String actual = newTaskTitle.getText();

		return actual;
	}

	public String editTask() throws InterruptedException {
		this.firstTaskElement.click();
		webDriverWait.until(ExpectedConditions.visibilityOf(firstTaskExpanded));
		this.editTaskButton.click();
		this.editTaskModal.editTask();
		
		webDriverWait.until(ExpectedConditions.visibilityOf(editTaskTitle));
		String actual = editTaskTitle.getText();
		
		return actual;
	}
}
