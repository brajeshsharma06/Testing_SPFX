package Bizportals.PageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Bizportals.BaseClass.BaseClass;

public class WebPartConfigurationPage extends BaseClass {
	WebDriver driver;
	

	@FindBy(xpath = "//span[text()='Configure']")
	WebElement configureButton;

	@FindBy(xpath = "//span[text()='Web part data properties']")
	WebElement dataProperties;

	@FindBy(css = "[value='BizPortals Headliner']")
	WebElement webPartTitle;

	@FindBy(css = "[placeholder='Enter a brief description (optional)']")
	WebElement webPartDescription;

	@FindBy(xpath = "//span[text()='Manage list']")
	WebElement manageListButton;

	@FindBy(xpath = "//p[text()='Please wait loading ...']")
	WebElement loader;

	@FindBy(xpath = "//span[text()='Select target list']")
	WebElement selectList;

	@FindBy(css = "[class*='ms-Dropdown-optionText']")
	List<WebElement> webPartListName;

	@FindBy(xpath = "//button[@role='checkbox']")
	WebElement toggleButton;

	@FindBy(id = "txtTitle")
	WebElement newListName;

	@FindBy(xpath = "//span[text()='Save']")
	WebElement saveButton;

	@FindBy(xpath = "//span[text()='Apply']")
	WebElement applyButton;

	@FindBy(xpath = "//p[text()='Please wait...']")
	WebElement webpartLoader;

	@FindBy(xpath = "//span[text()='Republish']")
	WebElement republishButton;

	@FindBy(xpath = "//span[text()='Manage types']")
	WebElement manageTypes;

	@FindBy(css = "[data-icon-name='CircleAdditionSolid']")
	WebElement addNewTypeButton;

	@FindBy(xpath = "(//input[@placeholder='Maximum 253 characters allowed'])[2]")
	WebElement headlinerTypeTitle;

	@FindBy(xpath = "//span[text()='Title, Message, Starts on, Expires on, Color, Notify users, Notify additional users']")
	WebElement activeFields;

	@FindBy(css = "[class*='ms-Checkbox-label']")
	List<WebElement> selectActiveFields;

	@FindBy(xpath = "//span[text()='Message']")
	WebElement fieldClick;

	@FindBy(css = "[aria-label='WorkItemAlert']")
	WebElement selectIcon;

	@FindBy(css = "[aria-label='FavoriteStar']")
	WebElement selectOneIcon;

	@FindBy(xpath = "//span[text()='Yes']")
	WebElement enableAlert;

	@FindBy(css = "[data-icon-name='Completed']")
	WebElement saveAlertTypeButton;

	@FindBy(css = "[class*='ms-PanelAction-']")
	WebElement closeButton;

	@FindBy(xpath = "//span[text()='Expiry date']")
	WebElement displayItemBasedOn;

	@FindBy(css = "[class*='dropdownOptionText']")
	List<WebElement> displayItemBasedOnText;

	@FindBy(xpath = "//span[text()='Descending order']")
	WebElement orderItemsIn;

	@FindBy(xpath = "//span[text()='Web part layout settings']")
	WebElement layoutSettings;

	@FindBy(xpath = "//span[text()='Panel 02']")
	WebElement selectHeaderLayout;

	@FindBy(xpath = "//span[text()='Layout 02']")
	WebElement selectLayout;

	@FindBy(xpath = "//label[text()='Display web part title']")
	WebElement displayTitle;

	@FindBy(xpath = "//label[text()='No']")
	WebElement customiseThemeToggle;

	@FindBy(xpath = "//span[text()='Email configuration']")
	WebElement emailConfiguration;

	@FindBy(xpath = "//label[text()='Off']")
	WebElement sendEmailAsText;

	@FindBy(css = "[value=\"BizPortals Headliner ['action']\"]")
	WebElement emailSubject;

	public WebPartConfigurationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public void goToConfigurePanel() {

		waitForWebElementToAppear(configureButton);
		configureButton.click();
	}

	public void manageList(String requiredListName) throws InterruptedException {
		manageListButton.click();
		waitForWebElementToDisappear(loader);
		selectList.click();
		WebElement listNames = webPartListName.stream()
				.filter(listName -> listName.getText().equalsIgnoreCase(requiredListName)).findFirst().orElse(null);
		Thread.sleep(2000);
		if (listNames != null && listNames.getText().equalsIgnoreCase(requiredListName)) {
			listNames.click();

		} else {
			selectList.click();
			toggleButton.click();
			waitForWebElementToAppear(newListName);
			newListName.sendKeys(requiredListName);
			Thread.sleep(1000);
		}
		saveButton.click();
		waitForWebElementToDisappear(loader);
	}

	public void manageTypes(String activeFieldsName, String headlinerTypeName) {
		manageTypes.click();
		waitForWebElementToClickable(addNewTypeButton);
		addNewTypeButton.click();
		headlinerTypeTitle.sendKeys(headlinerTypeName);
		activeFields.click();
//		WebElement selectFields = selectActiveFields.stream()
//				.filter(fieldName -> fieldName.getText().equalsIgnoreCase(activeFieldsName)).findFirst().orElse(null);
//		waitForWebElementToClickable(selectFields);
//		if (selectFields != null && selectFields.getText().equalsIgnoreCase(activeFieldsName)) {
//			selectFields.click();
//
//		}
		waitForWebElementToClickable(fieldClick);
		fieldClick.click();
		selectIcon.click();
		selectOneIcon.click();
		enableAlert.click();
		saveAlertTypeButton.click();
		closeButton.click();
	}

	public void sorting(String displayItemBasedOnValue, String orderItemsInValue) {
		displayItemBasedOn.click();
		WebElement selectItemBasedOn = displayItemBasedOnText.stream()
				.filter(basedOnText -> basedOnText.getText().equalsIgnoreCase(displayItemBasedOnValue)).findFirst()
				.orElse(null);
		selectItemBasedOn.click();
		orderItemsIn.click();
		WebElement orderItemsIn = displayItemBasedOnText.stream()
				.filter(ordersInText -> ordersInText.getText().equalsIgnoreCase(orderItemsInValue)).findFirst()
				.orElse(null);
		orderItemsIn.click();
	}

	public void webPartDataProperties(String descriptionText, String requiredListName, String headlinerTypeName,
			String activeFieldsName, String displayItemBasedOnValue, String orderItemsInValue)
			throws InterruptedException {
		waitForWebElementToAppear(dataProperties);
		dataProperties.click();
		webPartDescription.sendKeys(descriptionText);
		manageList(requiredListName);
		manageTypes(requiredListName, headlinerTypeName);
		sorting(displayItemBasedOnValue, orderItemsInValue);
		dataProperties.click();
	}

	public void webPartLayoutSettings() throws InterruptedException {
		waitForWebElementToAppear(layoutSettings);
		layoutSettings.click();
		selectHeaderLayout.click();
		selectLayout.click();
		/*
		 * Scroll is not working so leaving it as it is Actions a = new Actions(driver);
		 * a.moveToElement(displayTitle); waitForWebElementToAppear(displayTitle);
		 * displayTitle.click(); customiseThemeToggle.click();
		 */
		layoutSettings.click();
	}

	public void emailConfiguration() throws InterruptedException {
		waitForWebElementToAppear(emailConfiguration);
		emailConfiguration.click();
		sendEmailAsText.click();
		String emailSubjectValue = emailSubject.getText();
		System.out.println(emailSubjectValue);
		emailConfiguration.click();
	}

	public void webPartConfig(String requiredListName, String descriptionText, String headlinerTypeName,
			String activeFieldsName, String displayItemBasedOnValue, String orderItemsInValue)
			throws InterruptedException {

		goToConfigurePanel();
		webPartDataProperties(descriptionText, requiredListName, headlinerTypeName, activeFieldsName,
				displayItemBasedOnValue, orderItemsInValue);
		waitForWebElementToAppear(layoutSettings);
		layoutSettings.click();
		selectHeaderLayout.click();
		selectLayout.click();
		Thread.sleep(1500);
		js.executeScript("document.querySelector('.ac_c_d8e860b4.l_b_d8e860b4').scrollTop = 1000");

//		webPartLayoutSettings();
//		emailConfiguration();
//		waitForWebElementToClickable(applyButton);
//		Actions a = new Actions(driver);
//		a.moveToElement(applyButton);
//
//		applyButton.click();
//		waitForWebElementToDisappear(webpartLoader);
//		republishButton.click();
	}
}
