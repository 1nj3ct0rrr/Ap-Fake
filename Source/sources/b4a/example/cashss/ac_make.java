package b4a.example.cashss;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.B4AMenuItem;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.Msgbox;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.keywords.constants.Colors;
import anywheresoftware.b4a.keywords.constants.Gravity;
import anywheresoftware.b4a.keywords.constants.KeyCodes;
import anywheresoftware.b4a.keywords.constants.TypefaceWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import anywheresoftware.b4a.objects.CSBuilder;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.objects.SpinnerWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.drawable.CanvasWrapper;
import anywheresoftware.b4a.objects.streams.File;
import de.amberhome.objects.CardViewWrapper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class ac_make extends Activity implements B4AActivity {
    public static String _incard = "";
    public static String _outcard = "";
    public static String _str_in = "";
    public static String _str_mablagh = "";
    public static String _str_marja = "";
    public static String _str_marjabank = "";
    public static String _str_name = "";
    public static String _str_out = "";
    public static String _str_peygiri = "";
    public static String _str_tarikh = "";
    static boolean afterFirstLayout;
    public static final boolean fullScreen = false;
    public static final boolean includeTitle = false;
    static boolean isFirst = true;
    public static ac_make mostCurrent;
    public static WeakReference<Activity> previousOne;
    public static BA processBA;
    private static boolean processGlobalsRun = false;
    public Common __c = null;
    public ac_make2 _ac_make2 = null;
    public ac_make3 _ac_make3 = null;
    ActivityWrapper _activity;
    public CardViewWrapper _card_crash = null;
    public ImageViewWrapper _img_in = null;
    public ImageViewWrapper _img_out = null;
    public LabelWrapper[] _lbl = null;
    public LabelWrapper _lbl_1 = null;
    public LabelWrapper _lbl_10 = null;
    public LabelWrapper _lbl_11 = null;
    public LabelWrapper _lbl_12 = null;
    public LabelWrapper _lbl_13 = null;
    public LabelWrapper _lbl_2 = null;
    public LabelWrapper _lbl_3 = null;
    public LabelWrapper _lbl_4 = null;
    public LabelWrapper _lbl_5 = null;
    public LabelWrapper _lbl_6 = null;
    public LabelWrapper _lbl_7 = null;
    public LabelWrapper _lbl_8 = null;
    public LabelWrapper _lbl_9 = null;
    public main _main = null;
    public PanelWrapper _pnl_crash = null;
    public PanelWrapper _pnl_toolbar = null;
    public SpinnerWrapper _spinner1 = null;
    public SpinnerWrapper _spinner2 = null;
    public starter _starter = null;
    BA activityBA;
    BALayout layout;
    ArrayList<B4AMenuItem> menuItems;
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;

    public void onCreate(Bundle bundle) {
        Activity activity;
        super.onCreate(bundle);
        if (isFirst) {
            processBA = new BA(getApplicationContext(), null, null, "b4a.example.cashss", "b4a.example.cashss.ac_make");
            processBA.loadHtSubs(getClass());
            BALayout.setDeviceScale(getApplicationContext().getResources().getDisplayMetrics().density);
        } else if (!(previousOne == null || (activity = previousOne.get()) == null || activity == this)) {
            BA.LogInfo("Killing previous instance (ac_make).");
            activity.finish();
        }
        processBA.runHook("oncreate", this, null);
        getWindow().requestFeature(1);
        mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
        this.layout = new BALayout(this);
        setContentView(this.layout);
        afterFirstLayout = false;
        WaitForLayout waitForLayout = new WaitForLayout();
        if (ServiceHelper.StarterHelper.startFromActivity(processBA, waitForLayout, false)) {
            BA.handler.postDelayed(waitForLayout, 5);
        }
    }

    static class WaitForLayout implements Runnable {
        WaitForLayout() {
        }

        public void run() {
            if (ac_make.afterFirstLayout || ac_make.mostCurrent == null) {
                return;
            }
            if (ac_make.mostCurrent.layout.getWidth() == 0) {
                BA.handler.postDelayed(this, 5);
                return;
            }
            ac_make.mostCurrent.layout.getLayoutParams().height = ac_make.mostCurrent.layout.getHeight();
            ac_make.mostCurrent.layout.getLayoutParams().width = ac_make.mostCurrent.layout.getWidth();
            ac_make.afterFirstLayout = true;
            ac_make.mostCurrent.afterFirstLayout();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void afterFirstLayout() {
        if (this == mostCurrent) {
            this.activityBA = new BA(this, this.layout, processBA, "b4a.example.cashss", "b4a.example.cashss.ac_make");
            processBA.sharedProcessBA.activityBA = new WeakReference<>(this.activityBA);
            ViewWrapper.lastId = 0;
            this._activity = new ActivityWrapper(this.activityBA, "activity");
            Msgbox.isDismissing = false;
            if (BA.isShellModeRuntimeCheck(processBA)) {
                if (isFirst) {
                    processBA.raiseEvent2(null, true, "SHELL", false, new Object[0]);
                }
                processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.cashss.ac_make", processBA, this.activityBA, this._activity, Float.valueOf(Common.Density), mostCurrent);
                this._activity.reinitializeForShell(this.activityBA, "activity");
            }
            initializeProcessGlobals();
            initializeGlobals();
            BA.LogInfo("** Activity (ac_make) Create, isFirst = " + isFirst + " **");
            processBA.raiseEvent2(null, true, "activity_create", false, Boolean.valueOf(isFirst));
            isFirst = false;
            if (this == mostCurrent) {
                processBA.setActivityPaused(false);
                BA.LogInfo("** Activity (ac_make) Resume **");
                processBA.raiseEvent(null, "activity_resume", new Object[0]);
                if (Build.VERSION.SDK_INT >= 11) {
                    try {
                        Activity.class.getMethod("invalidateOptionsMenu", new Class[0]).invoke(this, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override // anywheresoftware.b4a.B4AActivity
    public void addMenuItem(B4AMenuItem b4AMenuItem) {
        if (this.menuItems == null) {
            this.menuItems = new ArrayList<>();
        }
        this.menuItems.add(b4AMenuItem);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", Boolean.TYPE).invoke(getClass().getMethod("getActionBar", new Class[0]).invoke(this, new Object[0]), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[]{menu})) {
            return true;
        }
        if (this.menuItems == null) {
            return false;
        }
        Iterator<B4AMenuItem> it = this.menuItems.iterator();
        while (it.hasNext()) {
            B4AMenuItem next = it.next();
            MenuItem add = menu.add(next.title);
            if (next.drawable != null) {
                add.setIcon(next.drawable);
            }
            if (Build.VERSION.SDK_INT >= 11) {
                try {
                    if (next.addToBar) {
                        MenuItem.class.getMethod("setShowAsAction", Integer.TYPE).invoke(add, 1);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            add.setOnMenuItemClickListener(new B4AMenuItemsClickListener(next.eventName.toLowerCase(BA.cul)));
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        processBA.raiseEvent(null, "activity_actionbarhomeclick", new Object[0]);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        processBA.runHook("onprepareoptionsmenu", this, new Object[]{menu});
        return true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        processBA.runHook("onstart", this, null);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        processBA.runHook("onstop", this, null);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (processBA.subExists("activity_windowfocuschanged")) {
            processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, Boolean.valueOf(z));
        }
    }

    private class B4AMenuItemsClickListener implements MenuItem.OnMenuItemClickListener {
        private final String eventName;

        public B4AMenuItemsClickListener(String str) {
            this.eventName = str;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            ac_make.processBA.raiseEventFromUI(menuItem.getTitle(), this.eventName + "_click", new Object[0]);
            return true;
        }
    }

    public static Class<?> getObject() {
        return ac_make.class;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (processBA.runHook("onkeydown", this, new Object[]{Integer.valueOf(i), keyEvent})) {
            return true;
        }
        if (this.onKeySubExist == null) {
            this.onKeySubExist = Boolean.valueOf(processBA.subExists("activity_keypress"));
        }
        if (this.onKeySubExist.booleanValue()) {
            if (i == 4 && Build.VERSION.SDK_INT >= 18) {
                HandleKeyDelayed handleKeyDelayed = new HandleKeyDelayed();
                handleKeyDelayed.kc = i;
                BA.handler.post(handleKeyDelayed);
                return true;
            } else if (new HandleKeyDelayed().runDirectly(i)) {
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    private class HandleKeyDelayed implements Runnable {
        int kc;

        private HandleKeyDelayed() {
        }

        public void run() {
            runDirectly(this.kc);
        }

        public boolean runDirectly(int i) {
            Boolean bool = (Boolean) ac_make.processBA.raiseEvent2(ac_make.this._activity, false, "activity_keypress", false, Integer.valueOf(i));
            if (bool == null || bool.booleanValue()) {
                return true;
            }
            if (i != 4) {
                return false;
            }
            ac_make.this.finish();
            return true;
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (processBA.runHook("onkeyup", this, new Object[]{Integer.valueOf(i), keyEvent})) {
            return true;
        }
        if (this.onKeyUpSubExist == null) {
            this.onKeyUpSubExist = Boolean.valueOf(processBA.subExists("activity_keyup"));
        }
        if (this.onKeyUpSubExist.booleanValue()) {
            Boolean bool = (Boolean) processBA.raiseEvent2(this._activity, false, "activity_keyup", false, Integer.valueOf(i));
            if (bool == null || bool.booleanValue()) {
                return true;
            }
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[]{intent});
    }

    public void onPause() {
        super.onPause();
        if (this._activity != null) {
            Msgbox.dismiss(true);
            BA.LogInfo("** Activity (ac_make) Pause, UserClosed = " + this.activityBA.activity.isFinishing() + " **");
            processBA.raiseEvent2(this._activity, true, "activity_pause", false, Boolean.valueOf(this.activityBA.activity.isFinishing()));
            processBA.setActivityPaused(true);
            mostCurrent = null;
            if (!this.activityBA.activity.isFinishing()) {
                previousOne = new WeakReference<>(this);
            }
            Msgbox.isDismissing = false;
            processBA.runHook("onpause", this, null);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        previousOne = null;
        processBA.runHook("ondestroy", this, null);
    }

    public void onResume() {
        super.onResume();
        mostCurrent = this;
        Msgbox.isDismissing = false;
        if (this.activityBA != null) {
            BA.handler.post(new ResumeMessage(mostCurrent));
        }
        processBA.runHook("onresume", this, null);
    }

    private static class ResumeMessage implements Runnable {
        private final WeakReference<Activity> activity;

        public ResumeMessage(Activity activity2) {
            this.activity = new WeakReference<>(activity2);
        }

        public void run() {
            if (ac_make.mostCurrent != null && ac_make.mostCurrent == this.activity.get()) {
                ac_make.processBA.setActivityPaused(false);
                BA.LogInfo("** Activity (ac_make) Resume **");
                ac_make.processBA.raiseEvent(ac_make.mostCurrent._activity, "activity_resume", null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        processBA.onActivityResult(i, i2, intent);
        processBA.runHook("onactivityresult", this, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
    }

    private static void initializeGlobals() {
        processBA.raiseEvent2(null, true, "globals", false, null);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            Object[] objArr = new Object[2];
            objArr[0] = strArr[i2];
            objArr[1] = Boolean.valueOf(iArr[i2] == 0);
            processBA.raiseEventFromDifferentThread(null, null, 0, "activity_permissionresult", true, objArr);
        }
    }

    public static void initializeProcessGlobals() {
        try {
            Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals", new Class[0]).invoke(null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String _activity_create(boolean z) throws Exception {
        TypefaceWrapper typefaceWrapper = new TypefaceWrapper();
        TypefaceWrapper typefaceWrapper2 = Common.Typeface;
        typefaceWrapper.setObject(TypefaceWrapper.LoadFromAssets("nyekan.ttf"));
        for (int i = 0; i <= 25; i = i + 0 + 1) {
            mostCurrent._lbl[i].Initialize(mostCurrent.activityBA, "lbls");
            LabelWrapper labelWrapper = mostCurrent._lbl[i];
            Colors colors = Common.Colors;
            labelWrapper.setTextColor(Colors.Black);
            mostCurrent._lbl[i].setTextSize(13.0f);
            LabelWrapper labelWrapper2 = mostCurrent._lbl[i];
            Gravity gravity = Common.Gravity;
            labelWrapper2.setGravity(5);
            mostCurrent._lbl[i].setTypeface((Typeface) typefaceWrapper.getObject());
        }
        ActivityWrapper activityWrapper = mostCurrent._activity;
        Colors colors2 = Common.Colors;
        activityWrapper.setColor(Colors.RGB(77, 77, 79));
        mostCurrent._pnl_toolbar.Initialize(mostCurrent.activityBA, "");
        PanelWrapper panelWrapper = mostCurrent._pnl_toolbar;
        Colors colors3 = Common.Colors;
        panelWrapper.setColor(Colors.RGB(48, 48, 48));
        mostCurrent._activity.AddView((View) mostCurrent._pnl_toolbar.getObject(), 0, 0, Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(50));
        CanvasWrapper.BitmapWrapper bitmapWrapper = new CanvasWrapper.BitmapWrapper();
        File file = Common.File;
        bitmapWrapper.Initialize(File.getDirAssets(), "topimg.jpg");
        ImageViewWrapper imageViewWrapper = new ImageViewWrapper();
        imageViewWrapper.Initialize(mostCurrent.activityBA, "");
        Gravity gravity2 = Common.Gravity;
        imageViewWrapper.setGravity(Gravity.FILL);
        imageViewWrapper.setBitmap((Bitmap) bitmapWrapper.getObject());
        mostCurrent._pnl_toolbar.AddView((View) imageViewWrapper.getObject(), 0, 0, Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(50));
        mostCurrent._card_crash.Initialize(mostCurrent.activityBA, "");
        CardViewWrapper cardViewWrapper = mostCurrent._card_crash;
        Colors colors4 = Common.Colors;
        cardViewWrapper.setColor(Colors.RGB(235, 235, 235));
        mostCurrent._card_crash.setCornerRadius(5.0f);
        mostCurrent._pnl_crash.Initialize(mostCurrent.activityBA, "");
        PanelWrapper panelWrapper2 = mostCurrent._pnl_crash;
        Colors colors5 = Common.Colors;
        panelWrapper2.setColor(Colors.RGB(235, 235, 235));
        mostCurrent._activity.AddView((View) mostCurrent._pnl_crash.getObject(), Common.PerXToCurrent(5.0f, mostCurrent.activityBA), Common.PerXToCurrent(2.0f, mostCurrent.activityBA) + Common.DipToCurrent(50), Common.PerXToCurrent(100.0f, mostCurrent.activityBA) - Common.PerXToCurrent(10.0f, mostCurrent.activityBA), Common.PerYToCurrent(100.0f, mostCurrent.activityBA) - Common.PerXToCurrent(60.0f, mostCurrent.activityBA));
        PanelWrapper panelWrapper3 = new PanelWrapper();
        panelWrapper3.Initialize(mostCurrent.activityBA, "");
        Colors colors6 = Common.Colors;
        panelWrapper3.setColor(Colors.RGB(20, 173, 59));
        mostCurrent._pnl_crash.AddView((View) panelWrapper3.getObject(), -Common.DipToCurrent(5), -Common.DipToCurrent(1), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(40));
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[1].getObject(), -Common.DipToCurrent(20), Common.DipToCurrent(8), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(40));
        LabelWrapper labelWrapper3 = mostCurrent._lbl[1];
        Gravity gravity3 = Common.Gravity;
        labelWrapper3.setGravity(1);
        mostCurrent._lbl[1].setText(BA.ObjectToCharSequence("تراکنش موفق"));
        LabelWrapper labelWrapper4 = mostCurrent._lbl[1];
        Colors colors7 = Common.Colors;
        labelWrapper4.setTextColor(-1);
        mostCurrent._lbl[1].setTextSize(16.0f);
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[2].getObject(), -Common.DipToCurrent(20), Common.DipToCurrent(50), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(50));
        mostCurrent._lbl[2].setText(BA.ObjectToCharSequence(" انتقال وجه کارت به کارت"));
        mostCurrent._lbl[2].setTextSize(18.0f);
        LabelWrapper labelWrapper5 = mostCurrent._lbl[2];
        Gravity gravity4 = Common.Gravity;
        labelWrapper5.setGravity(1);
        PanelWrapper panelWrapper4 = new PanelWrapper();
        panelWrapper4.Initialize(mostCurrent.activityBA, "");
        panelWrapper4.setColor(-2368549);
        mostCurrent._pnl_crash.AddView((View) panelWrapper4.getObject(), 0, Common.DipToCurrent(90), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(40));
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[9].getObject(), Common.DipToCurrent(5), Common.DipToCurrent(98), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(40));
        LabelWrapper labelWrapper6 = mostCurrent._lbl[9];
        Gravity gravity5 = Common.Gravity;
        labelWrapper6.setGravity(3);
        mostCurrent._lbl[9].setText(BA.ObjectToCharSequence(_str_tarikh));
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[3].getObject(), -Common.DipToCurrent(60), Common.DipToCurrent(98), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(40));
        mostCurrent._lbl[3].setText(BA.ObjectToCharSequence("تاریخ"));
        LabelWrapper labelWrapper7 = mostCurrent._lbl[3];
        Gravity gravity6 = Common.Gravity;
        labelWrapper7.setGravity(5);
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[10].getObject(), Common.DipToCurrent(5), Common.DipToCurrent(138), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(30));
        LabelWrapper labelWrapper8 = mostCurrent._lbl[10];
        Gravity gravity7 = Common.Gravity;
        labelWrapper8.setGravity(3);
        LabelWrapper labelWrapper9 = mostCurrent._lbl[10];
        Colors colors8 = Common.Colors;
        labelWrapper9.setTextColor(Colors.RGB(20, 173, 59));
        mostCurrent._lbl[10].setText(BA.ObjectToCharSequence(_str_mablagh));
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[4].getObject(), -Common.DipToCurrent(60), Common.DipToCurrent(138), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(30));
        mostCurrent._lbl[4].setText(BA.ObjectToCharSequence("مبلغ"));
        LabelWrapper labelWrapper10 = mostCurrent._lbl[4];
        Gravity gravity8 = Common.Gravity;
        labelWrapper10.setGravity(5);
        PanelWrapper panelWrapper5 = new PanelWrapper();
        panelWrapper5.Initialize(mostCurrent.activityBA, "");
        panelWrapper5.setColor(-2368549);
        mostCurrent._pnl_crash.AddView((View) panelWrapper5.getObject(), 0, Common.DipToCurrent(170), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(40));
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[11].getObject(), Common.DipToCurrent(40), Common.DipToCurrent(178), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(40));
        LabelWrapper labelWrapper11 = mostCurrent._lbl[11];
        Gravity gravity9 = Common.Gravity;
        labelWrapper11.setGravity(3);
        mostCurrent._lbl[11].setText(BA.ObjectToCharSequence(_str_in));
        PanelWrapper panelWrapper6 = new PanelWrapper();
        panelWrapper6.Initialize(mostCurrent.activityBA, "");
        Colors colors9 = Common.Colors;
        panelWrapper6.setColor(-1);
        mostCurrent._pnl_crash.AddView((View) panelWrapper6.getObject(), Common.DipToCurrent(7), Common.DipToCurrent(176), Common.DipToCurrent(30), Common.DipToCurrent(30));
        CanvasWrapper.BitmapWrapper bitmapWrapper2 = new CanvasWrapper.BitmapWrapper();
        File file2 = Common.File;
        bitmapWrapper2.Initialize(File.getDirAssets(), _incard + ".png");
        ImageViewWrapper imageViewWrapper2 = new ImageViewWrapper();
        imageViewWrapper2.Initialize(mostCurrent.activityBA, "");
        Gravity gravity10 = Common.Gravity;
        imageViewWrapper2.setGravity(Gravity.FILL);
        imageViewWrapper2.setBitmap((Bitmap) bitmapWrapper2.getObject());
        panelWrapper6.AddView((View) imageViewWrapper2.getObject(), 0, 0, panelWrapper6.getWidth(), panelWrapper6.getHeight());
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[5].getObject(), -Common.DipToCurrent(60), Common.DipToCurrent(178), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(40));
        mostCurrent._lbl[5].setText(BA.ObjectToCharSequence("کارت مبدا"));
        LabelWrapper labelWrapper12 = mostCurrent._lbl[5];
        Gravity gravity11 = Common.Gravity;
        labelWrapper12.setGravity(5);
        PanelWrapper panelWrapper7 = new PanelWrapper();
        panelWrapper7.Initialize(mostCurrent.activityBA, "");
        Colors colors10 = Common.Colors;
        panelWrapper7.setColor(-1);
        mostCurrent._pnl_crash.AddView((View) panelWrapper7.getObject(), Common.DipToCurrent(7), Common.DipToCurrent(216), Common.DipToCurrent(30), Common.DipToCurrent(30));
        CanvasWrapper.BitmapWrapper bitmapWrapper3 = new CanvasWrapper.BitmapWrapper();
        File file3 = Common.File;
        bitmapWrapper3.Initialize(File.getDirAssets(), _outcard + ".png");
        ImageViewWrapper imageViewWrapper3 = new ImageViewWrapper();
        imageViewWrapper3.Initialize(mostCurrent.activityBA, "");
        Gravity gravity12 = Common.Gravity;
        imageViewWrapper3.setGravity(Gravity.FILL);
        imageViewWrapper3.setBitmap((Bitmap) bitmapWrapper3.getObject());
        panelWrapper7.AddView((View) imageViewWrapper3.getObject(), 0, 0, panelWrapper7.getWidth(), panelWrapper7.getHeight());
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[12].getObject(), Common.DipToCurrent(40), Common.DipToCurrent(218), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(50));
        LabelWrapper labelWrapper13 = mostCurrent._lbl[12];
        Gravity gravity13 = Common.Gravity;
        labelWrapper13.setGravity(3);
        mostCurrent._lbl[12].setText(BA.ObjectToCharSequence(_str_out));
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[6].getObject(), -Common.DipToCurrent(60), Common.DipToCurrent(218), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(50));
        mostCurrent._lbl[6].setText(BA.ObjectToCharSequence("کارت مقصد"));
        LabelWrapper labelWrapper14 = mostCurrent._lbl[6];
        Gravity gravity14 = Common.Gravity;
        labelWrapper14.setGravity(5);
        PanelWrapper panelWrapper8 = new PanelWrapper();
        panelWrapper8.Initialize(mostCurrent.activityBA, "");
        panelWrapper8.setColor(-2368549);
        mostCurrent._pnl_crash.AddView((View) panelWrapper8.getObject(), 0, Common.DipToCurrent(250), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(40));
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[13].getObject(), Common.DipToCurrent(5), Common.DipToCurrent(258), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(30));
        LabelWrapper labelWrapper15 = mostCurrent._lbl[13];
        Gravity gravity15 = Common.Gravity;
        labelWrapper15.setGravity(3);
        mostCurrent._lbl[13].setText(BA.ObjectToCharSequence(_str_name));
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[7].getObject(), -Common.DipToCurrent(60), Common.DipToCurrent(258), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(30));
        mostCurrent._lbl[7].setText(BA.ObjectToCharSequence("بنام"));
        LabelWrapper labelWrapper16 = mostCurrent._lbl[7];
        Gravity gravity16 = Common.Gravity;
        labelWrapper16.setGravity(5);
        CSBuilder cSBuilder = new CSBuilder();
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[15].getObject(), Common.PerXToCurrent(32.0f, mostCurrent.activityBA), Common.DipToCurrent(303), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(50));
        LabelWrapper labelWrapper17 = mostCurrent._lbl[15];
        Gravity gravity17 = Common.Gravity;
        labelWrapper17.setGravity(3);
        LabelWrapper labelWrapper18 = mostCurrent._lbl[15];
        CSBuilder Initialize = cSBuilder.Initialize();
        TypefaceWrapper typefaceWrapper3 = Common.Typeface;
        labelWrapper18.setText(BA.ObjectToCharSequence(Initialize.Typeface(TypefaceWrapper.getMATERIALICONS()).Append(BA.ObjectToCharSequence(Character.valueOf(Common.Chr(57677)))).PopAll().getObject()));
        mostCurrent._lbl[15].setTextSize(17.0f);
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[14].getObject(), Common.DipToCurrent(5), Common.DipToCurrent(298), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(50));
        LabelWrapper labelWrapper19 = mostCurrent._lbl[14];
        Gravity gravity18 = Common.Gravity;
        labelWrapper19.setGravity(3);
        mostCurrent._lbl[14].setText(BA.ObjectToCharSequence(_str_marja + " "));
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[8].getObject(), -Common.DipToCurrent(60), Common.DipToCurrent(295), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(50));
        mostCurrent._lbl[8].setText(BA.ObjectToCharSequence("شماره مرجع"));
        LabelWrapper labelWrapper20 = mostCurrent._lbl[8];
        Gravity gravity19 = Common.Gravity;
        labelWrapper20.setGravity(5);
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[16].getObject(), -Common.DipToCurrent(20), Common.DipToCurrent(330), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(50));
        mostCurrent._lbl[16].setText(BA.ObjectToCharSequence("شماره مرجع بانک : " + _str_marjabank));
        LabelWrapper labelWrapper21 = mostCurrent._lbl[16];
        Gravity gravity20 = Common.Gravity;
        labelWrapper21.setGravity(1);
        mostCurrent._lbl[16].setTextSize(12.0f);
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[17].getObject(), -Common.DipToCurrent(20), Common.DipToCurrent(350), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(50));
        mostCurrent._lbl[17].setText(BA.ObjectToCharSequence("کد پیگیری بانک : " + _str_peygiri));
        LabelWrapper labelWrapper22 = mostCurrent._lbl[17];
        Gravity gravity21 = Common.Gravity;
        labelWrapper22.setGravity(1);
        mostCurrent._lbl[17].setTextSize(12.0f);
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[18].getObject(), -Common.DipToCurrent(20), Common.DipToCurrent(375), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(70));
        mostCurrent._lbl[18].setText(BA.ObjectToCharSequence("سفر به اروپا و روزانه یک گوشی هوشمند ویژه استفاده از خدمات"));
        LabelWrapper labelWrapper23 = mostCurrent._lbl[18];
        Gravity gravity22 = Common.Gravity;
        labelWrapper23.setGravity(1);
        mostCurrent._lbl[18].setTextSize(11.0f);
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[19].getObject(), -Common.DipToCurrent(20), Common.DipToCurrent(395), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(70));
        mostCurrent._lbl[19].setText(BA.ObjectToCharSequence("اپلیکیشن آپ,اطلاعات بیشتر در تلگرام آپ"));
        LabelWrapper labelWrapper24 = mostCurrent._lbl[19];
        Gravity gravity23 = Common.Gravity;
        labelWrapper24.setGravity(1);
        mostCurrent._lbl[19].setTextSize(11.0f);
        mostCurrent._pnl_crash.AddView((View) mostCurrent._lbl[20].getObject(), -Common.DipToCurrent(20), Common.DipToCurrent(415), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(70));
        mostCurrent._lbl[20].setText(BA.ObjectToCharSequence("https://t.me/AP_733"));
        LabelWrapper labelWrapper25 = mostCurrent._lbl[20];
        Gravity gravity24 = Common.Gravity;
        labelWrapper25.setGravity(1);
        mostCurrent._lbl[20].setTextSize(12.0f);
        mostCurrent._lbl[20].setTextColor(-2610120);
        PanelWrapper panelWrapper9 = new PanelWrapper();
        panelWrapper9.Initialize(mostCurrent.activityBA, "");
        panelWrapper9.setColor(-2610120);
        mostCurrent._pnl_crash.AddView((View) panelWrapper9.getObject(), Common.DipToCurrent(160), Common.DipToCurrent(428), Common.DipToCurrent(120), Common.DipToCurrent(2));
        CanvasWrapper.BitmapWrapper bitmapWrapper4 = new CanvasWrapper.BitmapWrapper();
        File file4 = Common.File;
        bitmapWrapper4.Initialize(File.getDirAssets(), "asanpardakht.png");
        ImageViewWrapper imageViewWrapper4 = new ImageViewWrapper();
        imageViewWrapper4.Initialize(mostCurrent.activityBA, "");
        Gravity gravity25 = Common.Gravity;
        imageViewWrapper4.setGravity(Gravity.FILL);
        imageViewWrapper4.setBitmap((Bitmap) bitmapWrapper4.getObject());
        mostCurrent._pnl_crash.AddView((View) imageViewWrapper4.getObject(), Common.PerXToCurrent(50.0f, mostCurrent.activityBA) - Common.DipToCurrent(70), mostCurrent._pnl_crash.getHeight() - Common.DipToCurrent(65), Common.DipToCurrent(45), Common.DipToCurrent(45));
        CanvasWrapper.BitmapWrapper bitmapWrapper5 = new CanvasWrapper.BitmapWrapper();
        File file5 = Common.File;
        bitmapWrapper5.Initialize(File.getDirAssets(), "ic_bank_melli.png");
        ImageViewWrapper imageViewWrapper5 = new ImageViewWrapper();
        imageViewWrapper5.Initialize(mostCurrent.activityBA, "");
        Gravity gravity26 = Common.Gravity;
        imageViewWrapper5.setGravity(Gravity.FILL);
        imageViewWrapper5.setBitmap((Bitmap) bitmapWrapper5.getObject());
        mostCurrent._pnl_crash.AddView((View) imageViewWrapper5.getObject(), Common.PerXToCurrent(50.0f, mostCurrent.activityBA), mostCurrent._pnl_crash.getHeight() - Common.DipToCurrent(65), Common.DipToCurrent(45), Common.DipToCurrent(45));
        CanvasWrapper.BitmapWrapper bitmapWrapper6 = new CanvasWrapper.BitmapWrapper();
        File file6 = Common.File;
        bitmapWrapper6.Initialize(File.getDirAssets(), "foterrs.jpg");
        ImageViewWrapper imageViewWrapper6 = new ImageViewWrapper();
        imageViewWrapper6.Initialize(mostCurrent.activityBA, "");
        Gravity gravity27 = Common.Gravity;
        imageViewWrapper6.setGravity(Gravity.FILL);
        imageViewWrapper6.setBitmap((Bitmap) bitmapWrapper6.getObject());
        mostCurrent._activity.AddView((View) imageViewWrapper6.getObject(), 0, Common.PerYToCurrent(100.0f, mostCurrent.activityBA) - Common.PerXToCurrent(50.0f, mostCurrent.activityBA), Common.PerXToCurrent(100.0f, mostCurrent.activityBA), Common.DipToCurrent(100));
        return "";
    }

    public static boolean _activity_keypress(int i) throws Exception {
        KeyCodes keyCodes = Common.KeyCodes;
        if (i != 4) {
            return false;
        }
        BA ba = processBA;
        main main = mostCurrent._main;
        Common.StartActivity(ba, main.getObject());
        mostCurrent._activity.Finish();
        return true;
    }

    public static String _activity_pause(boolean z) throws Exception {
        return "";
    }

    public static String _activity_resume() throws Exception {
        return "";
    }

    public static String _globals() throws Exception {
        mostCurrent._pnl_toolbar = new PanelWrapper();
        mostCurrent._lbl_1 = new LabelWrapper();
        mostCurrent._lbl_2 = new LabelWrapper();
        mostCurrent._lbl_3 = new LabelWrapper();
        mostCurrent._lbl_4 = new LabelWrapper();
        mostCurrent._lbl_5 = new LabelWrapper();
        mostCurrent._lbl_6 = new LabelWrapper();
        mostCurrent._lbl_7 = new LabelWrapper();
        mostCurrent._lbl_8 = new LabelWrapper();
        mostCurrent._lbl_9 = new LabelWrapper();
        mostCurrent._lbl_10 = new LabelWrapper();
        mostCurrent._lbl_11 = new LabelWrapper();
        mostCurrent._lbl_12 = new LabelWrapper();
        mostCurrent._lbl_13 = new LabelWrapper();
        mostCurrent._lbl = new LabelWrapper[30];
        int length = mostCurrent._lbl.length;
        for (int i = 0; i < length; i++) {
            mostCurrent._lbl[i] = new LabelWrapper();
        }
        mostCurrent._card_crash = new CardViewWrapper();
        mostCurrent._pnl_crash = new PanelWrapper();
        mostCurrent._img_in = new ImageViewWrapper();
        mostCurrent._img_out = new ImageViewWrapper();
        mostCurrent._spinner2 = new SpinnerWrapper();
        mostCurrent._spinner1 = new SpinnerWrapper();
        return "";
    }

    public static String _process_globals() throws Exception {
        _str_tarikh = "";
        _str_mablagh = "";
        _str_in = "";
        _str_out = "";
        _str_name = "";
        _str_marja = "";
        _str_peygiri = "";
        _str_marjabank = "";
        _incard = "";
        _outcard = "";
        return "";
    }
}
