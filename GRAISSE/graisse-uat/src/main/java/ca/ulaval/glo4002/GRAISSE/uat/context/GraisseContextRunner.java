package ca.ulaval.glo4002.GRAISSE.uat.context;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;

public class GraisseContextRunner {

    private static UatContext context;

    @BeforeStories
    public static void startServer() throws Exception {
        context = new UatContext();
        context.apply();
    }

    @BeforeScenario
    public void reinitializeData() {
        context.reinitialize();
    }
}
