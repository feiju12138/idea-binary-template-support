package cn.fj.loli.binarytemplatesupport.highlighting;

import cn.fj.loli.binarytemplatesupport.lexer.BtLexer;
import cn.fj.loli.binarytemplatesupport.lexer.BtTokenTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public final class BtSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey KEYWORD = key("BT_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey TYPE = key("BT_TYPE", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey BUILTIN_FUNCTION =
            key("BT_BUILTIN_FUNCTION", DefaultLanguageHighlighterColors.STATIC_METHOD);
    public static final TextAttributesKey CONSTANT =
            key("BT_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey ATTRIBUTE =
            key("BT_ATTRIBUTE", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey PREPROCESSOR =
            key("BT_PREPROCESSOR", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);
    public static final TextAttributesKey NUMBER = key("BT_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey STRING = key("BT_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey CHARACTER =
            key("BT_CHARACTER", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey LINE_COMMENT =
            key("BT_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT =
            key("BT_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey OPERATOR =
            key("BT_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey PARENTHESES =
            key("BT_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey BRACES = key("BT_BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey BRACKETS = key("BT_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey SEMICOLON =
            key("BT_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey COMMA = key("BT_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey DOT = key("BT_DOT", DefaultLanguageHighlighterColors.DOT);
    public static final TextAttributesKey BAD_CHARACTER = key("BT_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] EMPTY = TextAttributesKey.EMPTY_ARRAY;

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new BtLexer();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType == BtTokenTypes.KEYWORD) return pack(KEYWORD);
        if (tokenType == BtTokenTypes.TYPE) return pack(TYPE);
        if (tokenType == BtTokenTypes.BUILTIN_FUNCTION) return pack(BUILTIN_FUNCTION);
        if (tokenType == BtTokenTypes.CONSTANT) return pack(CONSTANT);
        if (tokenType == BtTokenTypes.ATTRIBUTE) return pack(ATTRIBUTE);
        if (tokenType == BtTokenTypes.PREPROCESSOR) return pack(PREPROCESSOR);
        if (tokenType == BtTokenTypes.NUMBER) return pack(NUMBER);
        if (tokenType == BtTokenTypes.STRING) return pack(STRING);
        if (tokenType == BtTokenTypes.CHARACTER) return pack(CHARACTER);
        if (tokenType == BtTokenTypes.LINE_COMMENT) return pack(LINE_COMMENT);
        if (tokenType == BtTokenTypes.BLOCK_COMMENT) return pack(BLOCK_COMMENT);
        if (tokenType == BtTokenTypes.OPERATOR) return pack(OPERATOR);
        if (tokenType == BtTokenTypes.LEFT_PARENTHESIS || tokenType == BtTokenTypes.RIGHT_PARENTHESIS)
            return pack(PARENTHESES);
        if (tokenType == BtTokenTypes.LEFT_BRACE || tokenType == BtTokenTypes.RIGHT_BRACE) return pack(BRACES);
        if (tokenType == BtTokenTypes.LEFT_BRACKET || tokenType == BtTokenTypes.RIGHT_BRACKET) return pack(BRACKETS);
        if (tokenType == BtTokenTypes.SEMICOLON) return pack(SEMICOLON);
        if (tokenType == BtTokenTypes.COMMA) return pack(COMMA);
        if (tokenType == BtTokenTypes.DOT) return pack(DOT);
        if (tokenType == BtTokenTypes.BAD_CHARACTER) return pack(BAD_CHARACTER);
        return EMPTY;
    }

    private static TextAttributesKey key(String externalName, TextAttributesKey fallback) {
        return TextAttributesKey.createTextAttributesKey(externalName, fallback);
    }
}
