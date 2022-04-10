package com.company.gs.mbt;

import com.company.gs.mbt.util.WebDriverHandler;
import com.company.gs.mbt.util.WebDriverUtil;
import com.company.gs.properties.GSProperties;
import com.company.gs.properties.util.Data;
import com.company.gs.properties.util.Id;
import com.company.gs.properties.util.Url;
import com.company.gs.properties.util.XPath;
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
        driver.get(GSProperties.get(Url.LOGIN_PAGE));
    }

    @Override
    public void v_onLoginPage() {
        WebDriverUtil.waitForUrl(driver, GSProperties.get(Url.LOGIN_PAGE));

        assertEquals(GSProperties.get(Url.LOGIN_PAGE), driver.getCurrentUrl());
        assertTrue(WebDriverUtil.isAntTabActive(driver, GSProperties.get(XPath.LOGIN_TAB)));
    }

    @Override
    public void e_clickOnFPLink() {
        WebDriverUtil.clickOnById(driver, GSProperties.get(Id.LOGIN_PAGE_FP_BUTTON));
    }

    @Override
    public void v_onFPModal() {
        assertTrue(WebDriverUtil.isModalActive(driver));
    }

    @Override
    public void e_fillFPForm() {
        WebDriverUtil.typeById(driver, GSProperties.get(Id.FP_MODAL_EMAIL), GSProperties.get(Data.USER_EMAIL));
    }

    @Override
    public void e_submitFPForm() {
        WebDriverUtil.clickOnById(driver, GSProperties.get(Id.FP_MODAL_SUBMIT));
    }

    @Override
    public void e_closeFPModal() {
        WebDriverUtil.clickOnByXPath(driver, GSProperties.get(XPath.MODAL_CLOSE));
    }

    @Override
    public void e_fillLoginForm() {
        WebDriverUtil.typeById(driver, GSProperties.get(Id.LOGIN_PAGE_EMAIL), GSProperties.get(Data.USER_EMAIL));
        WebDriverUtil.typeById(driver, GSProperties.get(Id.LOGIN_PAGE_PASSWORD), GSProperties.get(Data.USER_PASSWORD));
    }

    @Override
    public void e_submitLoginForm() {
        WebDriverUtil.clickOnById(driver, GSProperties.get(Id.LOGIN_PAGE_SUBMIT));
    }

    @Override
    public void v_onNotifyUserModal() {
        assertTrue(WebDriverUtil.isModalActive(driver));
    }

    @Override
    public void e_closeNotifyModal() {
        WebDriverUtil.clickOnByClassName(driver, "ant-checkbox");
        WebDriverUtil.clickOnByXPath(driver, GSProperties.get(XPath.MODAL_CLOSE));
    }

    @Override
    public void v_onFilePage() {
        WebDriverUtil.waitForUrl(driver, GSProperties.get(Url.FILE_PAGE));

        assertEquals(GSProperties.get(Url.FILE_PAGE), driver.getCurrentUrl());
        assertTrue(WebDriverUtil.existsById(driver, GSProperties.get(Id.FILE_PAGE_NEW_CONTAINER)));
    }

    @Override
    public void e_goToFilePage() {
        WebDriverUtil.clickOnById(driver, GSProperties.get(Id.LOGO));
    }

    @Override
    public void e_goToSigningProcessesPage() {
        WebDriverUtil.hoverOverById(driver, GSProperties.get(Id.USER_MENU));
        WebDriverUtil.clickOnById(driver, GSProperties.get(Id.USER_MENU_SP));
    }

    @Override
    public void v_onSigningProcessesPage() {
        WebDriverUtil.waitForUrl(driver, GSProperties.get(Url.SP_PAGE));

        assertEquals(GSProperties.get(Url.SP_PAGE), driver.getCurrentUrl());
        assertTrue(WebDriverUtil.existsById(driver, GSProperties.get(Id.SP_PAGE_BACK)));
    }

    @Override
    public void e_goToSettingsPage() {
        WebDriverUtil.hoverOverById(driver, GSProperties.get(Id.USER_MENU));
        WebDriverUtil.clickOnById(driver, GSProperties.get(Id.USER_MENU_SETTINGS));
    }

    @Override
    public void v_onSettingsPage() {
        WebDriverUtil.waitForUrl(driver, GSProperties.get(Url.SETTINGS_PAGE));

        assertEquals(GSProperties.get(Url.SETTINGS_PAGE), driver.getCurrentUrl());
        assertTrue(WebDriverUtil.existsById(driver, GSProperties.get(Id.SETTINGS_PAGE_BACK)));
    }

    @Override
    public void e_goToSigningMultipleFilesPage() {
        WebDriverUtil.hoverOverById(driver, GSProperties.get(Id.USER_MENU));
        WebDriverUtil.clickOnById(driver, GSProperties.get(Id.USER_MENU_SMF));
    }

    @Override
    public void v_onSigningMultipleFilesPage() {
        WebDriverUtil.waitForUrl(driver, GSProperties.get(Url.SMF_PAGE));

        assertEquals(GSProperties.get(Url.SMF_PAGE), driver.getCurrentUrl());
        assertTrue(WebDriverUtil.existsById(driver, GSProperties.get(Id.SMF_PAGE_BACK)));
    }

    @Override
    public void e_logout() {
        WebDriverUtil.hoverOverById(driver, GSProperties.get(Id.USER_MENU));
        WebDriverUtil.clickOnById(driver, GSProperties.get(Id.USER_MENU_LOGOUT));
    }

}
