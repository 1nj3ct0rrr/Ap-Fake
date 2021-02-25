package anywheresoftware.b4a.keywords;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;
import android.widget.Toast;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.Msgbox;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.keywords.constants.Colors;
import anywheresoftware.b4a.keywords.constants.DialogResponse;
import anywheresoftware.b4a.keywords.constants.Gravity;
import anywheresoftware.b4a.keywords.constants.KeyCodes;
import anywheresoftware.b4a.keywords.constants.TypefaceWrapper;
import anywheresoftware.b4a.objects.B4AException;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.collections.List;
import anywheresoftware.b4a.objects.collections.Map;
import anywheresoftware.b4a.objects.drawable.BitmapDrawable;
import anywheresoftware.b4a.objects.drawable.CanvasWrapper;
import anywheresoftware.b4a.objects.streams.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

@BA.Version(7.28f)
@BA.ActivityObject
public class Common {
    public static final B4AApplication Application = null;
    public static final Bit Bit = null;
    public static final String CRLF = "\n";
    public static final Colors Colors = null;
    public static final DateTime DateTime = null;
    public static final float Density = BA.density;
    public static final DialogResponse DialogResponse = null;
    public static final boolean False = false;
    public static final File File = null;
    public static final Gravity Gravity = null;
    public static KeyCodes KeyCodes = null;
    public static final Object Null = null;
    public static final String QUOTE = "\"";
    public static final Regex Regex = null;
    public static final String TAB = "\t";
    public static final boolean True = true;
    public static final TypefaceWrapper Typeface = null;
    public static final double cE = 2.718281828459045d;
    public static final double cPI = 3.141592653589793d;
    private static Random random;

    @BA.Hide
    public interface DesignerCustomView {
        void DesignerCreateView(PanelWrapper panelWrapper, LabelWrapper labelWrapper, Map map);

        void _initialize(BA ba, Object obj, String str);
    }

    public static String NumberFormat(double Number, int MinimumIntegers, int MaximumFractions) {
        if (BA.numberFormat == null) {
            BA.numberFormat = NumberFormat.getInstance(Locale.US);
        }
        BA.numberFormat.setMaximumFractionDigits(MaximumFractions);
        BA.numberFormat.setMinimumIntegerDigits(MinimumIntegers);
        return BA.numberFormat.format(Number);
    }

    public static String NumberFormat2(double Number, int MinimumIntegers, int MaximumFractions, int MinimumFractions, boolean GroupingUsed) {
        if (BA.numberFormat == null) {
            BA.numberFormat = NumberFormat.getInstance(Locale.US);
        }
        BA.numberFormat.setMaximumFractionDigits(MaximumFractions);
        BA.numberFormat.setMinimumIntegerDigits(MinimumIntegers);
        BA.numberFormat.setMinimumFractionDigits(MinimumFractions);
        BA.numberFormat.setGroupingUsed(GroupingUsed);
        return BA.numberFormat.format(Number);
    }

    public static void Log(String Message) {
        BA.Log(Message);
    }

    public static void LogColor(String Message, int Color) {
        BA.addLogPrefix("c" + Color, Message);
    }

    public static Object Sender(BA ba) {
        return ba.getSender();
    }

    public static boolean Not(boolean Value) {
        return !Value;
    }

    public static void RndSeed(long Seed) {
        if (random == null) {
            random = new Random(Seed);
        } else {
            random.setSeed(Seed);
        }
    }

    public static int Rnd(int Min, int Max) {
        if (random == null) {
            random = new Random();
        }
        return random.nextInt(Max - Min) + Min;
    }

    public static double Abs(double Number) {
        return Math.abs(Number);
    }

    @BA.Hide
    public static int Abs(int Number) {
        return Math.abs(Number);
    }

    public static double Max(double Number1, double Number2) {
        return Math.max(Number1, Number2);
    }

    @BA.Hide
    public static double Max(int Number1, int Number2) {
        return (double) Math.max(Number1, Number2);
    }

    public static double Min(double Number1, double Number2) {
        return Math.min(Number1, Number2);
    }

    @BA.Hide
    public static double Min(int Number1, int Number2) {
        return (double) Math.min(Number1, Number2);
    }

    public static double Sin(double Radians) {
        return Math.sin(Radians);
    }

    public static double SinD(double Degrees) {
        return Math.sin((Degrees / 180.0d) * 3.141592653589793d);
    }

    public static double Cos(double Radians) {
        return Math.cos(Radians);
    }

    public static double CosD(double Degrees) {
        return Math.cos((Degrees / 180.0d) * 3.141592653589793d);
    }

    public static double Tan(double Radians) {
        return Math.tan(Radians);
    }

    public static double TanD(double Degrees) {
        return Math.tan((Degrees / 180.0d) * 3.141592653589793d);
    }

    public static double Power(double Base, double Exponent) {
        return Math.pow(Base, Exponent);
    }

    public static double Sqrt(double Value) {
        return Math.sqrt(Value);
    }

    public static double ASin(double Value) {
        return Math.asin(Value);
    }

    public static double ASinD(double Value) {
        return (Math.asin(Value) / 3.141592653589793d) * 180.0d;
    }

    public static double ACos(double Value) {
        return Math.acos(Value);
    }

    public static double ACosD(double Value) {
        return (Math.acos(Value) / 3.141592653589793d) * 180.0d;
    }

    public static double ATan(double Value) {
        return Math.atan(Value);
    }

    public static double ATanD(double Value) {
        return (Math.atan(Value) / 3.141592653589793d) * 180.0d;
    }

    public static double ATan2(double Y, double X) {
        return Math.atan2(Y, X);
    }

    public static double ATan2D(double Y, double X) {
        return (Math.atan2(Y, X) / 3.141592653589793d) * 180.0d;
    }

    public static double Logarithm(double Number, double Base) {
        return Math.log(Number) / Math.log(Base);
    }

    public static long Round(double Number) {
        return Math.round(Number);
    }

    public static double Round2(double Number, int DecimalPlaces) {
        double shift = Math.pow(10.0d, (double) DecimalPlaces);
        return ((double) Math.round(Number * shift)) / shift;
    }

    public static double Floor(double Number) {
        return Math.floor(Number);
    }

    public static double Ceil(double Number) {
        return Math.ceil(Number);
    }

    public static int Asc(char Char) {
        return Char;
    }

    public static char Chr(int UnicodeValue) {
        return (char) UnicodeValue;
    }

    @BA.RaisesSynchronousEvents
    public static void DoEvents() {
        Msgbox.sendCloseMyLoopMessage();
        Msgbox.waitForMessage(false, true);
    }

    public static void ToastMessageShow(CharSequence Message, boolean LongDuration) {
        Toast.makeText(BA.applicationContext, Message, LongDuration ? 1 : 0).show();
    }

    @BA.RaisesSynchronousEvents
    public static void Msgbox(CharSequence Message, CharSequence Title, BA ba) {
        Msgbox2(Message, Title, "OK", "", "", null, ba);
    }

    @BA.RaisesSynchronousEvents
    public static int Msgbox2(CharSequence Message, CharSequence Title, String Positive, String Cancel, String Negative, Bitmap Icon, BA ba) {
        Msgbox.DialogResponse dr = new Msgbox.DialogResponse(false);
        Msgbox.msgbox(createMsgboxAlertDialog(Message, Title, Positive, Cancel, Negative, Icon, ba, dr), false);
        return dr.res;
    }

    public static void MsgboxAsync(CharSequence Message, CharSequence Title, BA mine) {
        Msgbox2Async(Message, Title, "OK", "", "", null, mine, true);
    }

    public static Object Msgbox2Async(CharSequence Message, CharSequence Title, String Positive, String Cancel, String Negative, CanvasWrapper.BitmapWrapper Icon, final BA mine, boolean Cancelable) {
        AlertDialog ad = createMsgboxAlertDialog(Message, Title, Positive, Cancel, Negative, Icon == null ? null : (Bitmap) Icon.getObjectOrNull(), mine.sharedProcessBA.activityBA.get(), new DialogInterface.OnClickListener() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass1 */

            public void onClick(DialogInterface dialog, int which) {
                BA.this.raiseEvent(dialog, "msgbox_result", Integer.valueOf(which));
            }
        });
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass2 */

            public void onCancel(DialogInterface dialog) {
                BA.this.raiseEvent(dialog, "msgbox_result", -3);
            }
        });
        return showAndTrackDialog(ad, Cancelable);
    }

    private static AlertDialog createMsgboxAlertDialog(CharSequence Message, CharSequence Title, String Positive, String Cancel, String Negative, Bitmap Icon, BA ba, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder b = new AlertDialog.Builder(ba.context);
        b.setTitle(Title).setMessage(Message);
        if (Positive.length() > 0) {
            b.setPositiveButton(Positive, listener);
        }
        if (Negative.length() > 0) {
            b.setNegativeButton(Negative, listener);
        }
        if (Cancel.length() > 0) {
            b.setNeutralButton(Cancel, listener);
        }
        if (Icon != null) {
            BitmapDrawable bd = new BitmapDrawable();
            bd.Initialize(Icon);
            b.setIcon((Drawable) bd.getObject());
        }
        return b.create();
    }

    @BA.RaisesSynchronousEvents
    public static int InputList(List Items, CharSequence Title, int CheckedItem, BA ba) {
        Msgbox.DialogResponse dr = new Msgbox.DialogResponse(true);
        Msgbox.msgbox(createInputList(Items, Title, CheckedItem, ba, dr), false);
        return dr.res;
    }

    public static Object InputListAsync(List Items, CharSequence Title, int CheckedItem, final BA mine, boolean Cancelable) {
        AlertDialog ad = createInputList(Items, Title, CheckedItem, mine.sharedProcessBA.activityBA.get(), new DialogInterface.OnClickListener() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass3 */

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                BA.this.raiseEvent(dialog, "inputlist_result", Integer.valueOf(which));
            }
        });
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass4 */

            public void onCancel(DialogInterface dialog) {
                BA.this.raiseEvent(dialog, "inputlist_result", -3);
            }
        });
        return showAndTrackDialog(ad, Cancelable);
    }

    private static AlertDialog createInputList(List Items, CharSequence Title, int CheckedItem, BA ba, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder b = new AlertDialog.Builder(ba.context);
        CharSequence[] items = new CharSequence[Items.getSize()];
        for (int i = 0; i < Items.getSize(); i++) {
            Object o = Items.Get(i);
            if (o instanceof CharSequence) {
                items[i] = (CharSequence) o;
            } else {
                items[i] = String.valueOf(o);
            }
        }
        b.setSingleChoiceItems(items, CheckedItem, listener);
        b.setTitle(Title);
        return b.create();
    }

    @BA.Hide
    public static Dialog showAndTrackDialog(Dialog ad, boolean Cancelable) {
        ad.setCancelable(Cancelable);
        ad.setCanceledOnTouchOutside(Cancelable);
        ad.show();
        Msgbox.trackAsyncDialog(ad);
        return ad;
    }

    @BA.RaisesSynchronousEvents
    public static void InputMap(Map Items, CharSequence Title, BA ba) {
        Msgbox.msgbox(createInputMap(Items, Title, ba, new Msgbox.DialogResponse(false)), false);
    }

    public static Object InputMapAsync(Map Items, CharSequence Title, final BA mine, boolean Cancelable) {
        AlertDialog ad = createInputMap(Items, Title, mine.sharedProcessBA.activityBA.get(), new DialogInterface.OnClickListener() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass5 */

            public void onClick(DialogInterface dialog, int which) {
                BA.this.raiseEvent(dialog, "inputmap_result", new Object[0]);
            }
        });
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass6 */

            public void onCancel(DialogInterface dialog) {
                BA.this.raiseEvent(dialog, "inputmap_result", new Object[0]);
            }
        });
        return showAndTrackDialog(ad, Cancelable);
    }

    private static AlertDialog createInputMap(final Map Items, CharSequence Title, BA ba, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder b = new AlertDialog.Builder(ba.context);
        final CharSequence[] items = new CharSequence[Items.getSize()];
        boolean[] checked = new boolean[Items.getSize()];
        int i = 0;
        for (Map.Entry<Object, Object> e : ((Map.MyMap) Items.getObject()).entrySet()) {
            if (!(e.getKey() instanceof String)) {
                throw new RuntimeException("Keys must be strings.");
            }
            items[i] = (String) e.getKey();
            Object o = e.getValue();
            if (o instanceof Boolean) {
                checked[i] = ((Boolean) o).booleanValue();
            } else {
                checked[i] = Boolean.parseBoolean(String.valueOf(o));
            }
            i++;
        }
        b.setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass7 */

            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    anywheresoftware.b4a.objects.collections.Map.this.Put(items[which], true);
                } else {
                    anywheresoftware.b4a.objects.collections.Map.this.Put(items[which], false);
                }
            }
        });
        b.setTitle(Title);
        b.setPositiveButton("Ok", listener);
        return b.create();
    }

    @BA.RaisesSynchronousEvents
    public static List InputMultiList(List Items, CharSequence Title, BA ba) {
        AlertDialog.Builder b = new AlertDialog.Builder(ba.context);
        CharSequence[] items = new CharSequence[Items.getSize()];
        for (int i = 0; i < Items.getSize(); i++) {
            Object o = Items.Get(i);
            if (o instanceof CharSequence) {
                items[i] = (CharSequence) o;
            } else {
                items[i] = String.valueOf(o);
            }
        }
        Msgbox.DialogResponse dr = new Msgbox.DialogResponse(false);
        final List result = new List();
        result.Initialize();
        b.setMultiChoiceItems(items, (boolean[]) null, new DialogInterface.OnMultiChoiceClickListener() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass8 */

            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    List.this.Add(Integer.valueOf(which));
                    return;
                }
                List.this.RemoveAt(List.this.IndexOf(Integer.valueOf(which)));
            }
        });
        b.setTitle(Title);
        b.setPositiveButton("Ok", dr);
        Msgbox.msgbox(b.create(), false);
        if (dr.res != -1) {
            result.Clear();
        } else {
            result.Sort(true);
        }
        return result;
    }

    public static void ProgressDialogShow(BA ba, CharSequence Text) {
        ProgressDialogShow2(ba, Text, true);
    }

    public static void ProgressDialogShow2(BA ba, CharSequence Text, boolean Cancelable) {
        ProgressDialogHide();
        Msgbox.pd = new WeakReference<>(ProgressDialog.show(ba.context, "", Text, true, Cancelable));
    }

    public static void ProgressDialogHide() {
        Msgbox.dismissProgressDialog();
    }

    public static String GetType(Object object) {
        return object.getClass().getName();
    }

    public static boolean IsDevTool(String ToolName) {
        return ToolName.toLowerCase(BA.cul).equals("b4a");
    }

    public static int DipToCurrent(int Length) {
        return (int) (Density * ((float) Length));
    }

    public static int PerXToCurrent(float Percentage, BA ba) {
        return (int) ((Percentage / 100.0f) * ((float) ba.vg.getWidth()));
    }

    public static int PerYToCurrent(float Percentage, BA ba) {
        return (int) ((Percentage / 100.0f) * ((float) ba.vg.getHeight()));
    }

    public static boolean IsNumber(String Text) {
        try {
            Double.parseDouble(Text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static B4AException LastException(BA ba) {
        B4AException e = new B4AException();
        e.setObject(ba.getLastException());
        return e;
    }

    public static LayoutValues GetDeviceLayoutValues(BA ba) {
        DisplayMetrics dm = BA.applicationContext.getResources().getDisplayMetrics();
        LayoutValues deviceValues = new LayoutValues();
        deviceValues.Scale = dm.density;
        deviceValues.Width = dm.widthPixels;
        deviceValues.Height = dm.heightPixels;
        return deviceValues;
    }

    public static void StartActivity(BA mine, Object Activity) throws ClassNotFoundException {
        Intent i = getComponentIntent(mine, Activity);
        BA activityBA = null;
        if (mine.sharedProcessBA.activityBA != null) {
            activityBA = mine.sharedProcessBA.activityBA.get();
        }
        if (activityBA != null) {
            i.addFlags(131072);
            activityBA.context.startActivity(i);
            return;
        }
        i.addFlags(268435456);
        mine.context.startActivity(i);
    }

    public static void StartService(final BA mine, final Object Service) throws ClassNotFoundException {
        if (BA.shellMode) {
            BA.handler.post(new BA.B4ARunnable() {
                /* class anywheresoftware.b4a.keywords.Common.AnonymousClass9 */

                public void run() {
                    try {
                        BA.this.context.startService(Common.getComponentIntent(BA.this, Service));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return;
        }
        BA.handler.post(new Runnable() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass10 */

            public void run() {
                Msgbox.isDismissing = false;
            }
        });
        mine.context.startService(getComponentIntent(mine, Service));
        Msgbox.isDismissing = true;
    }

    public static void StartServiceAt(BA mine, Object Service, long Time, boolean DuringSleep) throws ClassNotFoundException {
        int i = 1;
        AlarmManager am = (AlarmManager) BA.applicationContext.getSystemService("alarm");
        PendingIntent pi = PendingIntent.getService(mine.context, 1, getComponentIntent(mine, Service), 134217728);
        if (DuringSleep) {
            i = 0;
        }
        am.set(i, Time, pi);
    }

    public static void StartServiceAtExact(BA mine, Object Service, long Time, boolean DuringSleep) throws Exception {
        if (Build.VERSION.SDK_INT < 19) {
            StartServiceAt(mine, Service, Time, DuringSleep);
            return;
        }
        AlarmManager am = (AlarmManager) BA.applicationContext.getSystemService("alarm");
        PendingIntent pi = PendingIntent.getService(mine.context, 1, getComponentIntent(mine, Service), 134217728);
        Method m = am.getClass().getMethod("setExact", Integer.TYPE, Long.TYPE, PendingIntent.class);
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(DuringSleep ? 0 : 1);
        objArr[1] = Long.valueOf(Time);
        objArr[2] = pi;
        m.invoke(am, objArr);
    }

    public static void CancelScheduledService(BA mine, Object Service) throws ClassNotFoundException {
        ((AlarmManager) BA.applicationContext.getSystemService("alarm")).cancel(PendingIntent.getService(mine.context, 1, getComponentIntent(mine, Service), 134217728));
    }

    @BA.Hide
    public static Intent getComponentIntent(BA mine, Object component) throws ClassNotFoundException {
        if (component instanceof Class) {
            return new Intent(mine.context, (Class) component);
        }
        if (component == null || component.toString().length() == 0) {
            return new Intent(mine.context, Class.forName(mine.className));
        }
        if (component instanceof String) {
            return new Intent(mine.context, Class.forName(String.valueOf(BA.packageName) + "." + ((String) component).toLowerCase(BA.cul)));
        }
        return (Intent) component;
    }

    public static void StopService(BA mine, Object Service) throws ClassNotFoundException {
        mine.context.stopService(getComponentIntent(mine, Service));
    }

    public static boolean SubExists(BA mine, Object Object, String Sub) throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        BA ba;
        if (Object == null || (ba = getComponentBA(mine, Object)) == null) {
            return false;
        }
        return ba.subExists(Sub.toLowerCase(BA.cul));
    }

    @BA.RaisesSynchronousEvents
    @BA.DesignerName("CallSub")
    public static Object CallSubNew(BA mine, Object Component, String Sub) {
        return CallSub4(false, mine, Component, Sub, null);
    }

    @BA.RaisesSynchronousEvents
    @BA.DesignerName("CallSub2")
    public static Object CallSubNew2(BA mine, Object Component, String Sub, Object Argument) {
        return CallSub4(false, mine, Component, Sub, new Object[]{Argument});
    }

    @BA.RaisesSynchronousEvents
    @BA.DesignerName("CallSub3")
    public static Object CallSubNew3(BA mine, Object Component, String Sub, Object Argument1, Object Argument2) {
        return CallSub4(false, mine, Component, Sub, new Object[]{Argument1, Argument2});
    }

    @BA.Hide
    public static Object CallSubDebug(BA mine, Object Component, String Sub) throws Exception {
        return Class.forName("anywheresoftware.b4a.debug.Debug").getDeclaredMethod("CallSubNew", BA.class, Object.class, String.class).invoke(null, mine, Component, Sub);
    }

    @BA.Hide
    public static Object CallSubDebug2(BA mine, Object Component, String Sub, Object Argument) throws Exception {
        return Class.forName("anywheresoftware.b4a.debug.Debug").getDeclaredMethod("CallSubNew2", BA.class, Object.class, String.class, Object.class).invoke(null, mine, Component, Sub, Argument);
    }

    @BA.Hide
    public static Object CallSubDebug3(BA mine, Object Component, String Sub, Object Argument1, Object Argument2) throws Exception {
        return Class.forName("anywheresoftware.b4a.debug.Debug").getDeclaredMethod("CallSubNew3", BA.class, Object.class, String.class, Object.class, Object.class).invoke(null, mine, Component, Sub, Argument1, Argument2);
    }

    @BA.Hide
    public static String CallSub(BA mine, Object Component, String Sub) {
        return (String) CallSub4(true, mine, Component, Sub, null);
    }

    @BA.Hide
    public static String CallSub2(BA mine, Object Component, String Sub, Object Argument) {
        return (String) CallSub4(true, mine, Component, Sub, new Object[]{Argument});
    }

    @BA.Hide
    public static String CallSub3(BA mine, Object Component, String Sub, Object Argument1, Object Argument2) {
        return (String) CallSub4(true, mine, Component, Sub, new Object[]{Argument1, Argument2});
    }

    private static Object CallSub4(boolean old, BA mine, Object Component, String Sub, Object[] Arguments) {
        Object o = null;
        try {
            if (Component instanceof BA.SubDelegator) {
                Object o2 = ((BA.SubDelegator) Component).callSub(Sub, mine.eventsTarget, Arguments);
                if (o2 == BA.SubDelegator.SubNotFound) {
                    o = null;
                } else if (o2 == null || !(o2 instanceof ObjectWrapper)) {
                    return o2;
                } else {
                    return ((ObjectWrapper) o2).getObject();
                }
            }
            BA ba = getComponentBA(mine, Component);
            if (ba != null) {
                boolean isTargetClass = Component instanceof B4AClass;
                o = ba.raiseEvent2(mine.eventsTarget, isTargetClass, Sub.toLowerCase(BA.cul), isTargetClass, Arguments);
            }
            if (old) {
                if (o == null) {
                    o = "";
                }
                return String.valueOf(o);
            } else if (o == null || !(o instanceof ObjectWrapper)) {
                return o;
            } else {
                return ((ObjectWrapper) o).getObject();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void CallSubDelayed(BA mine, Object Component, String Sub) {
        CallSubDelayed4(mine, Component, Sub, null);
    }

    public static void CallSubDelayed2(BA mine, Object Component, String Sub, Object Argument) {
        CallSubDelayed4(mine, Component, Sub, new Object[]{Argument});
    }

    public static void CallSubDelayed3(BA mine, Object Component, String Sub, Object Argument1, Object Argument2) {
        CallSubDelayed4(mine, Component, Sub, new Object[]{Argument1, Argument2});
    }

    private static void CallSubDelayed4(final BA mine, final Object Component, final String Sub, final Object[] Arguments) {
        final Runnable runnable = new Runnable() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass11 */
            int retries = 5;

            public void run() {
                try {
                    final BA ba = Common.getComponentBA(BA.this, Component);
                    final Object sender = BA.this.eventsTarget;
                    if (ba == null || ba.isActivityPaused()) {
                        if (Component instanceof B4AClass) {
                            Common.Log("Object context is paused. Ignoring CallSubDelayed: " + Sub);
                            return;
                        }
                        ComponentName cn = Common.getComponentIntent(BA.this, Component).getComponent();
                        if (cn == null) {
                            Common.Log("ComponentName = null");
                            return;
                        }
                        Class<?> cls = Class.forName(cn.getClassName());
                        Field f = cls.getDeclaredField("mostCurrent");
                        f.setAccessible(true);
                        if (f.get(null) == null && this.retries == 5) {
                            if (Activity.class.isAssignableFrom(cls)) {
                                if (((Boolean) Class.forName(String.valueOf(BA.packageName) + ".main").getMethod("isAnyActivityVisible", null).invoke(null, null)).booleanValue()) {
                                    Common.StartActivity(BA.this, Component);
                                } else {
                                    this.retries = 0;
                                }
                            } else if (Service.class.isAssignableFrom(cls)) {
                                Common.StartService(BA.this, Component);
                            }
                        }
                        int i = this.retries - 1;
                        this.retries = i;
                        if (i > 0) {
                            BA.handler.postDelayed(this, 100);
                        } else if (ba != null) {
                            final String str = Sub;
                            final Object[] objArr = Arguments;
                            ba.addMessageToPausedMessageQueue("CallSubDelayed - " + Sub, new Runnable() {
                                /* class anywheresoftware.b4a.keywords.Common.AnonymousClass11.AnonymousClass1 */

                                public void run() {
                                    ba.raiseEvent2(sender, true, str.toLowerCase(BA.cul), true, objArr);
                                }
                            });
                        } else {
                            BA.addMessageToUninitializeActivity(cn.getClassName(), Sub.toLowerCase(BA.cul), sender, Arguments);
                        }
                    } else if (BA.shellMode) {
                        ba.raiseEventFromDifferentThread(sender, null, 0, Sub.toLowerCase(BA.cul), true, Arguments);
                    } else {
                        ba.raiseEvent2(sender, true, Sub.toLowerCase(BA.cul), true, Arguments);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        if (!BA.shellMode) {
            BA.handler.post(runnable);
        } else {
            BA.handler.post(new BA.B4ARunnable() {
                /* class anywheresoftware.b4a.keywords.Common.AnonymousClass12 */

                public void run() {
                    runnable.run();
                }
            });
        }
    }

    public static boolean IsPaused(BA mine, Object Component) throws ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
        BA ba = getComponentBA(mine, Component);
        return ba == null || ba.isActivityPaused();
    }

    @BA.Hide
    public static BA getComponentBA(BA mine, Object Component) throws ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
        Class<?> c;
        if (Component instanceof Class) {
            c = (Class) Component;
        } else if (Component instanceof B4AClass) {
            return ((B4AClass) Component).getBA();
        } else {
            if (Component == null || Component.toString().length() == 0) {
                return mine;
            }
            c = Class.forName(String.valueOf(BA.packageName) + "." + ((String) Component).toLowerCase(BA.cul));
        }
        return (BA) c.getField("processBA").get(null);
    }

    public static String CharsToString(char[] Chars, int StartOffset, int Length) {
        return new String(Chars, StartOffset, Length);
    }

    public static String BytesToString(byte[] Data, int StartOffset, int Length, String CharSet) throws UnsupportedEncodingException {
        return new String(Data, StartOffset, Length, CharSet);
    }

    @BA.Hide
    public static anywheresoftware.b4a.objects.collections.Map createMap(Object[] data) {
        anywheresoftware.b4a.objects.collections.Map m = new anywheresoftware.b4a.objects.collections.Map();
        m.Initialize();
        for (int i = 0; i < data.length; i += 2) {
            m.Put(data[i], data[i + 1]);
        }
        return m;
    }

    @BA.Hide
    public static List ArrayToList(Object[] Array) {
        List list = new List();
        list.setObject(Arrays.asList(Array));
        return list;
    }

    @BA.Hide
    public static List ArrayToList(int[] Array) {
        List list = new List();
        Object[] o = new Object[Array.length];
        for (int i = 0; i < Array.length; i++) {
            o[i] = Integer.valueOf(Array[i]);
        }
        list.setObject(Arrays.asList(o));
        return list;
    }

    @BA.Hide
    public static List ArrayToList(long[] Array) {
        List list = new List();
        Object[] o = new Object[Array.length];
        for (int i = 0; i < Array.length; i++) {
            o[i] = Long.valueOf(Array[i]);
        }
        list.setObject(Arrays.asList(o));
        return list;
    }

    @BA.Hide
    public static List ArrayToList(float[] Array) {
        List list = new List();
        Object[] o = new Object[Array.length];
        for (int i = 0; i < Array.length; i++) {
            o[i] = Float.valueOf(Array[i]);
        }
        list.setObject(Arrays.asList(o));
        return list;
    }

    @BA.Hide
    public static List ArrayToList(double[] Array) {
        List list = new List();
        Object[] o = new Object[Array.length];
        for (int i = 0; i < Array.length; i++) {
            o[i] = Double.valueOf(Array[i]);
        }
        list.setObject(Arrays.asList(o));
        return list;
    }

    @BA.Hide
    public static List ArrayToList(boolean[] Array) {
        List list = new List();
        Object[] o = new Object[Array.length];
        for (int i = 0; i < Array.length; i++) {
            o[i] = Boolean.valueOf(Array[i]);
        }
        list.setObject(Arrays.asList(o));
        return list;
    }

    @BA.Hide
    public static List ArrayToList(short[] Array) {
        List list = new List();
        Object[] o = new Object[Array.length];
        for (int i = 0; i < Array.length; i++) {
            o[i] = Short.valueOf(Array[i]);
        }
        list.setObject(Arrays.asList(o));
        return list;
    }

    @BA.Hide
    public static List ArrayToList(byte[] Array) {
        List list = new List();
        Object[] o = new Object[Array.length];
        for (int i = 0; i < Array.length; i++) {
            o[i] = Byte.valueOf(Array[i]);
        }
        list.setObject(Arrays.asList(o));
        return list;
    }

    public static boolean IsBackgroundTaskRunning(BA ba, Object ContainerObject, int TaskId) {
        return BA.isTaskRunning(ContainerObject, TaskId);
    }

    public static CanvasWrapper.BitmapWrapper LoadBitmap(String Dir, String FileName) throws IOException {
        CanvasWrapper.BitmapWrapper bw = new CanvasWrapper.BitmapWrapper();
        bw.Initialize(Dir, FileName);
        return bw;
    }

    public static CanvasWrapper.BitmapWrapper LoadBitmapSample(String Dir, String FileName, @BA.Pixel int MaxWidth, @BA.Pixel int MaxHeight) throws IOException {
        CanvasWrapper.BitmapWrapper bw = new CanvasWrapper.BitmapWrapper();
        bw.InitializeSample(Dir, FileName, MaxWidth, MaxHeight);
        return bw;
    }

    public static CanvasWrapper.BitmapWrapper LoadBitmapResize(String Dir, String FileName, @BA.Pixel int Width, @BA.Pixel int Height, boolean KeepAspectRatio) throws IOException {
        CanvasWrapper.BitmapWrapper bw = new CanvasWrapper.BitmapWrapper();
        bw.InitializeResize(Dir, FileName, Width, Height, KeepAspectRatio);
        return bw;
    }

    public static String SmartStringFormatter(String Format, Object Value) {
        int minInts;
        int maxFracs;
        if (Format.length() == 0) {
            return BA.ObjectToString(Value);
        }
        if (Format.equals("date")) {
            return DateTime.Date(BA.ObjectToLongNumber(Value));
        }
        if (Format.equals("datetime")) {
            long l = BA.ObjectToLongNumber(Value);
            return String.valueOf(DateTime.Date(l)) + " " + DateTime.Time(l);
        } else if (Format.equals("time")) {
            return DateTime.Time(BA.ObjectToLongNumber(Value));
        } else {
            if (Format.equals("xml")) {
                StringBuilder sb = new StringBuilder();
                String s = String.valueOf(Value);
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    switch (c) {
                        case KeyCodes.KEYCODE_F:
                            sb.append("&quot;");
                            break;
                        case KeyCodes.KEYCODE_J:
                            sb.append("&amp;");
                            break;
                        case KeyCodes.KEYCODE_K:
                            sb.append("&#39;");
                            break;
                        case KeyCodes.KEYCODE_SHIFT_RIGHT:
                            sb.append("&lt;");
                            break;
                        case KeyCodes.KEYCODE_SPACE:
                            sb.append("&gt;");
                            break;
                        default:
                            sb.append(c);
                            break;
                    }
                }
                return sb.toString();
            }
            int i2 = Format.indexOf(".");
            if (i2 > -1) {
                minInts = Integer.parseInt(Format.substring(0, i2));
                maxFracs = Integer.parseInt(Format.substring(i2 + 1));
            } else {
                minInts = Integer.parseInt(Format);
                maxFracs = Integer.MAX_VALUE;
            }
            try {
                return NumberFormat(BA.ObjectToNumber(Value), minInts, maxFracs);
            } catch (Exception e) {
                return "NaN";
            }
        }
    }

    public static void Array() {
    }

    public static void CreateMap() {
    }

    public static void If() {
    }

    public static void Try() {
    }

    public static void Catch() {
    }

    public static void Dim() {
    }

    public static void While() {
    }

    public static void Until() {
    }

    public static void For() {
    }

    public static void Type() {
    }

    public static void Return() {
    }

    public static void Sub() {
    }

    public static void Exit() {
    }

    public static void Continue() {
    }

    public static void Select() {
    }

    public static void Is() {
    }

    public static void ExitApplication() {
        System.exit(0);
    }

    public static RemoteViews ConfigureHomeWidget(String LayoutFile, String EventName, int UpdateIntervalMinutes, String WidgetName, boolean CenterWidget) {
        return null;
    }

    public static Object Me(BA ba) {
        return null;
    }

    public static void Sleep(int Milliseconds) {
    }

    @BA.Hide
    public static void Sleep(final BA ba, final BA.ResumableSub rs, int Milliseconds) {
        BA.handler.postDelayed(new Runnable() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass13 */

            public void run() {
                boolean isActivity = BA.this.processBA != null;
                if (isActivity && (BA.this.processBA.sharedProcessBA.activityBA == null || BA.this != BA.this.processBA.sharedProcessBA.activityBA.get())) {
                    BA.LogInfo("Sleep not resumed (context destroyed): " + rs.getClass().getName());
                } else if (!BA.this.isActivityPaused()) {
                    try {
                        rs.resume(BA.this, null);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if (isActivity) {
                    BA.this.processBA.addMessageToPausedMessageQueue("sleep", this);
                } else {
                    BA.LogInfo("Sleep not resumed (context is paused): " + rs.getClass().getName());
                }
            }
        }, (long) Milliseconds);
    }

    @BA.Hide
    public static void WaitFor(String SubName, BA ba, BA.ResumableSub rs, Object SenderFilter) {
        Object o;
        if (ba.waitForEvents == null) {
            ba.waitForEvents = new HashMap<>();
        }
        if (SenderFilter instanceof ObjectWrapper) {
            o = ((ObjectWrapper) SenderFilter).getObject();
        } else {
            o = SenderFilter;
        }
        if (o instanceof BA.ResumableSub) {
            BA.ResumableSub rsSenderFilter = (BA.ResumableSub) o;
            if (rsSenderFilter.completed) {
                throw new RuntimeException("Resumable sub already completed");
            }
            rsSenderFilter.waitForBA = ba;
        }
        LinkedList<BA.WaitForEvent> ll = ba.waitForEvents.get(SubName);
        if (ll == null) {
            ll = new LinkedList<>();
            ba.waitForEvents.put(SubName, ll);
        }
        boolean added = false;
        Iterator<BA.WaitForEvent> it = ll.iterator();
        while (it.hasNext()) {
            BA.WaitForEvent wfe = it.next();
            if (!added && ((o == null && wfe.noFilter()) || (o != null && o == wfe.senderFilter.get()))) {
                added = true;
                wfe.rs = rs;
            } else if (wfe.cleared()) {
                it.remove();
            }
        }
        if (!added) {
            BA.WaitForEvent wfe2 = new BA.WaitForEvent(rs, o);
            if (wfe2.noFilter()) {
                ll.addLast(wfe2);
            } else {
                ll.addFirst(wfe2);
            }
        }
    }

    @BA.Hide
    public static void ReturnFromResumableSub(final BA.ResumableSub rs, final Object returnValue) {
        BA.handler.post(new Runnable() {
            /* class anywheresoftware.b4a.keywords.Common.AnonymousClass14 */

            public void run() {
                BA.ResumableSub.this.completed = true;
                if (BA.ResumableSub.this.waitForBA != null) {
                    BA.ResumableSub.this.waitForBA.raiseEvent(BA.ResumableSub.this, "complete", returnValue);
                }
            }
        });
    }

    @BA.ShortName("ResumableSub")
    public static class ResumableSubWrapper extends AbsObjectWrapper<BA.ResumableSub> {
        public boolean getCompleted() {
            return ((BA.ResumableSub) getObject()).completed;
        }
    }
}
