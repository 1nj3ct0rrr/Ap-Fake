package anywheresoftware.b4a.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.drawable.BitmapDrawable;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("TabHost")
public class TabHostWrapper extends ViewWrapper<TabHost> {
    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject) {
            setObject(new TabHost(ba.context, null));
        }
        super.innerInitialize(ba, eventName, true);
        initializeTabWidget(ba.context, (TabHost) getObject());
        if (ba.subExists(String.valueOf(eventName) + "_tabchanged")) {
            ((TabHost) getObject()).setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                /* class anywheresoftware.b4a.objects.TabHostWrapper.AnonymousClass1 */

                public void onTabChanged(String tabId) {
                    ba.raiseEvent2(TabHostWrapper.this.getObject(), false, String.valueOf(eventName) + "_tabchanged", false, new Object[0]);
                }
            });
        }
        MyContentFactory m = new MyContentFactory(new View(ba.context));
        TabHost.TabSpec ts = ((TabHost) getObject()).newTabSpec("~temp");
        ts.setContent(m);
        ts.setIndicator("");
        ((TabHost) getObject()).addTab(ts);
    }

    private static void initializeTabWidget(Context context, TabHost tabHost) {
        TabWidget tw = new TabWidget(context);
        LinearLayout ll = new LinearLayout(context);
        int pad = Common.DipToCurrent(5);
        ll.setPadding(pad, pad, pad, pad);
        ll.setOrientation(1);
        tw.setId(16908307);
        ll.addView(tw, new ViewGroup.LayoutParams(-1, -2));
        FrameLayout fl = new FrameLayout(context);
        fl.setId(16908305);
        fl.setPadding(pad, pad, pad, pad);
        ll.addView(fl, new ViewGroup.LayoutParams(-1, -1));
        tabHost.addView(ll, new ViewGroup.LayoutParams(-1, -1));
        tabHost.setup();
    }

    public void AddTab2(String Title, View View) {
        if (((TabHost) getObject()).getCurrentTabTag().equals("~temp")) {
            ((TabHost) getObject()).clearAllTabs();
        }
        MyContentFactory m = new MyContentFactory(View);
        TabHost.TabSpec ts = ((TabHost) getObject()).newTabSpec("");
        ts.setContent(m);
        ts.setIndicator(Title);
        ((TabHost) getObject()).addTab(ts);
    }

    @BA.RaisesSynchronousEvents
    public void AddTab(BA ba, String Title, String LayoutFile) throws Exception {
        AddTab2(Title, createPanelForLayoutFile(ba, LayoutFile));
    }

    private View createPanelForLayoutFile(BA ba, String LayoutFile) throws Exception {
        PanelWrapper pw = new PanelWrapper();
        pw.Initialize(ba, "");
        int yfix = 84;
        if (BA.applicationContext.getApplicationInfo().targetSdkVersion >= 11 && Build.VERSION.SDK_INT >= 11 && Common.GetDeviceLayoutValues(ba).getApproximateScreenSize() < 5.0d) {
            yfix = 68;
        }
        ((ViewGroup) pw.getObject()).setLayoutParams(new ViewGroup.LayoutParams(getWidth() - Common.DipToCurrent(20), getHeight() - Common.DipToCurrent(yfix)));
        pw.LoadLayout(LayoutFile, ba);
        return (View) pw.getObject();
    }

    public void AddTabWithIcon2(String Title, Bitmap DefaultBitmap, Bitmap SelectedBitmap, View View) {
        if (((TabHost) getObject()).getCurrentTabTag().equals("~temp")) {
            ((TabHost) getObject()).clearAllTabs();
        }
        MyContentFactory m = new MyContentFactory(View);
        TabHost.TabSpec ts = ((TabHost) getObject()).newTabSpec("");
        ts.setContent(m);
        BitmapDrawable bd1 = new BitmapDrawable();
        bd1.Initialize(DefaultBitmap);
        BitmapDrawable bd2 = new BitmapDrawable();
        bd2.Initialize(SelectedBitmap);
        StateListDrawable sd = new StateListDrawable();
        sd.addState(new int[]{16842913}, (Drawable) bd2.getObject());
        sd.addState(new int[0], (Drawable) bd1.getObject());
        ts.setIndicator(Title, sd);
        ((TabHost) getObject()).addTab(ts);
    }

    @BA.RaisesSynchronousEvents
    public void AddTabWithIcon(BA ba, String Title, Bitmap DefaultBitmap, Bitmap SelectedBitmap, String LayoutFile) throws Exception {
        AddTabWithIcon2(Title, DefaultBitmap, SelectedBitmap, createPanelForLayoutFile(ba, LayoutFile));
    }

    public int getCurrentTab() {
        return ((TabHost) getObject()).getCurrentTab();
    }

    @BA.RaisesSynchronousEvents
    public void setCurrentTab(int Index) {
        ((TabHost) getObject()).setCurrentTab(Index);
    }

    public int getTabCount() {
        return ((TabHost) getObject()).getTabWidget().getTabCount();
    }

    /* access modifiers changed from: private */
    public static class MyContentFactory implements TabHost.TabContentFactory {
        private View view;

        public MyContentFactory(View view2) {
            this.view = view2;
        }

        public View createTabContent(String tag) {
            return this.view;
        }
    }

    @BA.Hide
    public static class MyTabHost extends TabHost {
        public MyTabHost(Context context) {
            super(context, null);
        }
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        boolean firstTime = false;
        if (prev == null) {
            firstTime = true;
            prev = ViewWrapper.buildNativeView((Context) tag, MyTabHost.class, props, designer);
        }
        TabHost th = (TabHost) ViewWrapper.build(prev, props, designer);
        if (designer && firstTime) {
            initializeTabWidget((Context) tag, th);
            TextView v = new TextView((Context) tag);
            v.setText("This is an example page.\nTab pages should be added programmatically.");
            for (int i = 1; i <= 3; i++) {
                MyContentFactory m = new MyContentFactory(v);
                TabHost.TabSpec ts = th.newTabSpec("");
                ts.setContent(m);
                ts.setIndicator("Page " + i);
                th.addTab(ts);
            }
        }
        return th;
    }
}
