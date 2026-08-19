package cn.fj.loli.binarytemplatesupport.runtime;

public record TemplateDiagnostic(Severity severity, int line, int column, String message) {
    public enum Severity {
        INFO,
        WARNING,
        ERROR
    }
}
