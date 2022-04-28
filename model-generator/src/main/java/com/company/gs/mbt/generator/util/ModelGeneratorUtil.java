package com.company.gs.mbt.generator.util;

import org.graphwalker.core.model.Action;
import org.graphwalker.core.model.Guard;

public class ModelGeneratorUtil {

    public static Guard conjunctGuards(Guard... guards) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < guards.length; ++i) {
            sb.append("(");
            sb.append(guards[i].getScript());
            sb.append(") ");

            if (i != guards.length - 1) {
                sb.append("&& ");
            }
        }

        return new Guard(sb.toString());
    }

    public static Action mergeActions(Action... actions) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < actions.length; ++i) {
            sb.append(actions[i].getScript());
            sb.append("\n");
        }

        return new Action(sb.toString());
    }

}
