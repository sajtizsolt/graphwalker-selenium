package com.company.gs.mbt;

import com.company.gs.mbt.util.WebDriverHandler;
import java.io.IOException;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.generator.QuickRandomPath;
import org.graphwalker.java.test.Executor;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ComplexTestExecutor {

    @AfterAll
    public static void closeBrowser() {
        WebDriverHandler.closeInstance();
    }

    @Test
    public void executeGraphWalkerTest() throws IOException {
        Executor executor = new TestExecutor(ComplexTest.class);
        executor
            .getMachine()
            .getCurrentContext()
            .setPathGenerator(new QuickRandomPath(new EdgeCoverage(100)));

        Result result = executor.execute(true);

        if (result.hasErrors()) {
            System.out.println("Errors:");
            for (String error : result.getErrors()) {
                System.out.println(error);
            }
        }

        assertFalse(result.hasErrors());
    }

}
