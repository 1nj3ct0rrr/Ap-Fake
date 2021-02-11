package anywheresoftware.b4a;

import android.graphics.drawable.Drawable;

public class B4AMenuItem {
    public final boolean addToBar;
    public final Drawable drawable;
    public final String eventName;
    public final CharSequence title;

    public B4AMenuItem(CharSequence title2, Drawable drawable2, String eventName2, boolean addToBar2) {
        this.title = title2;
        this.drawable = drawable2;
        this.eventName = eventName2;
        this.addToBar = addToBar2;
    }
}
