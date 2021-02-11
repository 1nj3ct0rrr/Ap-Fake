package anywheresoftware.b4a.objects;

import android.text.Layout;
import android.text.StaticLayout;
import android.widget.TextView;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.collections.List;
import anywheresoftware.b4a.objects.streams.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

@BA.Version(1.11f)
@BA.ShortName("StringUtils")
public class StringUtils {
    @BA.Hide
    public int MeasureMultilineTextHeight(TextView TextView, String Text) {
        return MeasureMultilineTextHeight(TextView, (CharSequence) Text);
    }

    public int MeasureMultilineTextHeight(TextView TextView, CharSequence Text) {
        StaticLayout sl = new StaticLayout(Text, TextView.getPaint(), (TextView.getLayoutParams().width - TextView.getPaddingLeft()) - TextView.getPaddingRight(), Layout.Alignment.ALIGN_NORMAL, 1.0f, Common.Density, true);
        return sl.getLineTop(sl.getLineCount());
    }

    public String EncodeBase64(byte[] Data) {
        return Base64.encodeBytes(Data);
    }

    public byte[] DecodeBase64(String Data) throws IOException {
        return Base64.decode(Data);
    }

    public String EncodeUrl(String Url, String CharSet) throws UnsupportedEncodingException {
        return URLEncoder.encode(Url, CharSet);
    }

    public String DecodeUrl(String Url, String CharSet) throws UnsupportedEncodingException {
        return URLDecoder.decode(Url, CharSet);
    }

    public static void SaveCSV(String Dir, String FileName, char SeparatorChar, List Table) throws IOException {
        SaveCSV2(Dir, FileName, SeparatorChar, Table, null);
    }

    public static void SaveCSV2(String Dir, String FileName, char SeparatorChar, List Table, List Headers) throws IOException {
        int colCount = ((String[]) Table.Get(0)).length;
        StringBuilder data = new StringBuilder();
        Pattern problemChars = Pattern.compile("[\"\\r\\n" + SeparatorChar + "]");
        if (Headers != null) {
            for (String str : (java.util.List) Headers.getObject()) {
                data.append(Word(str, problemChars, SeparatorChar));
            }
            data.setCharAt(data.length() - 1, '\n');
        }
        for (int rowI = 0; rowI < Table.getSize(); rowI++) {
            String[] row = (String[]) Table.Get(rowI);
            for (int i = 0; i < colCount; i++) {
                data.append(Word(row[i], problemChars, SeparatorChar));
            }
            data.setCharAt(data.length() - 1, '\n');
        }
        File.WriteString(Dir, FileName, data.toString());
    }

    private static String Word(String word, Pattern problemChars, char sep) {
        if (problemChars.matcher(word).find()) {
            word = Common.QUOTE + word + Common.QUOTE;
            int i = word.indexOf(34, 1);
            while (i > -1 && i < word.length() - 1) {
                word = String.valueOf(word.substring(0, i)) + Common.QUOTE + word.substring(i);
                i = word.indexOf(Common.QUOTE, i + 2);
            }
        }
        return String.valueOf(word) + sep;
    }

    public static List LoadCSV(String Dir, String FileName, char SeparatorChar) throws IOException {
        return LoadCSV2(Dir, FileName, SeparatorChar, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0124, code lost:
        if (r5.charAt(r8 - 1) != '\r') goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0126, code lost:
        r14 = false;
        r8 = r8 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0129, code lost:
        r3.add(r5.substring(r6, r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0130, code lost:
        if (r14 == false) goto L_0x0137;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0132, code lost:
        r15 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0133, code lost:
        r6 = r8 + r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0137, code lost:
        r15 = 2;
     */
    public static List LoadCSV2(String Dir, String FileName, char SeparatorChar, List Headers) throws IOException {
        int i2;
        int i = 0;
        String data = File.ReadString(Dir, FileName);
        List Table = new List();
        Table.Initialize();
        ArrayList<String> alFirstLine = new ArrayList<>();
        boolean unixEnd = true;
        while (true) {
            if (i >= data.length()) {
                break;
            } else if (data.charAt(i) == '\"') {
                i2 = data.indexOf(Common.QUOTE, i + 1);
                boolean shouldReplaceQuotes = false;
                while (i2 < data.length() && i2 > -1 && i2 != data.length() - 1 && data.charAt(i2 + 1) == '\"') {
                    shouldReplaceQuotes = true;
                    i2 = data.indexOf(Common.QUOTE, i2 + 2);
                }
                String ret = data.substring(i + 1, i2);
                if (shouldReplaceQuotes) {
                    ret = ret.replace("\"\"", Common.QUOTE);
                }
                alFirstLine.add(ret);
                i = i2 + 2;
                if (data.charAt(i2 + 1) == '\r' || data.charAt(i2 + 1) == '\n') {
                }
            } else {
                int i22 = data.indexOf(SeparatorChar, i);
                int i3 = data.indexOf(10, i);
                if (i3 == -1) {
                    data = String.valueOf(data) + Common.CRLF;
                    i3 = data.length() - 1;
                }
                if (i3 >= i22 && i22 != -1) {
                    alFirstLine.add(data.substring(i, i22));
                    i = i22 + 1;
                }
            }
        }
        if (data.charAt(i2 + 1) == '\r') {
            unixEnd = false;
            i++;
        }
        if (data.charAt(data.length() - 1) != '\n') {
            data = String.valueOf(data) + (unixEnd ? Common.CRLF : "\r\n");
        }
        int colCount = alFirstLine.size();
        if (Headers != null) {
            if (!Headers.IsInitialized()) {
                Headers.Initialize();
            }
            Iterator<String> it = alFirstLine.iterator();
            while (it.hasNext()) {
                Headers.Add(it.next());
            }
        } else {
            String[] list = new String[colCount];
            for (int a = 0; a < list.length; a++) {
                list[a] = alFirstLine.get(a);
            }
            Table.Add(list);
        }
        int[] ii = {i};
        while (ii[0] < data.length() - 1) {
            String[] list2 = new String[colCount];
            int i23 = 0;
            while (i23 < colCount - 1) {
                list2[i23] = ReadWord(data, ii, SeparatorChar);
                i23++;
            }
            if (!unixEnd) {
                list2[i23] = ReadWord(data, ii, '\r');
                ii[0] = ii[0] + 1;
            } else {
                list2[i23] = ReadWord(data, ii, '\n');
            }
            Table.Add(list2);
        }
        return Table;
    }

    private static String ReadWord(String data, int[] ii, char sep) {
        if (data.charAt(ii[0]) == '\"') {
            int i2 = data.indexOf(Common.QUOTE, ii[0] + 1);
            boolean shouldReplaceQuotes = false;
            while (i2 < data.length() && i2 > -1 && i2 != data.length() - 1 && data.charAt(i2 + 1) == '\"') {
                shouldReplaceQuotes = true;
                i2 = data.indexOf(Common.QUOTE, i2 + 2);
            }
            String ret = data.substring(ii[0] + 1, i2);
            if (shouldReplaceQuotes) {
                ret = ret.replace("\"\"", Common.QUOTE);
            }
            ii[0] = i2 + 2;
            return ret;
        }
        int i22 = data.indexOf(sep, ii[0]);
        String ret2 = data.substring(ii[0], i22);
        ii[0] = i22 + 1;
        return ret2;
    }
}
