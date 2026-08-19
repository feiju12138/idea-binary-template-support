package cn.fj.loli.binarytemplatesupport.integration;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

public final class HexSupportStartupActivity implements StartupActivity.DumbAware {
    @Override
    public void runActivity(@NotNull Project project) {
        ApplicationManager.getApplication()
                .getService(HexSupportBridgeService.class)
                .ensureRegistered();
    }
}
