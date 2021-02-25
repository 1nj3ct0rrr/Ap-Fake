package anywheresoftware.b4a.keywords;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.ConnectorUtils;
import java.io.DataInputStream;
import java.io.IOException;

@BA.ShortName("LayoutValues")
public class LayoutValues {
    public int Height;
    public float Scale;
    public int Width;

    public double getApproximateScreenSize() {
        return Math.sqrt(Math.pow((double) (((float) this.Width) / this.Scale), 2.0d) + Math.pow((double) (((float) this.Height) / this.Scale), 2.0d)) / 160.0d;
    }

    @BA.Hide
    public static LayoutValues readFromStream(DataInputStream din) throws IOException {
        LayoutValues lv = new LayoutValues();
        lv.Scale = Float.intBitsToFloat(ConnectorUtils.readInt(din));
        lv.Width = ConnectorUtils.readInt(din);
        lv.Height = ConnectorUtils.readInt(din);
        return lv;
    }

    @BA.Hide
    public float calcDistance(LayoutValues device) {
        float fixedScale = device.Scale / this.Scale;
        float w = ((float) this.Width) * fixedScale;
        float h = ((float) this.Height) * fixedScale;
        if (((double) w) > ((double) device.Width) * 1.2d || ((double) h) > ((double) device.Height) * 1.2d) {
            return Float.MAX_VALUE;
        }
        if (w > ((float) device.Width)) {
            w += 50.0f;
        }
        if (h > ((float) device.Height)) {
            h += 50.0f;
        }
        return Math.abs(w - ((float) device.Width)) + Math.abs(h - ((float) device.Height)) + (100.0f * Math.abs(this.Scale - device.Scale)) + ((float) (Math.signum(w - h) == Math.signum((float) (device.Width - device.Height)) ? 0 : 100));
    }

    public String toString() {
        return this.Width + " x " + this.Height + ", scale = " + this.Scale + " (" + ((int) (this.Scale * 160.0f)) + " dpi)";
    }
}
