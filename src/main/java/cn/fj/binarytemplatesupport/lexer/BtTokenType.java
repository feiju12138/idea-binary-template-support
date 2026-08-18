package cn.fj.loli.binarytemplatesupport.lexer;

import cn.fj.loli.binarytemplatesupport.BtLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public final class BtTokenType extends IElementType {
    public BtTokenType(@NotNull @NonNls String debugName) {
        super(debugName, BtLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "BinaryTemplateToken." + super.toString();
    }
}

