package anywheresoftware.b4a.objects;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import java.io.Serializable;
import java.net.URISyntaxException;

@BA.ShortName("Intent")
public class IntentWrapper extends AbsObjectWrapper<Intent> {
    public static final String ACTION_APPWIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";
    public static final String ACTION_CALL = "android.intent.action.CALL";
    public static final String ACTION_EDIT = "android.intent.action.EDIT";
    public static final String ACTION_MAIN = "android.intent.action.MAIN";
    public static final String ACTION_PICK = "android.intent.action.PICK";
    public static final String ACTION_SEND = "android.intent.action.SEND";
    public static final String ACTION_VIEW = "android.intent.action.VIEW";

    public void Initialize(String Action, String Uri) {
        setObject(new Intent(Action, Uri.length() > 0 ? Uri.parse(Uri) : null));
    }

    public void Initialize2(String Uri, int Flags) throws URISyntaxException {
        setObject(Intent.parseUri(Uri, Flags));
    }

    public String getAction() {
        return ((Intent) getObject()).getAction() == null ? "" : ((Intent) getObject()).getAction();
    }

    public void setAction(String v) {
        ((Intent) getObject()).setAction(v);
    }

    public void SetType(String Type) {
        ((Intent) getObject()).setDataAndType(((Intent) getObject()).getData(), Type);
    }

    public int getFlags() {
        return ((Intent) getObject()).getFlags();
    }

    public void setFlags(int flags) {
        ((Intent) getObject()).setFlags(flags);
    }

    public void AddCategory(String Category) {
        ((Intent) getObject()).addCategory(Category);
    }

    public String GetData() {
        return ((Intent) getObject()).getDataString();
    }

    public void PutExtra(String Name, Object Value) {
        Intent i = (Intent) getObject();
        if (Value instanceof Serializable) {
            i.putExtra(Name, (Serializable) Value);
        } else if (Value instanceof Parcelable) {
            i.putExtra(Name, (Parcelable) Value);
        }
    }

    public Object GetExtra(String Name) {
        return ((Intent) getObject()).getSerializableExtra(Name);
    }

    public boolean HasExtra(String Name) {
        return ((Intent) getObject()).hasExtra(Name);
    }

    public String ExtrasToString() {
        if (!IsInitialized()) {
            return "not initialized";
        }
        Bundle b = ((Intent) getObject()).getExtras();
        if (b == null) {
            return "no extras";
        }
        b.size();
        return b.toString();
    }

    public void WrapAsIntentChooser(String Title) {
        setObject(Intent.createChooser((Intent) getObject(), Title));
    }

    public void SetComponent(String Component) {
        ((Intent) getObject()).setComponent(ComponentName.unflattenFromString(Component));
    }
}
