package cn.fj.loli.binarytemplatesupport.integration;

import cn.fj.loli.binarytemplatesupport.BinaryTemplateBundle;
import cn.fj.loli.binarytemplatesupport.runtime.BinaryInput;
import cn.fj.loli.binarytemplatesupport.runtime.BtTemplateEngine;
import cn.fj.loli.binarytemplatesupport.runtime.TemplateAnalysisResult;
import cn.fj.loli.binarytemplatesupport.runtime.TemplateDiagnostic;
import cn.fj.loli.binarytemplatesupport.runtime.TemplateNode;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.extensions.ExtensionPoint;
import com.intellij.openapi.extensions.ExtensionPointName;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BooleanSupplier;

public final class HexSupportBridgeService implements Disposable {
    private static final String EXTENSION_POINT = "cn.fj.loli.hexsupport.binaryStructureProvider";
    private static final String PROVIDER_CLASS = "cn.fj.loli.hexsupport.structure.BinaryStructureProvider";

    private Object provider;

    public synchronized boolean ensureRegistered() {
        if (provider != null) return true;

        ExtensionPoint<Object> extensionPoint;
        Class<?> providerClass;
        try {
            extensionPoint = ExtensionPointName.<Object>create(EXTENSION_POINT).getPoint();
            providerClass = Class.forName(PROVIDER_CLASS, false, getClass().getClassLoader());
        } catch (IllegalArgumentException | ClassNotFoundException ignored) {
            return false;
        }

        ProviderApi api;
        try {
            api = new ProviderApi(providerClass.getClassLoader());
        } catch (ReflectiveOperationException | LinkageError ignored) {
            return false;
        }

        provider = Proxy.newProxyInstance(
                providerClass.getClassLoader(),
                new Class<?>[]{providerClass},
                new ProviderInvocationHandler(api)
        );
        extensionPoint.registerExtension(provider, this);
        return true;
    }

    @Override
    public synchronized void dispose() {
        provider = null;
    }

    private static final class ProviderInvocationHandler implements InvocationHandler {
        private final ProviderApi api;
        private final BtTemplateEngine engine = new BtTemplateEngine();

        private ProviderInvocationHandler(ProviderApi api) {
            this.api = api;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
            return switch (method.getName()) {
                case "id" -> "010-binary-template";
                case "displayName" -> BinaryTemplateBundle.message("provider.displayName");
                case "templateExtensions" -> List.of("bt");
                case "supportsTemplate" -> supportsTemplate((Path) arguments[0]);
                case "analyze" -> analyze(arguments);
                case "equals" -> proxy == arguments[0];
                case "hashCode" -> System.identityHashCode(proxy);
                case "toString" -> "Binary Template Structure Provider";
                default -> {
                    if (method.isDefault()) {
                        yield InvocationHandler.invokeDefault(proxy, method, arguments);
                    }
                    throw new UnsupportedOperationException(method.toString());
                }
            };
        }

        private Object analyze(Object[] arguments) {
            Path template = (Path) arguments[0];
            BinaryInput input = api.input(arguments[1]);
            BooleanSupplier canceled = (BooleanSupplier) arguments[2];
            return api.result(engine.run(template, input, canceled));
        }

        private static boolean supportsTemplate(Path template) {
            Path fileName = template.getFileName();
            return fileName != null && fileName.toString().toLowerCase().endsWith(".bt");
        }
    }

    private static final class ProviderApi {
        private final Method lengthMethod;
        private final Method revisionMethod;
        private final Method readMethod;
        private final Constructor<?> resultConstructor;
        private final Constructor<?> diagnosticConstructor;
        private final Constructor<?> nodeConstructor;
        private final Class<? extends Enum> severityClass;

        @SuppressWarnings("unchecked")
        private ProviderApi(ClassLoader classLoader) throws ReflectiveOperationException {
            Class<?> snapshotClass = classLoader.loadClass("cn.fj.loli.hexsupport.structure.BinarySnapshot");
            Class<?> resultClass = classLoader.loadClass("cn.fj.loli.hexsupport.structure.StructureAnalysisResult");
            Class<?> diagnosticClass = classLoader.loadClass("cn.fj.loli.hexsupport.structure.StructureDiagnostic");
            Class<?> nodeClass = classLoader.loadClass("cn.fj.loli.hexsupport.structure.StructureNode");
            severityClass = (Class<? extends Enum>) classLoader.loadClass(
                    "cn.fj.loli.hexsupport.structure.StructureDiagnostic$Severity"
            );

            lengthMethod = snapshotClass.getMethod("length");
            revisionMethod = snapshotClass.getMethod("revision");
            readMethod = snapshotClass.getMethod("read", long.class, int.class);
            resultConstructor = resultClass.getConstructor(Path.class, long.class, List.class, List.class, List.class);
            diagnosticConstructor = diagnosticClass.getConstructor(severityClass, int.class, int.class, String.class);
            nodeConstructor = nodeClass.getConstructor(
                    String.class, String.class, String.class, long.class, long.class,
                    String.class, String.class, String.class, String.class, List.class
            );
        }

        private BinaryInput input(Object snapshot) {
            return new BinaryInput() {
                @Override
                public long length() {
                    return ((Number) invoke(lengthMethod, snapshot)).longValue();
                }

                @Override
                public long revision() {
                    return ((Number) invoke(revisionMethod, snapshot)).longValue();
                }

                @Override
                public byte[] read(long offset, int length) {
                    return (byte[]) invoke(readMethod, snapshot, offset, length);
                }
            };
        }

        private Object result(TemplateAnalysisResult result) {
            List<Object> nodes = nodes(result.nodes());
            List<Object> diagnostics = new ArrayList<>(result.diagnostics().size());
            for (TemplateDiagnostic diagnostic : result.diagnostics()) {
                diagnostics.add(construct(
                        diagnosticConstructor,
                        severity(diagnostic.severity()),
                        diagnostic.line(),
                        diagnostic.column(),
                        diagnostic.message()
                ));
            }
            return construct(
                    resultConstructor,
                    result.template(),
                    result.documentRevision(),
                    nodes,
                    diagnostics,
                    result.output()
            );
        }

        private List<Object> nodes(Collection<TemplateNode> source) {
            List<Object> result = new ArrayList<>(source.size());
            for (TemplateNode node : source) {
                result.add(construct(
                        nodeConstructor,
                        node.name(),
                        node.type(),
                        node.value(),
                        node.offset(),
                        node.size(),
                        node.format(),
                        node.foregroundColor(),
                        node.backgroundColor(),
                        node.comment(),
                        nodes(node.children())
                ));
            }
            return result;
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        private Object severity(TemplateDiagnostic.Severity severity) {
            return Enum.valueOf((Class) severityClass, severity.name());
        }

        private static Object invoke(Method method, Object target, Object... arguments) {
            try {
                return method.invoke(target, arguments);
            } catch (IllegalAccessException exception) {
                throw new IllegalStateException(exception);
            } catch (InvocationTargetException exception) {
                Throwable cause = exception.getCause();
                if (cause instanceof RuntimeException runtimeException) throw runtimeException;
                if (cause instanceof Error error) throw error;
                throw new IllegalStateException(cause);
            }
        }

        private static Object construct(Constructor<?> constructor, Object... arguments) {
            try {
                return constructor.newInstance(arguments);
            } catch (ReflectiveOperationException exception) {
                throw new IllegalStateException(exception);
            }
        }
    }
}
