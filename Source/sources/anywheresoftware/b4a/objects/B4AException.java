package anywheresoftware.b4a.objects;

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;

@BA.ShortName("Exception")
public class B4AException extends AbsObjectWrapper<Exception> {
    public String getMessage() {
        return ((Exception) getObject()).toString();
    }
}
