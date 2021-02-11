package de.amberhome.objects;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.CustomViewWrapper;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.collections.Map;

@BA.ActivityObject
@BA.Version(1.1f)
@BA.Author("Markus Stipp")
@BA.ShortName("CardView")
public class CardViewWrapper extends ViewWrapper<CardView> implements Common.DesignerCustomView {
    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void Initialize(BA ba, String EventName) {
        super.Initialize(ba, EventName);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(BA ba, String eventName, boolean keepOldObject) {
        if (!keepOldObject) {
            setObject(new CardView(ba.context));
        }
        super.innerInitialize(ba, eventName, true);
    }

    @Override // anywheresoftware.b4a.keywords.Common.DesignerCustomView
    @BA.Hide
    public void _initialize(BA ba, Object activityClass, String EventName) {
        Initialize(ba, EventName);
    }

    @Override // anywheresoftware.b4a.keywords.Common.DesignerCustomView
    public void DesignerCreateView(PanelWrapper base, LabelWrapper label, Map props) {
        setVisible(base.getVisible());
        setEnabled(base.getEnabled());
        ((CardView) getObject()).setPadding(((ViewGroup) base.getObject()).getPaddingLeft(), ((ViewGroup) base.getObject()).getPaddingTop(), ((ViewGroup) base.getObject()).getPaddingRight(), ((ViewGroup) base.getObject()).getPaddingBottom());
        if (props.Get("CornerRadius") != null) {
            ((CardView) getObject()).setRadius((float) Common.DipToCurrent(((Integer) props.Get("CornerRadius")).intValue()));
        }
        if (props.Get("Elevation") != null) {
            ((CardView) getObject()).setCardElevation((float) Common.DipToCurrent(((Integer) props.Get("Elevation")).intValue()));
        }
        if (!(props.Get("CardColor") == null || ((Integer) props.Get("CardColor")).intValue() == -984833)) {
            ((CardView) getObject()).setCardBackgroundColor(((Integer) props.Get("CardColor")).intValue());
        }
        if (props.Get("ContentPadding") != null) {
            int padding = Common.DipToCurrent(((Integer) props.Get("ContentPadding")).intValue());
            ((CardView) getObject()).setContentPadding(padding, padding, padding, padding);
        }
        if (props.Get("MaxElevation") != null) {
            ((CardView) getObject()).setMaxCardElevation((float) Common.DipToCurrent(((Integer) props.Get("MaxElevation")).intValue()));
        }
        if (props.Get("PreventCornerOverlap") != null) {
            ((CardView) getObject()).setPreventCornerOverlap(((Boolean) props.Get("PreventCornerOverlap")).booleanValue());
        }
        if (props.Get("UseCompatPadding") != null) {
            ((CardView) getObject()).setUseCompatPadding(((Boolean) props.Get("UseCompatPadding")).booleanValue());
        }
        CustomViewWrapper.replaceBaseWithView2(base, (View) getObject());
    }

    public void AddView(View View, int Left, int Top, int Width, int Height) {
        ((CardView) getObject()).addView(View, new BALayout.LayoutParams(Left, Top, Width, Height));
    }

    @BA.RaisesSynchronousEvents
    public void LoadLayout(BA ba, String LayoutFile) throws Exception {
        PanelWrapper p = new PanelWrapper();
        p.Initialize(ba, "");
        ((ViewGroup) p.getObject()).setLayoutParams(new ViewGroup.LayoutParams((((CardView) getObject()).getLayoutParams().width - ((CardView) getObject()).getPaddingLeft()) - ((CardView) getObject()).getPaddingRight(), (((CardView) getObject()).getLayoutParams().height - ((CardView) getObject()).getPaddingTop()) - ((CardView) getObject()).getPaddingBottom()));
        p.setColor(0);
        ((CardView) getObject()).addView((View) p.getObject());
        p.LoadLayout(LayoutFile, ba);
    }

    public void setElevation(float Elevation) {
        ((CardView) getObject()).setCardElevation(Elevation);
    }

    public float getElevation() {
        return ((CardView) getObject()).getCardElevation();
    }

    public void setMaxElevation(float MaxElevation) {
        ((CardView) getObject()).setMaxCardElevation(MaxElevation);
    }

    public float getMaxElevation() {
        return ((CardView) getObject()).getMaxCardElevation();
    }

    public void SetContentPadding(int Left, int Top, int Right, int Bottom) {
        ((CardView) getObject()).setContentPadding(Left, Top, Right, Bottom);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void setBackground(Drawable BG) {
        super.setBackground(BG);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public Drawable getBackground() {
        return super.getBackground();
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void setColor(int Color) {
        ((CardView) getObject()).setCardBackgroundColor(Color);
    }

    public float getCornerRadius() {
        return ((CardView) getObject()).getRadius();
    }

    public void setCornerRadius(float Radius) {
        ((CardView) getObject()).setRadius(Radius);
    }

    public void RemoveAllViews() {
        ((CardView) getObject()).removeAllViews();
    }

    public void setUseCompatPadding(boolean Padding) {
        ((CardView) getObject()).setUseCompatPadding(Padding);
    }

    public boolean getUseCompatPadding() {
        return ((CardView) getObject()).getUseCompatPadding();
    }

    public void setPreventCornerOverlap(boolean Overlap) {
        ((CardView) getObject()).setPreventCornerOverlap(Overlap);
    }

    public boolean getPreventCornerOverlap() {
        return ((CardView) getObject()).getPreventCornerOverlap();
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void Invalidate2(Rect Rect) {
        super.Invalidate2(Rect);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void Invalidate3(int Left, int Top, int Right, int Bottom) {
        super.Invalidate3(Left, Top, Right, Bottom);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @TargetApi(12)
    public void SetColorAnimated(int Duration, int FromColor, int ToColor) {
        if (Build.VERSION.SDK_INT < 12 || Duration <= 0) {
            ((CardView) getObject()).setCardBackgroundColor(ToColor);
            return;
        }
        final View target = (View) getObject();
        ((CardView) getObject()).setCardBackgroundColor(FromColor);
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
            /* class de.amberhome.objects.CardViewWrapper.AnonymousClass1 */

            public void onAnimationUpdate(ValueAnimator animation) {
                hsv[0] = from[0] + ((to[0] - from[0]) * animation.getAnimatedFraction());
                hsv[1] = from[1] + ((to[1] - from[1]) * animation.getAnimatedFraction());
                hsv[2] = from[2] + ((to[2] - from[2]) * animation.getAnimatedFraction());
                ((CardView) CardViewWrapper.this.getObject()).setCardBackgroundColor(Color.HSVToColor((int) (((float) fromAlpha) + (((float) (toAlpha - fromAlpha)) * animation.getAnimatedFraction())), hsv));
                target.invalidate();
            }
        });
        anim.start();
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void SetVisibleAnimated(int Duration, boolean Visible) {
        super.SetVisibleAnimated(Duration, Visible);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void SetLayoutAnimated(int Duration, int Left, int Top, int Width, int Height) {
        super.SetLayoutAnimated(Duration, Left, Top, Width, Height);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void SetLayout(int Left, int Top, int Width, int Height) {
        super.SetLayout(Left, Top, Width, Height);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public int getLeft() {
        return super.getLeft();
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void setLeft(int Left) {
        super.setLeft(Left);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public int getTop() {
        return super.getTop();
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void setTop(int Top) {
        super.setTop(Top);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public int getWidth() {
        return super.getWidth();
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void setWidth(int Width) {
        super.setWidth(Width);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public int getHeight() {
        return super.getHeight();
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    public void setHeight(int Height) {
        super.setHeight(Height);
    }
}
