/**
 * 
 */
package com.quantum.steps;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.StringUtil;
import com.quantum.utils.AppiumUtils;
import com.quantum.utils.ConfigurationUtils;
import com.quantum.utils.ConsoleUtils;
import com.quantum.utils.DeviceUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

@QAFTestStepProvider
public class CommonStepsDefs {
	private static final String ALLOW_CAMERA_ACCESS_OK = "//*[@label='OK']";
	final static By allowPopUp = By.xpath(
			"//*[@resource-id=\"com.android.packageinstaller:id/permission_allow_button\" or @resource-id=\"com.android.permissioncontroller:id/permission_allow_button\" or @resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]");

	@Then("I switch to frame \"(.*?)\"")
	public static void switchToFrame(String nameOrIndex) {
		if (StringUtil.isNumeric(nameOrIndex)) {
			int index = Integer.parseInt(nameOrIndex);
			new WebDriverTestBase().getDriver().switchTo().frame(index);
		} else {
			new WebDriverTestBase().getDriver().switchTo().frame(nameOrIndex);
		}
	}

	@Then("I switch to \"(.*?)\" frame by element")
	public static void switchToFrameByElement(String loc) {
		new WebDriverTestBase().getDriver().switchTo().frame(new QAFExtendedWebElement(loc));
	}

	@When("I am using an AppiumDriver")
	public void testForAppiumDriver() {
		if (ConfigurationUtils.getBaseBundle().getPropertyValue("driver.name").contains("Remote"))
			ConsoleUtils.logWarningBlocks("Driver is an instance of QAFExtendedWebDriver");
		else if (AppiumUtils.getAppiumDriver() instanceof IOSDriver)
			ConsoleUtils.logWarningBlocks("Driver is an instance of IOSDriver");
		else if (AppiumUtils.getAppiumDriver() instanceof AndroidDriver)
			ConsoleUtils.logWarningBlocks("Driver is an instance of AndroidDriver");
	}

	@When("I click on text \"(.*?)\"")
	public void clickOnText(String textToClick) {
		Map<String, Object> params = new HashMap<>();
		params.put("label", textToClick);
		params.put("threshold", 80);
		params.put("ignorecase", "nocase");
		DeviceUtils.getQAFDriver().executeScript("mobile:button-text:click", params);
	}

	@When("I give permissions to the camera if asked")
	public void giveAppCameraPermission() {
		System.out.println(
				"PLATFORM NAME - " + DeviceUtils.getQAFDriver().getCapabilities().getCapability("platformName"));
		if (DeviceUtils.getQAFDriver().getCapabilities().getCapability("platformName").toString()
				.equalsIgnoreCase("Android")) {
			try {
				WebElement popUpBtn = DeviceUtils.getQAFDriver().findElement(allowPopUp);
				if (popUpBtn != null)
					popUpBtn.click();
			} catch (Exception e) {
			}

			try {
				WebElement popUpBtn = DeviceUtils.getQAFDriver().findElement(allowPopUp);
				if (popUpBtn != null)
					popUpBtn.click();
			} catch (Exception e) {
			}
		} else {
			// to allow access to camera - press OK on permission pop-up
			try {
				WebElement popUpBtn = DeviceUtils.getQAFDriver().findElementByXPath(ALLOW_CAMERA_ACCESS_OK);
				if (popUpBtn != null)
					popUpBtn.click();
			} catch (Exception e) {
			}
		}
	}

}