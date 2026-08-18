package cn.fj.loli.binarytemplatesupport.lexer;

import cn.fj.loli.binarytemplatesupport.lang.BtLanguageCatalog;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class BtLexer extends LexerBase {
    private static final int DEFAULT_STATE = 0;
    private static final int BLOCK_COMMENT_STATE = 1;

    private CharSequence buffer = "";
    private int endOffset;
    private int tokenStart;
    private int tokenEnd;
    private int currentState;
    private int tokenState;
    private int nextState;
    private IElementType tokenType;

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.buffer = buffer;
        this.endOffset = endOffset;
        tokenStart = startOffset;
        currentState = initialState;
        locateToken();
    }

    @Override
    public int getState() {
        return tokenState;
    }

    @Override
    public @Nullable IElementType getTokenType() {
        return tokenType;
    }

    @Override
    public int getTokenStart() {
        return tokenStart;
    }

    @Override
    public int getTokenEnd() {
        return tokenEnd;
    }

    @Override
    public void advance() {
        tokenStart = tokenEnd;
        currentState = nextState;
        locateToken();
    }

    @Override
    public @NotNull CharSequence getBufferSequence() {
        return buffer;
    }

    @Override
    public int getBufferEnd() {
        return endOffset;
    }

    private void locateToken() {
        tokenState = currentState;
        nextState = currentState;
        if (tokenStart >= endOffset) {
            tokenEnd = tokenStart;
            tokenType = null;
            return;
        }

        if (currentState == BLOCK_COMMENT_STATE) {
            tokenType = BtTokenTypes.BLOCK_COMMENT;
            tokenEnd = scanBlockComment(tokenStart);
            return;
        }

        char current = charAt(tokenStart);
        if (Character.isWhitespace(current)) {
            tokenEnd = tokenStart + 1;
            while (tokenEnd < endOffset && Character.isWhitespace(charAt(tokenEnd))) tokenEnd++;
            tokenType = BtTokenTypes.WHITE_SPACE;
            return;
        }

        if (current == '/' && peek(tokenStart + 1) == '/') {
            tokenEnd = tokenStart + 2;
            while (tokenEnd < endOffset && charAt(tokenEnd) != '\n' && charAt(tokenEnd) != '\r') tokenEnd++;
            tokenType = BtTokenTypes.LINE_COMMENT;
            return;
        }
        if (current == '/' && peek(tokenStart + 1) == '*') {
            tokenType = BtTokenTypes.BLOCK_COMMENT;
            tokenEnd = scanBlockComment(tokenStart + 2);
            return;
        }

        if (current == '#') {
            tokenEnd = tokenStart + 1;
            while (tokenEnd < endOffset && (charAt(tokenEnd) == ' ' || charAt(tokenEnd) == '\t')) tokenEnd++;
            while (tokenEnd < endOffset && isIdentifierPart(charAt(tokenEnd))) tokenEnd++;
            tokenType = BtTokenTypes.PREPROCESSOR;
            return;
        }

        if ((current == 'L' || current == 'u' || current == 'U')
                && (peek(tokenStart + 1) == '"' || peek(tokenStart + 1) == '\'')) {
            char quote = peek(tokenStart + 1);
            tokenEnd = scanQuoted(tokenStart + 2, quote);
            tokenType = quote == '"' ? BtTokenTypes.STRING : BtTokenTypes.CHARACTER;
            return;
        }
        if (current == '"' || current == '\'') {
            tokenEnd = scanQuoted(tokenStart + 1, current);
            tokenType = current == '"' ? BtTokenTypes.STRING : BtTokenTypes.CHARACTER;
            return;
        }

        if (isIdentifierStart(current)) {
            tokenEnd = tokenStart + 1;
            while (tokenEnd < endOffset && isIdentifierPart(charAt(tokenEnd))) tokenEnd++;
            String word = buffer.subSequence(tokenStart, tokenEnd).toString();
            tokenType = classifyWord(word);
            return;
        }

        if (Character.isDigit(current)) {
            tokenEnd = scanNumber(tokenStart);
            tokenType = BtTokenTypes.NUMBER;
            return;
        }

        tokenEnd = Math.min(tokenStart + operatorLength(tokenStart), endOffset);
        tokenType = switch (current) {
            case '(' -> BtTokenTypes.LEFT_PARENTHESIS;
            case ')' -> BtTokenTypes.RIGHT_PARENTHESIS;
            case '{' -> BtTokenTypes.LEFT_BRACE;
            case '}' -> BtTokenTypes.RIGHT_BRACE;
            case '[' -> BtTokenTypes.LEFT_BRACKET;
            case ']' -> BtTokenTypes.RIGHT_BRACKET;
            case ';' -> BtTokenTypes.SEMICOLON;
            case ',' -> BtTokenTypes.COMMA;
            case '.' -> BtTokenTypes.DOT;
            case '+', '-', '*', '/', '%', '=', '!', '<', '>', '&', '|', '^', '~', '?', ':' ->
                    BtTokenTypes.OPERATOR;
            default -> BtTokenTypes.BAD_CHARACTER;
        };
    }

    private int scanBlockComment(int from) {
        int index = from;
        while (index < endOffset) {
            if (charAt(index) == '*' && peek(index + 1) == '/') {
                nextState = DEFAULT_STATE;
                return index + 2;
            }
            index++;
        }
        nextState = BLOCK_COMMENT_STATE;
        return endOffset;
    }

    private int scanQuoted(int from, char quote) {
        int index = from;
        boolean escaped = false;
        while (index < endOffset) {
            char value = charAt(index++);
            if (escaped) {
                escaped = false;
            } else if (value == '\\') {
                escaped = true;
            } else if (value == quote) {
                break;
            } else if (value == '\n' || value == '\r') {
                break;
            }
        }
        return index;
    }

    private int scanNumber(int from) {
        int index = from;
        if (charAt(index) == '0' && (peek(index + 1) == 'x' || peek(index + 1) == 'X')) {
            index += 2;
            while (index < endOffset && (isHexDigit(charAt(index)) || charAt(index) == '_')) index++;
        } else if (charAt(index) == '0' && (peek(index + 1) == 'b' || peek(index + 1) == 'B')) {
            index += 2;
            while (index < endOffset && (charAt(index) == '0' || charAt(index) == '1'
                    || charAt(index) == '_')) index++;
        } else {
            while (index < endOffset && (Character.isDigit(charAt(index)) || charAt(index) == '_')) index++;
            if (index < endOffset && charAt(index) == '.' && peek(index + 1) != '.') {
                index++;
                while (index < endOffset && Character.isDigit(charAt(index))) index++;
            }
            if (index < endOffset && (charAt(index) == 'e' || charAt(index) == 'E')) {
                int exponent = index++;
                if (index < endOffset && (charAt(index) == '+' || charAt(index) == '-')) index++;
                int digits = index;
                while (index < endOffset && Character.isDigit(charAt(index))) index++;
                if (digits == index) index = exponent;
            } else {
                int hex = index;
                while (index < endOffset && isHexDigit(charAt(index))) index++;
                if (index >= endOffset || (charAt(index) != 'h' && charAt(index) != 'H')) index = hex;
                else index++;
            }
        }
        while (index < endOffset && "uUlLfF".indexOf(charAt(index)) >= 0) index++;
        return index;
    }

    private int operatorLength(int offset) {
        String three = slice(offset, 3);
        if (three.equals("<<=") || three.equals(">>=")) return 3;
        String two = slice(offset, 2);
        return switch (two) {
            case "==", "!=", "<=", ">=", "&&", "||", "++", "--", "+=", "-=", "*=", "/=",
                    "%=", "&=", "|=", "^=", "<<", ">>", "->", "::" -> 2;
            default -> 1;
        };
    }

    private IElementType classifyWord(String word) {
        if (BtLanguageCatalog.isKeyword(word)) return BtTokenTypes.KEYWORD;
        if (BtLanguageCatalog.isType(word)) return BtTokenTypes.TYPE;
        if (BtLanguageCatalog.isBuiltinFunction(word)) return BtTokenTypes.BUILTIN_FUNCTION;
        if (BtLanguageCatalog.isAttribute(word)) return BtTokenTypes.ATTRIBUTE;
        if (BtLanguageCatalog.isConstant(word)) return BtTokenTypes.CONSTANT;
        return BtTokenTypes.IDENTIFIER;
    }

    private char charAt(int offset) {
        return buffer.charAt(offset);
    }

    private char peek(int offset) {
        return offset >= 0 && offset < endOffset ? charAt(offset) : '\0';
    }

    private String slice(int offset, int length) {
        int end = Math.min(offset + length, endOffset);
        return buffer.subSequence(offset, end).toString();
    }

    private static boolean isIdentifierStart(char value) {
        return Character.isLetter(value) || value == '_' || value == '$';
    }

    private static boolean isIdentifierPart(char value) {
        return Character.isLetterOrDigit(value) || value == '_' || value == '$';
    }

    private static boolean isHexDigit(char value) {
        return Character.digit(value, 16) >= 0;
    }
}
