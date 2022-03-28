package com.company.gs.mbt.generator;

import java.util.UUID;
import org.graphwalker.core.model.Action;
import org.graphwalker.core.model.Edge;
import org.graphwalker.core.model.Guard;
import org.graphwalker.core.model.Model;
import org.graphwalker.core.model.Vertex;

public class ForgottenPasswordModelGenerator {

    public static Model generate() {
        Action clearFPForm = new Action("fpFormFilled = false;");
        Action fillFPForm = new Action("fpFormFilled = true;");

        Guard fpFormIsFilled = new Guard("fpFormFilled == true");
        Guard fpFormIsNotFilled = new Guard("fpFormFilled == false");

        Vertex v_browserNotRunning = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_browserNotRunning");

        Vertex v_onLoginPage = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_onLoginPage");

        Vertex v_onFPModal = new Vertex()
            .setId(UUID.randomUUID().toString())
            .setName("v_onFPModal");

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

        return new Model()
            .setId(UUID.randomUUID().toString())
            .setName("ForgottenPassword")
            .addAction(clearFPForm)
            .addVertex(v_browserNotRunning)
            .addVertex(v_onLoginPage)
            .addVertex(v_onFPModal)
            .addEdge(e_startBrowserAndGoToLoginPage)
            .addEdge(e_clickOnFPLink)
            .addEdge(e_closeFPModal)
            .addEdge(e_submitFPForm)
            .addEdge(e_fillFPForm);
    }

}
