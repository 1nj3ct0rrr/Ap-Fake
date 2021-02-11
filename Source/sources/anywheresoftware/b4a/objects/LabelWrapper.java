package anywheresoftware.b4a.objects;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.DynamicBuilder;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("Label")
public class LabelWrapper extends TextViewWrapper<TextView> {
    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(BA ba, String eventName, boolean keepOldObject) {
        if (!keepOldObject) {
            setObject(new TextView(ba.context));
        }
        super.innerInitialize(ba, eventName, true);
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        Drawable d;
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context) tag, TextView.class, props, designer);
        }
        TextView v = (TextView) TextViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap) props.get("drawable");
        if (!(drawProps == null || (d = (Drawable) DynamicBuilder.build(prev, drawProps, designer, null)) == null)) {
            v.setBackgroundDrawable(d);
        }
        return v;
    }
}
