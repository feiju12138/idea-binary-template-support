package cn.fj.loli.binarytemplatesupport;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public final class BinaryTemplateBundle extends DynamicBundle {
    private static final String BUNDLE = "cn.fj.loli.binarytemplatesupport.BinaryTemplateBundle";
    private static final BinaryTemplateBundle INSTANCE = new BinaryTemplateBundle();

    private BinaryTemplateBundle() {
        super(BUNDLE);
    }

    public static @Nls @NotNull String message(
            @NotNull @PropertyKey(resourceBundle = BUNDLE) String key,
            Object @NotNull ... params
    ) {
        return INSTANCE.getMessage(key, params);
    }
}

