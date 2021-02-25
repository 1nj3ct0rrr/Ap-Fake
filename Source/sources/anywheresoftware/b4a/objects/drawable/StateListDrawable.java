package anywheresoftware.b4a.objects.drawable;

import android.graphics.drawable.Drawable;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.DynamicBuilder;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("StateListDrawable")
public class StateListDrawable extends AbsObjectWrapper<android.graphics.drawable.StateListDrawable> {
    public static final int State_Checked = 16842912;
    public static final int State_Disabled = -16842910;
    public static final int State_Enabled = 16842910;
    public static final int State_Focused = 16842908;
    public static final int State_Pressed = 16842919;
    public static final int State_Selected = 16842913;
    public static final int State_Unchecked = -16842912;

    public void Initialize() {
        setObject(new android.graphics.drawable.StateListDrawable());
    }

    public void AddState(int State, Drawable Drawable) {
        ((android.graphics.drawable.StateListDrawable) getObject()).addState(new int[]{State}, Drawable);
    }

    public void AddState2(int[] State, Drawable Drawable) {
        ((android.graphics.drawable.StateListDrawable) getObject()).addState(State, Drawable);
    }

    public void AddCatchAllState(Drawable Drawable) {
        ((android.graphics.drawable.StateListDrawable) getObject()).addState(new int[0], Drawable);
    }

    @BA.Hide
    public static Drawable build(Object prev, HashMap<String, Object> d, boolean designer, Object tag) {
        android.graphics.drawable.StateListDrawable sld = new android.graphics.drawable.StateListDrawable();
        sld.addState(new int[]{-16842910}, (Drawable) DynamicBuilder.build(prev, (HashMap) d.get("disabledDrawable"), designer, tag));
        sld.addState(new int[]{16842919}, (Drawable) DynamicBuilder.build(prev, (HashMap) d.get("pressedDrawable"), designer, tag));
        sld.addState(new int[0], (Drawable) DynamicBuilder.build(prev, (HashMap) d.get("enabledDrawable"), designer, tag));
        return sld;
    }
}
