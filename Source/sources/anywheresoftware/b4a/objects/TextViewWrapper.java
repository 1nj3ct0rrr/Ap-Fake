package anywheresoftware.b4a.objects;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.keywords.LayoutBuilder;
import anywheresoftware.b4a.keywords.constants.Colors;
import anywheresoftware.b4a.keywords.constants.TypefaceWrapper;
import anywheresoftware.b4a.objects.streams.File;
import java.util.HashMap;
import java.util.Map;

@BA.Hide
public class TextViewWrapper<T extends TextView> extends ViewWrapper<T> implements LayoutBuilder.DesignerTextSizeMethod {
    private static final HashMap<String, Typeface> cachedTypefaces = new HashMap<>();
    @BA.Hide
    public static String fontAwesomeFile = "b4x_fontawesome.otf";
    @BA.Hide
    public static String materialIconsFile = "b4x_materialicons.ttf";

    public String getText() {
        return ((TextView) getObject()).getText().toString();
    }

    public void setText(CharSequence Text) {
        ((TextView) getObject()).setText(Text);
    }

    @BA.Hide
    public void setText(Object Text) {
        setText(BA.ObjectToCharSequence(Text));
    }

    public void setTextColor(int Color) {
        ((TextView) getObject()).setTextColor(Color);
    }

    public int getTextColor() {
        return ((TextView) getObject()).getTextColors().getDefaultColor();
    }

    public void setEllipsize(String e) {
        ((TextView) getObject()).setEllipsize(e.equals("NONE") ? null : TextUtils.TruncateAt.valueOf(e));
    }

    public String getEllipsize() {
        TextUtils.TruncateAt t = ((TextView) getObject()).getEllipsize();
        return t == null ? "NONE" : t.toString();
    }

    public void setSingleLine(boolean singleLine) {
        ((TextView) getObject()).setSingleLine(singleLine);
    }

    public void SetTextColorAnimated(int Duration, int ToColor) {
        if (Build.VERSION.SDK_INT < 11 || Duration <= 0) {
            setTextColor(ToColor);
            return;
        }
        final TextView target = (TextView) getObject();
        final float[] from = new float[3];
        final float[] to = new float[3];
        int FromColor = getTextColor();
        Color.colorToHSV(FromColor, from);
        Color.colorToHSV(ToColor, to);
        ValueAnimator anim = ValueAnimator.ofFloat(Common.Density, 1.0f);
        anim.setDuration((long) Duration);
        final float[] hsv = new float[3];
        final int fromAlpha = Color.alpha(FromColor);
        final int toAlpha = Color.alpha(ToColor);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class anywheresoftware.b4a.objects.TextViewWrapper.AnonymousClass1 */

            public void onAnimationUpdate(ValueAnimator animation) {
                hsv[0] = from[0] + ((to[0] - from[0]) * animation.getAnimatedFraction());
                hsv[1] = from[1] + ((to[1] - from[1]) * animation.getAnimatedFraction());
                hsv[2] = from[2] + ((to[2] - from[2]) * animation.getAnimatedFraction());
                target.setTextColor(Color.HSVToColor((int) (((float) fromAlpha) + (((float) (toAlpha - fromAlpha)) * animation.getAnimatedFraction())), hsv));
            }
        });
        anim.start();
    }

    public void SetTextSizeAnimated(int Duration, float TextSize) {
        if (Build.VERSION.SDK_INT < 11 || Duration <= 0) {
            setTextSize(TextSize);
            return;
        }
        ObjectAnimator.ofFloat((TextView) getObject(), "TextSize", getTextSize(), TextSize).setDuration((long) Duration).start();
    }

    @Override // anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod
    public void setTextSize(float TextSize) {
        ((TextView) getObject()).setTextSize(TextSize);
    }

    @Override // anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod
    public float getTextSize() {
        return ((TextView) getObject()).getTextSize() / ((TextView) getObject()).getContext().getResources().getDisplayMetrics().scaledDensity;
    }

    public void setGravity(int Gravity) {
        ((TextView) getObject()).setGravity(Gravity);
    }

    public int getGravity() {
        return ((TextView) getObject()).getGravity();
    }

    public void setTypeface(Typeface Typeface) {
        ((TextView) getObject()).setTypeface(Typeface);
    }

    public Typeface getTypeface() {
        return ((TextView) getObject()).getTypeface();
    }

    @Override // anywheresoftware.b4a.AbsObjectWrapper, anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public String toString() {
        String s = super.toString();
        if (IsInitialized()) {
            return String.valueOf(s) + ", Text=" + getText();
        }
        return s;
    }

    @BA.Hide
    public static Typeface getTypeface(String name) {
        Typeface tf = cachedTypefaces.get(name);
        if (tf != null) {
            return tf;
        }
        Typeface tf2 = Typeface.createFromAsset(BA.applicationContext.getAssets(), name);
        cachedTypefaces.put(name, tf2);
        return tf2;
    }

    @BA.Hide
    public static View build(Object prev, Map<String, Object> props, boolean designer) throws Exception {
        Typeface tf;
        TextView v = (TextView) ViewWrapper.build(prev, props, designer);
        ColorStateList defaultTextColor = null;
        if (designer) {
            defaultTextColor = (ColorStateList) ViewWrapper.getDefault(v, "textColor", v.getTextColors());
        }
        String typeFace = (String) props.get("typeface");
        if (typeFace.contains(".")) {
            if (designer) {
                tf = Typeface.createFromFile(File.Combine(File.getDirInternal(), typeFace.toLowerCase(BA.cul)));
            } else {
                tf = TypefaceWrapper.LoadFromAssets(typeFace);
            }
        } else if (typeFace.equals("FontAwesome")) {
            tf = getTypeface(fontAwesomeFile);
            props.put("text", props.get("fontAwesome"));
        } else if (typeFace.equals("Material Icons")) {
            tf = getTypeface(materialIconsFile);
            props.put("text", props.get("materialIcons"));
        } else {
            tf = (Typeface) Typeface.class.getField(typeFace).get(null);
        }
        v.setText((CharSequence) props.get("text"));
        int style = ((Integer) Typeface.class.getField((String) props.get("style")).get(null)).intValue();
        v.setTextSize(((Float) props.get("fontsize")).floatValue());
        v.setTypeface(tf, style);
        v.setGravity(((Integer) Gravity.class.getField((String) props.get("vAlignment")).get(null)).intValue() | ((Integer) Gravity.class.getField((String) props.get("hAlignment")).get(null)).intValue());
        int textColor = ((Integer) props.get("textColor")).intValue();
        if (textColor != -984833) {
            v.setTextColor(textColor);
        }
        if (designer && textColor == -984833) {
            v.setTextColor(defaultTextColor);
        }
        if (designer) {
            setHint(v, (String) props.get("name"));
        }
        v.setSingleLine(((Boolean) BA.gm(props, "singleLine", false)).booleanValue());
        String ellipsizeMode = (String) BA.gm(props, "ellipsize", "NONE");
        if (!ellipsizeMode.equals("NONE")) {
            v.setEllipsize(TextUtils.TruncateAt.valueOf(ellipsizeMode));
        } else if (designer) {
            v.setEllipsize(null);
        }
        return v;
    }

    @BA.Hide
    public static void setHint(TextView v, String name) {
        if (v.getText().length() == 0 && !(v instanceof EditText)) {
            v.setText(name);
            v.setTextColor(Colors.Gray);
        }
    }
}
