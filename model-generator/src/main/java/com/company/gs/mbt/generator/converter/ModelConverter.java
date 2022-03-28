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

    public static JSONObject toJson(List<Action> graphWalkerActions) {
        JSONArray actions = new JSONArray();
        for (Action action : graphWalkerActions) {
            actions.put(action.getScript());
        }
        return new JSONObject().put("actions", actions);
    }

    public static JSONObject toJson(Edge edge) {
        return new JSONObject()
            .put("id", edge.getId())
            .put("name", edge.getName())
            .put("sourceVertexId", edge.getSourceVertex().getId())
            .put("targetVertexId", edge.getTargetVertex().getId())
            .put("actions", toJson(edge.getActions()))
            .put("guard", edge.getGuard());
    }

    public static JSONObject toJson(Vertex vertex) {
        return new JSONObject()
            .put("id", vertex.getId())
            .put("name", vertex.getName());
    }

    public static JSONObject toJson(Model graphWalkerModel) {
        JSONArray edges = new JSONArray();
        for (Edge edge : graphWalkerModel.getEdges()) {
            edges.put(toJson(edge));
        }

        JSONArray vertices = new JSONArray();
        for (Vertex vertex : graphWalkerModel.getVertices()) {
            vertices.put(toJson(vertex));
        }

        JSONArray actions = new JSONArray()
            .put(toJson(graphWalkerModel.getActions()));

        JSONObject model = new JSONObject()
            .put("actions", actions)
            .put("edges", edges)
            .put("generator", "quick_random(edge_coverage(100))")
            .put("id", graphWalkerModel.getId())
            .put("name", graphWalkerModel.getName())
            .put("startElementId", graphWalkerModel.getVertices().get(0).getId())
            .put("vertices", vertices);

        return new JSONObject().put("models", new JSONArray().put(model))
            .put("selectedElementId", graphWalkerModel.getVertices().get(0))
            .put("selectedModelIndex", 0);
    }

}
