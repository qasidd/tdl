package com.qa.tdl.pom;

import java.util.List;

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
	
	@FindBy(css = ".accordion-button")
	private List<WebElement> taskElements;
	
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

	public boolean test() {		
		this.newTaskButton.click();
		newTaskModal.closeNewTaskModal();
		
		this.newAssigneeButton.click();
		newAssigneeModal.closeNewAssigneeModal();
		
		this.editAssigneeButton.click();
		editAssigneeModal.closeEditAssigneeModal();
		
		taskElements.forEach(WebElement::click);
		
		return true;
	}
}
