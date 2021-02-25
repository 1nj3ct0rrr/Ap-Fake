package anywheresoftware.b4a.objects;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.DynamicBuilder;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.keywords.LayoutBuilder;
import anywheresoftware.b4a.objects.collections.Map;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@BA.Hide
public class CustomViewWrapper extends ViewWrapper<BALayout> implements LayoutBuilder.DesignerTextSizeMethod {
    public Object customObject;
    private String eventName;
    public HashMap<String, Object> props;

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(BA ba, String eventName2, boolean keepOldObject) {
        this.ba = ba;
        this.eventName = eventName2;
    }

    public void AfterDesignerScript() throws ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<?> c = this.customObject.getClass();
        boolean userClass = this.customObject instanceof B4AClass;
        Map m = new Map();
        m.Initialize();
        m.Put("defaultcolor", Integer.valueOf((int) ViewWrapper.defaultColor));
        PanelWrapper pw = new PanelWrapper();
        pw.setObject((ViewGroup) getObject());
        LabelWrapper lw = new LabelWrapper();
        lw.setObject((TextView) getTag());
        lw.setTextSize(((Float) this.props.get("fontsize")).floatValue());
        pw.setTag(this.props.get("tag"));
        m.Put("activity", this.ba.vg);
        m.Put("ba", this.ba);
        this.eventName = this.eventName.toLowerCase(BA.cul);
        m.Put("eventName", this.eventName);
        if (this.props.containsKey("customProperties")) {
            for (Map.Entry<String, Object> e : ((HashMap) this.props.get("customProperties")).entrySet()) {
                m.Put(e.getKey(), e.getValue());
            }
        }
        Object target = this.ba.eventsTarget != null ? this.ba.eventsTarget : this.ba.activity.getClass();
        if (!BA.isShellModeRuntimeCheck(this.ba) || !userClass) {
            c.getMethod("_initialize", BA.class, Object.class, String.class).invoke(this.customObject, this.ba, target, this.eventName);
            if (userClass) {
                ((B4AClass) this.customObject).getBA().raiseEvent2(null, true, "designercreateview", true, pw, lw, m);
            } else {
                ((Common.DesignerCustomView) this.customObject).DesignerCreateView(pw, lw, m);
            }
        } else {
            this.ba.raiseEvent2(null, true, "CREATE_CUSTOM_VIEW", true, this.customObject, this.ba, target, this.eventName, pw, lw, m);
        }
    }

    @Override // anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod
    public float getTextSize() {
        return ((Float) this.props.get("fontsize")).floatValue();
    }

    @Override // anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod
    public void setTextSize(float TextSize) {
        this.props.put("fontsize", Float.valueOf(TextSize));
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props2, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context) tag, BALayout.class, props2, designer);
        }
        ViewGroup v = (ViewGroup) ViewWrapper.build(prev, props2, designer);
        Drawable d = (Drawable) DynamicBuilder.build(prev, (HashMap) props2.get("drawable"), designer, null);
        if (d != null) {
            v.setBackgroundDrawable(d);
        }
        TextView label = (TextView) TextViewWrapper.build(ViewWrapper.buildNativeView((Context) tag, TextView.class, props2, designer), props2, designer);
        if (!designer) {
            v.setTag(label);
        }
        if (designer) {
            v.removeAllViews();
            v.addView(label, new BALayout.LayoutParams(0, 0, -1, -1));
        }
        return v;
    }

    @BA.Hide
    @Deprecated
    public static void replaceBaseWithView(PanelWrapper base, View view) {
        ViewGroup basePanel = (ViewGroup) base.getObject();
        ViewGroup vg = (ViewGroup) basePanel.getParent();
        vg.addView(view, vg.indexOfChild(basePanel), basePanel.getLayoutParams());
        base.RemoveView();
    }

    @BA.Hide
    public static void replaceBaseWithView2(PanelWrapper base, View view) {
        ViewGroup basePanel = (ViewGroup) base.getObject();
        ViewGroup vg = (ViewGroup) basePanel.getParent();
        vg.addView(view, vg.indexOfChild(basePanel), basePanel.getLayoutParams());
        view.setTag(basePanel.getTag());
        base.RemoveView();
    }
}
