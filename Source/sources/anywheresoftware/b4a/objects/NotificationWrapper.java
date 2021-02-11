package anywheresoftware.b4a.objects;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.Common;

@BA.ShortName("Notification")
public class NotificationWrapper extends AbsObjectWrapper<Notification> {
    private static int pendingId = 1;

    public void Initialize() {
        setObject(new Notification());
        ((Notification) getObject()).defaults |= -1;
    }

    public void setVibrate(boolean v) {
        setValue(v, 2);
    }

    public void setSound(boolean v) {
        setValue(v, 1);
    }

    public void setLight(boolean v) {
        setValue(v, 4);
    }

    private void setValue(boolean v, int Default) {
        if (v) {
            ((Notification) getObject()).defaults |= Default;
            return;
        }
        ((Notification) getObject()).defaults &= Default ^ -1;
    }

    public void setAutoCancel(boolean v) {
        setFlag(v, 16);
    }

    public void setInsistent(boolean v) {
        setFlag(v, 4);
    }

    public void setOnGoingEvent(boolean v) {
        setFlag(v, 2);
    }

    public int getNumber() {
        return ((Notification) getObject()).number;
    }

    public void setNumber(int v) {
        ((Notification) getObject()).number = v;
    }

    private void setFlag(boolean v, int Flag) {
        if (v) {
            ((Notification) getObject()).flags |= Flag;
            return;
        }
        ((Notification) getObject()).flags &= Flag ^ -1;
    }

    public void setIcon(String s) {
        ((Notification) getObject()).icon = BA.applicationContext.getResources().getIdentifier(s, "drawable", BA.packageName);
    }

    public void SetInfo(BA ba, String Title, String Body, Object Activity) throws ClassNotFoundException {
        SetInfo2(ba, Title, Body, null, Activity);
    }

    public void SetInfo2(BA ba, String Title, String Body, String Tag, Object Activity) throws ClassNotFoundException {
        int i;
        ((Notification) getObject()).when = System.currentTimeMillis();
        Intent i2 = Common.getComponentIntent(ba, Activity);
        i2.addFlags(268435456);
        i2.addFlags(131072);
        if (Tag != null) {
            i2.putExtra("Notification_Tag", Tag);
        }
        Context context = ba.context;
        if (Tag == null) {
            i = 0;
        } else {
            i = pendingId;
            pendingId = i + 1;
        }
        ((Notification) getObject()).setLatestEventInfo(ba.context, Title, Body, PendingIntent.getActivity(context, i, i2, 134217728));
    }

    public void Notify(int Id) {
        ((NotificationManager) BA.applicationContext.getSystemService("notification")).notify(Id, (Notification) getObject());
    }

    public void Cancel(int Id) {
        ((NotificationManager) BA.applicationContext.getSystemService("notification")).cancel(Id);
    }
}
