package anywheresoftware.b4a.objects;

import anywheresoftware.b4a.BA;

public abstract class String2 {
    @BA.DesignerName("CharAt")
    public abstract char charAt(int i);

    @BA.DesignerName("CompareTo")
    public abstract int compareTo(String str);

    @BA.DesignerName("Contains")
    public abstract boolean contains(String str);

    @BA.DesignerName("EndsWith")
    public abstract boolean endsWith(String str);

    @BA.DesignerName("EqualsIgnoreCase")
    public abstract boolean equalsIgnoreCase(String str);

    @BA.DesignerName("GetBytes")
    public abstract byte[] getBytes(String str);

    @BA.DesignerName("IndexOf")
    public abstract int indexOf(String str);

    @BA.DesignerName("IndexOf2")
    public abstract int indexOf(String str, int i);

    @BA.DesignerName("LastIndexOf")
    public abstract int lastIndexOf(String str);

    @BA.DesignerName("LastIndexOf2")
    public abstract int lastIndexOf(String str, int i);

    @BA.DesignerName("Length")
    public abstract int length();

    @BA.DesignerName("Replace")
    public abstract String replace(String str, String str2);

    @BA.DesignerName("StartsWith")
    public abstract boolean startsWith(String str);

    @BA.DesignerName("SubString")
    public abstract String substring(int i);

    @BA.DesignerName("SubString2")
    public abstract String substring(int i, int i2);

    @BA.DesignerName("ToLowerCase")
    public abstract String toLowerCase();

    @BA.DesignerName("ToUpperCase")
    public abstract String toUpperCase();

    @BA.DesignerName("Trim")
    public abstract String trim();
}
