package cn.fj.loli.binarytemplatesupport.highlighting;

import cn.fj.loli.binarytemplatesupport.BinaryTemplateBundle;
import cn.fj.loli.binarytemplatesupport.BtFileType;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import java.util.Map;

public final class BtColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = {
            descriptor("color.keyword", BtSyntaxHighlighter.KEYWORD),
            descriptor("color.type", BtSyntaxHighlighter.TYPE),
            descriptor("color.builtinFunction", BtSyntaxHighlighter.BUILTIN_FUNCTION),
            descriptor("color.constant", BtSyntaxHighlighter.CONSTANT),
            descriptor("color.attribute", BtSyntaxHighlighter.ATTRIBUTE),
            descriptor("color.preprocessor", BtSyntaxHighlighter.PREPROCESSOR),
            descriptor("color.number", BtSyntaxHighlighter.NUMBER),
            descriptor("color.string", BtSyntaxHighlighter.STRING),
            descriptor("color.character", BtSyntaxHighlighter.CHARACTER),
            descriptor("color.lineComment", BtSyntaxHighlighter.LINE_COMMENT),
            descriptor("color.blockComment", BtSyntaxHighlighter.BLOCK_COMMENT),
            descriptor("color.operator", BtSyntaxHighlighter.OPERATOR),
            descriptor("color.braces", BtSyntaxHighlighter.BRACES),
            descriptor("color.brackets", BtSyntaxHighlighter.BRACKETS),
            descriptor("color.parentheses", BtSyntaxHighlighter.PARENTHESES)
    };

    @Override
    public @Nullable Icon getIcon() {
        return BtFileType.INSTANCE.getIcon();
    }

    @Override
    public @NotNull SyntaxHighlighter getHighlighter() {
        return new BtSyntaxHighlighter();
    }

    @Override
    public @NotNull String getDemoText() {
        return """
                // 010 Editor Binary Template
                #include "Common.bt"
                #define MAGIC 0x504B0304

                typedef struct {
                    uint signature <format=hex, bgcolor=cLtBlue>;
                    ushort version;
                    char name[];
                } HEADER;

                LittleEndian();
                HEADER header;
                if (header.signature != MAGIC)
                    Warning("Unexpected signature");
                """;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @Override
    public @NotNull String getDisplayName() {
        return BinaryTemplateBundle.message("settings.colors.displayName");
    }

    @Override
    public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    private static AttributesDescriptor descriptor(String key, TextAttributesKey attributesKey) {
        return new AttributesDescriptor(BinaryTemplateBundle.message(key), attributesKey);
    }
}
