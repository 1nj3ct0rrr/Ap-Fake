package anywheresoftware.b4a;

import android.graphics.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.HashMap;

public class ConnectorUtils {
    public static final byte BOOL = 5;
    public static final byte CACHED_STRING = 9;
    public static final byte COLOR = 6;
    public static final byte ENDOFMAP = 4;
    public static final byte FLOAT = 7;
    public static final byte INT = 1;
    public static final byte MAP = 3;
    public static final byte NULL = 12;
    public static final byte RECT32 = 11;
    public static final byte SCALED_INT = 8;
    public static final byte STRING = 2;
    private static Charset charset = Charset.forName("UTF8");
    private static ThreadLocal<ByteBuffer> myBb = new ThreadLocal<ByteBuffer>() {
        /* class anywheresoftware.b4a.ConnectorUtils.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public ByteBuffer initialValue() {
            ByteBuffer bbW = ByteBuffer.allocate(51200);
            bbW.order(ByteOrder.LITTLE_ENDIAN);
            return bbW;
        }
    };

    public static ByteBuffer startMessage(byte message) {
        ByteBuffer bbW = myBb.get();
        bbW.clear();
        bbW.put(message);
        return bbW;
    }

    public static void sendMessage(ConnectorConsumer consumer) {
        byte[] b;
        if (consumer != null) {
            ByteBuffer bbW = myBb.get();
            bbW.flip();
            if (consumer.shouldAddPrefix()) {
                b = new byte[(bbW.limit() + 4)];
                int size = b.length - 4;
                bbW.get(b, 4, size);
                for (int i = 0; i <= 3; i++) {
                    b[i] = (byte) (size & 255);
                    size >>= 8;
                }
            } else {
                b = new byte[bbW.limit()];
                bbW.get(b);
            }
            consumer.putTask(b);
        }
    }

    public static void writeInt(int i) {
        myBb.get().putInt(i);
    }

    public static void writeFloat(float f) {
        myBb.get().putFloat(f);
    }

    public static void mark() {
        myBb.get().mark();
    }

    public static void resetToMark() {
        myBb.get().reset();
    }

    public static boolean writeString(String str) {
        if (str == null) {
            str = "";
        }
        if (str.length() > 700) {
            str = String.valueOf(str.substring(0, 699)) + "......";
        }
        ByteBuffer bbW = myBb.get();
        int pos = bbW.position();
        ByteBuffer bb = charset.encode(str);
        if (bbW.remaining() - bb.remaining() < 1000) {
            return false;
        }
        bbW.putInt(0);
        bbW.put(bb);
        bbW.putInt(pos, (bbW.position() - pos) - 4);
        return true;
    }

    public static int readInt(DataInputStream in) throws IOException {
        return Integer.reverseBytes(in.readInt());
    }

    public static short readShort(DataInputStream in) throws IOException {
        return Short.reverseBytes(in.readShort());
    }

    public static String readString(DataInputStream in) throws IOException {
        int size = readInt(in);
        ByteBuffer bb = ByteBuffer.allocate(size);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        in.readFully(bb.array());
        bb.limit(size);
        return charset.decode(bb).toString();
    }

    private static String readCacheString(DataInputStream din, String[] cache) throws IOException {
        if (cache == null) {
            return readString(din);
        }
        return cache[readInt(din)];
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x001d */
    /* JADX WARN: Type inference failed for: r3v1, types: [int[]] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Float] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.lang.Integer] */
    /* JADX WARNING: Unknown variable types count: 1 */
    public static HashMap<String, Object> readMap(DataInputStream in, String[] cache) throws IOException {
        byte b;
        ?? r3;
        HashMap<String, Object> props = new HashMap<>();
        while (true) {
            String key = readCacheString(in, cache);
            b = in.readByte();
            if (b != 1) {
                if (b != 9) {
                    if (b != 2) {
                        if (b != 7) {
                            if (b != 3) {
                                if (b != 5) {
                                    if (b != 6) {
                                        if (b != 12) {
                                            if (b != 11) {
                                                break;
                                            }
                                            r3 = new int[]{readShort(in), readShort(in), readShort(in), readShort(in)};
                                        } else {
                                            r3 = 0;
                                        }
                                    } else {
                                        r3 = Integer.valueOf(Color.argb(in.readUnsignedByte(), in.readUnsignedByte(), in.readUnsignedByte(), in.readUnsignedByte()));
                                    }
                                } else {
                                    r3 = Boolean.valueOf(in.readByte() == 1);
                                }
                            } else {
                                r3 = readMap(in, cache);
                            }
                        } else {
                            r3 = Float.valueOf(Float.intBitsToFloat(readInt(in)));
                        }
                    } else {
                        r3 = readString(in);
                    }
                } else {
                    r3 = readCacheString(in, cache);
                }
            } else {
                r3 = Integer.valueOf(readInt(in));
            }
            props.put(key, r3 == true ? 1 : 0);
        }
        if (b == 4) {
            return props;
        }
        throw new RuntimeException("unknown type");
    }
}
