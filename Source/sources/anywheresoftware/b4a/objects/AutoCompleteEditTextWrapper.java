package anywheresoftware.b4a.objects;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.collections.List;
import java.util.HashMap;

@BA.ShortName("AutoCompleteEditText")
public class AutoCompleteEditTextWrapper extends EditTextWrapper {
    @Override // anywheresoftware.b4a.objects.ViewWrapper, anywheresoftware.b4a.objects.EditTextWrapper
    @BA.Hide
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        ((EditTextWrapper) this).ba = ba;
        if (!keepOldObject) {
            setObject(new AutoCompleteTextView(ba.context));
            ((EditText) getObject()).setSingleLine(true);
            ((EditText) getObject()).setImeOptions(6);
        }
        super.innerInitialize(ba, eventName, true);
        final AutoCompleteTextView a = (AutoCompleteTextView) getObject();
        if ((a.getInputType() & 15) == 1) {
            a.setInputType(a.getInputType() | 524288);
        }
        a.setThreshold(1);
        a.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* class anywheresoftware.b4a.objects.AutoCompleteEditTextWrapper.AnonymousClass1 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ((InputMethodManager) BA.applicationContext.getSystemService("input_method")).hideSoftInputFromWindow(((EditText) AutoCompleteEditTextWrapper.this.getObject()).getWindowToken(), 0);
                ba.raiseEventFromUI(AutoCompleteEditTextWrapper.this.getObject(), String.valueOf(eventName) + "_itemclick", String.valueOf(a.getAdapter().getItem(position)));
            }
        });
    }

    public void SetItems(BA ba, List Items) {
        ((AutoCompleteTextView) getObject()).setAdapter(new MyArrayAdapter(ba.context, (java.util.List) Items.getObject(), getTextSize(), getTypeface(), getGravity(), getTextColor()));
    }

    public void SetItems2(BA ba, List Items, Typeface Typeface, int Gravity, float TextSize, int TextColor) {
        ((AutoCompleteTextView) getObject()).setAdapter(new MyArrayAdapter(ba.context, (java.util.List) Items.getObject(), TextSize, Typeface, Gravity, TextColor));
    }

    public void ShowDropDown() {
        ((AutoCompleteTextView) getObject()).showDropDown();
    }

    public void DismissDropDown() {
        ((AutoCompleteTextView) getObject()).dismissDropDown();
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        AutoCompleteTextView v;
        if (prev == null) {
            v = (AutoCompleteTextView) ViewWrapper.buildNativeView((Context) tag, AutoCompleteTextView.class, props, designer);
        } else {
            v = (AutoCompleteTextView) prev;
        }
        return EditTextWrapper.build(v, props, designer, tag);
    }

    @BA.Hide
    public static class MyArrayAdapter extends ArrayAdapter<String> {
        int gravity;
        int textColor;
        float textSize;
        private Typeface typeface;

        public MyArrayAdapter(Context context, java.util.List list, float textSize2, Typeface typeface2, int gravity2, int textColor2) {
            super(context, 0, list);
            this.typeface = typeface2;
            this.textColor = textColor2;
            this.textSize = textSize2;
            this.gravity = gravity2;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                tv = new TextView(getContext());
                tv.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
                int p = Common.DipToCurrent(10);
                tv.setPadding(p, p, p, p);
                tv.setTextColor(this.textColor);
                tv.setTextSize(this.textSize);
                tv.setTypeface(this.typeface);
                tv.setGravity(this.gravity);
            } else {
                tv = (TextView) convertView;
            }
            tv.setText((CharSequence) getItem(position));
            return tv;
        }
    }
}
