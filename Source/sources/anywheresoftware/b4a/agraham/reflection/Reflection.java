package anywheresoftware.b4a.agraham.reflection;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import anywheresoftware.b4a.BA;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

@BA.Version(2.4f)
@BA.Author("Andrew Graham")
@BA.ShortName("Reflector")
public class Reflection {
    private static HashMap<String, Class<?>> primitives = new HashMap<String, Class<?>>() {
        /* class anywheresoftware.b4a.agraham.reflection.Reflection.AnonymousClass1 */
        private static final long serialVersionUID = 1;

        {
            put("java.lang.boolean", Boolean.TYPE);
            put("java.lang.byte", Byte.TYPE);
            put("java.lang.char", Character.TYPE);
            put("java.lang.short", Short.TYPE);
            put("java.lang.int", Integer.TYPE);
            put("java.lang.long", Long.TYPE);
            put("java.lang.float", Float.TYPE);
            put("java.lang.double", Double.TYPE);
            put("Z", Boolean.TYPE);
            put("B", Byte.TYPE);
            put("C", Character.TYPE);
            put("S", Short.TYPE);
            put("I", Integer.TYPE);
            put("J", Long.TYPE);
            put("F", Float.TYPE);
            put("D", Double.TYPE);
        }
    };
    private static final double version = 2.4d;
    public Object Target;

    public void LIBRARY_DOC() {
    }

    private static Class<?> classforname(String type) {
        if (primitives.containsKey(type)) {
            return primitives.get(type);
        }
        try {
            return Class.forName(type);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object sethelper(String o, Class t) {
        if (t.isEnum()) {
            return Enum.valueOf(t, o);
        }
        if (!t.isPrimitive()) {
            return o;
        }
        if (t == Boolean.TYPE) {
            return Boolean.valueOf(Boolean.parseBoolean(o));
        }
        if (t == Byte.TYPE) {
            return Byte.valueOf(Byte.parseByte(o));
        }
        if (t == Character.TYPE) {
            return Character.valueOf(o.charAt(0));
        }
        if (t == Short.TYPE) {
            return Short.valueOf(Short.parseShort(o));
        }
        if (t == Integer.TYPE) {
            return Integer.valueOf(Integer.parseInt(o));
        }
        if (t == Long.TYPE) {
            return Long.valueOf(Long.parseLong(o));
        }
        if (t == Float.TYPE) {
            return Float.valueOf(Float.parseFloat(o));
        }
        if (t == Double.TYPE) {
            return Double.valueOf(Double.parseDouble(o));
        }
        return o;
    }

    private Object runmethod(String method, Object[] Args, Class<?>[] clsArr) throws Exception {
        Class<?> ob = Class.forName("java.lang.Object");
        Class<?> ot = this.Target.getClass();
        Boolean loop = true;
        do {
            if (ot == ob) {
                try {
                } catch (NoSuchMethodException e) {
                    ot = ot.getSuperclass();
                    if (!loop.booleanValue()) {
                        throw e;
                    }
                } catch (InvocationTargetException e2) {
                    throw ((Exception) e2.getCause());
                }
            }
            Method m = ot.getDeclaredMethod(method, clsArr);
            m.setAccessible(true);
            return m.invoke(this.Target, Args);
        } while (!loop.booleanValue());
        throw e;
    }

    public Object CreateObject(String type) {
        try {
            return classforname(type).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object CreateObject2(String type, Object[] args, String[] types) {
        Class[] ts = new Class[args.length];
        try {
            Class<?> ot = classforname(type);
            for (int i = 0; i < args.length; i++) {
                ts[i] = classforname(types[i]);
            }
            return ot.getConstructor(ts).newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Field GetFieldInfo(String field) throws Exception {
        Class<?> ob = Class.forName("java.lang.Object");
        Class<?> ot = this.Target.getClass();
        do {
            try {
                Field f = ot.getDeclaredField(field);
                f.setAccessible(true);
                return f;
            } catch (Exception e) {
                ot = ot.getSuperclass();
                if (ot == ob) {
                    throw e;
                }
            }
        } while (ot == ob);
        throw e;
    }

    public Object GetField(String field) throws Exception {
        Class<?> ob = Class.forName("java.lang.Object");
        Class<?> ot = this.Target.getClass();
        do {
            try {
                Field f = ot.getDeclaredField(field);
                f.setAccessible(true);
                return f.get(this.Target);
            } catch (Exception e) {
                ot = ot.getSuperclass();
                if (ot == ob) {
                    throw e;
                }
            }
        } while (ot == ob);
        throw e;
    }

    public Object GetField2(Field fieldinfo) throws Exception {
        return fieldinfo.get(this.Target);
    }

    public Object GetPublicField(String field) {
        try {
            return this.Target.getClass().getField(field).get(this.Target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object GetStaticField(String classname, String field) throws Exception {
        Class<?> ob = Class.forName("java.lang.Object");
        Class<?> ot = Class.forName(classname);
        do {
            try {
                Field f = ot.getDeclaredField(field);
                f.setAccessible(true);
                return f.get(null);
            } catch (Exception e) {
                ot = ot.getSuperclass();
                if (ot == ob) {
                    throw e;
                }
            }
        } while (ot == ob);
        throw e;
    }

    public void SetField(String field, String value, String type) throws Exception {
        Class<?> ob = Class.forName("java.lang.Object");
        Class<?> ot = this.Target.getClass();
        do {
            try {
                Field f = ot.getDeclaredField(field);
                f.setAccessible(true);
                f.set(this.Target, sethelper(value, classforname(type)));
                return;
            } catch (Exception e) {
                ot = ot.getSuperclass();
                if (ot == ob) {
                    throw e;
                }
            }
        } while (ot == ob);
        throw e;
    }

    public void SetField2(String field, Object value) throws Exception {
        Class<?> ob = Class.forName("java.lang.Object");
        Class<?> ot = this.Target.getClass();
        do {
            try {
                Field f = ot.getDeclaredField(field);
                f.setAccessible(true);
                f.set(this.Target, value);
                return;
            } catch (Exception e) {
                ot = ot.getSuperclass();
                if (ot == ob) {
                    throw e;
                }
            }
        } while (ot == ob);
        throw e;
    }

    public void SetField3(Field fieldinfo, String value, String type) throws Exception {
        fieldinfo.set(this.Target, sethelper(value, classforname(type)));
    }

    public void SetField4(Field fieldinfo, Object value) throws Exception {
        fieldinfo.set(this.Target, value);
    }

    public void SetPublicField(String field, String value, String type) {
        try {
            this.Target.getClass().getField(field).set(this.Target, sethelper(value, classforname(type)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void SetPublicField2(String field, Object value) {
        try {
            this.Target.getClass().getField(field).set(this.Target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void SetStaticField(String classname, String field, String value, String type) throws Exception {
        Class<?> ob = Class.forName("java.lang.Object");
        Class<?> ot = Class.forName(classname);
        do {
            try {
                Field f = ot.getDeclaredField(field);
                f.setAccessible(true);
                f.set(null, sethelper(value, classforname(type)));
                return;
            } catch (Exception e) {
                ot = ot.getSuperclass();
                if (ot == ob) {
                    throw e;
                }
            }
        } while (ot == ob);
        throw e;
    }

    public void SetStaticField2(String classname, String field, Object value) throws Exception {
        Class<?> ob = Class.forName("java.lang.Object");
        Class<?> ot = Class.forName(classname);
        do {
            try {
                Field f = ot.getDeclaredField(field);
                f.setAccessible(true);
                f.set(null, value);
                return;
            } catch (Exception e) {
                ot = ot.getSuperclass();
                if (ot == ob) {
                    throw e;
                }
            }
        } while (ot == ob);
        throw e;
    }

    public int[] TargetRank() {
        Class<?> type = this.Target.getClass();
        if (!type.isArray()) {
            return new int[0];
        }
        int dims = type.getName().lastIndexOf(91) + 1;
        int[] lens = new int[dims];
        Object arr = this.Target;
        for (int i = 0; i < dims; i++) {
            int len = Array.getLength(arr);
            lens[i] = len;
            if (len > 0) {
                arr = Array.get(arr, 0);
            }
        }
        return lens;
    }

    public Object GetArray(int[] indeces) {
        Object arr = this.Target;
        for (int i = 0; i < indeces.length - 1; i++) {
            arr = Array.get(arr, indeces[i]);
        }
        return Array.get(arr, indeces[indeces.length - 1]);
    }

    public void SetArray(int[] indeces, String value, String type) {
        Object arr = this.Target;
        for (int i = 0; i < indeces.length - 1; i++) {
            arr = Array.get(arr, indeces[i]);
        }
        Array.set(arr, indeces[indeces.length - 1], sethelper(value, classforname(type)));
    }

    public void SetArray2(int[] indeces, Object value) {
        Object arr = this.Target;
        for (int i = 0; i < indeces.length - 1; i++) {
            arr = Array.get(arr, indeces[i]);
        }
        Array.set(arr, indeces[indeces.length - 1], value);
    }

    public Context GetContext(BA ba) {
        return ba.context;
    }

    public Activity GetActivity(BA ba) {
        return ba.sharedProcessBA.activityBA.get().activity;
    }

    public BA GetActivityBA(BA ba) {
        return ba.sharedProcessBA.activityBA.get();
    }

    public Class<?> GetB4AClass(String component) {
        try {
            return Class.forName(String.valueOf(BA.packageName) + "." + component.toLowerCase(BA.cul));
        } catch (Exception e) {
            throw new RuntimeException("Class " + component + " not found.");
        }
    }

    public BA GetProcessBA(String component) {
        try {
            Field f = Class.forName(String.valueOf(BA.packageName) + "." + component.toLowerCase(BA.cul)).getDeclaredField("processBA");
            f.setAccessible(true);
            return (BA) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object GetMostCurrent(String component) {
        try {
            Field f = Class.forName(String.valueOf(BA.packageName) + "." + component.toLowerCase(BA.cul)).getDeclaredField("mostCurrent");
            f.setAccessible(true);
            return f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Method GetMethod(String method, String[] types) throws Exception {
        Class<?> ob = Class.forName("java.lang.Object");
        Class<?> ot = this.Target.getClass();
        if (types == null) {
            types = new String[0];
        }
        Class[] ots = new Class[types.length];
        for (int i = 0; i < types.length; i++) {
            ots[i] = classforname(types[i]);
        }
        do {
            try {
                Method m = ot.getDeclaredMethod(method, ots);
                m.setAccessible(true);
                return m;
            } catch (NoSuchMethodException e) {
                ot = ot.getSuperclass();
                if (ot == ob) {
                    throw e;
                }
            }
        } while (ot == ob);
        throw e;
    }

    public Object InvokeMethod(Object instance, Method method, Object[] args) throws Exception {
        try {
            return method.invoke(instance, args);
        } catch (InvocationTargetException e) {
            throw ((Exception) e.getCause());
        }
    }

    public Object RunMethod(String method) throws Exception {
        return runmethod(method, new Object[0], new Class[0]);
    }

    public Object RunMethod2(String method, String arg1, String type1) throws Exception {
        Class[] ot = {classforname(type1)};
        return runmethod(method, new Object[]{sethelper(arg1, ot[0])}, ot);
    }

    public Object RunMethod3(String method, String arg1, String type1, String arg2, String type2) throws Exception {
        Class[] ot = {classforname(type1), classforname(type2)};
        return runmethod(method, new Object[]{sethelper(arg1, ot[0]), sethelper(arg2, ot[1])}, ot);
    }

    public Object RunMethod4(String method, Object[] args, String[] types) throws Exception {
        if (types == null) {
            types = new String[0];
        }
        Class[] ot = new Class[types.length];
        for (int i = 0; i < types.length; i++) {
            ot[i] = classforname(types[i]);
        }
        return runmethod(method, args, ot);
    }

    public Object RunPublicmethod(String Method, Object[] Args, String[] types) {
        if (types == null) {
            types = new String[0];
        }
        Class[] ot = new Class[types.length];
        for (int i = 0; i < types.length; i++) {
            ot[i] = classforname(types[i]);
        }
        try {
            return this.Target.getClass().getMethod(Method, ot).invoke(this.Target, Args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object RunStaticMethod(String classname, String method, Object[] args, String[] types) throws Exception {
        if (types == null) {
            types = new String[0];
        }
        Class<?> ob = Class.forName("java.lang.Object");
        Class<?> ot = Class.forName(classname);
        Class[] ots = new Class[types.length];
        for (int i = 0; i < types.length; i++) {
            ots[i] = classforname(types[i]);
        }
        do {
            try {
                Method m = ot.getDeclaredMethod(method, ots);
                m.setAccessible(true);
                return m.invoke(null, args);
            } catch (NoSuchMethodException e) {
                ot = ot.getSuperclass();
                if (ot == ob) {
                    throw e;
                }
            } catch (InvocationTargetException e2) {
                throw ((Exception) e2.getCause());
            }
        } while (ot == ob);
        throw e;
    }

    public String ToString() {
        return this.Target.toString();
    }

    public double getVersion() {
        return version;
    }

    public String getTypeName() {
        return this.Target.getClass().getName();
    }

    public boolean getIsNull() {
        return this.Target == null;
    }

    public void SetOnClickListener(final BA ba, String sub) {
        final String fsub = sub.toLowerCase();
        ((View) this.Target).setOnClickListener(new View.OnClickListener() {
            /* class anywheresoftware.b4a.agraham.reflection.Reflection.AnonymousClass2 */

            public void onClick(View v) {
                ba.raiseEvent(v, fsub, v.getTag());
            }
        });
    }

    public void SetOnLongClickListener(final BA ba, String sub) {
        final String fsub = sub.toLowerCase();
        ((View) this.Target).setOnLongClickListener(new View.OnLongClickListener() {
            /* class anywheresoftware.b4a.agraham.reflection.Reflection.AnonymousClass3 */

            public boolean onLongClick(View v) {
                Object b = ba.raiseEvent(v, fsub, v.getTag());
                if (b != null) {
                    return ((Boolean) b).booleanValue();
                }
                Log.w("B4A", String.valueOf(fsub) + " raiseEvent returned null");
                return false;
            }
        });
    }

    public void SetOnCreateContextMenuListener(final BA ba, String sub) {
        final String fsub = sub.toLowerCase();
        ((View) this.Target).setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            /* class anywheresoftware.b4a.agraham.reflection.Reflection.AnonymousClass4 */

            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                ba.raiseEvent(v, fsub, v.getTag(), menu, menuInfo);
            }
        });
    }

    public void SetOnFocusListener(final BA ba, String sub) {
        final String fsub = sub.toLowerCase();
        ((View) this.Target).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /* class anywheresoftware.b4a.agraham.reflection.Reflection.AnonymousClass5 */

            public void onFocusChange(View v, boolean hasFocus) {
                ba.raiseEvent(v, fsub, v.getTag(), Boolean.valueOf(hasFocus));
            }
        });
    }

    public void SetOnKeyListener(final BA ba, String sub) {
        final String fsub = sub.toLowerCase();
        ((View) this.Target).setOnKeyListener(new View.OnKeyListener() {
            /* class anywheresoftware.b4a.agraham.reflection.Reflection.AnonymousClass6 */

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Object b = ba.raiseEvent(v, fsub, v.getTag(), Integer.valueOf(keyCode), event);
                if (b != null) {
                    return ((Boolean) b).booleanValue();
                }
                Log.w("B4A", String.valueOf(fsub) + " raiseEvent returned null");
                return false;
            }
        });
    }

    public void SetOnTouchListener(final BA ba, String sub) {
        final String fsub = sub.toLowerCase();
        ((View) this.Target).setOnTouchListener(new View.OnTouchListener() {
            /* class anywheresoftware.b4a.agraham.reflection.Reflection.AnonymousClass7 */

            public boolean onTouch(View v, MotionEvent event) {
                Object b = ba.raiseEvent(v, fsub, v.getTag(), Integer.valueOf(event.getAction()), Float.valueOf(event.getX()), Float.valueOf(event.getY()), event);
                if (b != null) {
                    return ((Boolean) b).booleanValue();
                }
                Log.w("B4A", "raiseEvent for " + fsub + " returned null");
                return true;
            }
        });
    }

    public Proxy GetProxy(BA ba, String[] interfacenames, String b4asubname) throws ClassNotFoundException {
        Class[] ifs = new Class[interfacenames.length];
        for (int i = 0; i < interfacenames.length; i++) {
            ifs[i] = Class.forName(interfacenames[i]);
        }
        return (Proxy) Proxy.newProxyInstance(ifs[0].getClassLoader(), ifs, new MyInvocationHandler(ba, b4asubname));
    }

    private class MyInvocationHandler implements InvocationHandler {
        BA ba;
        String event;

        public MyInvocationHandler(BA ba2, String b4asub) {
            this.event = b4asub.toLowerCase();
            this.ba = ba2;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method method, Object[] args) {
            if (args == null) {
                args = new Object[0];
            }
            return this.ba.raiseEvent(proxy, this.event, method.getName(), args);
        }
    }
}
