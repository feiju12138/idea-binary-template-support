package cn.fj.loli.binarytemplatesupport.runtime;

import java.util.List;

public record TemplateNode(
        String name,
        String type,
        String value,
        long offset,
        long size,
        String format,
        String foregroundColor,
        String backgroundColor,
        String comment,
        List<TemplateNode> children
) {
    public TemplateNode {
        children = List.copyOf(children);
    }
}
