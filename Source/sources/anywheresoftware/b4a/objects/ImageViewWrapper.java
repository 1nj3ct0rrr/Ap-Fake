package anywheresoftware.b4a.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import anywheresoftware.b4a.BA;
import java.io.IOException;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("ImageView")
public class ImageViewWrapper extends ViewWrapper<ImageView> {
    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(BA ba, String eventName, boolean keepOldObject) {
        if (!keepOldObject) {
            setObject(new ImageView(ba.context));
        }
        super.innerInitialize(ba, eventName, true);
    }

    public int getGravity() {
        Drawable d = ((ImageView) getObject()).getBackground();
        if (d == null) {
            return 0;
        }
        if (d instanceof BitmapDrawable) {
            return ((BitmapDrawable) d).getGravity();
        }
        if (d instanceof ColorDrawable) {
            return d.getLevel();
        }
        return 0;
    }

    public void setGravity(int value) {
        Drawable d = ((ImageView) getObject()).getBackground();
        if (d == null || !(d instanceof BitmapDrawable)) {
            anywheresoftware.b4a.objects.drawable.BitmapDrawable bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
            bd.Initialize(null);
            ((ImageView) getObject()).setBackgroundDrawable((Drawable) bd.getObject());
            d = (Drawable) bd.getObject();
        }
        ((BitmapDrawable) d).setGravity(value);
    }

    public Bitmap getBitmap() {
        Drawable d = ((ImageView) getObject()).getBackground();
        if (d == null || !(d instanceof BitmapDrawable)) {
            return null;
        }
        return ((BitmapDrawable) d).getBitmap();
    }

    public void setBitmap(Bitmap value) {
        SetBackgroundImage(value);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.DesignerName("SetBackgroundImage")
    public anywheresoftware.b4a.objects.drawable.BitmapDrawable SetBackgroundImageNew(Bitmap Bitmap) {
        int gravity = getGravity();
        anywheresoftware.b4a.objects.drawable.BitmapDrawable bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
        bd.Initialize(Bitmap);
        bd.setGravity(gravity);
        setBackground((Drawable) bd.getObject());
        return bd;
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void SetBackgroundImage(Bitmap Bitmap) {
        SetBackgroundImageNew(Bitmap);
    }

    @BA.Hide
    public static void setImage(View v, HashMap<String, Object> drawProps, boolean designer) {
        try {
            Drawable d = anywheresoftware.b4a.objects.drawable.BitmapDrawable.build(v, drawProps, designer, null);
            if (d == null) {
                ColorDrawable cd = new ColorDrawable(-1);
                Integer gravity = (Integer) drawProps.get("gravity");
                if (gravity == null) {
                    gravity = 0;
                }
                v.setBackgroundDrawable(cd);
                cd.setLevel(gravity.intValue());
                return;
            }
            v.setBackgroundDrawable(d);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context) tag, ImageView.class, props, designer);
        }
        ImageView iv = (ImageView) ViewWrapper.build(prev, props, designer);
        setImage(iv, (HashMap) props.get("drawable"), designer);
        return iv;
    }
}
