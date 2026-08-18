package cn.fj.loli.binarytemplatesupport;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;

public final class BtFileType extends LanguageFileType {
    public static final BtFileType INSTANCE = new BtFileType();
    private static final Icon ICON = IconLoader.getIcon("/icons/btFile.svg", BtFileType.class);

    private BtFileType() {
        super(BtLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Binary Template";
    }

    @Override
    public @Nls @NotNull String getDescription() {
        return BinaryTemplateBundle.message("fileType.description");
    }

    @Override
    public @NonNls @NotNull String getDefaultExtension() {
        return "bt";
    }

    @Override
    public @NotNull Icon getIcon() {
        return ICON;
    }
}

