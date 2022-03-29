package com.company.gs.mbt.generator.util;

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

}
