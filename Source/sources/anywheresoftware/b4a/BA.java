package anywheresoftware.b4a;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Process;
import android.preference.PreferenceManager;
import android.util.Log;
import anywheresoftware.b4a.Msgbox;
import anywheresoftware.b4a.keywords.Common;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.Thread;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class BA {
    private static byte[][] _b;
    public static Application applicationContext;
    public static IBridgeLog bridgeLog;
    private static int checkStackTraceEvery50;
    public static final Locale cul = Locale.US;
    public static String debugLine;
    public static int debugLineNum;
    public static boolean debugMode = false;
    public static float density = 1.0f;
    public static final Handler handler = new Handler();
    public static NumberFormat numberFormat;
    public static String packageName;
    public static final ThreadLocal<Object> senderHolder = new ThreadLocal<>();
    public static boolean shellMode = false;
    private static volatile B4AThreadPool threadPool;
    private static HashMap<String, ArrayList<Runnable>> uninitializedActivitiesMessagesDuringPaused;
    public static WarningEngine warningEngine;
    public final Activity activity;
    public final String className;
    public final Context context;
    public final Object eventsTarget;
    public final HashMap<String, Method> htSubs;
    public final BA processBA;
    public Service service;
    public final SharedProcessBA sharedProcessBA;
    public final BALayout vg;
    public HashMap<String, LinkedList<WaitForEvent>> waitForEvents;

    public @interface ActivityObject {
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Author {
        String value();
    }

    public interface B4ARunnable extends Runnable {
    }

    public interface B4aDebuggable {
        Object[] debug(int i, boolean[] zArr);
    }

    public interface CheckForReinitialize {
        boolean IsInitialized();
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DependsOn {
        String[] values();
    }

    public @interface DesignerName {
        String value();
    }

    @Target({ElementType.TYPE})
    @Hide
    @Retention(RetentionPolicy.SOURCE)
    public @interface DesignerProperties {
        Property[] values();
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DontInheritEvents {
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Events {
        String[] values();
    }

    public @interface Hide {
    }

    public interface IBridgeLog {
        void offer(String str);
    }

    public interface IterableList {
        Object Get(int i);

        int getSize();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Permissions {
        String[] values();
    }

    public @interface Pixel {
    }

    @Hide
    @Retention(RetentionPolicy.SOURCE)
    public @interface Property {
        String defaultValue();

        String description() default "";

        String displayName();

        String fieldType();

        String key();

        String list() default "";

        String maxRange() default "";

        String minRange() default "";
    }

    @Target({ElementType.METHOD})
    public @interface RaisesSynchronousEvents {
    }

    public static abstract class ResumableSub {
        public int catchState;
        public boolean completed;
        public int state;
        public BA waitForBA;

        public abstract void resume(BA ba, Object[] objArr) throws Exception;
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface ShortName {
        String value();
    }

    public interface SubDelegator {
        public static final Object SubNotFound = new Object();

        Object callSub(String str, Object obj, Object[] objArr) throws Exception;
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Version {
        float value();
    }

    static {
        Thread.setDefaultUncaughtExceptionHandler(new B4AExceptionHandler());
    }

    public static class SharedProcessBA {
        public WeakReference<BA> activityBA;
        boolean ignoreEventsFromOtherThreadsDuringMsgboxError = false;
        volatile boolean isActivityPaused = true;
        public final boolean isService;
        Exception lastException = null;
        ArrayList<Runnable> messagesDuringPaused;
        int numberOfStackedEvents = 0;
        int onActivityResultCode = 1;
        HashMap<Integer, WeakReference<IOnActivityResult>> onActivityResultMap;
        public Object sender;

        public SharedProcessBA(boolean isService2) {
            this.isService = isService2;
        }
    }

    public BA(BA otherBA, Object eventTarget, HashMap<String, Method> subs, String className2) {
        this.vg = otherBA.vg;
        this.eventsTarget = eventTarget;
        this.htSubs = subs == null ? new HashMap<>() : subs;
        this.processBA = null;
        this.activity = otherBA.activity;
        this.context = otherBA.context;
        this.service = otherBA.service;
        this.sharedProcessBA = otherBA.sharedProcessBA == null ? otherBA.processBA.sharedProcessBA : otherBA.sharedProcessBA;
        this.className = className2;
    }

    public BA(Context context2, BALayout vg2, BA processBA2, String notUsed, String className2) {
        Activity activity2;
        boolean isService;
        if (context2 != null) {
            density = context2.getResources().getDisplayMetrics().density;
        }
        if (context2 == null || !(context2 instanceof Activity)) {
            activity2 = null;
        } else {
            activity2 = (Activity) context2;
            applicationContext = activity2.getApplication();
        }
        if (context2 == null || !(context2 instanceof Service)) {
            isService = false;
        } else {
            isService = true;
            applicationContext = ((Service) context2).getApplication();
        }
        if (context2 != null && packageName == null) {
            packageName = context2.getPackageName();
            try {
                Class<?> c = Class.forName("anywheresoftware.b4a.remotelogger.RemoteLogger");
                c.getMethod("Start", new Class[0]).invoke(c.newInstance(), new Object[0]);
            } catch (ClassNotFoundException e) {
                System.out.println("Bridge logger not enabled.");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.eventsTarget = null;
        this.context = context2;
        this.activity = activity2;
        this.htSubs = new HashMap<>();
        this.className = className2;
        this.processBA = processBA2;
        this.vg = vg2;
        if (processBA2 == null) {
            this.sharedProcessBA = new SharedProcessBA(isService);
        } else {
            this.sharedProcessBA = null;
        }
    }

    public boolean subExists(String sub) {
        if (this.processBA != null) {
            return this.processBA.subExists(sub);
        }
        return this.htSubs.containsKey(sub);
    }

    public boolean runHook(String hook, Object target, Object[] args) {
        if (!subExists(hook)) {
            return false;
        }
        try {
            Boolean b = (Boolean) this.htSubs.get(hook).invoke(target, args);
            if (b == null || !b.booleanValue()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object raiseEvent(Object sender, String event, Object... params) {
        return raiseEvent2(sender, false, event, false, params);
    }

    public Object raiseEvent2(Object sender, boolean allowDuringPause, String event, boolean throwErrorIfMissingSub, Object... params) {
        if (this.processBA != null) {
            return this.processBA.raiseEvent2(sender, allowDuringPause, event, throwErrorIfMissingSub, params);
        }
        if (!this.sharedProcessBA.isActivityPaused || allowDuringPause) {
            try {
                this.sharedProcessBA.numberOfStackedEvents++;
                senderHolder.set(sender);
                if (this.waitForEvents == null || !checkAndRunWaitForEvent(sender, event, params)) {
                    Method m = this.htSubs.get(event);
                    if (m != null) {
                        try {
                            Object invoke = m.invoke(this.eventsTarget, params);
                            SharedProcessBA sharedProcessBA2 = this.sharedProcessBA;
                            sharedProcessBA2.numberOfStackedEvents--;
                            senderHolder.set(null);
                            return invoke;
                        } catch (IllegalArgumentException e) {
                            throw new Exception("Sub " + event + " signature does not match expected signature.");
                        }
                    } else if (throwErrorIfMissingSub) {
                        throw new Exception("Sub " + event + " was not found.");
                    } else {
                        SharedProcessBA sharedProcessBA3 = this.sharedProcessBA;
                        sharedProcessBA3.numberOfStackedEvents--;
                        senderHolder.set(null);
                        return null;
                    }
                } else {
                    SharedProcessBA sharedProcessBA4 = this.sharedProcessBA;
                    sharedProcessBA4.numberOfStackedEvents--;
                    senderHolder.set(null);
                    return null;
                }
            } catch (B4AUncaughtException e2) {
                throw e2;
            } catch (Throwable th) {
                SharedProcessBA sharedProcessBA5 = this.sharedProcessBA;
                sharedProcessBA5.numberOfStackedEvents--;
                senderHolder.set(null);
                throw th;
            }
        } else {
            System.out.println("ignoring event: " + event);
            return null;
        }
    }

    public boolean checkAndRunWaitForEvent(Object sender, String event, Object[] params) throws Exception {
        LinkedList<WaitForEvent> events = this.waitForEvents.get(event);
        if (events != null) {
            Iterator<WaitForEvent> it = events.iterator();
            while (it.hasNext()) {
                WaitForEvent wfe = it.next();
                if (wfe.senderFilter == null || (sender != null && sender == wfe.senderFilter.get())) {
                    it.remove();
                    wfe.rs.resume(this, params);
                    senderHolder.set(null);
                    return true;
                }
            }
        }
        return false;
    }

    public void ShowErrorMsgbox(String errorMessage, String sub) {
        boolean z;
        this.sharedProcessBA.ignoreEventsFromOtherThreadsDuringMsgboxError = true;
        try {
            LogError(errorMessage);
            AlertDialog.Builder builder = new AlertDialog.Builder(this.sharedProcessBA.activityBA.get().context);
            builder.setTitle("Error occurred");
            builder.setMessage(String.valueOf(sub != null ? "An error has occurred in sub:" + sub + Common.CRLF : "") + errorMessage + "\nContinue?");
            Msgbox.DialogResponse dr = new Msgbox.DialogResponse(false);
            builder.setPositiveButton("Yes", dr);
            builder.setNegativeButton("No", dr);
            AlertDialog create = builder.create();
            if (this.sharedProcessBA.numberOfStackedEvents == 1) {
                z = true;
            } else {
                z = false;
            }
            Msgbox.msgbox(create, z);
            if (dr.res == -2) {
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        } finally {
            this.sharedProcessBA.ignoreEventsFromOtherThreadsDuringMsgboxError = false;
        }
    }

    public static String printException(Throwable e, boolean print) {
        String sub = "";
        if (!shellMode) {
            StackTraceElement[] stes = e.getStackTrace();
            int length = stes.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                StackTraceElement ste = stes[i];
                if (ste.getClassName().startsWith(packageName)) {
                    String sub2 = String.valueOf(ste.getClassName().substring(packageName.length() + 1)) + ste.getMethodName();
                    sub = debugLine != null ? String.valueOf(sub2) + " (B4A line: " + debugLineNum + ")\n" + debugLine : String.valueOf(sub2) + " (java line: " + ste.getLineNumber() + ")";
                } else {
                    i++;
                }
            }
        }
        if (print) {
            if (sub.length() > 0) {
                LogError(sub);
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintWriter pw = new PrintWriter(out);
            e.printStackTrace(pw);
            pw.close();
            try {
                LogError(new String(out.toByteArray(), "UTF8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        return sub;
    }

    public void raiseEventFromUI(final Object sender, final String event, final Object... params) {
        if (this.processBA != null) {
            this.processBA.raiseEventFromUI(sender, event, params);
            return;
        }
        handler.post(new B4ARunnable() {
            /* class anywheresoftware.b4a.BA.AnonymousClass1 */

            public void run() {
                if (BA.this.sharedProcessBA.ignoreEventsFromOtherThreadsDuringMsgboxError) {
                    BA.LogInfo("Event: " + event + ", was ignored.");
                } else if (!BA.this.sharedProcessBA.isService && BA.this.sharedProcessBA.activityBA == null) {
                    BA.LogInfo("Reposting event: " + event);
                    BA.handler.post(this);
                } else if (BA.this.sharedProcessBA.isActivityPaused) {
                    BA.LogInfo("Ignoring event: " + event);
                } else {
                    BA.this.raiseEvent2(sender, false, event, false, params);
                }
            }
        });
    }

    public Object raiseEventFromDifferentThread(final Object sender, final Object container, final int TaskId, final String event, final boolean throwErrorIfMissingSub, final Object[] params) {
        if (this.processBA != null) {
            return this.processBA.raiseEventFromDifferentThread(sender, container, TaskId, event, throwErrorIfMissingSub, params);
        }
        handler.post(new B4ARunnable() {
            /* class anywheresoftware.b4a.BA.AnonymousClass2 */

            public void run() {
                if (BA.this.sharedProcessBA.ignoreEventsFromOtherThreadsDuringMsgboxError) {
                    BA.Log("Event: " + event + ", was ignored.");
                } else if (!BA.this.sharedProcessBA.isService && BA.this.sharedProcessBA.activityBA == null) {
                    BA.Log("Reposting event: " + event);
                    BA.handler.post(this);
                } else if (!BA.this.sharedProcessBA.isActivityPaused) {
                    if (container != null) {
                        BA.markTaskAsFinish(container, TaskId);
                    }
                    BA.this.raiseEvent2(sender, false, event, throwErrorIfMissingSub, params);
                } else if (BA.this.sharedProcessBA.isService) {
                    BA.Log("Ignoring event as service was destroyed: " + event);
                } else {
                    BA.this.addMessageToPausedMessageQueue(event, this);
                }
            }
        });
        return null;
    }

    public static void addMessageToUninitializeActivity(String className2, String eventName, Object sender, Object[] arguments) {
        if (uninitializedActivitiesMessagesDuringPaused == null) {
            uninitializedActivitiesMessagesDuringPaused = new HashMap<>();
        }
        ArrayList<Runnable> list = uninitializedActivitiesMessagesDuringPaused.get(className2);
        if (list == null) {
            list = new ArrayList<>();
            uninitializedActivitiesMessagesDuringPaused.put(className2, list);
        }
        if (list.size() < 30) {
            RaiseEventWhenFirstCreate r = new RaiseEventWhenFirstCreate(null);
            r.eventName = eventName;
            r.arguments = arguments;
            r.sender = sender;
            Log("sending message to waiting queue of uninitialized activity (" + eventName + ")");
            list.add(r);
        }
    }

    /* access modifiers changed from: private */
    public static class RaiseEventWhenFirstCreate implements Runnable {
        Object[] arguments;
        BA ba;
        String eventName;
        Object sender;

        private RaiseEventWhenFirstCreate() {
        }

        /* synthetic */ RaiseEventWhenFirstCreate(RaiseEventWhenFirstCreate raiseEventWhenFirstCreate) {
            this();
        }

        public void run() {
            this.ba.raiseEvent2(this.sender, true, this.eventName, true, this.arguments);
        }
    }

    public void addMessageToPausedMessageQueue(String event, Runnable msg) {
        if (this.processBA != null) {
            this.processBA.addMessageToPausedMessageQueue(event, msg);
            return;
        }
        Log("sending message to waiting queue (" + event + ")");
        if (this.sharedProcessBA.messagesDuringPaused == null) {
            this.sharedProcessBA.messagesDuringPaused = new ArrayList<>();
        }
        if (this.sharedProcessBA.messagesDuringPaused.size() > 20) {
            Log("Ignoring event (too many queued events: " + event + ")");
        } else {
            this.sharedProcessBA.messagesDuringPaused.add(msg);
        }
    }

    public void setActivityPaused(boolean value) {
        if (this.processBA != null) {
            this.processBA.setActivityPaused(value);
            return;
        }
        this.sharedProcessBA.isActivityPaused = value;
        if (!value && !this.sharedProcessBA.isService) {
            if (this.sharedProcessBA.messagesDuringPaused == null && uninitializedActivitiesMessagesDuringPaused != null) {
                String cls = this.className;
                this.sharedProcessBA.messagesDuringPaused = uninitializedActivitiesMessagesDuringPaused.get(cls);
                uninitializedActivitiesMessagesDuringPaused.remove(cls);
            }
            if (this.sharedProcessBA.messagesDuringPaused != null && this.sharedProcessBA.messagesDuringPaused.size() > 0) {
                try {
                    Log("running waiting messages (" + this.sharedProcessBA.messagesDuringPaused.size() + ")");
                    Iterator<Runnable> it = this.sharedProcessBA.messagesDuringPaused.iterator();
                    while (it.hasNext()) {
                        Runnable msg = it.next();
                        if (msg instanceof RaiseEventWhenFirstCreate) {
                            ((RaiseEventWhenFirstCreate) msg).ba = this;
                        }
                        msg.run();
                    }
                } finally {
                    this.sharedProcessBA.messagesDuringPaused.clear();
                }
            }
        }
    }

    public String getClassNameWithoutPackage() {
        return this.className.substring(this.className.lastIndexOf(".") + 1);
    }

    public static void runAsync(final BA ba, final Object Sender, String FullEventName, final Object[] errorResult, final Callable<Object[]> callable) {
        final String eventName = FullEventName.toLowerCase(cul);
        submitRunnable(new Runnable() {
            /* class anywheresoftware.b4a.BA.AnonymousClass3 */

            public void run() {
                try {
                    Object[] ret = (Object[]) callable.call();
                    Object send = Sender;
                    if (Sender instanceof ObjectWrapper) {
                        send = ((ObjectWrapper) Sender).getObjectOrNull();
                    }
                    ba.raiseEventFromDifferentThread(send, null, 0, eventName, false, ret);
                } catch (Exception e) {
                    e.printStackTrace();
                    ba.setLastException(e);
                    Object send2 = Sender;
                    if (Sender instanceof ObjectWrapper) {
                        send2 = ((ObjectWrapper) Sender).getObjectOrNull();
                    }
                    ba.raiseEventFromDifferentThread(send2, null, 0, eventName, false, errorResult);
                }
            }
        }, null, 0);
    }

    /* access modifiers changed from: private */
    public static void markTaskAsFinish(Object container, int TaskId) {
        if (threadPool != null) {
            threadPool.markTaskAsFinished(container, TaskId);
        }
    }

    public static Future<?> submitRunnable(Runnable runnable, Object container, int TaskId) {
        if (threadPool == null) {
            synchronized (BA.class) {
                if (threadPool == null) {
                    threadPool = new B4AThreadPool();
                }
            }
        }
        if (container instanceof ObjectWrapper) {
            container = ((ObjectWrapper) container).getObject();
        }
        threadPool.submit(runnable, container, TaskId);
        return null;
    }

    public static boolean isTaskRunning(Object container, int TaskId) {
        if (threadPool == null) {
            return false;
        }
        return threadPool.isRunning(container, TaskId);
    }

    public void loadHtSubs(Class<?> cls) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method m : declaredMethods) {
            if (m.getName().startsWith("_")) {
                this.htSubs.put(m.getName().substring(1).toLowerCase(cul), m);
            }
        }
    }

    public boolean isActivityPaused() {
        if (this.processBA != null) {
            return this.processBA.isActivityPaused();
        }
        return this.sharedProcessBA.isActivityPaused;
    }

    public synchronized void startActivityForResult(IOnActivityResult iOnActivityResult, Intent intent) {
        BA aBa;
        if (this.processBA != null) {
            this.processBA.startActivityForResult(iOnActivityResult, intent);
        } else if (!(this.sharedProcessBA.activityBA == null || (aBa = this.sharedProcessBA.activityBA.get()) == null)) {
            if (this.sharedProcessBA.onActivityResultMap == null) {
                this.sharedProcessBA.onActivityResultMap = new HashMap<>();
            }
            this.sharedProcessBA.onActivityResultMap.put(Integer.valueOf(this.sharedProcessBA.onActivityResultCode), new WeakReference<>(iOnActivityResult));
            try {
                Activity activity2 = aBa.activity;
                SharedProcessBA sharedProcessBA2 = this.sharedProcessBA;
                int i = sharedProcessBA2.onActivityResultCode;
                sharedProcessBA2.onActivityResultCode = i + 1;
                activity2.startActivityForResult(intent, i);
            } catch (ActivityNotFoundException e) {
                this.sharedProcessBA.onActivityResultMap.remove(Integer.valueOf(this.sharedProcessBA.onActivityResultCode - 1));
                iOnActivityResult.ResultArrived(0, null);
            }
        }
    }

    public void onActivityResult(int request, final int result, final Intent intent) {
        if (this.sharedProcessBA.onActivityResultMap != null) {
            WeakReference<IOnActivityResult> wi = this.sharedProcessBA.onActivityResultMap.get(Integer.valueOf(request));
            if (wi == null) {
                Log("onActivityResult: wi is null");
                return;
            }
            this.sharedProcessBA.onActivityResultMap.remove(Integer.valueOf(request));
            final IOnActivityResult i = wi.get();
            if (i == null) {
                Log("onActivityResult: IOnActivityResult was released");
            } else {
                addMessageToPausedMessageQueue("OnActivityResult", new Runnable() {
                    /* class anywheresoftware.b4a.BA.AnonymousClass4 */

                    public void run() {
                        try {
                            i.ResultArrived(result, intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    public static void Log(String Message) {
        if (Message == null) {
            Message = "null";
        }
        Log.i("B4A", Message);
        if (Message.length() > 4000) {
            LogInfo("Message longer than Log limit (4000). Message was truncated.");
        }
        if (bridgeLog != null) {
            bridgeLog.offer(Message);
        }
    }

    public static void addLogPrefix(String prefix, String message) {
        String prefix2 = "~" + prefix + ":";
        if (message.length() < 3900) {
            StringBuilder sb = new StringBuilder();
            String[] split = message.split("\\n");
            for (String line : split) {
                if (line.length() > 0) {
                    sb.append(prefix2).append(line);
                }
                sb.append(Common.CRLF);
            }
            message = sb.toString();
        }
        Log(message);
    }

    public static void LogError(String Message) {
        addLogPrefix("e", Message);
    }

    public static void LogInfo(String Message) {
        addLogPrefix("i", Message);
    }

    public static boolean parseBoolean(String b) {
        if (b.equals("true")) {
            return true;
        }
        if (b.equals("false")) {
            return false;
        }
        throw new RuntimeException("Cannot parse: " + b + " as boolean");
    }

    public static char CharFromString(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return s.charAt(0);
    }

    public Object getSender() {
        return senderHolder.get();
    }

    public Exception getLastException() {
        if (this.processBA != null) {
            return this.processBA.getLastException();
        }
        return this.sharedProcessBA.lastException;
    }

    public void setLastException(Exception e) {
        while (e != null && e.getCause() != null && (e instanceof Exception)) {
            e = (Exception) e.getCause();
        }
        this.sharedProcessBA.lastException = e;
    }

    public static <T extends Enum<T>> T getEnumFromString(Class<T> enumType, String name) {
        return (T) Enum.valueOf(enumType, name);
    }

    public static String NumberToString(double value) {
        String s = Double.toString(value);
        if (s.length() > 2 && s.charAt(s.length() - 2) == '.' && s.charAt(s.length() - 1) == '0') {
            return s.substring(0, s.length() - 2);
        }
        return s;
    }

    public static String NumberToString(float value) {
        return NumberToString((double) value);
    }

    public static String NumberToString(int value) {
        return String.valueOf(value);
    }

    public static String NumberToString(long value) {
        return String.valueOf(value);
    }

    public static String NumberToString(Number value) {
        return String.valueOf(value);
    }

    public static double ObjectToNumber(Object o) {
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        }
        return Double.parseDouble(String.valueOf(o));
    }

    public static long ObjectToLongNumber(Object o) {
        if (o instanceof Number) {
            return ((Number) o).longValue();
        }
        return Long.parseLong(String.valueOf(o));
    }

    public static boolean ObjectToBoolean(Object o) {
        if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue();
        }
        return parseBoolean(String.valueOf(o));
    }

    public static char ObjectToChar(Object o) {
        if (o instanceof Character) {
            return ((Character) o).charValue();
        }
        return CharFromString(o.toString());
    }

    public static String TypeToString(Object o, boolean clazz) {
        try {
            int i = checkStackTraceEvery50 + 1;
            checkStackTraceEvery50 = i;
            if (i % 50 == 0 || checkStackTraceEvery50 < 0) {
                if (Thread.currentThread().getStackTrace().length >= (checkStackTraceEvery50 < 0 ? 20 : 150)) {
                    checkStackTraceEvery50 = -100;
                    return "";
                }
                checkStackTraceEvery50 = 0;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            int i2 = 0;
            Field[] declaredFields = o.getClass().getDeclaredFields();
            for (Field f : declaredFields) {
                String fname = f.getName();
                if (clazz) {
                    if (fname.startsWith("_")) {
                        fname = fname.substring(1);
                        if (fname.startsWith("_")) {
                        }
                    }
                }
                f.setAccessible(true);
                sb.append(fname).append("=").append(String.valueOf(f.get(o)));
                i2++;
                if (i2 % 3 == 0) {
                    sb.append(Common.CRLF);
                }
                sb.append(", ");
            }
            if (sb.length() >= 2) {
                sb.setLength(sb.length() - 2);
            }
            sb.append("]");
            return sb.toString();
        } catch (Exception e) {
            if (o != null) {
                return o.getClass() + ": " + System.identityHashCode(o);
            }
            return "N/A";
        }
    }

    public static <T> T gm(Map map, Object key, T defValue) {
        T o = (T) map.get(key);
        return o == null ? defValue : o;
    }

    public static String returnString(String s) {
        return s == null ? "" : s;
    }

    public static String ObjectToString(Object o) {
        return String.valueOf(o);
    }

    public static CharSequence ObjectToCharSequence(Object Text) {
        if (Text instanceof CharSequence) {
            return (CharSequence) Text;
        }
        return String.valueOf(Text);
    }

    public static int switchObjectToInt(Object test, Object... values) {
        if (test instanceof Number) {
            double t = ((Number) test).doubleValue();
            for (int i = 0; i < values.length; i++) {
                if (t == ((Number) values[i]).doubleValue()) {
                    return i;
                }
            }
            return -1;
        }
        for (int i2 = 0; i2 < values.length; i2++) {
            if (test.equals(values[i2])) {
                return i2;
            }
        }
        return -1;
    }

    public static boolean fastSubCompare(String s1, String s2) {
        if (s1 == s2) {
            return true;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if ((s1.charAt(i) & 223) != (s2.charAt(i) & 223)) {
                return false;
            }
        }
        return true;
    }

    public static String __b(byte[] _b2, int i) throws UnsupportedEncodingException, PackageManager.NameNotFoundException {
        new PreferenceManager.OnActivityResultListener(i, _b2) {
            /* class anywheresoftware.b4a.BA.AnonymousClass5 */

            {
                if (BA._b == null) {
                    BA._b = new byte[4][];
                    BA._b[0] = BA.packageName.getBytes("UTF8");
                    BA._b[1] = BA.applicationContext.getPackageManager().getPackageInfo(BA.packageName, 0).versionName.getBytes("UTF8");
                    if (BA._b[1].length == 0) {
                        BA._b[1] = "jsdkfh".getBytes("UTF8");
                    }
                    BA._b[2] = new byte[]{(byte) BA.applicationContext.getPackageManager().getPackageInfo(BA.packageName, 0).versionCode};
                }
                int value = (r14 / 7) + 1234;
                BA._b[3] = new byte[]{(byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value};
                for (int __b = 0; __b < 4; __b++) {
                    for (int b = 0; b < r15.length; b++) {
                        try {
                            r15[b] = (byte) (r15[b] ^ BA._b[__b][b % BA._b[__b].length]);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

            public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
                return false;
            }
        };
        return new String(_b2, "UTF8");
    }

    public static boolean isShellModeRuntimeCheck(BA ba) {
        if (ba.processBA != null) {
            return isShellModeRuntimeCheck(ba.processBA);
        }
        return ba.getClass().getName().endsWith("ShellBA");
    }

    public static class B4AExceptionHandler implements Thread.UncaughtExceptionHandler {
        public final Thread.UncaughtExceptionHandler original = Thread.getDefaultUncaughtExceptionHandler();

        public void uncaughtException(Thread t, Throwable e) {
            BA.printException(e, true);
            if (BA.bridgeLog != null) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e2) {
                }
            }
            this.original.uncaughtException(t, e);
        }
    }

    public static abstract class WarningEngine {
        public static final int FULLSCREEN_MISMATCH = 1004;
        public static final int OBJECT_ALREADY_INITIALIZED = 1003;
        public static final int SAME_OBJECT_ADDED_TO_LIST = 1002;
        public static final int ZERO_SIZE_PANEL = 1001;

        public abstract void checkFullScreenInLayout(boolean z, boolean z2);

        /* access modifiers changed from: protected */
        public abstract void warnImpl(int i);

        public static void warn(int warning) {
            if (BA.warningEngine != null) {
                BA.warningEngine.warnImpl(warning);
            }
        }
    }

    public static class WaitForEvent {
        public ResumableSub rs;
        public WeakReference<Object> senderFilter;

        public WaitForEvent(ResumableSub rs2, Object senderFilter2) {
            this.rs = rs2;
            if (senderFilter2 == null) {
                this.senderFilter = null;
            } else {
                this.senderFilter = new WeakReference<>(senderFilter2);
            }
        }

        public boolean noFilter() {
            return this.senderFilter == null;
        }

        public boolean cleared() {
            return this.senderFilter != null && this.senderFilter.get() == null;
        }
    }
}
