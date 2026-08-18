package cn.fj.loli.binarytemplatesupport;

import com.intellij.lang.Language;

public final class BtLanguage extends Language {
    public static final BtLanguage INSTANCE = new BtLanguage();

    private BtLanguage() {
        super("BinaryTemplate");
    }
}

