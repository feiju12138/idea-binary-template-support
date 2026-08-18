package cn.fj.loli.binarytemplatesupport.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public final class BtTokenTypes {
    public static final IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    public static final IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    public static final IElementType IDENTIFIER = new BtTokenType("IDENTIFIER");
    public static final IElementType KEYWORD = new BtTokenType("KEYWORD");
    public static final IElementType TYPE = new BtTokenType("TYPE");
    public static final IElementType BUILTIN_FUNCTION = new BtTokenType("BUILTIN_FUNCTION");
    public static final IElementType CONSTANT = new BtTokenType("CONSTANT");
    public static final IElementType ATTRIBUTE = new BtTokenType("ATTRIBUTE");
    public static final IElementType PREPROCESSOR = new BtTokenType("PREPROCESSOR");
    public static final IElementType NUMBER = new BtTokenType("NUMBER");
    public static final IElementType STRING = new BtTokenType("STRING");
    public static final IElementType CHARACTER = new BtTokenType("CHARACTER");
    public static final IElementType LINE_COMMENT = new BtTokenType("LINE_COMMENT");
    public static final IElementType BLOCK_COMMENT = new BtTokenType("BLOCK_COMMENT");
    public static final IElementType LEFT_PARENTHESIS = new BtTokenType("LEFT_PARENTHESIS");
    public static final IElementType RIGHT_PARENTHESIS = new BtTokenType("RIGHT_PARENTHESIS");
    public static final IElementType LEFT_BRACE = new BtTokenType("LEFT_BRACE");
    public static final IElementType RIGHT_BRACE = new BtTokenType("RIGHT_BRACE");
    public static final IElementType LEFT_BRACKET = new BtTokenType("LEFT_BRACKET");
    public static final IElementType RIGHT_BRACKET = new BtTokenType("RIGHT_BRACKET");
    public static final IElementType SEMICOLON = new BtTokenType("SEMICOLON");
    public static final IElementType COMMA = new BtTokenType("COMMA");
    public static final IElementType DOT = new BtTokenType("DOT");
    public static final IElementType OPERATOR = new BtTokenType("OPERATOR");

    public static final TokenSet COMMENTS = TokenSet.create(LINE_COMMENT, BLOCK_COMMENT);
    public static final TokenSet STRINGS = TokenSet.create(STRING, CHARACTER);

    private BtTokenTypes() {}
}
