package anywheresoftware.b4a.keywords;

import anywheresoftware.b4a.objects.streams.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Bit {
    public static int And(int N1, int N2) {
        return N1 & N2;
    }

    public static int Or(int N1, int N2) {
        return N1 | N2;
    }

    public static int Xor(int N1, int N2) {
        return N1 ^ N2;
    }

    public static int Not(int N) {
        return N ^ -1;
    }

    public static int ShiftLeft(int N, int Shift) {
        return N << Shift;
    }

    public static int ShiftRight(int N, int Shift) {
        return N >> Shift;
    }

    public static int UnsignedShiftRight(int N, int Shift) {
        return N >>> Shift;
    }

    public static String ToBinaryString(int N) {
        return Integer.toBinaryString(N);
    }

    public static String ToOctalString(int N) {
        return Integer.toOctalString(N);
    }

    public static String ToHexString(int N) {
        return Integer.toHexString(N);
    }

    public static int ParseInt(String Value, int Radix) {
        return Integer.parseInt(Value, Radix);
    }

    public static byte[] InputStreamToBytes(InputStream In) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        File.Copy2(In, out);
        return out.toByteArray();
    }
}
