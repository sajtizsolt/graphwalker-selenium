package com.company.gs.mbt.generator;

import com.company.gs.mbt.generator.converter.ModelConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.graphwalker.core.model.Model;

public class Generator {

    public static void main(String... arguments) throws IOException {
        Path modelDestination = getModelDestination(arguments);
        Model model = ComplexModelGenerator.generate();

        Files.write(modelDestination, ModelConverter.toJson(model).toString().getBytes());
    }

    private static Path getModelDestination(String... arguments) {
        int argumentsLength = arguments.length;

        for (int i = 0; i < argumentsLength; ++i) {
            if (arguments[i].equals("--model")) {
                ++i;
                if (i < argumentsLength) {
                    return Path.of(arguments[i]);
                }
            }
        }

        return Path.of("..\\model-runner\\src\\main\\resources\\com\\company\\gs\\mbt\\model.json");
    }

}
