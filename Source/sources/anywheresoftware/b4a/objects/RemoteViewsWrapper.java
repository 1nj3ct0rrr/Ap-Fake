package anywheresoftware.b4a.objects;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.widget.RemoteViews;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.ConnectorUtils;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.keywords.LayoutValues;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.HashMap;

@BA.ShortName("RemoteViews")
public class RemoteViewsWrapper {
    protected RemoteViews current;
    protected String eventName;
    protected Parcel original;

    @BA.Hide
    public static RemoteViewsWrapper createRemoteViews(BA ba, int id, String layout, String eventName2) throws Exception {
        RemoteViews rv = new RemoteViews(BA.packageName, id);
        String layout2 = layout.toLowerCase(BA.cul);
        if (!layout2.endsWith(".bal")) {
            layout2 = String.valueOf(layout2) + ".bal";
        }
        InputStream in = BA.applicationContext.getAssets().open(layout2);
        DataInputStream din = new DataInputStream(in);
        int version = ConnectorUtils.readInt(din);
        for (int pos = ConnectorUtils.readInt(din); pos > 0; pos = (int) (((long) pos) - in.skip((long) pos))) {
        }
        String[] cache = null;
        if (version >= 3) {
            cache = new String[ConnectorUtils.readInt(din)];
            for (int i = 0; i < cache.length; i++) {
                cache[i] = ConnectorUtils.readString(din);
            }
        }
        int numberOfVariants = ConnectorUtils.readInt(din);
        for (int i2 = 0; i2 < numberOfVariants; i2++) {
            LayoutValues.readFromStream(din);
        }
        loadLayoutHelper(ba, ConnectorUtils.readMap(din, cache), rv);
        din.close();
        RemoteViewsWrapper rvw = new RemoteViewsWrapper();
        rvw.original = Parcel.obtain();
        rv.writeToParcel(rvw.original, 0);
        rvw.eventName = eventName2.toLowerCase(BA.cul);
        return rvw;
    }

    private static void loadLayoutHelper(BA ba, HashMap<String, Object> props, RemoteViews rv) throws Exception {
        String eventName2 = ((String) props.get("eventName")).toLowerCase(BA.cul);
        String name = ((String) props.get("name")).toLowerCase(BA.cul);
        HashMap<String, Object> kids = (HashMap) props.get(":kids");
        if (kids != null) {
            for (int i = 0; i < kids.size(); i++) {
                loadLayoutHelper(ba, (HashMap) kids.get(String.valueOf(i)), rv);
            }
        }
        if (ba.htSubs.containsKey(String.valueOf(eventName2) + "_click")) {
            Intent i2 = Common.getComponentIntent(ba, null);
            i2.putExtra("b4a_internal_event", String.valueOf(eventName2) + "_click");
            int id = getIdForView(ba, name);
            rv.setOnClickPendingIntent(id, PendingIntent.getService(ba.context, id, i2, 134217728));
        }
    }

    protected static int getIdForView(BA ba, String viewName) {
        try {
            return Class.forName(String.valueOf(BA.packageName) + ".R$id").getField(String.valueOf(ba.getClassNameWithoutPackage()) + "_" + viewName.toLowerCase(BA.cul)).getInt(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void checkNull() {
        if (this.original == null) {
            throw new RuntimeException("RemoteViews should be set by calling ConfigureHomeWidget.");
        } else if (this.current == null) {
            this.original.setDataPosition(0);
            this.current = new RemoteViews(this.original);
        }
    }

    public boolean HandleWidgetEvents(BA ba, Intent StartingIntent) {
        if (StartingIntent == null) {
            return false;
        }
        if (StartingIntent.hasExtra("b4a_internal_event")) {
            raiseEventWithDebuggingSupport(ba, StartingIntent.getStringExtra("b4a_internal_event"));
            return true;
        } else if (IntentWrapper.ACTION_APPWIDGET_UPDATE.equals(StartingIntent.getAction())) {
            raiseEventWithDebuggingSupport(ba, String.valueOf(this.eventName) + "_requestupdate");
            return true;
        } else if (!"android.appwidget.action.APPWIDGET_DISABLED".equals(StartingIntent.getAction())) {
            return false;
        } else {
            raiseEventWithDebuggingSupport(ba, String.valueOf(this.eventName) + "_disabled");
            return true;
        }
    }

    private void raiseEventWithDebuggingSupport(final BA ba, final String event) {
        if (BA.debugMode) {
            BA.handler.post(new BA.B4ARunnable() {
                /* class anywheresoftware.b4a.objects.RemoteViewsWrapper.AnonymousClass1 */

                public void run() {
                    ba.raiseEvent(this, event, new Object[0]);
                }
            });
        } else {
            ba.raiseEvent(this, event, new Object[0]);
        }
    }

    public void SetText(BA ba, String ViewName, CharSequence Text) {
        checkNull();
        this.current.setTextViewText(getIdForView(ba, ViewName), Text);
    }

    public void SetVisible(BA ba, String ViewName, boolean Visible) {
        checkNull();
        this.current.setViewVisibility(getIdForView(ba, ViewName), Visible ? 0 : 4);
    }

    public void SetImage(BA ba, String ImageViewName, Bitmap Image) {
        checkNull();
        this.current.setImageViewBitmap(getIdForView(ba, ImageViewName), Image);
    }

    public void SetTextColor(BA ba, String ViewName, int Color) {
        checkNull();
        this.current.setTextColor(getIdForView(ba, ViewName), Color);
    }

    public void SetTextSize(BA ba, String ViewName, float Size) {
        checkNull();
        this.current.setFloat(getIdForView(ba, ViewName), "setTextSize", Size);
    }

    public void SetProgress(BA ba, String ProgressBarName, int Progress) {
        checkNull();
        this.current.setInt(getIdForView(ba, ProgressBarName), "setProgress", Progress);
    }

    public void UpdateWidget(BA ba) throws ClassNotFoundException {
        checkNull();
        AppWidgetManager.getInstance(ba.context).updateAppWidget(new ComponentName(ba.context, Class.forName(String.valueOf(ba.className) + "$" + ba.getClassNameWithoutPackage() + "_BR")), this.current);
        this.current = null;
    }
}
