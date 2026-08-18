package cn.fj.loli.binarytemplatesupport.editor;

import cn.fj.loli.binarytemplatesupport.lexer.BtTokenTypes;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class BtBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = {
            new BracePair(BtTokenTypes.LEFT_PARENTHESIS, BtTokenTypes.RIGHT_PARENTHESIS, false),
            new BracePair(BtTokenTypes.LEFT_BRACE, BtTokenTypes.RIGHT_BRACE, true),
            new BracePair(BtTokenTypes.LEFT_BRACKET, BtTokenTypes.RIGHT_BRACKET, false)
    };

    @Override
    public BracePair @NotNull [] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType leftBraceType,
                                                    @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
