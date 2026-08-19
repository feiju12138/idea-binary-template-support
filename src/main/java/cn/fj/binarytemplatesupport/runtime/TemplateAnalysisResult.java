package cn.fj.loli.binarytemplatesupport.runtime;

import java.nio.file.Path;
import java.util.List;

public record TemplateAnalysisResult(
        Path template,
        long documentRevision,
        List<TemplateNode> nodes,
        List<TemplateDiagnostic> diagnostics,
        List<String> output
) {
    public TemplateAnalysisResult {
        nodes = List.copyOf(nodes);
        diagnostics = List.copyOf(diagnostics);
        output = List.copyOf(output);
    }
}
