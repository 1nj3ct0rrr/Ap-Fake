package anywheresoftware.b4a;

import anywheresoftware.b4a.BA;

public interface ObjectWrapper<T> {
    @BA.Hide
    T getObject();

    @BA.Hide
    T getObjectOrNull();

    @BA.Hide
    void setObject(T t);
}
