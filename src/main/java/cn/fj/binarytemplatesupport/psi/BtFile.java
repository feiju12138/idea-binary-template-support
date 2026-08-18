package cn.fj.loli.binarytemplatesupport.psi;

import cn.fj.loli.binarytemplatesupport.BtFileType;
import cn.fj.loli.binarytemplatesupport.BtLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public final class BtFile extends PsiFileBase {
    public BtFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, BtLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return BtFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Binary Template File";
    }
}

