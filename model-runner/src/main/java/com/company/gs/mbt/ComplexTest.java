package com.company.gs.mbt;

import com.company.gs.mbt.util.InputExamples;
import com.company.gs.mbt.util.WebDriverHandler;
import com.company.gs.mbt.util.WebDriverUtil;
import com.company.gs.mbt.util.WebElementId;
import com.company.gs.mbt.util.WebElementXPath;
import com.company.gs.mbt.util.WebPageUrl;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@GraphWalker
public class ComplexTest extends ExecutionContext implements Complex {

    private WebDriver driver;

    @Override
    public void v_browserNotRunning() {
        assertNull(driver);
    }

    @Override
    public void e_startBrowserAndGoToLoginPage() {
        driver = WebDriverHandler.getInstance();
        driver.get(WebPageUrl.LOGIN_PAGE);
    }

    @Override
    public void v_onLoginPage() {
        WebDriverUtil.waitForUrl(driver, 5, WebPageUrl.LOGIN_PAGE);

        assertEquals(WebPageUrl.LOGIN_PAGE, driver.getCurrentUrl());
        assertTrue(WebDriverUtil.isAntTabActive(driver, WebElementXPath.LOGIN_TAB));
    }

    @Override
    public void e_clickOnFPLink() {
        WebDriverUtil.clickOnById(driver, WebElementId.FORGOTTEN_PASSWORD_BUTTON);
    }

    @Override
    public void v_onFPModal() {
        assertTrue(WebDriverUtil.isModalActive(driver));
    }

    @Override
    public void e_fillFPForm() {
        WebDriverUtil.typeById(driver, WebElementId.FORGOTTEN_PASSWORD_MODAL_EMAIL_INPUT, InputExamples.VALID_EMAIL);
    }

    @Override
    public void e_submitFPForm() {
        WebDriverUtil.clickOnById(driver, WebElementId.FORGOTTEN_PASSWORD_MODAL_SUBMIT);
    }

    @Override
    public void e_closeFPModal() {
        WebDriverUtil.clickOnByXPath(driver, WebElementXPath.MODAL_CLOSE);
    }

    @Override
    public void e_fillLoginForm() {
        WebDriverUtil.typeById(driver, WebElementId.LOGIN_TAB_EMAIL_INPUT, InputExamples.VALID_EMAIL);
        WebDriverUtil.typeById(driver, WebElementId.LOGIN_TAB_PASSWORD_INPUT, InputExamples.VALID_PASSWORD);
    }

    @Override
    public void e_submitLoginForm() {
        WebDriverUtil.clickOnById(driver, WebElementId.LOGIN_BUTTON);
    }

    @Override
    public void v_onNotifyUserModal() {
        assertTrue(WebDriverUtil.isModalActive(driver));
    }

    @Override
    public void e_closeNotifyModal() {
        WebDriverUtil.clickOnByClassName(driver, "ant-checkbox");
        WebDriverUtil.clickOnByXPath(driver, WebElementXPath.MODAL_CLOSE);
    }

    @Override
    public void v_onFilePage() {
        WebDriverUtil.waitForUrl(driver, 5, WebPageUrl.FILE_PAGE);

        assertEquals(WebPageUrl.FILE_PAGE, driver.getCurrentUrl());
        assertTrue(WebDriverUtil.existsById(driver, WebElementId.FILE_PAGE_NEW_BUTTON));
    }

    @Override
    public void e_goToFilePage() {
        WebDriverUtil.clickOnById(driver, WebElementId.LOGO);
    }

    @Override
    public void e_goToSigningProcessesPage() {
        WebDriverUtil.hoverOverById(driver, WebElementId.USER_MENU);
        WebDriverUtil.clickOnById(driver, WebElementId.USER_MENU_SIGNING_PROCESSES_BUTTON);
    }

    @Override
    public void v_onSigningProcessesPage() {
        WebDriverUtil.waitForUrl(driver, 5, WebPageUrl.SIGNING_PROCESSES_PAGE);

        assertEquals(WebPageUrl.SIGNING_PROCESSES_PAGE, driver.getCurrentUrl());
        assertTrue(WebDriverUtil.existsById(driver, WebElementId.SIGNING_PROCESSES_TO_FILE_PAGE_BUTTON));
    }

    @Override
    public void e_goToSettingsPage() {
        WebDriverUtil.hoverOverById(driver, WebElementId.USER_MENU);
        WebDriverUtil.clickOnById(driver, WebElementId.USER_MENU_SETTINGS_BUTTON);
    }

    @Override
    public void v_onSettingsPage() {
        WebDriverUtil.waitForUrl(driver, 5, WebPageUrl.SETTINGS_PAGE);

        assertEquals(WebPageUrl.SETTINGS_PAGE, driver.getCurrentUrl());
        assertTrue(WebDriverUtil.existsById(driver, WebElementId.SETTINGS_TO_FILE_PAGE_BUTTON));
    }

    @Override
    public void e_goToSigningMultipleFilesPage() {
        WebDriverUtil.hoverOverById(driver, WebElementId.USER_MENU);
        WebDriverUtil.clickOnById(driver, WebElementId.USER_MENU_SIGNING_MULTIPLE_FILES_BUTTON);
    }

    @Override
    public void v_onSigningMultipleFilesPage() {
        WebDriverUtil.waitForUrl(driver, 5, WebPageUrl.SIGNING_MULTIPLE_FILES_PAGE);

        assertEquals(WebPageUrl.SIGNING_MULTIPLE_FILES_PAGE, driver.getCurrentUrl());
        assertTrue(WebDriverUtil.existsById(driver, WebElementId.SIGNING_MULTIPLE_FILES_TO_FILE_PAGE_BUTTON));
    }

    @Override
    public void e_logout() {
        WebDriverUtil.hoverOverById(driver, WebElementId.USER_MENU);
        WebDriverUtil.clickOnById(driver, WebElementId.USER_MENU_LOGOUT_BUTTON);
    }

}
