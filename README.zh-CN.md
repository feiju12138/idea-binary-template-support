# Binary Template Support

[English](README.md) | 简体中文

Binary Template Support 是一款面向 010 Editor Binary Template（`.bt`）文件的 IntelliJ Platform 语言插件。

## 功能

### 轻量 `.bt` 语言支持

- 识别 `.bt` 文件，并提供专属文件类型和图标。
- 高亮控制关键字、内置类型、官方内置函数、常量、模板属性、属性值、预处理指令、数字、字符串、字符、注释、运算符和分隔符。
- 补全 010 Editor v16 手册记录的 Binary Template 语言词汇，包括 300 多个界面、I/O、字符串、数学和工具函数。
- 补全内置函数时自动插入圆括号，并将光标放到括号之间。
- 支持行注释、块注释、引号处理，以及圆括号、花括号和方括号匹配。
- 在 **设置 | 编辑器 | 配色方案 | 二进制模板** 中使用 IDEA 原生编辑器配色配置所有语法类别。
- 提供英文和简体中文界面文本。

首个版本有意采用无错误提示的扁平 PSI，只用于承载轻量编辑功能。它不会独立提供语法错误检查、符号解析、格式化、定义跳转或模板运行/调试。

## 语言参考

语言词表来源于 010 Editor v16 官方文档：

- [编写模板](https://www.sweetscape.com/010editor/manual/IntroTemplates.htm)
- [数据类型、typedef 和枚举](https://www.sweetscape.com/010editor/manual/DataTypes.htm)
- [模板变量与属性](https://www.sweetscape.com/010editor/manual/TemplateVariables.htm)
- [Interface Functions](https://www.sweetscape.com/010editor/manual/FuncInterface.htm)
- [I/O Functions](https://www.sweetscape.com/010editor/manual/FuncIO.htm)
- [String Functions](https://www.sweetscape.com/010editor/manual/FuncString.htm)
- [Math Functions](https://www.sweetscape.com/010editor/manual/FuncMath.htm)
- [Tool Functions](https://www.sweetscape.com/010editor/manual/FuncTools.htm)

010 Editor 和 Binary Templates 是 SweetScape Software 的产品/技术。本项目是独立的 IntelliJ Platform 插件，与 SweetScape 无关联。

## 构建

项目要求使用 JDK 21 和 Gradle 9 或更高版本：

```shell
gradle test buildPlugin
```

若要使用已安装的 IDE 构建，可传入其安装目录：

```shell
gradle test buildPlugin -PlocalIdePath=/path/to/IntelliJ-IDEA
```

生成的 ZIP 位于 `build/distributions/`。

## 兼容性

- 当前版本：Binary Template Support 1.0.1
- IntelliJ IDEA 2025.1 或更高版本（build 251+）
- 可选的 Binary Structure 分析需要 Hex Support 3.0.0 或更高版本
- 从源码构建需要 JDK 21
- Gradle 9 或更高版本
