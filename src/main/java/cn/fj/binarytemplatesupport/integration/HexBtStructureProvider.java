package cn.fj.loli.binarytemplatesupport.integration;

import cn.fj.loli.binarytemplatesupport.BinaryTemplateBundle;
import cn.fj.loli.binarytemplatesupport.runtime.BtTemplateEngine;
import cn.fj.loli.hexsupport.structure.BinarySnapshot;
import cn.fj.loli.hexsupport.structure.BinaryStructureProvider;
import cn.fj.loli.hexsupport.structure.StructureAnalysisResult;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.BooleanSupplier;

public final class HexBtStructureProvider implements BinaryStructureProvider {
    @Override
    public @NotNull String id() {
        return "010-binary-template";
    }

    @Override
    public @NotNull String displayName() {
        return BinaryTemplateBundle.message("provider.displayName");
    }

    @Override
    public @NotNull Collection<String> templateExtensions() {
        return List.of("bt");
    }

    @Override
    public @NotNull StructureAnalysisResult analyze(@NotNull Path template,
                                                    @NotNull BinarySnapshot input,
                                                    @NotNull BooleanSupplier canceled) {
        return new BtTemplateEngine().run(template, input, canceled);
    }
}
