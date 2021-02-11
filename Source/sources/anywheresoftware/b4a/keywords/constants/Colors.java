package anywheresoftware.b4a.keywords.constants;

import android.graphics.Color;

public class Colors {
    public static final int Black = -16777216;
    public static final int Blue = -16776961;
    public static final int Cyan = -16711681;
    public static final int DarkGray = -12303292;
    public static final int Gray = -7829368;
    public static final int Green = -16711936;
    public static final int LightGray = -3355444;
    public static final int Magenta = -65281;
    public static final int Red = -65536;
    public static final int Transparent = 0;
    public static final int White = -1;
    public static final int Yellow = -256;

    public static int RGB(int R, int G, int B) {
        return Color.rgb(R, G, B);
    }

    public static int ARGB(int Alpha, int R, int G, int B) {
        return Color.argb(Alpha, R, G, B);
    }
}
