package anywheresoftware.b4a;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import anywheresoftware.b4a.BA;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

@BA.Hide
public class Msgbox {
    private static Object closeMyLoop = new Object();
    private static Field flagsF;
    public static boolean isDismissing = false;
    private static final ArrayList<WeakReference<Dialog>> listOfAsyncDialogs = new ArrayList<>();
    private static Method nextM;
    public static WeakReference<ProgressDialog> pd;
    private static Method recycleUnchecked;
    private static boolean stopCodeAfterDismiss = false;
    private static boolean visible = false;
    private static WeakReference<AlertDialog> visibleAD;
    private static Field whenF;

    static {
        try {
            nextM = MessageQueue.class.getDeclaredMethod("next", null);
            nextM.setAccessible(true);
            whenF = Message.class.getDeclaredField("when");
            whenF.setAccessible(true);
            flagsF = null;
            try {
                flagsF = Message.class.getDeclaredField("flags");
                flagsF.setAccessible(true);
                recycleUnchecked = Message.class.getDeclaredMethod("recycleUnchecked", new Class[0]);
                recycleUnchecked.setAccessible(true);
            } catch (Exception e) {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean msgboxIsVisible() {
        return visible;
    }

    public static boolean isItReallyAMsgboxAndNotDebug() {
        return visibleAD != null;
    }

    public static void dismiss(boolean stopCodeAfterDismiss2) {
        dismissProgressDialog();
        if (BA.debugMode) {
            try {
                Class.forName("anywheresoftware.b4a.debug.Debug").getMethod("hideProgressDialogToAvoidLeak", null).invoke(null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        isDismissing = true;
        if (visible) {
            if (visibleAD != null) {
                AlertDialog ad = visibleAD.get();
                if (ad != null) {
                    ad.dismiss();
                }
            } else {
                sendCloseMyLoopMessage();
            }
            stopCodeAfterDismiss = stopCodeAfterDismiss2;
        }
        Iterator<WeakReference<Dialog>> it = listOfAsyncDialogs.iterator();
        while (it.hasNext()) {
            Dialog d = it.next().get();
            if (d != null && d.isShowing()) {
                try {
                    d.dismiss();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static void trackAsyncDialog(Dialog d) {
        Iterator<WeakReference<Dialog>> it = listOfAsyncDialogs.iterator();
        while (it.hasNext()) {
            if (it.next().get() == null) {
                it.remove();
            }
        }
        listOfAsyncDialogs.add(new WeakReference<>(d));
    }

    public static void sendCloseMyLoopMessage() {
        Message msg = Message.obtain();
        msg.setTarget(BA.handler);
        msg.obj = closeMyLoop;
        msg.sendToTarget();
    }

    public static void dismissProgressDialog() {
        ProgressDialog p;
        if (pd != null && (p = pd.get()) != null) {
            try {
                p.dismiss();
            } catch (Exception e) {
                BA.LogInfo("Error while dismissing ProgressDialog");
                e.printStackTrace();
            }
            pd = null;
        }
    }

    public static class DialogResponse implements DialogInterface.OnClickListener {
        private boolean dismiss;
        public int res = -3;

        public DialogResponse(boolean dismissAfterClick) {
            this.dismiss = dismissAfterClick;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.res = which;
            if (this.dismiss) {
                ((AlertDialog) Msgbox.visibleAD.get()).dismiss();
            }
        }
    }

    public static void msgbox(AlertDialog ad, boolean isTopMostInStack) {
        if (!visible) {
            try {
                if (!isDismissing) {
                    stopCodeAfterDismiss = false;
                    Message msg = Message.obtain();
                    msg.setTarget(BA.handler);
                    msg.obj = closeMyLoop;
                    ad.setDismissMessage(msg);
                    visible = true;
                    visibleAD = new WeakReference<>(ad);
                    ad.show();
                    waitForMessage(false);
                    if (!stopCodeAfterDismiss || isTopMostInStack) {
                        visible = false;
                        visibleAD = null;
                        return;
                    }
                    throw new B4AUncaughtException();
                }
            } finally {
                visible = false;
                visibleAD = null;
            }
        }
    }

    public static void debugWait(Dialog d) {
        if (visible) {
            System.out.println("already visible");
            return;
        }
        try {
            if (!isDismissing) {
                stopCodeAfterDismiss = false;
                visible = true;
                waitForMessage(true);
                if (stopCodeAfterDismiss) {
                    Log.w("", "throwing b4a uncaught exception");
                    throw new B4AUncaughtException();
                } else {
                    visible = false;
                }
            }
        } finally {
            visible = false;
        }
    }

    public static void waitForMessage(boolean notUsed, boolean onlyDrawableEvents) {
        waitForMessage(onlyDrawableEvents);
    }

    private static void waitForMessage(boolean onlyDrawableEvents) {
        try {
            MessageQueue queue = Looper.myQueue();
            while (true) {
                Message msg = (Message) nextM.invoke(queue, null);
                if (msg != null) {
                    if (msg.obj == closeMyLoop) {
                        recycle(msg);
                        return;
                    } else if (msg.getCallback() != null && (msg.getCallback() instanceof BA.B4ARunnable)) {
                        skipMessage(msg);
                    } else if (!onlyDrawableEvents || ((msg.obj != null && (msg.obj instanceof Drawable)) || msg.what < 100 || msg.what > 150)) {
                        msg.getTarget().dispatchMessage(msg);
                        recycle(msg);
                    } else {
                        skipMessage(msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void recycle(Message msg) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (recycleUnchecked != null) {
            recycleUnchecked.invoke(msg, new Object[0]);
        } else {
            msg.recycle();
        }
    }

    private static void skipMessage(Message msg) throws IllegalArgumentException, IllegalAccessException {
        whenF.set(msg, 0);
        if (flagsF != null) {
            flagsF.setInt(msg, flagsF.getInt(msg) & -2);
        }
        msg.getTarget().sendMessage(msg);
    }
}
