package cn.fj.loli.binarytemplatesupport.completion;

import cn.fj.loli.binarytemplatesupport.BinaryTemplateBundle;
import cn.fj.loli.binarytemplatesupport.BtLanguage;
import cn.fj.loli.binarytemplatesupport.lang.BtLanguageCatalog;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final class BtCompletionContributor extends CompletionContributor {
    public BtCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement().withLanguage(BtLanguage.INSTANCE),
                new CompletionProvider<>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  @NotNull ProcessingContext context,
                                                  @NotNull CompletionResultSet result) {
                        addWords(result, BtLanguageCatalog.KEYWORDS, "completion.kind.keyword", 100);
                        addWords(result, BtLanguageCatalog.TYPES, "completion.kind.type", 95);
                        addFunctions(result, BtLanguageCatalog.BUILTIN_FUNCTIONS);
                        addWords(result, BtLanguageCatalog.ATTRIBUTES, "completion.kind.attribute", 85);
                        addWords(result, BtLanguageCatalog.ATTRIBUTE_VALUES, "completion.kind.attributeValue", 70);
                        addWords(result, BtLanguageCatalog.CONSTANTS, "completion.kind.constant", 65);
                        addPreprocessor(result);
                    }
                });
    }

    private static void addWords(CompletionResultSet result, Set<String> words, String kindKey, double priority) {
        String kind = BinaryTemplateBundle.message(kindKey);
        for (String word : words) {
            LookupElement element = LookupElementBuilder.create(word).withTypeText(kind, true);
            result.addElement(PrioritizedLookupElement.withPriority(element, priority));
        }
    }

    private static void addFunctions(CompletionResultSet result, Set<String> functions) {
        String kind = BinaryTemplateBundle.message("completion.kind.builtinFunction");
        for (String function : functions) {
            LookupElement element = LookupElementBuilder.create(function)
                    .withPresentableText(function)
                    .withTailText("(…)", true)
                    .withTypeText(kind, true)
                    .withInsertHandler(BtCompletionContributor::insertFunctionCall);
            result.addElement(PrioritizedLookupElement.withPriority(element, 90));
        }
    }

    private static void addPreprocessor(CompletionResultSet result) {
        String kind = BinaryTemplateBundle.message("completion.kind.preprocessor");
        for (String directive : BtLanguageCatalog.PREPROCESSOR) {
            String value = "#" + directive;
            LookupElement element = LookupElementBuilder.create(value).withTypeText(kind, true);
            result.addElement(PrioritizedLookupElement.withPriority(element, 80));
        }
    }

    private static void insertFunctionCall(@NotNull InsertionContext context, @NotNull LookupElement item) {
        int offset = context.getTailOffset();
        CharSequence chars = context.getDocument().getCharsSequence();
        if (offset < chars.length() && chars.charAt(offset) == '(') return;
        context.getDocument().insertString(offset, "()");
        context.getEditor().getCaretModel().moveToOffset(offset + 1);
    }
}
