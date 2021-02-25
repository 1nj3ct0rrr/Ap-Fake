package anywheresoftware.b4a.objects;

import android.view.View;
import anywheresoftware.b4a.BA;

@BA.ActivityObject
@BA.ShortName("View")
public class ConcreteViewWrapper extends ViewWrapper<View> {
    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void Initialize(BA ba, String eventName) {
        throw new RuntimeException("Cannot initialize object.");
    }
}
