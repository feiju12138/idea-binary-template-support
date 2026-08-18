package cn.fj.loli.binarytemplatesupport.lang;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/** Vocabulary from the 010 Editor v16 Binary Template language and function reference. */
public final class BtLanguageCatalog {
    public static final Set<String> KEYWORDS = words("""
            break case const continue default do else enum extern for if register return signed static
            struct switch typedef union unsigned while void local sizeof startof exists function_exists
            this parentof true false
            """);

    public static final Set<String> TYPES = words("""
            char byte CHAR BYTE int8 INT8 uchar ubyte UCHAR UBYTE uint8 UINT8
            short int16 SHORT INT16 ushort uint16 USHORT UINT16 WORD
            int int32 long INT INT32 LONG uint uint32 ulong UINT UINT32 ULONG DWORD
            int64 quad QUAD INT64 __int64 uint64 uquad UQUAD UINT64 QWORD __uint64
            float FLOAT double DOUBLE hfloat HFLOAT
            DOSDATE DOSTIME FILETIME OLETIME time_t time64_t
            string wchar_t wstring GUID Opcode
            """);

    public static final Set<String> ATTRIBUTES = words("""
            format fgcolor bgcolor style comment name open hidden read write size edit pos localpos
            optimize disasm warn
            """);

    public static final Set<String> ATTRIBUTE_VALUES = words("""
            hex decimal decimalhex octal binary check color flags none suppress
            """);

    public static final Set<String> PREPROCESSOR = words("""
            include define undef ifdef ifndef else endif
            """);

    public static final Set<String> CONSTANTS = words("""
            NULL TRUE FALSE _010EDITOR _010_WIN _010_MAC _010_LINUX _010_64BIT _010_SCRIPT
            CHECKSUM_CRC32 CHECKSUM_CRC16 CHECKSUM_CRC8 CHECKSUM_ADLER32
            FINDMETHOD_NORMAL FINDMETHOD_WILDCARDS FINDMETHOD_REGEX
            HIGHLIGHT_WHOLEWORD HIGHLIGHT_IGNORECASE HIGHLIGHT_REGEX HIGHLIGHT_CSTRING HIGHLIGHT_XMLSTRING
            CHARSET_ANSI CHARSET_ASCII CHARSET_UTF8 CHARSET_UNICODE CHARSET_UNICODE_BIGENDIAN CHARSET_EBCDIC
            cNone cBlack cWhite cRed cGreen cBlue cPurple cDkBlue cDkPurple cLtPurple cLtGray cLtRed
            cLtGreen cLtBlue cYellow cLtYellow cAqua cLtAqua cGray cDkGray cSilver cTeal cMaroon
            cOlive cNavy cFuchsia cLime cDkAqua cDkGreen cDkRed cDkYellow cLtBlack cOrange
            sNone sData sHeading1 sHeading2 sHeading3 sHeading4 sHeading5
            """);

    public static final Set<String> BUILTIN_FUNCTIONS = words("""
            AddBookmark AddressFileToLocal AddressLocalToFile Assert ClearClipboard CopyBytesToClipboard
            CopyStringToClipboard CopyToClipboard CutToClipboard DeleteFile DisableUndo DisplayFormatBinary
            DisplayFormatDecimal DisplayFormatDecimalHex DisplayFormatHex DisplayFormatOctal EnableUndo Exec
            Exit ExpandAll ExportCSV ExportXML FileClose FileCount FileExists FileNew FileOpen FileSave
            FileSaveRange FileSelect FindOpenFile FindOpenFileW GetArg GetArgW GetBackColor
            GetBookmarkArraySize GetBookmarkBackColor GetBookmarkForeColor GetBookmarkMoveWithCursor
            GetBookmarkName GetBookmarkPos GetBookmarkType GetBytesPerLine GetClipboardBytes GetClipboardIndex
            GetClipboardString GetCurrentDate GetCurrentDateTime GetCurrentTime GetCursorPos GetDefaultDateFormat
            GetDefaultDateTimeFormat GetDefaultTimeFormat GetDisplayFormat GetEnv GetFileAttributesUnix
            GetFileAttributesWin GetFileCharSet GetFileInterface GetFileName GetFileNameW GetFileNum GetForeColor
            GetKeepFileTime GetMouseWheelScrollSpeed GetNumArgs GetNumBookmarks GetReadOnly GetScriptFileName
            GetScriptFileNameW GetScriptName GetScriptNameW GetSelSize GetSelStart GetStartingAddress GetStyle
            GetStyleBackColor GetStyleForeColor GetTempDirectory GetTempFileName GetTemplateFileName
            GetTemplateFileNameW GetTemplateName GetTemplateNameW GetUnoptimizedArraysCollapsible
            GetWorkingDirectory GetWorkingDirectoryW HighlightAllowInstanceSharing HighlightApplyColor
            HighlightApplyStyle HighlightBuildKeywordList HighlightBytesRealtime HighlightCheckCommentRule
            HighlightCheckKeywordRule HighlightCheckMultiLineRule HighlightCheckSingleLineRule HighlightCheckTagRule
            HighlightCheckTagTokenRule HighlightColorPattern HighlightFindString HighlightFindStyle
            HighlightGetNextToken HighlightGetStyleBackColor HighlightGetStyleForeColor HighlightLineRealtime
            HighlightMatchKeyword HighlightMatchString InputDirectory InputFloat InputNumber InputOpenFileName
            InputOpenFileNames InputRadioButtonBox InputSaveFileName InputString InputWString InsertFile
            IsEditorFocused IsModified IsNoUIMode IsUndoEnabled MessageBox OffsetClear OffsetGetLimitSize
            OffsetGetStart OffsetSetLimitSize OffsetSetStart OutputPaneClear OutputPaneCopy OutputPaneSave
            PasteFromClipboard Printf ProcessGetHeapLocalAddress ProcessGetHeapModule ProcessGetHeapSize
            ProcessGetHeapStartAddress ProcessGetNumHeaps ProcessHeapToLocalAddress ProcessLocalToHeapAddress
            ProjectClose ProjectOpen RemoveBookmark RenameFile RequiresFile RequiresVersion RunTemplate SetBackColor
            SetBytesPerLine SetClipboardIndex SetColor SetCursorPos SetDisplayFormat SetEnv SetFileAttributesUnix
            SetFileAttributesWin SetFileCharSet SetFileInterface SetForeColor SetKeepFileTime SetMouseWheelScrollSpeed
            SetReadOnly SetSelection SetStartingAddress SetStyle SetUnoptimizedArraysCollapsible SetWorkingDirectory
            SetWorkingDirectoryW Sleep StatusMessage Terminate ThemeAutoScaleColors ThemeIsDark Warning

            BigEndian BitfieldDisablePadding BitfieldEnablePadding BitfieldGetAutoCheckBox BitfieldGetCurrentShift
            BitfieldLeftToRight BitfieldRightToLeft BitfieldSetAutoCheckBox ConvertBytesToDouble ConvertBytesToFloat
            ConvertBytesToHFloat ConvertDataToBytes DeleteBytes DirectoryExists DisasmGetMode DisasmNumOps
            DisasmOpSizeFromFile DisasmOpString DisasmOpStringFromFile DisasmSetMode FEof FileSize FindFiles
            FPrintf FSeek FSkip FTell InsertBytes IsBigEndian IsBitfieldLeftToRight IsBitfieldPaddingEnabled
            IsLittleEndian LittleEndian MakeDir OverwriteBytes ReadByte ReadBytes ReadDouble ReadFloat ReadHFloat
            ReadInt ReadInt64 ReadLine ReadQuad ReadShort ReadString ReadStringLength ReadUByte ReadUInt ReadUInt64
            ReadUQuad ReadUShort ReadWLine ReadWString ReadWStringLength TextAddressToColumn TextAddressToLine
            TextColumnToAddress TextGetLineSize TextGetNumLines TextLineToAddress TextReadLine TextReadLineW
            TextWriteLine TextWriteLineW WriteByte WriteBytes WriteDouble WriteFloat WriteHFloat WriteInt WriteInt64
            WriteQuad WriteShort WriteString WriteUByte WriteUInt WriteUInt64 WriteUQuad WriteUShort WriteWString

            Atof Atoi BinaryStrToInt ConvertString DosDateToString DosTimeToString EnumFlagsToString
            EnumStringToFlags EnumToString FileNameGetBase FileNameGetBaseW FileNameGetExtension
            FileNameGetExtensionW FileNameGetPath FileNameGetPathW FileNameSetExtension FileNameSetExtensionW
            FileTimeToString GUIDToString IntToBinaryStr IsCharAlpha IsCharAlphaNum IsCharAlphaNumW IsCharAlphaW
            IsCharNum IsCharNumW IsCharPunct IsCharPunctW IsCharSymbol IsCharSymbolW IsCharWhitespace
            IsCharWhitespaceW Memcmp Memcpy Memset OleTimeToString RegExMatch RegExMatchW RegExSearch
            RegExSearchW SPrintf SScanf Str Strcat Strchr Strcmp Strcpy StrDel Stricmp StringToDosDate
            StringToDosTime StringToFileTime StringToGUID StringToOleTime StringToTime64T StringToTimeT
            StringToUTF8 StringToWString Strlen Strncmp Strncpy Strnicmp Strstr SubStr Time64TToString TimeTToString
            ToLower ToLowerW ToUpper ToUpperW WMemcmp WMemcpy WMemset WStrcat WStrchr WStrcmp WStrcpy WStrDel
            WStricmp WStringToString WStringToUTF8 WStrlen WStrncmp WStrncpy WStrnicmp WStrstr WSubStr

            Abs Ceil Cos Exp Floor Log Log10 Max Min Pow Random Sin Sqrt SRand SwapBytes Tan

            Checksum ChecksumAlgArrayBytes ChecksumAlgArrayStr ChecksumAlgBytes ChecksumAlgStr Compare
            ConvertASCIIToEBCDIC ConvertASCIIToUNICODE ConvertASCIIToUNICODEW ConvertEBCDICToASCII
            ConvertUNICODEToASCII ConvertUNICODEToASCIIW ExportFile FindAll FindFirst FindInFiles FindNext
            FindStrings GetSectorSize HexOperation Histogram ImportFile IsDrive IsLogicalDrive IsPhysicalDrive
            IsProcess OpenLogicalDrive OpenPhysicalDrive OpenProcessById OpenProcessByName ReplaceAll
            """);

    private BtLanguageCatalog() {}

    public static boolean isKeyword(String value) {
        return KEYWORDS.contains(value);
    }

    public static boolean isType(String value) {
        return TYPES.contains(value);
    }

    public static boolean isAttribute(String value) {
        return ATTRIBUTES.contains(value);
    }

    public static boolean isBuiltinFunction(String value) {
        return BUILTIN_FUNCTIONS.contains(value);
    }

    public static boolean isConstant(String value) {
        return CONSTANTS.contains(value) || ATTRIBUTE_VALUES.contains(value);
    }

    private static Set<String> words(String source) {
        LinkedHashSet<String> result = new LinkedHashSet<>(Arrays.asList(source.strip().split("\\s+")));
        result.remove("");
        return Collections.unmodifiableSet(result);
    }
}
