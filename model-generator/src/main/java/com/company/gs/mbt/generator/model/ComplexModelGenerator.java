package com.company.gs.mbt.generator.model;

import com.company.gs.mbt.generator.util.ModelGeneratorUtil;
import java.util.UUID;
import org.graphwalker.core.model.Action;
import org.graphwalker.core.model.Edge;
import org.graphwalker.core.model.Guard;
import org.graphwalker.core.model.Model;
import org.graphwalker.core.model.Vertex;

public class ComplexModelGenerator {

    public static Model generate() {
        Action clearFPForm = new Action("fpFormFilled = false;");
        Action fillFPForm = new Action("fpFormFilled = true;");
        Action clearLoginForm = new Action("loginFormFilled = false;");
        Action fillLoginForm = new Action("loginFormFilled = true;");
        Action uncheckNUModal = new Action("nuModalChecked = false;");
        Action checkNUModal = new Action("nuModalChecked = true;");

        Guard fpFormIsFilled = new Guard("fpFormFilled == true");
        Guard fpFormIsNotFilled = new Guard("fpFormFilled == false");
        Guard loginFormIsFilled = new Guard("loginFormFilled == true");
        Guard loginFormIsNotFilled = new Guard("loginFormFilled == false");
        Guard nuModalIsChecked = new Guard("nuModalChecked == true");
        Guard nuModalIsUnchecked = new Guard("nuModalChecked == false");

        Vertex v_browserNotRunning = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_browserNotRunning");

        Vertex v_onLoginPage = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_onLoginPage");

        Vertex v_onFPModal = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_onFPModal");

        Vertex v_onNotifyUserModal = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_onNotifyUserModal");

        Vertex v_onFilePage = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_onFilePage");

        Vertex v_onSettingsPage = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_onSettingsPage");

        Vertex v_onSigningMultipleFilesPage = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_onSigningMultipleFilesPage");

        Vertex v_onSigningProcessesPage = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_onSigningProcessesPage");

        Edge e_startBrowserAndGoToLoginPage = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_startBrowserAndGoToLoginPage")
            .setSourceVertex(v_browserNotRunning)
            .setTargetVertex(v_onLoginPage);

        Edge e_clickOnFPLink = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_clickOnFPLink")
            .setSourceVertex(v_onLoginPage)
            .setTargetVertex(v_onFPModal)
            .addAction(clearFPForm);

        Edge e_closeFPModal = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_closeFPModal")
            .setSourceVertex(v_onFPModal)
            .setTargetVertex(v_onLoginPage)
            .addAction(clearFPForm);

        Edge e_submitFPForm = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_submitFPForm")
            .setSourceVertex(v_onFPModal)
            .setTargetVertex(v_onLoginPage)
            .addAction(clearFPForm)
            .setGuard(fpFormIsFilled);

        Edge e_fillFPForm = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_fillFPForm")
            .setSourceVertex(v_onFPModal)
            .setTargetVertex(v_onFPModal)
            .addAction(fillFPForm)
            .setGuard(fpFormIsNotFilled);

        Edge e_fillLoginForm = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_fillLoginForm")
            .setSourceVertex(v_onLoginPage)
            .setTargetVertex(v_onLoginPage)
            .addAction(fillLoginForm)
            .setGuard(loginFormIsNotFilled);

        Edge e_submitLoginFormForTheFirstTime = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_submitLoginForm")
            .setSourceVertex(v_onLoginPage)
            .setTargetVertex(v_onNotifyUserModal)
            .addAction(clearLoginForm)
            .setGuard(ModelGeneratorUtil.conjunctGuards(loginFormIsFilled, nuModalIsUnchecked));

        Edge e_submitLoginForm = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_submitLoginForm")
            .setSourceVertex(v_onLoginPage)
            .setTargetVertex(v_onFilePage)
            .addAction(clearLoginForm)
            .setGuard(ModelGeneratorUtil.conjunctGuards(loginFormIsFilled, nuModalIsChecked));

        Edge e_closeNotifyModal = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_closeNotifyModal")
            .setSourceVertex(v_onNotifyUserModal)
            .setTargetVertex(v_onFilePage)
            .addAction(checkNUModal);

        Edge e_logout = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_logout")
            .setSourceVertex(v_onFilePage)
            .setTargetVertex(v_onLoginPage);

        Edge e_goToSettingsPage = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_goToSettingsPage")
            .setSourceVertex(v_onFilePage)
            .setTargetVertex(v_onSettingsPage);

        Edge e_goToSigningMultipleFilesPage = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_goToSigningMultipleFilesPage")
            .setSourceVertex(v_onFilePage)
            .setTargetVertex(v_onSigningMultipleFilesPage);

        Edge e_goToSigningProcessesPage = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_goToSigningProcessesPage")
            .setSourceVertex(v_onFilePage)
            .setTargetVertex(v_onSigningProcessesPage);

        Edge e_goToFilePageFromSettingsPage = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_goToFilePage")
            .setSourceVertex(v_onSettingsPage)
            .setTargetVertex(v_onFilePage);

        Edge e_goToFilePageFromSigningMultipleFilesPage = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_goToFilePage")
            .setSourceVertex(v_onSigningMultipleFilesPage)
            .setTargetVertex(v_onFilePage);

        Edge e_goToFilePageFromSigningProcessesPage = new Edge()
            .setId(UUID.randomUUID().toString())
            .setName("e_goToFilePage")
            .setSourceVertex(v_onSigningProcessesPage)
            .setTargetVertex(v_onFilePage);

        return new Model()
            .setId(UUID.randomUUID().toString())
            .setName("Complex")
            .addAction(clearFPForm)
            .addAction(clearLoginForm)
            .addAction(uncheckNUModal)
            .addVertex(v_browserNotRunning)
            .addVertex(v_onLoginPage)
            .addVertex(v_onFPModal)
            .addVertex(v_onNotifyUserModal)
            .addVertex(v_onFilePage)
            .addVertex(v_onSettingsPage)
            .addVertex(v_onSigningMultipleFilesPage)
            .addVertex(v_onSigningProcessesPage)
            .addEdge(e_startBrowserAndGoToLoginPage)
            .addEdge(e_clickOnFPLink)
            .addEdge(e_closeFPModal)
            .addEdge(e_submitFPForm)
            .addEdge(e_fillFPForm)
            .addEdge(e_fillLoginForm)
            .addEdge(e_submitLoginFormForTheFirstTime)
            .addEdge(e_submitLoginForm)
            .addEdge(e_closeNotifyModal)
            .addEdge(e_logout)
            .addEdge(e_goToSettingsPage)
            .addEdge(e_goToSigningMultipleFilesPage)
            .addEdge(e_goToSigningProcessesPage)
            .addEdge(e_goToFilePageFromSettingsPage)
            .addEdge(e_goToFilePageFromSigningMultipleFilesPage)
            .addEdge(e_goToFilePageFromSigningProcessesPage);
    }

}
