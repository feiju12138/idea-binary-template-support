package cn.fj.loli.binarytemplatesupport.editor;

import cn.fj.loli.binarytemplatesupport.lexer.BtTokenTypes;
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;

public final class BtQuoteHandler extends SimpleTokenSetQuoteHandler {
    public BtQuoteHandler() {
        super(BtTokenTypes.STRINGS);
    }
}
