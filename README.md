# Binary Template Support

English | [简体中文](README.zh-CN.md)

Binary Template Support is an IntelliJ Platform language plugin for 010 Editor Binary Template (`.bt`) files.

## Features

### Lightweight `.bt` language support

- Recognizes `.bt` files with a dedicated file type and icon.
- Highlights control keywords, built-in types, official built-in functions, constants, template attributes, attribute values, preprocessor directives, numbers, strings, characters, comments, operators, and delimiters.
- Completes the Binary Template language vocabulary documented by the 010 Editor v16 manual, including more than 300 Interface, I/O, String, Math, and Tool functions.
- Inserts parentheses for completed built-in functions and places the caret between them.
- Supports line and block comment actions, quote handling, and matching parentheses, braces, and brackets.
- Exposes all syntax categories under **Settings | Editor | Color Scheme | Binary Template** using native IntelliJ editor colors.
- Provides English and Simplified Chinese UI text.

The first release intentionally uses a flat, error-free PSI tree. It does not report parser errors, resolve symbols, format code, navigate definitions, or run/debug templates by itself.

## Language reference

The language catalog is based on the official 010 Editor v16 documentation:

- [Writing Templates](https://www.sweetscape.com/010editor/manual/IntroTemplates.htm)
- [Data Types, Typedefs, and Enums](https://www.sweetscape.com/010editor/manual/DataTypes.htm)
- [Declaring Template Variables and Attributes](https://www.sweetscape.com/010editor/manual/TemplateVariables.htm)
- [Interface Functions](https://www.sweetscape.com/010editor/manual/FuncInterface.htm)
- [I/O Functions](https://www.sweetscape.com/010editor/manual/FuncIO.htm)
- [String Functions](https://www.sweetscape.com/010editor/manual/FuncString.htm)
- [Math Functions](https://www.sweetscape.com/010editor/manual/FuncMath.htm)
- [Tool Functions](https://www.sweetscape.com/010editor/manual/FuncTools.htm)

010 Editor and Binary Templates are products/technology of SweetScape Software. This project is an independent IntelliJ Platform plugin and is not affiliated with SweetScape.

## Build

The project requires JDK 21 and Gradle 9 or later:

```shell
gradle test buildPlugin
```

To build against an installed IDE, pass its installation directory:

```shell
gradle test buildPlugin -PlocalIdePath=/path/to/IntelliJ-IDEA
```

The resulting ZIP is written to `build/distributions/`.

## Compatibility

- Current release: Binary Template Support 1.0.1
- IntelliJ IDEA 2025.1 or later (build 251+)
- Hex Support 3.0.0 or later for optional Binary Structure analysis
- JDK 21 for building from source
- Gradle 9 or later
