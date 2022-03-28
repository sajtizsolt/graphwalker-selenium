package com.company.gs.mbt.generator;

import org.graphwalker.core.model.Action;
import org.graphwalker.core.model.Edge;
import org.graphwalker.core.model.Guard;
import org.graphwalker.core.model.Model;
import org.graphwalker.core.model.Vertex;

public class ForgottenPasswordModelGenerator {

    public static void main(String... arguments) {
        generate();
    }

    public static Model.RuntimeModel generate() {
        Model model = new Model();

        Action clearFPForm = new Action("fpFormFilled = false;");
        Action fillFPForm = new Action("fpFormFilled = true;");

        Guard fpFormIsFilled = new Guard("fpFormFilled == true");
        Guard fpFormIsNotFilled = new Guard("fpFormFilled == false");

        Vertex v_browserNotRunning = new Vertex();
        Vertex v_onLoginPage = new Vertex();
        Vertex v_onFPModal = new Vertex();

        Edge e_startBrowserAndGoToLoginPage = new Edge();
        e_startBrowserAndGoToLoginPage.setSourceVertex(v_browserNotRunning);
        e_startBrowserAndGoToLoginPage.setTargetVertex(v_onLoginPage);

        Edge e_clickOnFPLink = new Edge();
        e_clickOnFPLink.setSourceVertex(v_onLoginPage);
        e_clickOnFPLink.setTargetVertex(v_onFPModal);

        Edge e_closeFPModal = new Edge();
        e_closeFPModal.setSourceVertex(v_onFPModal);
        e_closeFPModal.setTargetVertex(v_onLoginPage);
        e_closeFPModal.addAction(clearFPForm);

        Edge e_submitFPForm = new Edge();
        e_submitFPForm.setSourceVertex(v_onFPModal);
        e_submitFPForm.setTargetVertex(v_onLoginPage);
        e_submitFPForm.addAction(clearFPForm);
        e_submitFPForm.setGuard(fpFormIsFilled);

        Edge e_fillFPForm = new Edge();
        e_fillFPForm.setSourceVertex(v_onFPModal);
        e_fillFPForm.setTargetVertex(v_onFPModal);
        e_fillFPForm.addAction(fillFPForm);
        e_fillFPForm.setGuard(fpFormIsNotFilled);

        model.addAction(clearFPForm);
        model.addVertex(v_browserNotRunning);
        model.addVertex(v_onLoginPage);
        model.addVertex(v_onFPModal);
        model.addEdge(e_startBrowserAndGoToLoginPage);
        model.addEdge(e_clickOnFPLink);
        model.addEdge(e_closeFPModal);
        model.addEdge(e_submitFPForm);
        model.addEdge(e_fillFPForm);

        return model.build();
    }

}
