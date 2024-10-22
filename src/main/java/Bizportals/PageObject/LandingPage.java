package Bizportals.PageObject;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Bizportals.BaseClass.BaseClass;

public class LandingPage extends BaseClass {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Edit']")
	WebElement editButton;

	@FindBy(css = "[data-automation-id='CanvasSection']")
	WebElement moveToElement;

	@FindBy(css = "[data-automation-id='CanvasSection']")
	WebElement addIcon;

	@FindBy(xpath = "//input[@aria-label='Type here to search for a web part. ']")
	WebElement searchBar;

	@FindBy(xpath = "//div[@role=\"listitem\"]")
	List<WebElement> webpartList;

	@FindBy(xpath = "//i[@data-icon-name='BulletedList']")
	WebElement hamburgerIcon;

	@FindBy(css = "[class*='bizPanelHeading']")
	WebElement webpartBody;

	@FindBy(xpath = "//span[text()='Manage BizPortals Headliner']")
	WebElement manageButton;

	@FindBy(xpath = "//i[@data-icon-name='CircleAdditionSolid']")
	WebElement newButton;

	@FindBy(css = "[value='Alert']")
	WebElement alertTypeDropdown;

	@FindBy(css = "[class*='ms-ComboBox-optionText']")
	List<WebElement> alertTypes;

	@FindBy(css = "[name='Title']")
	WebElement titleField;

	@FindBy(css = "[data-placeholder='Add a detailed message here']")
	WebElement messageField;

	@FindBy(css = "[value='None']")
	WebElement notifyUsers;

	@FindBy(css = "[class*='ms-ComboBox-optionText']")
	List<WebElement> notifyUsersOptions;

	@FindBy(css = "[data-icon-name='Completed']")
	WebElement newFormSaveButton;

	@FindBy(xpath = "//label[text()='Notify users ']")
	WebElement elementToScroll;

	@FindBy(css = "[id='chkIsOthersActive']")
	WebElement notifyAdditionalToggle;

	@FindBy(css = "[aria-label='People Picker']")
	WebElement notifyAddtionalUsers;

	@FindBy(css = "[class*='ms-PeoplePicker-Persona']")
	WebElement userProfile;

	@FindBy(css = "[title='Close']")
	WebElement closeButton;

	@FindBy(css = "[aria-label*='Account manager for']")
	WebElement profileIconClick;

	@FindBy(id = "mectrl_body_signOut")
	WebElement signoutButton;

	@FindBy(css = "[data-icon-name='MoreVertical']")
	WebElement ellipsisIcon;

	@FindBy(xpath = "(//button[@name='Edit'])[2]")
	WebElement editItem;

	@FindBy(xpath = "//span[text()='Delete']")
	WebElement deleteItem;
	@FindBy(xpath = "//div[@class='k-notification-content']")
	WebElement notificationContent;

	@FindBy(xpath = "(//i[@data-icon-name='Delete'])[1]")
	WebElement deleteWebPart;

	@FindBy(css = "[class*='placeholderContainer']")
	WebElement clickWebPart;
	
	@FindBy(css = "[class*='placeholderText']")
	WebElement placeHolderText;

	@FindBy(xpath = "(//i[@data-icon-name='Copy'])[1]")
	WebElement copyWebPart;
	@FindBy(xpath = " (//a[@href='javascript:void(0);'])[2]")
	WebElement itemViewMode;

	@FindBy(xpath = "(//span[text()='Edit'])[2]")
	WebElement viewModeEditButton;
 
	public void logout() throws InterruptedException {
		waitForWebElementToClickable(profileIconClick);
		Actions a = new Actions(driver);
		a.moveToElement(profileIconClick);
		profileIconClick.click();
		waitForWebElementToAppear(signoutButton);
		signoutButton.click();
	}

	public WebPartConfigurationPage addWebPart(String webpartName) throws InterruptedException {
		Actions a = new Actions(driver);
		waitForWebElementToAppear(editButton);
		editButton.click();
		waitForWebElementToAppear(moveToElement);
		a.moveToElement(moveToElement).build().perform();
		moveToElement.click();
		waitForWebElementToAppear(searchBar);
		searchBar.sendKeys(webpartName);
		Thread.sleep(3000);
		System.out.println(webpartList);
		WebElement webPart = webpartList.stream()
				.filter(webpart -> webpart
						.findElement(By.xpath("//button[@data-automationid=\"splitbuttonprimary\"]/span/div/div"))
						.getText().equalsIgnoreCase(webpartName))
				.findFirst().orElse(null);
		webPart.click();
		waitForWebElementToAppear(placeHolderText);
		WebPartConfigurationPage webpartConfigurationObj = new WebPartConfigurationPage(driver);
		return webpartConfigurationObj;
	}

	public void deleteWebPart() {
		clickWebPart.click();
		deleteWebPart.click();
	}

	public void copyWebPart() {
		clickWebPart.click();
		copyWebPart.click();
	}

	public void creatingNewEntry(String alertType, String title, String message, String notifyUsersName,
			String userName) throws InterruptedException {
		Actions a = new Actions(driver);
		opensettingGrid();
		waitForWebElementToClickable(newButton);
		newButton.click();
		waitForWebElementToClickable(alertTypeDropdown);
		alertTypeDropdown.click();
		WebElement selectAlertType = alertTypes.stream()
				.filter(alertName -> alertName.getText().equalsIgnoreCase(alertType)).findFirst().orElse(null);
		selectAlertType.click();
		titleField.sendKeys(title);
		messageField.sendKeys(message);
		selectDate();
		a.moveToElement(notifyAdditionalToggle).perform();
		notifyAdditionalToggle.click();
		notifyAddtionalUsers.sendKeys(userName);
		waitForWebElementToAppear(userProfile);
		userProfile.click();
		a.moveToElement(newFormSaveButton).perform();
		notifyUsers.click();
		WebElement selectNotifyUsers = notifyUsersOptions.stream()
				.filter(selectNotifyUser -> selectNotifyUser.getText().equalsIgnoreCase(notifyUsersName)).findFirst()
				.orElse(null);
		selectNotifyUsers.click();
		newFormSaveButton.click();
		waitForWebElementToAppear(notificationContent);
	}

	public String successMessages() {
		String successMessage = notificationContent.getText();
		String toasterMessage = "";
		switch (successMessage) {
		case "New item added":
		case "Item updated":
		case "Deleted":
			toasterMessage = successMessage;
			break;
		}
		return toasterMessage;
	}

	public String webPartAddMessage() {
		String addWebPartMessage = placeHolderText.getText();
		System.out.println(addWebPartMessage);
		String placeholderText = "";
		switch (addWebPartMessage) {
		case "Configure BizPortals Spotlight":
		case "Configure BizPortals Headliners":
		case "Configure BizPortals Chat":
		case "Configure BizPortals Employee Showcase":
		case "Configure BizPortals News":
		case "Configure BizPortals Directory":
		case "Configure BizPortals People Connect":
		case "Configure BizPortals Calendar":
		case "Configure BizPortals Bookmarks":
		case "Configure BizPortals Ask a Guru":
			placeholderText = addWebPartMessage;
			break;
		}
		return placeholderText;
	}

	public void opensettingGrid() {
		Actions a = new Actions(driver);
		waitForWebElementToAppear(webpartBody);
		a.moveToElement(webpartBody).perform();
		hamburgerIcon.click();
		manageButton.click();
	}

	public void editEntry(String newTitle) {
		Actions actions = new Actions(driver);
		waitForWebElementToAppear(ellipsisIcon);
		ellipsisIcon.click();
		editItem.click();
		waitForWebElementToClickable(titleField);
		titleField.clear();
		titleField.sendKeys(newTitle);
		actions.moveToElement(newFormSaveButton).click().perform();
		waitForWebElementToAppear(notificationContent);
	}

	public void editFromViewForm(String newMessageViewMode) {
		Actions actions = new Actions(driver);
		waitForWebElementToClickable(itemViewMode);
		itemViewMode.click();
		waitForWebElementToClickable(viewModeEditButton);
		viewModeEditButton.click();
		waitForWebElementToClickable(messageField);
		messageField.clear();
		messageField.sendKeys(newMessageViewMode);
		actions.moveToElement(newFormSaveButton).click().perform();
	}

	public void deleteItem() {
		waitForWebElementToAppear(ellipsisIcon);
		ellipsisIcon.click();
		waitForWebElementToAppear(deleteItem);
		deleteItem.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		waitForWebElementToAppear(notificationContent);
	}

}
