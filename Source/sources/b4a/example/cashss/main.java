package b4a.example.cashss;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.B4AMenuItem;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.Msgbox;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.ActivityWrapper;
import anywheresoftware.b4a.objects.EditTextWrapper;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.objects.SpinnerWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.collections.List;
import anywheresoftware.b4a.objects.streams.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class main extends Activity implements B4AActivity {
    public static String _val1 = "";
    public static String _val2 = "";
    static boolean afterFirstLayout;
    public static final boolean fullScreen = false;
    public static final boolean includeTitle = false;
    static boolean isFirst = true;
    public static main mostCurrent;
    public static WeakReference<Activity> previousOne;
    public static BA processBA;
    private static boolean processGlobalsRun = false;
    public Common __c = null;
    public ac_make _ac_make = null;
    public ac_make2 _ac_make2 = null;
    public ac_make3 _ac_make3 = null;
    ActivityWrapper _activity;
    public EditTextWrapper _edittext1 = null;
    public EditTextWrapper _edittext2 = null;
    public EditTextWrapper _edittext3 = null;
    public EditTextWrapper _edittext4 = null;
    public EditTextWrapper _edittext5 = null;
    public EditTextWrapper _edittext6 = null;
    public EditTextWrapper _edittext8 = null;
    public EditTextWrapper _edittext9 = null;
    public List _lista = null;
    public List _listbank = null;
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
            processBA = new BA(getApplicationContext(), null, null, "b4a.example.cashss", "b4a.example.cashss.main");
            processBA.loadHtSubs(getClass());
            BALayout.setDeviceScale(getApplicationContext().getResources().getDisplayMetrics().density);
        } else if (!(previousOne == null || (activity = previousOne.get()) == null || activity == this)) {
            BA.LogInfo("Killing previous instance (main).");
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
            if (main.afterFirstLayout || main.mostCurrent == null) {
                return;
            }
            if (main.mostCurrent.layout.getWidth() == 0) {
                BA.handler.postDelayed(this, 5);
                return;
            }
            main.mostCurrent.layout.getLayoutParams().height = main.mostCurrent.layout.getHeight();
            main.mostCurrent.layout.getLayoutParams().width = main.mostCurrent.layout.getWidth();
            main.afterFirstLayout = true;
            main.mostCurrent.afterFirstLayout();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void afterFirstLayout() {
        if (this == mostCurrent) {
            this.activityBA = new BA(this, this.layout, processBA, "b4a.example.cashss", "b4a.example.cashss.main");
            processBA.sharedProcessBA.activityBA = new WeakReference<>(this.activityBA);
            ViewWrapper.lastId = 0;
            this._activity = new ActivityWrapper(this.activityBA, "activity");
            Msgbox.isDismissing = false;
            if (BA.isShellModeRuntimeCheck(processBA)) {
                if (isFirst) {
                    processBA.raiseEvent2(null, true, "SHELL", false, new Object[0]);
                }
                processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.cashss.main", processBA, this.activityBA, this._activity, Float.valueOf(Common.Density), mostCurrent);
                this._activity.reinitializeForShell(this.activityBA, "activity");
            }
            initializeProcessGlobals();
            initializeGlobals();
            BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
            processBA.raiseEvent2(null, true, "activity_create", false, Boolean.valueOf(isFirst));
            isFirst = false;
            if (this == mostCurrent) {
                processBA.setActivityPaused(false);
                BA.LogInfo("** Activity (main) Resume **");
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
            main.processBA.raiseEventFromUI(menuItem.getTitle(), this.eventName + "_click", new Object[0]);
            return true;
        }
    }

    public static Class<?> getObject() {
        return main.class;
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
            Boolean bool = (Boolean) main.processBA.raiseEvent2(main.this._activity, false, "activity_keypress", false, Integer.valueOf(i));
            if (bool == null || bool.booleanValue()) {
                return true;
            }
            if (i != 4) {
                return false;
            }
            main.this.finish();
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
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + this.activityBA.activity.isFinishing() + " **");
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
            if (main.mostCurrent != null && main.mostCurrent == this.activity.get()) {
                main.processBA.setActivityPaused(false);
                BA.LogInfo("** Activity (main) Resume **");
                main.processBA.raiseEvent(main.mostCurrent._activity, "activity_resume", null);
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

    public static boolean isAnyActivityVisible() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        if (mostCurrent != null) {
            z = true;
        } else {
            z = false;
        }
        boolean z5 = false | z;
        if (ac_make.mostCurrent != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z6 = z5 | z2;
        if (ac_make2.mostCurrent != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z7 = z3 | z6;
        if (ac_make3.mostCurrent == null) {
            z4 = false;
        }
        return z7 | z4;
    }

    public static String _activity_create(boolean z) throws Exception {
        mostCurrent._activity.LoadLayout("lay", mostCurrent.activityBA);
        mostCurrent._lista.Initialize();
        main main = mostCurrent;
        File file = Common.File;
        File file2 = Common.File;
        main._lista = File.ListFiles(File.getDirAssets());
        Common.Log(BA.NumberToString(mostCurrent._lista.getSize()));
        int size = mostCurrent._lista.getSize() - 1;
        for (int i = 0; i <= size; i = i + 0 + 1) {
            String ObjectToString = BA.ObjectToString(mostCurrent._lista.Get(i));
            if (ObjectToString.contains(".png")) {
                String replace = ObjectToString.replace(".png", "");
                mostCurrent._spinner1.Add(replace);
                mostCurrent._spinner2.Add(replace);
            }
        }
        return "";
    }

    public static String _activity_pause(boolean z) throws Exception {
        return "";
    }

    public static String _activity_resume() throws Exception {
        return "";
    }

    public static String _button1_click() throws Exception {
        main main = mostCurrent;
        if (!_val1.equals("")) {
            main main2 = mostCurrent;
            if (!_val2.equals("")) {
                ac_make3 ac_make3 = mostCurrent._ac_make3;
                ac_make3._str_tarikh = mostCurrent._edittext1.getText();
                ac_make3 ac_make32 = mostCurrent._ac_make3;
                ac_make3._str_mablagh = mostCurrent._edittext2.getText();
                ac_make3 ac_make33 = mostCurrent._ac_make3;
                ac_make3._str_in = mostCurrent._edittext3.getText();
                ac_make3 ac_make34 = mostCurrent._ac_make3;
                ac_make3._str_out = mostCurrent._edittext6.getText();
                ac_make3 ac_make35 = mostCurrent._ac_make3;
                ac_make3._str_name = mostCurrent._edittext5.getText();
                ac_make3 ac_make36 = mostCurrent._ac_make3;
                ac_make3._str_marja = mostCurrent._edittext4.getText();
                ac_make3 ac_make37 = mostCurrent._ac_make3;
                ac_make3._str_marjabank = mostCurrent._edittext9.getText();
                ac_make3 ac_make38 = mostCurrent._ac_make3;
                ac_make3._str_peygiri = mostCurrent._edittext8.getText();
                BA ba = processBA;
                ac_make3 ac_make39 = mostCurrent._ac_make3;
                Common.StartActivity(ba, ac_make3.getObject());
                return "";
            }
        }
        Common.ToastMessageShow(BA.ObjectToCharSequence("حاجی بانکو انتخاب کن"), false);
        return BA.ObjectToString(true);
    }

    public static String _globals() throws Exception {
        mostCurrent._spinner1 = new SpinnerWrapper();
        mostCurrent._edittext1 = new EditTextWrapper();
        mostCurrent._edittext2 = new EditTextWrapper();
        mostCurrent._edittext3 = new EditTextWrapper();
        mostCurrent._edittext4 = new EditTextWrapper();
        mostCurrent._edittext5 = new EditTextWrapper();
        mostCurrent._edittext6 = new EditTextWrapper();
        mostCurrent._edittext8 = new EditTextWrapper();
        mostCurrent._edittext9 = new EditTextWrapper();
        mostCurrent._lista = new List();
        mostCurrent._listbank = new List();
        mostCurrent._spinner2 = new SpinnerWrapper();
        main main = mostCurrent;
        _val1 = "";
        main main2 = mostCurrent;
        _val2 = "";
        return "";
    }

    public static void initializeProcessGlobals() {
        if (!processGlobalsRun) {
            processGlobalsRun = true;
            try {
                _process_globals();
                starter._process_globals();
                ac_make._process_globals();
                ac_make2._process_globals();
                ac_make3._process_globals();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String _process_globals() throws Exception {
        return "";
    }

    public static String _spinner1_itemclick(int i, Object obj) throws Exception {
        main main = mostCurrent;
        _val1 = BA.ObjectToString(obj);
        ac_make3 ac_make3 = mostCurrent._ac_make3;
        ac_make3._incard = BA.ObjectToString(obj);
        return "";
    }

    public static String _spinner2_itemclick(int i, Object obj) throws Exception {
        ac_make3 ac_make3 = mostCurrent._ac_make3;
        ac_make3._outcard = BA.ObjectToString(obj);
        main main = mostCurrent;
        _val2 = BA.ObjectToString(obj);
        return "";
    }
}
