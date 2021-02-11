package anywheresoftware.b4a.objects;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.Common;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.Thread;
import java.lang.reflect.InvocationTargetException;

@BA.ActivityObject
public class ServiceHelper {
    NotificationManager mNM = ((NotificationManager) BA.applicationContext.getSystemService("notification"));
    private Service service;

    @BA.Hide
    public static void init() {
    }

    public ServiceHelper(Service service2) {
        this.service = service2;
    }

    public void StartForeground(int Id, Notification Notification) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        this.service.startForeground(Id, Notification);
    }

    public void StopForeground(int Id) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        this.service.stopForeground(true);
    }

    @BA.Hide
    public static class StarterHelper {
        private static boolean alreadyRun;
        private static boolean insideHandler;
        private static BA serviceProcessBA;
        private static Runnable waitForLayouts;

        public static boolean startFromActivity(BA ba, Runnable waitForLayout, boolean noStarter) {
            if (alreadyRun || noStarter) {
                return true;
            }
            alreadyRun = true;
            addWaitForLayout(waitForLayout);
            try {
                Common.StartService(ba, "starter");
                return false;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public static boolean startFromServiceCreate(BA ba, boolean noStarter) {
            if (alreadyRun || noStarter) {
                return true;
            }
            alreadyRun = true;
            serviceProcessBA = ba;
            return false;
        }

        public static void runWaitForLayouts() {
            if (waitForLayouts != null) {
                BA.handler.post(waitForLayouts);
            }
        }

        public static void addWaitForLayout(Runnable r) {
            waitForLayouts = r;
        }

        public static void removeWaitForLayout() {
            waitForLayouts = null;
        }

        public static boolean onStartCommand(BA ba, Runnable handleStart) {
            if (ba == null || ba != serviceProcessBA) {
                if (!ba.isActivityPaused() || waitForLayouts == null) {
                    handleStart.run();
                } else {
                    BA.handler.postDelayed(handleStart, 500);
                }
                return true;
            }
            try {
                Common.StartService(ba, "starter");
                serviceProcessBA = null;
                return false;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        /* JADX INFO: finally extract failed */
        public static boolean handleUncaughtException(Throwable t, BA ba) throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, UnsupportedEncodingException {
            if (insideHandler) {
                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), t);
                return true;
            }
            try {
                insideHandler = true;
                if (!alreadyRun || !Common.SubExists(ba, "starter", "application_error")) {
                    insideHandler = false;
                    return false;
                } else if (Common.IsPaused(ba, "starter")) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), t);
                    insideHandler = false;
                    return true;
                } else {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    PrintWriter pw = new PrintWriter(out);
                    t.printStackTrace(pw);
                    pw.close();
                    byte[] b = out.toByteArray();
                    B4AException exc = new B4AException();
                    if (t instanceof Exception) {
                        exc.setObject((Exception) t);
                    } else {
                        exc.setObject(new Exception(t));
                    }
                    if (Boolean.TRUE.equals((Boolean) Common.CallSubNew3(ba, "starter", "application_error", exc, Common.BytesToString(b, 0, b.length, "UTF8")))) {
                        Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
                        if (handler instanceof BA.B4AExceptionHandler) {
                            ((BA.B4AExceptionHandler) handler).original.uncaughtException(Thread.currentThread(), t);
                        } else {
                            handler.uncaughtException(Thread.currentThread(), t);
                        }
                    }
                    insideHandler = false;
                    return true;
                }
            } catch (Throwable th) {
                insideHandler = false;
                throw th;
            }
        }

        public static void callOSExceptionHandler(B4AException e) {
            Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
            if (handler instanceof BA.B4AExceptionHandler) {
                ((BA.B4AExceptionHandler) handler).original.uncaughtException(Thread.currentThread(), (Throwable) e.getObject());
            } else {
                handler.uncaughtException(Thread.currentThread(), (Throwable) e.getObject());
            }
        }
    }
}
