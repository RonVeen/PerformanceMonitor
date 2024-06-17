package org.veenron.sports.permon;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class PerformanceMonitorStructualTest {

    @Test
    void createApplicationModuleModel() {
        ApplicationModules modules = ApplicationModules.of(PerformanceMonitorApplication.class);
        modules.forEach(System.out::println);
    }

    @Test
    void verifiesModularStructure() {
        ApplicationModules modules = ApplicationModules.of(PerformanceMonitorApplication.class);
        modules.verify();
    }

    @Test
    void createModuleDocumentation() {
        ApplicationModules modules = ApplicationModules.of(PerformanceMonitorApplication.class);
        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
    }
}
