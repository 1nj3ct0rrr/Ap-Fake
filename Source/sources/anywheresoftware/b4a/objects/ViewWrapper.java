package anywheresoftware.b4a.objects;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.keywords.LayoutBuilder;
import anywheresoftware.b4a.objects.drawable.BitmapDrawable;
import anywheresoftware.b4a.objects.drawable.ColorDrawable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@BA.Hide
public class ViewWrapper<T extends View> extends AbsObjectWrapper<T> implements BA.B4aDebuggable {
    @BA.Hide
    public static int animationDuration = 400;
    @BA.Hide
    public static final int defaultColor = -984833;
    @BA.Hide
    public static int lastId = 0;
    protected BA ba;

    public void Initialize(BA ba2, String EventName) {
        innerInitialize(ba2, EventName.toLowerCase(BA.cul), false);
    }

    @BA.Hide
    public void innerInitialize(final BA ba2, final String eventName, boolean keepOldObject) {
        this.ba = ba2;
        int i = lastId + 1;
        lastId = i;
        ((View) getObject()).setId(i);
        if (ba2.subExists(String.valueOf(eventName) + "_click")) {
            ((View) getObject()).setOnClickListener(new View.OnClickListener() {
                /* class anywheresoftware.b4a.objects.ViewWrapper.AnonymousClass1 */

                public void onClick(View v) {
                    ba2.raiseEvent(v, String.valueOf(eventName) + "_click", new Object[0]);
                }
            });
        }
        if (ba2.subExists(String.valueOf(eventName) + "_longclick")) {
            ((View) getObject()).setOnLongClickListener(new View.OnLongClickListener() {
                /* class anywheresoftware.b4a.objects.ViewWrapper.AnonymousClass2 */

                public boolean onLongClick(View v) {
                    ba2.raiseEvent(v, String.valueOf(eventName) + "_longclick", new Object[0]);
                    return true;
                }
            });
        }
    }

    public Drawable getBackground() {
        return ((View) getObject()).getBackground();
    }

    public void setBackground(Drawable drawable) {
        ((View) getObject()).setBackgroundDrawable(drawable);
    }

    @BA.DesignerName("SetBackgroundImage")
    public BitmapDrawable SetBackgroundImageNew(Bitmap Bitmap) {
        BitmapDrawable bd = new BitmapDrawable();
        bd.Initialize(Bitmap);
        ((View) getObject()).setBackgroundDrawable((Drawable) bd.getObject());
        return bd;
    }

    public void SetBackgroundImage(Bitmap Bitmap) {
        SetBackgroundImageNew(Bitmap);
    }

    public void Invalidate() {
        ((View) getObject()).invalidate();
    }

    public void Invalidate2(Rect Rect) {
        ((View) getObject()).invalidate(Rect);
    }

    public void Invalidate3(int Left, int Top, int Right, int Bottom) {
        ((View) getObject()).invalidate(Left, Top, Right, Bottom);
    }

    public void setWidth(@BA.Pixel int width) {
        getLayoutParams().width = width;
        requestLayout();
    }

    public int getWidth() {
        return getLayoutParams().width;
    }

    public int getHeight() {
        return getLayoutParams().height;
    }

    public int getLeft() {
        return ((BALayout.LayoutParams) getLayoutParams()).left;
    }

    public int getTop() {
        return ((BALayout.LayoutParams) getLayoutParams()).top;
    }

    public void setHeight(@BA.Pixel int height) {
        getLayoutParams().height = height;
        requestLayout();
    }

    public void setLeft(@BA.Pixel int left) {
        ((BALayout.LayoutParams) getLayoutParams()).left = left;
        requestLayout();
    }

    public void setTop(@BA.Pixel int top) {
        ((BALayout.LayoutParams) getLayoutParams()).top = top;
        requestLayout();
    }

    private void requestLayout() {
        ViewParent parent = ((View) getObject()).getParent();
        if (parent != null) {
            parent.requestLayout();
        }
    }

    public void setPadding(int[] p) {
        ((View) getObject()).setPadding(p[0], p[1], p[2], p[3]);
    }

    public int[] getPadding() {
        return new int[]{((View) getObject()).getPaddingLeft(), ((View) getObject()).getPaddingTop(), ((View) getObject()).getPaddingRight(), ((View) getObject()).getPaddingBottom()};
    }

    public void setColor(int color) {
        Drawable d = ((View) getObject()).getBackground();
        if (d == null || !(d instanceof GradientDrawable)) {
            ((View) getObject()).setBackgroundColor(color);
        } else if (!(d instanceof ColorDrawable.GradientDrawableWithCorners) || ((ColorDrawable.GradientDrawableWithCorners) d).borderWidth == 0) {
            ColorDrawable cd = new ColorDrawable();
            cd.Initialize(color, (int) findRadius());
            ((View) getObject()).setBackgroundDrawable((Drawable) cd.getObject());
        } else {
            ((ColorDrawable.GradientDrawableWithCorners) d).setColor(color);
            ((View) getObject()).invalidate();
            ((View) getObject()).requestLayout();
        }
    }

    private float findRadius() {
        Drawable d = ((View) getObject()).getBackground();
        if (d == null || !(d instanceof GradientDrawable)) {
            return Common.Density;
        }
        if (d instanceof ColorDrawable.GradientDrawableWithCorners) {
            return ((ColorDrawable.GradientDrawableWithCorners) d).cornerRadius;
        }
        GradientDrawable g = (GradientDrawable) ((View) getObject()).getBackground();
        try {
            Field state = g.getClass().getDeclaredField("mGradientState");
            state.setAccessible(true);
            Object gstate = state.get(g);
            return ((Float) gstate.getClass().getDeclaredField("mRadius").get(gstate)).floatValue();
        } catch (Exception e) {
            e.printStackTrace();
            return Common.Density;
        }
    }

    public void setTag(Object tag) {
        ((View) getObject()).setTag(tag);
    }

    public Object getTag() {
        return ((View) getObject()).getTag();
    }

    public Object getParent() {
        return ((View) getObject()).getParent();
    }

    public void setVisible(boolean Visible) {
        ((View) getObject()).setVisibility(Visible ? 0 : 8);
    }

    public boolean getVisible() {
        return ((View) getObject()).getVisibility() == 0;
    }

    public void setEnabled(boolean Enabled) {
        ((View) getObject()).setEnabled(Enabled);
    }

    public boolean getEnabled() {
        return ((View) getObject()).isEnabled();
    }

    public void BringToFront() {
        if (((View) getObject()).getParent() instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) ((View) getObject()).getParent();
            vg.removeView((View) getObject());
            vg.addView((View) getObject());
        }
    }

    public void SendToBack() {
        if (((View) getObject()).getParent() instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) ((View) getObject()).getParent();
            vg.removeView((View) getObject());
            vg.addView((View) getObject(), 0);
        }
    }

    public void RemoveView() {
        if (((View) getObject()).getParent() instanceof ViewGroup) {
            ((ViewGroup) ((View) getObject()).getParent()).removeView((View) getObject());
        }
    }

    public void SetLayout(@BA.Pixel int Left, @BA.Pixel int Top, @BA.Pixel int Width, @BA.Pixel int Height) {
        BALayout.LayoutParams lp = (BALayout.LayoutParams) getLayoutParams();
        lp.left = Left;
        lp.top = Top;
        lp.width = Width;
        lp.height = Height;
        if (((View) getObject()).getParent() != null) {
            ((View) getObject()).getParent().requestLayout();
        }
    }

    private ViewGroup.LayoutParams getLayoutParams() {
        ViewGroup.LayoutParams lp = ((View) getObject()).getLayoutParams();
        if (lp != null) {
            return lp;
        }
        ViewGroup.LayoutParams lp2 = new BALayout.LayoutParams(0, 0, 0, 0);
        ((View) getObject()).setLayoutParams(lp2);
        return lp2;
    }

    public void SetLayoutAnimated(int Duration, @BA.Pixel int Left, @BA.Pixel int Top, @BA.Pixel int Width, @BA.Pixel int Height) {
        BALayout.LayoutParams lp = (BALayout.LayoutParams) ((View) getObject()).getLayoutParams();
        if (lp == null) {
            SetLayout(Left, Top, Width, Height);
            return;
        }
        int pLeft = lp.left;
        int pTop = lp.top;
        int pWidth = lp.width;
        int pHeight = lp.height;
        SetLayout(Left, Top, Width, Height);
        AnimateFrom((View) getObject(), Duration, pLeft, pTop, pWidth, pHeight);
    }

    public void SetColorAnimated(int Duration, int FromColor, int ToColor) {
        final ColorDrawable.GradientDrawableWithCorners gdc;
        if (Build.VERSION.SDK_INT < 11 || Duration <= 0) {
            setColor(ToColor);
            return;
        }
        final View target = (View) getObject();
        if (target.getBackground() instanceof ColorDrawable.GradientDrawableWithCorners) {
            gdc = (ColorDrawable.GradientDrawableWithCorners) target.getBackground();
        } else {
            gdc = new ColorDrawable.GradientDrawableWithCorners();
        }
        gdc.setColor(FromColor);
        target.setBackgroundDrawable(gdc);
        final float[] from = new float[3];
        final float[] to = new float[3];
        Color.colorToHSV(FromColor, from);
        Color.colorToHSV(ToColor, to);
        ValueAnimator anim = ValueAnimator.ofFloat(Common.Density, 1.0f);
        anim.setDuration((long) Duration);
        final float[] hsv = new float[3];
        final int fromAlpha = Color.alpha(FromColor);
        final int toAlpha = Color.alpha(ToColor);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class anywheresoftware.b4a.objects.ViewWrapper.AnonymousClass3 */

            public void onAnimationUpdate(ValueAnimator animation) {
                hsv[0] = from[0] + ((to[0] - from[0]) * animation.getAnimatedFraction());
                hsv[1] = from[1] + ((to[1] - from[1]) * animation.getAnimatedFraction());
                hsv[2] = from[2] + ((to[2] - from[2]) * animation.getAnimatedFraction());
                gdc.setColor(Color.HSVToColor((int) (((float) fromAlpha) + (((float) (toAlpha - fromAlpha)) * animation.getAnimatedFraction())), hsv));
                target.invalidate();
            }
        });
        anim.start();
    }

    public void SetVisibleAnimated(int Duration, final boolean Visible) {
        ObjectAnimator oa;
        if (Visible != getVisible()) {
            if (Build.VERSION.SDK_INT < 11 || Duration <= 0) {
                setVisible(Visible);
                return;
            }
            final View target = (View) getObject();
            if (Visible) {
                oa = ObjectAnimator.ofFloat(target, "alpha", Common.Density, 1.0f);
            } else {
                oa = ObjectAnimator.ofFloat(target, "alpha", 1.0f, Common.Density);
            }
            oa.addListener(new Animator.AnimatorListener() {
                /* class anywheresoftware.b4a.objects.ViewWrapper.AnonymousClass4 */

                public void onAnimationCancel(Animator animation) {
                }

                public void onAnimationEnd(Animator animation) {
                    if (!Visible) {
                        target.setVisibility(8);
                    }
                    target.setAlpha(1.0f);
                }

                public void onAnimationRepeat(Animator animation) {
                }

                public void onAnimationStart(Animator animation) {
                }
            });
            oa.setDuration((long) Duration).start();
            if (Visible) {
                target.setVisibility(0);
            }
        }
    }

    @BA.Hide
    public static void AnimateFrom(View target, int Duration, int pLeft, int pTop, int pWidth, int pHeight) {
        BALayout.LayoutParams lp = (BALayout.LayoutParams) target.getLayoutParams();
        if (Build.VERSION.SDK_INT >= 11 && Duration > 0 && pWidth >= 0 && pHeight >= 0) {
            target.setPivotX(Common.Density);
            target.setPivotY(Common.Density);
            WeakReference<AnimatorSet> wr = (WeakReference) AbsObjectWrapper.getExtraTags(target).get("prevSet");
            AnimatorSet prevSet = wr != null ? wr.get() : null;
            if (prevSet != null && prevSet.isRunning()) {
                prevSet.end();
            }
            AnimatorSet set = new AnimatorSet();
            AbsObjectWrapper.getExtraTags(target).put("prevSet", new WeakReference(set));
            set.playTogether(ObjectAnimator.ofFloat(target, "translationX", (float) (pLeft - lp.left), 0.0f), ObjectAnimator.ofFloat(target, "translationY", (float) (pTop - lp.top), 0.0f), ObjectAnimator.ofFloat(target, "scaleX", ((float) pWidth) / ((float) lp.width), 1.0f), ObjectAnimator.ofFloat(target, "scaleY", ((float) pHeight) / ((float) lp.height), 1.0f));
            set.setDuration((long) Duration);
            set.start();
        }
    }

    public boolean RequestFocus() {
        return ((View) getObject()).requestFocus();
    }

    @Override // anywheresoftware.b4a.AbsObjectWrapper
    @BA.Hide
    public String toString() {
        String s;
        String s2 = baseToString();
        if (!IsInitialized()) {
            return s2;
        }
        String s3 = String.valueOf(s2) + ": ";
        if (!getEnabled()) {
            s3 = String.valueOf(s3) + "Enabled=false, ";
        }
        if (!getVisible()) {
            s3 = String.valueOf(s3) + "Visible=false, ";
        }
        if (((View) getObject()).getLayoutParams() == null || !(((View) getObject()).getLayoutParams() instanceof BALayout.LayoutParams)) {
            s = String.valueOf(s3) + "Layout not available";
        } else {
            s = String.valueOf(s3) + "Left=" + getLeft() + ", Top=" + getTop() + ", Width=" + getWidth() + ", Height=" + getHeight();
        }
        if (getTag() != null) {
            return String.valueOf(s) + ", Tag=" + getTag().toString();
        }
        return s;
    }

    @BA.Hide
    public static View build(Object prev, Map<String, Object> props, boolean designer) throws Exception {
        View v = (View) prev;
        if (v.getTag() == null && designer) {
            HashMap<String, Object> defaults = new HashMap<>();
            defaults.put("background", v.getBackground());
            defaults.put("padding", new int[]{v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), v.getPaddingBottom()});
            v.setTag(defaults);
        }
        BALayout.LayoutParams lp = (BALayout.LayoutParams) v.getLayoutParams();
        if (lp == null) {
            lp = new BALayout.LayoutParams();
            v.setLayoutParams(lp);
        }
        lp.setFromUserPlane(((Integer) props.get("left")).intValue(), ((Integer) props.get("top")).intValue(), ((Integer) props.get("width")).intValue(), ((Integer) props.get("height")).intValue());
        v.setEnabled(((Boolean) props.get("enabled")).booleanValue());
        if (!designer) {
            int visible = 0;
            if (!((Boolean) props.get("visible")).booleanValue()) {
                visible = 8;
            }
            v.setVisibility(visible);
            v.setTag(props.get("tag"));
        }
        int[] padding = (int[]) props.get("padding");
        if (padding != null) {
            v.setPadding(Math.round(BALayout.getDeviceScale() * ((float) padding[0])), Math.round(BALayout.getDeviceScale() * ((float) padding[1])), Math.round(BALayout.getDeviceScale() * ((float) padding[2])), Math.round(BALayout.getDeviceScale() * ((float) padding[3])));
        } else if (designer) {
            int[] defaultPadding = (int[]) getDefault(v, "padding", null);
            v.setPadding(defaultPadding[0], defaultPadding[1], defaultPadding[2], defaultPadding[3]);
        }
        return v;
    }

    @BA.Hide
    public static void fixAnchor(int pw, int ph, LayoutBuilder.ViewWrapperAndAnchor vwa) {
        if (vwa.hanchor == LayoutBuilder.ViewWrapperAndAnchor.RIGHT) {
            vwa.right = vwa.vw.getLeft();
            vwa.vw.setLeft((pw - vwa.right) - vwa.vw.getWidth());
        } else if (vwa.hanchor == LayoutBuilder.ViewWrapperAndAnchor.BOTH) {
            vwa.right = vwa.vw.getWidth();
            vwa.vw.setWidth((pw - vwa.right) - vwa.vw.getLeft());
        }
        if (vwa.vanchor == LayoutBuilder.ViewWrapperAndAnchor.BOTTOM) {
            vwa.bottom = vwa.vw.getTop();
            vwa.vw.setTop((ph - vwa.bottom) - vwa.vw.getHeight());
        } else if (vwa.vanchor == LayoutBuilder.ViewWrapperAndAnchor.BOTH) {
            vwa.bottom = vwa.vw.getHeight();
            vwa.vw.setHeight((ph - vwa.bottom) - vwa.vw.getTop());
        }
    }

    @Override // anywheresoftware.b4a.BA.B4aDebuggable
    @BA.Hide
    public Object[] debug(int limit, boolean[] outShouldAddReflectionFields) {
        Object[] res = {"ToString", toString()};
        outShouldAddReflectionFields[0] = true;
        return res;
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [T extends android.view.View, java.lang.Object] */
    @BA.Hide
    public static <T> T buildNativeView(Context context, Class<T> cls, HashMap<String, Object> props, boolean designer) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        Class cls2;
        String overideClass = (String) props.get("nativeClass");
        if (overideClass != null && overideClass.startsWith(".")) {
            overideClass = String.valueOf(BA.applicationContext.getPackageName()) + overideClass;
        }
        if (!designer && overideClass != null) {
            try {
                if (overideClass.length() != 0) {
                    cls2 = Class.forName(overideClass);
                    return cls2.getConstructor(Context.class).newInstance(context);
                }
            } catch (ClassNotFoundException e) {
                int i = overideClass.lastIndexOf(".");
                cls2 = Class.forName(String.valueOf(overideClass.substring(0, i)) + "$" + overideClass.substring(i + 1));
            }
        }
        cls2 = cls;
        return cls2.getConstructor(Context.class).newInstance(context);
    }

    @BA.Hide
    public static Object getDefault(View v, String key, Object defaultValue) {
        HashMap<String, Object> map = (HashMap) v.getTag();
        if (map.containsKey(key)) {
            return map.get(key);
        }
        map.put(key, defaultValue);
        return defaultValue;
    }
}
