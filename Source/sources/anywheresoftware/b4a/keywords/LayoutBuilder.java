package anywheresoftware.b4a.keywords;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.ConnectorUtils;
import anywheresoftware.b4a.DynamicBuilder;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import anywheresoftware.b4a.objects.CustomViewWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.streams.File;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@BA.Hide
public class LayoutBuilder {
    private static double autoscale;
    private static HashMap<String, WeakReference<MapAndCachedStrings>> cachedLayouts = new HashMap<>();
    private static LayoutValues chosen;
    private static HashMap<String, Field> classFields;
    private static String currentClass;
    private static List<CustomViewWrapper> customViewWrappers;
    private static double screenSize = 0.0d;
    private static BA tempBA;
    private static HashMap<String, Object> viewsToSendInShellMode;

    @BA.Hide
    public interface DesignerTextSizeMethod {
        float getTextSize();

        void setTextSize(float f);
    }

    public static class LayoutValuesAndMap {
        public final LayoutValues layoutValues;
        public final LinkedHashMap<String, ViewWrapperAndAnchor> map;

        public LayoutValuesAndMap(LayoutValues layoutValues2, LinkedHashMap<String, ViewWrapperAndAnchor> map2) {
            this.layoutValues = layoutValues2;
            this.map = map2;
        }
    }

    /* access modifiers changed from: private */
    public static class MapAndCachedStrings {
        public final String[] cachedStrings;
        public final HashMap<String, Object> map;

        public MapAndCachedStrings(HashMap<String, Object> map2, String[] cachedStrings2) {
            this.map = map2;
            this.cachedStrings = cachedStrings2;
        }
    }

    public static LayoutValuesAndMap loadLayout(String file, BA ba, boolean isActivity, ViewGroup parent, LinkedHashMap<String, ViewWrapperAndAnchor> dynamicTable, boolean d4a) throws IOException {
        Exception e;
        Throwable th;
        int mainHeight;
        int mainWidth;
        HashMap<String, Object> props;
        Object obj;
        try {
            tempBA = ba;
            if (!d4a) {
                file = file.toLowerCase(BA.cul);
            }
            if (!file.endsWith(".bal")) {
                file = String.valueOf(file) + ".bal";
            }
            MapAndCachedStrings mcs = null;
            WeakReference<MapAndCachedStrings> cl = cachedLayouts.get(file);
            if (cl != null) {
                mcs = cl.get();
            }
            DataInputStream din = new DataInputStream((InputStream) File.OpenInput(File.getDirAssets(), file).getObject());
            int version = ConnectorUtils.readInt(din);
            for (int pos = ConnectorUtils.readInt(din); pos > 0; pos = (int) (((long) pos) - din.skip((long) pos))) {
            }
            String[] cache = null;
            if (version >= 3) {
                if (mcs != null) {
                    cache = mcs.cachedStrings;
                    ConnectorUtils.readInt(din);
                    for (int i = 0; i < cache.length; i++) {
                        din.skipBytes(ConnectorUtils.readInt(din));
                    }
                } else {
                    cache = new String[ConnectorUtils.readInt(din)];
                    for (int i2 = 0; i2 < cache.length; i2++) {
                        cache[i2] = ConnectorUtils.readString(din);
                    }
                }
            }
            int numberOfVariants = ConnectorUtils.readInt(din);
            chosen = null;
            LayoutValues device = Common.GetDeviceLayoutValues(ba);
            int variantIndex = 0;
            float distance = Float.MAX_VALUE;
            for (int i3 = 0; i3 < numberOfVariants; i3++) {
                LayoutValues test = LayoutValues.readFromStream(din);
                if (chosen == null) {
                    chosen = test;
                    distance = test.calcDistance(device);
                    variantIndex = i3;
                } else {
                    float testDistance = test.calcDistance(device);
                    if (testDistance < distance) {
                        chosen = test;
                        distance = testDistance;
                        variantIndex = i3;
                    }
                }
            }
            BALayout.setUserScale(chosen.Scale);
            if (isActivity || parent.getLayoutParams() == null) {
                mainWidth = ba.vg.getWidth();
                mainHeight = ba.vg.getHeight();
            } else {
                mainWidth = parent.getLayoutParams().width;
                mainHeight = parent.getLayoutParams().height;
            }
            int animationDuration = 0;
            if (dynamicTable == null) {
                LinkedHashMap<String, ViewWrapperAndAnchor> dynamicTable2 = new LayoutHashMap<>();
                if (mcs != null) {
                    try {
                        props = mcs.map;
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            throw e;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        throw new RuntimeException(e);
                    } catch (Throwable th3) {
                        th = th3;
                        tempBA = null;
                        customViewWrappers = null;
                        throw th;
                    }
                } else {
                    props = ConnectorUtils.readMap(din, cache);
                    cachedLayouts.put(file, new WeakReference<>(new MapAndCachedStrings(props, cache)));
                }
                if (ba.eventsTarget == null) {
                    obj = ba.activity;
                } else {
                    obj = ba.eventsTarget;
                }
                loadLayoutHelper(props, ba, obj, parent, isActivity, "variant" + variantIndex, true, dynamicTable2, mainWidth, mainHeight);
                if (BA.isShellModeRuntimeCheck(ba) && viewsToSendInShellMode != null) {
                    ba.raiseEvent2(null, true, "SEND_VIEWS_AFTER_LAYOUT", true, viewsToSendInShellMode);
                    viewsToSendInShellMode = null;
                }
                animationDuration = ((Integer) BA.gm(props, "animationDuration", 0)).intValue();
                dynamicTable = dynamicTable2;
            }
            din.close();
            runScripts(file, chosen, dynamicTable, mainWidth, mainHeight, Common.Density, d4a);
            BALayout.setUserScale(1.0f);
            if (customViewWrappers != null) {
                for (CustomViewWrapper cvw : customViewWrappers) {
                    cvw.AfterDesignerScript();
                }
            }
            animateLayout(dynamicTable, parent, mainWidth, mainHeight, animationDuration);
            LayoutValuesAndMap layoutValuesAndMap = new LayoutValuesAndMap(chosen, dynamicTable);
            tempBA = null;
            customViewWrappers = null;
            return layoutValuesAndMap;
        } catch (IOException e4) {
            e = e4;
            throw e;
        } catch (Exception e5) {
            e = e5;
            throw new RuntimeException(e);
        }
    }

    private static void animateLayout(LinkedHashMap<String, ViewWrapperAndAnchor> views, View parent, int parentWidth, int parentHeight, int duration) {
        if (duration > 0) {
            for (ViewWrapperAndAnchor vwa : views.values()) {
                if (vwa.parent == parent) {
                    int pl = 0;
                    int pt = 0;
                    if (vwa.hanchor == ViewWrapperAndAnchor.RIGHT) {
                        pl = parentWidth;
                    } else if (vwa.hanchor == ViewWrapperAndAnchor.BOTH) {
                        pl = parentWidth / 2;
                    }
                    if (vwa.vanchor == ViewWrapperAndAnchor.BOTTOM) {
                        pt = parentHeight;
                    } else if (vwa.vanchor == ViewWrapperAndAnchor.BOTH) {
                        pt = parentHeight / 2;
                    }
                    ViewWrapper.AnimateFrom((View) vwa.vw.getObject(), duration, pl, pt, 0, 0);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00c9, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00ca, code lost:
        r2.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00ce, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00d8, code lost:
        throw new java.lang.RuntimeException(r2.getCause());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00c9 A[ExcHandler: SecurityException (r2v1 'e' java.lang.SecurityException A[CUSTOM_DECLARE]), Splitter:B:3:0x0013] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00ce A[ExcHandler: InvocationTargetException (r2v0 'e' java.lang.reflect.InvocationTargetException A[CUSTOM_DECLARE]), Splitter:B:3:0x0013] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00db A[ExcHandler: ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter:B:3:0x0013] */
    private static void runScripts(String file, LayoutValues lv, LinkedHashMap<String, ViewWrapperAndAnchor> views, int w, int h, float s, boolean d4a) throws IllegalArgumentException, IllegalAccessException {
        Class<?> c;
        StringBuilder sb = new StringBuilder();
        sb.append("LS_");
        for (int i = 0; i < file.length() - 4; i++) {
            char c2 = file.charAt(i);
            if (Character.isLetterOrDigit(c2)) {
                sb.append(c2);
            } else {
                sb.append("_");
            }
        }
        try {
            c = Class.forName(String.valueOf(BA.packageName) + ".designerscripts." + sb.toString());
            c.getMethod(variantToMethod(null), LinkedHashMap.class, Integer.TYPE, Integer.TYPE, Float.TYPE).invoke(null, views, Integer.valueOf(w), Integer.valueOf(h), Float.valueOf(s));
        } catch (NoSuchMethodException e) {
        } catch (ClassNotFoundException e2) {
        } catch (SecurityException e3) {
        } catch (InvocationTargetException e4) {
        }
        c.getMethod(variantToMethod(lv), LinkedHashMap.class, Integer.TYPE, Integer.TYPE, Float.TYPE).invoke(null, views, Integer.valueOf(w), Integer.valueOf(h), Float.valueOf(s));
    }

    public static void setScaleRate(double rate) {
        double deviceSize = (double) (((float) (tempBA.vg.getWidth() + tempBA.vg.getHeight())) / Common.Density);
        double variantSize = (double) (((float) ((chosen.Width + chosen.Height) - 25)) / chosen.Scale);
        double deviceToLayout = deviceSize / variantSize;
        if (System.getProperty("autoscaleall_old_behaviour", "false").equals("true")) {
            autoscale = 1.0d + (rate * ((double) ((((float) (tempBA.vg.getWidth() + tempBA.vg.getHeight())) / (750.0f * Common.Density)) - 1.0f)));
        } else if (deviceToLayout <= 0.95d || deviceToLayout >= 1.05d) {
            double vscale = 1.0d + (rate * ((variantSize / 750.0d) - 1.0d));
            autoscale = (1.0d + (rate * ((deviceSize / 750.0d) - 1.0d))) / vscale;
        } else {
            autoscale = 1.0d;
        }
        screenSize = 0.0d;
    }

    public static double getScreenSize() {
        if (screenSize == 0.0d) {
            screenSize = (Math.sqrt(Math.pow((double) tempBA.vg.getWidth(), 2.0d) + Math.pow((double) tempBA.vg.getHeight(), 2.0d)) / 160.0d) / ((double) Common.Density);
        }
        return screenSize;
    }

    public static boolean isPortrait() {
        return tempBA.vg.getHeight() >= tempBA.vg.getWidth();
    }

    public static void scaleAll(LinkedHashMap<String, ViewWrapperAndAnchor> views) {
        for (ViewWrapperAndAnchor vwa : views.values()) {
            if (vwa.vw.IsInitialized() && !(vwa.vw instanceof ActivityWrapper)) {
                scaleView(vwa);
            }
        }
    }

    public static void scaleView(ViewWrapperAndAnchor vwa) {
        int newLeft;
        int newWidth;
        int newTop;
        int newHeight;
        ViewWrapper<?> v = vwa.vw;
        int left = v.getLeft();
        int width = v.getWidth();
        int height = v.getHeight();
        int top = v.getTop();
        int pw = (vwa.parent == null || vwa.parent.getLayoutParams() == null) ? vwa.pw : vwa.parent.getLayoutParams().width;
        int ph = (vwa.parent == null || vwa.parent.getLayoutParams() == null) ? vwa.ph : vwa.parent.getLayoutParams().height;
        int right = vwa.right;
        int bottom = vwa.bottom;
        if (vwa.hanchor == ViewWrapperAndAnchor.LEFT) {
            newLeft = (int) ((((double) left) * autoscale) + 0.5d);
            newWidth = ((int) ((((double) (left + width)) * autoscale) + 0.5d)) - newLeft;
        } else if (vwa.hanchor == ViewWrapperAndAnchor.RIGHT) {
            int newRight = (int) ((((double) right) * autoscale) + 0.5d);
            newWidth = ((int) ((((double) (right + width)) * autoscale) + 0.5d)) - newRight;
            newLeft = (pw - newRight) - newWidth;
        } else {
            newLeft = (int) ((((double) left) * autoscale) + 0.5d);
            newWidth = (pw - ((int) ((((double) right) * autoscale) + 0.5d))) - newLeft;
        }
        v.setLeft(newLeft);
        v.setWidth(newWidth);
        if (vwa.vanchor == ViewWrapperAndAnchor.TOP) {
            newTop = (int) ((((double) top) * autoscale) + 0.5d);
            newHeight = ((int) ((((double) (top + height)) * autoscale) + 0.5d)) - newTop;
        } else if (vwa.vanchor == ViewWrapperAndAnchor.BOTTOM) {
            int newBottom = (int) ((((double) bottom) * autoscale) + 0.5d);
            newHeight = ((int) ((((double) (bottom + height)) * autoscale) + 0.5d)) - newBottom;
            newTop = (ph - newBottom) - newHeight;
        } else {
            newTop = (int) ((((double) top) * autoscale) + 0.5d);
            newHeight = (ph - newTop) - ((int) ((((double) bottom) * autoscale) + 0.5d));
        }
        v.setTop(newTop);
        v.setHeight(newHeight);
        if (v instanceof DesignerTextSizeMethod) {
            DesignerTextSizeMethod t = (DesignerTextSizeMethod) v;
            t.setTextSize((float) (((double) t.getTextSize()) * autoscale));
        }
    }

    private static String variantToMethod(LayoutValues lv) {
        String variant;
        if (lv == null) {
            variant = "general";
        } else {
            variant = String.valueOf(String.valueOf(lv.Width)) + "x" + String.valueOf(lv.Height) + "_" + BA.NumberToString(lv.Scale).replace(".", "_");
        }
        return "LS_" + variant;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r31v1, resolved type: anywheresoftware.b4a.ObjectWrapper */
    /* JADX WARN: Multi-variable type inference failed */
    private static void loadLayoutHelper(HashMap<String, Object> props, BA ba, Object fieldsTarget, ViewGroup parent, boolean isActivity, String currentVariant, boolean firstCall, HashMap<String, ViewWrapperAndAnchor> dynamicTable, int parentWidth, int parentHeight) throws Exception {
        View o;
        ViewGroup viewGroup;
        Class<?> customClass;
        HashMap<String, Object> variant = (HashMap) props.get(currentVariant);
        if (isActivity || !firstCall) {
            ViewGroup act = isActivity ? parent : null;
            props.put("left", variant.get("left"));
            props.put("top", variant.get("top"));
            props.put("width", variant.get("width"));
            props.put("height", variant.get("height"));
            o = (View) DynamicBuilder.build(act, props, false, parent.getContext());
            if (!isActivity) {
                String name = ((String) props.get("name")).toLowerCase(BA.cul);
                String cls = (String) props.get("type");
                if (cls.startsWith(".")) {
                    cls = "anywheresoftware.b4a.objects" + cls;
                }
                ViewWrapper ow = (ViewWrapper) Class.forName(cls).newInstance();
                if (isActivity) {
                    viewGroup = null;
                } else {
                    viewGroup = parent;
                }
                ViewWrapperAndAnchor vwa = new ViewWrapperAndAnchor(ow, viewGroup);
                if (variant.containsKey("hanchor")) {
                    vwa.hanchor = ((Integer) variant.get("hanchor")).intValue();
                    vwa.vanchor = ((Integer) variant.get("vanchor")).intValue();
                }
                vwa.pw = parentWidth;
                vwa.ph = parentHeight;
                dynamicTable.put(name, vwa);
                if (classFields == null || currentClass != ba.className) {
                    classFields = new HashMap<>();
                    currentClass = ba.className;
                    Field[] declaredFields = Class.forName(ba.className).getDeclaredFields();
                    for (Field field : declaredFields) {
                        if (field.getName().startsWith("_")) {
                            classFields.put(field.getName(), field);
                        }
                    }
                }
                Field field2 = classFields.get("_" + name);
                Object obj = ow;
                if (ow instanceof CustomViewWrapper) {
                    if (customViewWrappers == null) {
                        customViewWrappers = new ArrayList();
                    }
                    customViewWrappers.add((CustomViewWrapper) ow);
                    String cclass = (String) props.get("customType");
                    if (cclass == null || cclass.length() == 0) {
                        throw new RuntimeException("CustomView CustomType property was not set.");
                    }
                    try {
                        customClass = Class.forName(cclass);
                    } catch (ClassNotFoundException cnfe) {
                        int dollar = cclass.lastIndexOf(".");
                        if (dollar > -1) {
                            String corrected = String.valueOf(BA.packageName) + cclass.substring(dollar);
                            BA.LogInfo("Class not found: " + cclass + ", trying: " + corrected);
                            customClass = Class.forName(corrected);
                        } else {
                            throw cnfe;
                        }
                    }
                    Object customObject = customClass.newInstance();
                    CustomViewWrapper cvw = (CustomViewWrapper) ow;
                    cvw.customObject = customObject;
                    cvw.props = new HashMap<>(props);
                    obj = customObject;
                } else if (!(field2 == null || field2.getType() == obj.getClass())) {
                    if (BA.debugMode) {
                        Type t = ow.getClass().getGenericSuperclass();
                        if (t instanceof ParameterizedType) {
                            ParameterizedType pt = (ParameterizedType) t;
                            if (pt.getActualTypeArguments().length > 0 && !((Class) ((ParameterizedType) field2.getType().getGenericSuperclass()).getActualTypeArguments()[0]).isAssignableFrom((Class) pt.getActualTypeArguments()[0])) {
                                throw new RuntimeException("Cannot convert: " + ow.getClass() + ", to: " + field2.getType());
                            }
                        }
                    }
                    ObjectWrapper nw = (ObjectWrapper) field2.getType().newInstance();
                    nw.setObject(o);
                    obj = nw;
                }
                if (BA.isShellModeRuntimeCheck(ba)) {
                    if (viewsToSendInShellMode == null) {
                        viewsToSendInShellMode = new HashMap<>();
                    }
                    viewsToSendInShellMode.put(name, obj);
                }
                if (field2 != null) {
                    try {
                        field2.set(fieldsTarget, obj);
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException("Field " + name + " was declared with the wrong type.");
                    }
                }
                ow.setObject(o);
                ow.innerInitialize(ba, ((String) props.get("eventName")).toLowerCase(BA.cul), true);
                parent.addView(o, o.getLayoutParams());
                if (!(vwa.hanchor == 0 && vwa.vanchor == 0)) {
                    ViewWrapper.fixAnchor(parentWidth, parentHeight, vwa);
                }
            }
        } else {
            o = parent;
            parent.setBackgroundDrawable((Drawable) DynamicBuilder.build(parent, (HashMap) props.get("drawable"), false, null));
        }
        HashMap<String, Object> kids = (HashMap) props.get(":kids");
        if (kids != null) {
            int pw = o.getLayoutParams() == null ? 0 : o.getLayoutParams().width;
            int ph = o.getLayoutParams() == null ? 0 : o.getLayoutParams().height;
            for (int i = 0; i < kids.size(); i++) {
                loadLayoutHelper((HashMap) kids.get(String.valueOf(i)), ba, fieldsTarget, (ViewGroup) o, false, currentVariant, false, dynamicTable, pw, ph);
            }
        }
    }

    @BA.Hide
    public static class LayoutHashMap<K, V> extends LinkedHashMap<K, V> {
        @Override // java.util.LinkedHashMap, java.util.AbstractMap, java.util.Map, java.util.HashMap
        public V get(Object key) {
            V v = (V) super.get(key);
            if (v != null) {
                return v;
            }
            throw new RuntimeException("Cannot find view: " + key.toString() + "\nAll views in script should be declared.");
        }
    }

    @BA.Hide
    public static class ViewWrapperAndAnchor {
        public static int BOTH = 2;
        public static int BOTTOM = 1;
        public static int LEFT = 0;
        public static int RIGHT = 1;
        public static int TOP = 0;
        public int bottom;
        public int hanchor;
        public final View parent;
        public int ph;
        public int pw;
        public int right;
        public int vanchor;
        public final ViewWrapper<?> vw;

        public ViewWrapperAndAnchor(ViewWrapper<?> vw2, View parent2) {
            this.vw = vw2;
            this.parent = parent2;
        }
    }
}
