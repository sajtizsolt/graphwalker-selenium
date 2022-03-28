package com.company.gs.mbt.generator.converter;

import com.company.gs.mbt.generator.ForgottenPasswordModelGenerator;
import java.util.List;
import org.graphwalker.core.model.Action;
import org.graphwalker.core.model.Edge;
import org.graphwalker.core.model.Model;
import org.graphwalker.core.model.Vertex;
import org.json.JSONArray;
import org.json.JSONObject;

public class ModelConverter {

    public static void main(String... arguments) {
        System.out.println(toJson(ForgottenPasswordModelGenerator.generate()));
    }

    public static JSONArray toJson(List<Action> graphWalkerActions) {
        JSONArray actions = new JSONArray();
        for (Action action : graphWalkerActions) {
            actions.put(action.getScript());
        }
        return actions.isEmpty() ? null : actions;
    }

    public static JSONObject toJson(Edge edge) {
        return new JSONObject()
            .put("id", edge.getId())
            .put("name", edge.getName())
            .put("sourceVertexId", edge.getSourceVertex().getId())
            .put("targetVertexId", edge.getTargetVertex().getId())
            .put("actions", toJson(edge.getActions()))
            .put("guard", edge.getGuard() != null ? edge.getGuard().getScript() : null);
    }

    public static JSONObject toJson(Vertex vertex) {
        return new JSONObject()
            .put("id", vertex.getId())
            .put("name", vertex.getName());
    }

    public static JSONObject toJson(Model gwModel) {
        JSONArray edges = new JSONArray();
        for (Edge edge : gwModel.getEdges()) {
            edges.put(toJson(edge));
        }

        JSONArray vertices = new JSONArray();
        for (Vertex vertex : gwModel.getVertices()) {
            vertices.put(toJson(vertex));
        }

        JSONObject model = new JSONObject()
            .put("actions", toJson(gwModel.getActions()))
            .put("edges", edges)
            .put("generator", "quick_random(edge_coverage(100))")
            .put("id", gwModel.getId())
            .put("name", gwModel.getName())
            .put("startElementId", gwModel.getVertices().get(0).getId())
            .put("vertices", vertices);

        return new JSONObject().put("models", new JSONArray().put(model))
            .put("selectedElementId", gwModel.getVertices().get(0).getId())
            .put("selectedModelIndex", 0);
    }

    public static JSONObject toJson(Model... gwModels) {
        JSONArray models = new JSONArray();
        for (Model model : gwModels) {
            models.put(toJson(model));
        }

        return new JSONObject().put("models", models)
            .put("selectedElementId", gwModels[0].getVertices().get(0).getId())
            .put("selectedModelIndex", 0);
    }

}
