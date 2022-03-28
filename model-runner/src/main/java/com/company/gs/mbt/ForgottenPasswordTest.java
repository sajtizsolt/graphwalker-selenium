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
public class ForgottenPasswordTest extends ExecutionContext implements ForgottenPassword {

    private WebDriver driver;

    @Override
    public void v_browserNotRunning() {
        assertNull(driver);
    }

    @Override
    public void e_startBrowserAndGoToLoginPage() {
        driver = WebDriverHandler.getInstance();
        driver.get(WebPageUrl.HOMEPAGE);
    }

    @Override
    public void v_onLoginPage() {
        assertEquals(WebPageUrl.HOMEPAGE, driver.getCurrentUrl());
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

}
