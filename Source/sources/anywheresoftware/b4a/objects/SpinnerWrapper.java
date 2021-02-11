package anywheresoftware.b4a.objects;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.LayoutBuilder;
import anywheresoftware.b4a.objects.collections.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("Spinner")
public class SpinnerWrapper extends ViewWrapper<B4ASpinner> implements LayoutBuilder.DesignerTextSizeMethod {
    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(BA ba, String eventName, boolean keepOldObject) {
        if (!keepOldObject) {
            setObject(new B4ASpinner(ba.context));
        }
        super.innerInitialize(ba, eventName, true);
        ((B4ASpinner) getObject()).ba = ba;
        ((B4ASpinner) getObject()).eventName = eventName;
        ((B4ASpinner) getObject()).disallowItemClick = false;
    }

    public int getSize() {
        return ((B4ASpinner) getObject()).adapter.getCount();
    }

    public int getSelectedIndex() {
        return ((B4ASpinner) getObject()).selectedItem;
    }

    public void setSelectedIndex(int value) {
        ((B4ASpinner) getObject()).disallowItemClick = true;
        try {
            ((B4ASpinner) getObject()).setSelection(value);
            ((B4ASpinner) getObject()).selectedItem = value;
        } finally {
            ((B4ASpinner) getObject()).disallowItemClick = false;
        }
    }

    public String getSelectedItem() {
        Object o = ((B4ASpinner) getObject()).getItemAtPosition(((B4ASpinner) getObject()).selectedItem);
        if (o == null) {
            o = "";
        }
        return String.valueOf(o);
    }

    public int IndexOf(String value) {
        return ((B4ASpinner) getObject()).adapter.items.indexOf(value);
    }

    public String getPrompt() {
        return (String) ((B4ASpinner) getObject()).getPrompt();
    }

    public void setPrompt(CharSequence title) {
        if (title == null || title.length() == 0) {
            title = null;
        }
        ((B4ASpinner) getObject()).setPrompt(title);
    }

    public void Add(String Item) {
        ((B4ASpinner) getObject()).disallowItemClick = true;
        try {
            ((B4ASpinner) getObject()).adapter.items.add(Item);
            ((B4ASpinner) getObject()).adapter.notifyDataSetChanged();
            if (((B4ASpinner) getObject()).selectedItem == -1) {
                ((B4ASpinner) getObject()).selectedItem = 0;
            }
        } finally {
            ((B4ASpinner) getObject()).disallowItemClick = false;
        }
    }

    public void AddAll(List List) {
        ((B4ASpinner) getObject()).disallowItemClick = true;
        try {
            ((B4ASpinner) getObject()).adapter.items.addAll((Collection) List.getObject());
            ((B4ASpinner) getObject()).adapter.notifyDataSetChanged();
            if (((B4ASpinner) getObject()).selectedItem == -1) {
                ((B4ASpinner) getObject()).selectedItem = 0;
            }
        } finally {
            ((B4ASpinner) getObject()).disallowItemClick = false;
        }
    }

    public String GetItem(int Index) {
        return String.valueOf(((B4ASpinner) getObject()).adapter.getItem(Index));
    }

    public void RemoveAt(int Index) {
        ((B4ASpinner) getObject()).disallowItemClick = true;
        try {
            ((B4ASpinner) getObject()).adapter.items.remove(Index);
            ((B4ASpinner) getObject()).adapter.notifyDataSetChanged();
            if (((B4ASpinner) getObject()).selectedItem == ((B4ASpinner) getObject()).adapter.getCount()) {
                B4ASpinner b4ASpinner = (B4ASpinner) getObject();
                b4ASpinner.selectedItem--;
            }
        } finally {
            ((B4ASpinner) getObject()).disallowItemClick = false;
        }
    }

    public void Clear() {
        ((B4ASpinner) getObject()).disallowItemClick = true;
        try {
            ((B4ASpinner) getObject()).adapter.items.clear();
            ((B4ASpinner) getObject()).adapter.notifyDataSetChanged();
            ((B4ASpinner) getObject()).selectedItem = -1;
        } finally {
            ((B4ASpinner) getObject()).disallowItemClick = false;
        }
    }

    public void setTextColor(int Color) {
        ((B4ASpinner) getObject()).adapter.textColor = Color;
    }

    public int getTextColor() {
        return ((B4ASpinner) getObject()).adapter.textColor;
    }

    public void setDropdownTextColor(int Color) {
        ((B4ASpinner) getObject()).adapter.dropdownTextColor = Color;
    }

    public int getDropdownTextColor() {
        return ((B4ASpinner) getObject()).adapter.dropdownTextColor;
    }

    public void setDropdownBackgroundColor(int Color) {
        ((B4ASpinner) getObject()).adapter.ddbackgroundColor = Color;
    }

    public int getDropdownBackgroundColor() {
        return ((B4ASpinner) getObject()).adapter.ddbackgroundColor;
    }

    @Override // anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod
    public void setTextSize(float TextSize) {
        ((B4ASpinner) getObject()).adapter.textSize = TextSize;
    }

    @Override // anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod
    public float getTextSize() {
        return ((B4ASpinner) getObject()).adapter.textSize;
    }

    @BA.Hide
    public static class B4ASpinner extends Spinner {
        public B4ASpinnerAdapter adapter;
        public BA ba;
        public boolean disallowItemClick = true;
        public String eventName;
        int selectedItem = -1;

        public B4ASpinner(Context context) {
            super(context);
            this.adapter = new B4ASpinnerAdapter(context);
            setAdapter((SpinnerAdapter) this.adapter);
        }

        public void setSelection(int position) {
            super.setSelection(position);
            this.selectedItem = position;
            if (this.ba != null && !this.disallowItemClick) {
                this.ba.raiseEventFromUI(this, String.valueOf(this.eventName) + "_itemclick", Integer.valueOf(this.selectedItem), this.adapter.getItem(this.selectedItem));
            }
        }
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context) tag, B4ASpinner.class, props, designer);
        }
        B4ASpinner list = (B4ASpinner) ViewWrapper.build(prev, props, designer);
        Float f = (Float) props.get("fontsize");
        if (f != null) {
            list.adapter.textSize = f.floatValue();
            list.adapter.textColor = ((Integer) props.get("textColor")).intValue();
            if (Color.alpha(list.adapter.textColor) == 0 || list.adapter.textColor == -984833) {
                list.adapter.textColor = 0;
            }
        }
        if (designer) {
            list.adapter.items.clear();
            list.adapter.items.add(props.get("name"));
            list.adapter.notifyDataSetChanged();
        }
        String prompt = (String) props.get("prompt");
        if (prompt != null && prompt.length() > 0) {
            list.setPrompt(prompt);
        }
        return list;
    }

    @BA.Hide
    public static class B4ASpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
        public int ddbackgroundColor = 0;
        public int dropdownTextColor = 0;
        private LayoutInflater inflater;
        ArrayList<Object> items = new ArrayList<>();
        public int textColor = 0;
        public float textSize = 16.0f;

        public B4ASpinnerAdapter(Context context) {
            this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public int getCount() {
            return this.items.size();
        }

        public Object getItem(int position) {
            return this.items.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = this.inflater.inflate(17367049, parent, false);
                ((TextView) convertView).setTextSize(this.textSize);
                if (this.textColor != 0 && this.dropdownTextColor == 0) {
                    ((TextView) convertView).setTextColor(this.textColor);
                } else if (this.dropdownTextColor != 0) {
                    ((TextView) convertView).setTextColor(this.dropdownTextColor);
                }
                if (this.ddbackgroundColor != 0) {
                    convertView.setBackgroundColor(this.ddbackgroundColor);
                }
            }
            TextView tv = (TextView) convertView;
            Object o = this.items.get(position);
            if (o instanceof CharSequence) {
                tv.setText((CharSequence) o);
            } else {
                tv.setText(String.valueOf(o));
            }
            return convertView;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = this.inflater.inflate(17367048, parent, false);
                ((TextView) convertView).setTextSize(this.textSize);
                if (this.textColor != 0) {
                    ((TextView) convertView).setTextColor(this.textColor);
                }
            }
            TextView tv = (TextView) convertView;
            Object o = this.items.get(position);
            if (o instanceof CharSequence) {
                tv.setText((CharSequence) o);
            } else {
                tv.setText(String.valueOf(o));
            }
            return convertView;
        }
    }
}
