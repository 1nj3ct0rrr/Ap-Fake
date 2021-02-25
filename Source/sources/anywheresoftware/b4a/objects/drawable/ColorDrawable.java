package anywheresoftware.b4a.objects.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.keywords.constants.Colors;
import anywheresoftware.b4a.objects.ViewWrapper;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("ColorDrawable")
public class ColorDrawable extends AbsObjectWrapper<Drawable> {
    public void Initialize(int Color, int CornerRadius) {
        Initialize2(Color, CornerRadius, 0, 0);
    }

    public void Initialize2(int Color, int CornerRadius, int BorderWidth, int BorderColor) {
        GradientDrawableWithCorners gd = new GradientDrawableWithCorners();
        gd.setColor(Color);
        gd.setCornerRadius((float) CornerRadius);
        gd.setStroke(BorderWidth, BorderColor);
        setObject(gd);
    }

    @BA.Hide
    public static class GradientDrawableWithCorners extends GradientDrawable {
        public int borderColor;
        public int borderWidth;
        public float cornerRadius;

        public GradientDrawableWithCorners() {
        }

        public GradientDrawableWithCorners(GradientDrawable.Orientation o, int[] colors) {
            super(o, colors);
        }

        public void setCornerRadius(float radius) {
            super.setCornerRadius(radius);
            this.cornerRadius = radius;
        }

        @Override // android.graphics.drawable.GradientDrawable
        public void setStroke(int borderWidth2, int borderColor2) {
            super.setStroke(borderWidth2, borderColor2);
            this.borderWidth = borderWidth2;
            this.borderColor = borderColor2;
        }
    }

    @BA.Hide
    public static Drawable build(Object prev, HashMap<String, Object> d, boolean designer, Object tag) {
        int alpha = ((Integer) d.get("alpha")).intValue();
        int solidColor = ((Integer) d.get("color")).intValue();
        if (solidColor == -984833) {
            if (!designer) {
                return null;
            }
            if (((Drawable) ViewWrapper.getDefault((View) prev, "background", null)) == null) {
                return new android.graphics.drawable.ColorDrawable(0);
            }
        }
        int color = (alpha << 24) | ((solidColor << 8) >>> 8);
        Integer corners = (Integer) d.get("cornerRadius");
        if (corners == null) {
            corners = 0;
        }
        ColorDrawable cd = new ColorDrawable();
        cd.Initialize2(color, (int) (BALayout.getDeviceScale() * ((float) corners.intValue())), (int) (BALayout.getDeviceScale() * ((float) ((Integer) BA.gm(d, "borderWidth", 0)).intValue())), ((Integer) BA.gm(d, "borderColor", Integer.valueOf((int) Colors.Black))).intValue());
        return (Drawable) cd.getObject();
    }
}
