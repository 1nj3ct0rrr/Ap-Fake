package anywheresoftware.b4a.objects;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.DynamicBuilder;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("HorizontalScrollView")
public class HorizontalScrollViewWrapper extends ViewWrapper<HorizontalScrollView> {
    private PanelWrapper pw = new PanelWrapper();

    public void Initialize(BA ba, int Width, String EventName) {
        super.Initialize(ba, EventName);
        PanelWrapper p = new PanelWrapper();
        p.Initialize(ba, "");
        ((HorizontalScrollView) getObject()).addView((View) p.getObject(), Width, -1);
    }

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(BA ba, String EventName, boolean keepOldObject) {
        if (!keepOldObject) {
            setObject(new MyHScrollView(ba.context));
        }
        super.innerInitialize(ba, EventName, true);
        if (ba.subExists(String.valueOf(EventName) + "_scrollchanged") && (getObject() instanceof MyHScrollView)) {
            MyHScrollView m = (MyHScrollView) getObject();
            m.ba = ba;
            m.eventName = EventName;
        }
    }

    public PanelWrapper getPanel() {
        this.pw.setObject((ViewGroup) ((HorizontalScrollView) getObject()).getChildAt(0));
        return this.pw;
    }

    public void FullScroll(boolean Right) {
        ((HorizontalScrollView) getObject()).fullScroll(Right ? 66 : 17);
    }

    public int getScrollPosition() {
        return ((HorizontalScrollView) getObject()).getScrollX();
    }

    public void setScrollPosition(int Scroll) {
        ((HorizontalScrollView) getObject()).smoothScrollTo(Scroll, 0);
    }

    public void ScrollToNow(int Scroll) {
        ((HorizontalScrollView) getObject()).scrollTo(Scroll, 0);
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context) tag, MyHScrollView.class, props, designer);
        }
        HorizontalScrollView v = (HorizontalScrollView) ViewWrapper.build(prev, props, designer);
        if (v.getChildCount() > 0) {
            v.removeAllViews();
        }
        Drawable d = (Drawable) DynamicBuilder.build(prev, (HashMap) props.get("drawable"), designer, null);
        if (props.containsKey("innerWidth")) {
            BALayout b = new BALayout((Context) tag);
            v.addView(b, (int) (((float) ((Integer) props.get("innerWidth")).intValue()) * BALayout.getDeviceScale()), -1);
            if (d != null) {
                b.setBackgroundDrawable(d);
            }
        } else if (d != null) {
            v.setBackgroundDrawable(d);
        }
        return v;
    }

    @BA.Hide
    public static class MyHScrollView extends HorizontalScrollView {
        public BA ba;
        public String eventName;

        public MyHScrollView(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            super.onScrollChanged(l, t, oldl, oldt);
            if (this.ba != null) {
                this.ba.raiseEventFromUI(this, String.valueOf(this.eventName) + "_scrollchanged", Integer.valueOf(l));
            }
        }
    }
}
