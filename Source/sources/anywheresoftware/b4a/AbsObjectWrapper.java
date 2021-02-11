package anywheresoftware.b4a;

import anywheresoftware.b4a.BA;
import java.util.HashMap;
import java.util.WeakHashMap;

public class AbsObjectWrapper<T> implements ObjectWrapper<T> {
    @BA.Hide
    public static boolean Activity_LoadLayout_Was_Called = false;
    private static final WeakHashMap<Object, HashMap<String, Object>> extraMap = new WeakHashMap<>();
    private T object;

    public boolean IsInitialized() {
        return this.object != null;
    }

    @Override // anywheresoftware.b4a.ObjectWrapper
    @BA.Hide
    public T getObjectOrNull() {
        return this.object;
    }

    @Override // anywheresoftware.b4a.ObjectWrapper
    @BA.Hide
    public T getObject() {
        String msg;
        if (this.object != null) {
            return this.object;
        }
        BA.ShortName typeName = (BA.ShortName) getClass().getAnnotation(BA.ShortName.class);
        if (typeName == null) {
            msg = String.valueOf("Object should first be initialized") + ".";
        } else {
            msg = String.valueOf("Object should first be initialized") + " (" + typeName.value() + ").";
        }
        try {
            if (Class.forName("anywheresoftware.b4a.objects.ViewWrapper").isInstance(this) && !Activity_LoadLayout_Was_Called) {
                msg = String.valueOf(msg) + "\nDid you forget to call Activity.LoadLayout?";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException(msg);
    }

    @BA.Hide
    public static HashMap<String, Object> getExtraTags(Object me) {
        HashMap<String, Object> map = extraMap.get(me);
        if (map != null) {
            return map;
        }
        HashMap<String, Object> map2 = new HashMap<>();
        extraMap.put(me, map2);
        return map2;
    }

    @Override // anywheresoftware.b4a.ObjectWrapper
    @BA.Hide
    public void setObject(T object2) {
        this.object = object2;
    }

    @BA.Hide
    public int hashCode() {
        if (this.object == null) {
            return 0;
        }
        return this.object.hashCode();
    }

    @BA.Hide
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return this.object == null;
        }
        if (obj instanceof AbsObjectWrapper) {
            AbsObjectWrapper<?> other = (AbsObjectWrapper) obj;
            if (this.object == null) {
                return other.object == null;
            }
            return this.object.equals(other.object);
        } else if (this.object == null) {
            return false;
        } else {
            return this.object.equals(obj);
        }
    }

    @BA.Hide
    public String baseToString() {
        String type;
        if (this.object != null) {
            type = this.object.getClass().getSimpleName();
        } else {
            BA.ShortName typeName = (BA.ShortName) getClass().getAnnotation(BA.ShortName.class);
            if (typeName != null) {
                type = typeName.value();
            } else {
                type = getClass().getSimpleName();
            }
        }
        int i = type.lastIndexOf(".");
        if (i > -1) {
            type = type.substring(i + 1);
        }
        String s = "(" + type + ")";
        if (this.object == null) {
            return String.valueOf(s) + " Not initialized";
        }
        return s;
    }

    @BA.Hide
    public String toString() {
        String s = baseToString();
        return this.object == null ? s : String.valueOf(s) + " " + this.object.toString();
    }

    @BA.Hide
    public static ObjectWrapper ConvertToWrapper(ObjectWrapper ow, Object o) {
        ow.setObject(o);
        return ow;
    }
}
