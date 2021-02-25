package anywheresoftware.b4a.objects;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.DynamicBuilder;
import java.util.HashMap;

@BA.Hide
public class CompoundButtonWrapper<T extends CompoundButton> extends TextViewWrapper<T> {
    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(BA ba, String eventName, boolean keepOldObject) {
        innerInitialize(ba, eventName, keepOldObject, true);
    }

    /* access modifiers changed from: protected */
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject, boolean addCheckedChangeEvent) {
        super.innerInitialize(ba, eventName, true);
        if (ba.subExists(String.valueOf(eventName) + "_checkedchange")) {
            ((CompoundButton) getObject()).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /* class anywheresoftware.b4a.objects.CompoundButtonWrapper.AnonymousClass1 */

                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ba.raiseEvent2(CompoundButtonWrapper.this.getObject(), false, String.valueOf(eventName) + "_checkedchange", false, Boolean.valueOf(isChecked));
                }
            });
        }
    }

    public boolean getChecked() {
        return ((CompoundButton) getObject()).isChecked();
    }

    @BA.RaisesSynchronousEvents
    public void setChecked(boolean Value) {
        ((CompoundButton) getObject()).setChecked(Value);
    }

    @Override // anywheresoftware.b4a.objects.TextViewWrapper, anywheresoftware.b4a.AbsObjectWrapper, anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public String toString() {
        String s = super.toString();
        if (IsInitialized()) {
            return String.valueOf(s) + ", Checked=" + getChecked();
        }
        return s;
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer) throws Exception {
        Drawable d;
        CompoundButton v = (CompoundButton) TextViewWrapper.build(prev, props, designer);
        v.setChecked(((Boolean) props.get("isChecked")).booleanValue());
        HashMap<String, Object> drawProps = (HashMap) props.get("drawable");
        if (!(drawProps == null || (d = (Drawable) DynamicBuilder.build(prev, drawProps, designer, null)) == null)) {
            v.setBackgroundDrawable(d);
        }
        return v;
    }

    @BA.ActivityObject
    @BA.ShortName("CheckBox")
    public static class CheckBoxWrapper extends CompoundButtonWrapper<CheckBox> {
        @Override // anywheresoftware.b4a.objects.CompoundButtonWrapper, anywheresoftware.b4a.objects.ViewWrapper
        @BA.Hide
        public void innerInitialize(BA ba, String eventName, boolean keepOldObject) {
            if (!keepOldObject) {
                setObject(new CheckBox(ba.context));
            }
            CompoundButtonWrapper.super.innerInitialize(ba, eventName, true);
        }

        @BA.Hide
        public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
            if (prev == null) {
                prev = ViewWrapper.buildNativeView((Context) tag, CheckBox.class, props, designer);
            }
            return (CheckBox) CompoundButtonWrapper.build(prev, props, designer);
        }
    }

    @BA.ActivityObject
    @BA.ShortName("RadioButton")
    public static class RadioButtonWrapper extends CompoundButtonWrapper<RadioButton> {
        @Override // anywheresoftware.b4a.objects.CompoundButtonWrapper, anywheresoftware.b4a.objects.ViewWrapper
        @BA.Hide
        public void innerInitialize(BA ba, String eventName, boolean keepOldObject) {
            if (!keepOldObject) {
                setObject(new RadioButton(ba.context));
            }
            CompoundButtonWrapper.super.innerInitialize(ba, eventName, true, false);
            ((RadioButton) getObject()).setOnCheckedChangeListener(new RadioButtonListener(eventName, ba, (RadioButton) getObject()));
        }

        private static class RadioButtonListener implements CompoundButton.OnCheckedChangeListener {
            private BA ba;
            private RadioButton current;
            private String eventName;

            public RadioButtonListener(String eventName2, BA ba2, RadioButton current2) {
                this.eventName = eventName2;
                this.ba = ba2;
                this.current = current2;
            }

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ViewParent vp = this.current.getParent();
                    if (vp instanceof ViewGroup) {
                        ViewGroup vg = (ViewGroup) vp;
                        for (int i = 0; i < vg.getChildCount(); i++) {
                            View v = vg.getChildAt(i);
                            if ((v instanceof RadioButton) && v != this.current) {
                                RadioButton rb = (RadioButton) v;
                                if (rb.isChecked()) {
                                    rb.setChecked(false);
                                }
                            }
                        }
                    }
                    if (this.eventName.length() > 0) {
                        this.ba.raiseEvent2(this.current, false, String.valueOf(this.eventName) + "_checkedchange", false, Boolean.valueOf(isChecked));
                    }
                }
            }
        }

        @BA.Hide
        public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
            if (prev == null) {
                prev = ViewWrapper.buildNativeView((Context) tag, RadioButton.class, props, designer);
            }
            return (RadioButton) CompoundButtonWrapper.build(prev, props, designer);
        }
    }

    @BA.ActivityObject
    @BA.ShortName("ToggleButton")
    public static class ToggleButtonWrapper extends CompoundButtonWrapper<ToggleButton> {
        @Override // anywheresoftware.b4a.objects.CompoundButtonWrapper, anywheresoftware.b4a.objects.ViewWrapper
        @BA.Hide
        public void innerInitialize(BA ba, String eventName, boolean keepOldObject) {
            if (!keepOldObject) {
                setObject(new ToggleButton(ba.context));
                ((ToggleButton) getObject()).setText("");
            }
            CompoundButtonWrapper.super.innerInitialize(ba, eventName, true);
        }

        public String getTextOn() {
            return String.valueOf(((ToggleButton) getObject()).getTextOn());
        }

        public void setTextOn(CharSequence value) {
            ((ToggleButton) getObject()).setTextOn(value);
            setChecked(getChecked());
        }

        public String getTextOff() {
            return String.valueOf(((ToggleButton) getObject()).getTextOff());
        }

        public void setTextOff(CharSequence value) {
            ((ToggleButton) getObject()).setTextOff(value);
            setChecked(getChecked());
        }

        @Override // anywheresoftware.b4a.objects.TextViewWrapper
        @BA.Hide
        public String getText() {
            return "";
        }

        @Override // anywheresoftware.b4a.objects.TextViewWrapper
        @BA.Hide
        public void setText(CharSequence Text) {
        }

        @BA.Hide
        public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
            if (prev == null) {
                prev = ViewWrapper.buildNativeView((Context) tag, ToggleButton.class, props, designer);
            }
            ToggleButton v = (ToggleButton) prev;
            v.setTextOn((String) props.get("textOn"));
            v.setTextOff((String) props.get("textOff"));
            ToggleButton v2 = (ToggleButton) CompoundButtonWrapper.build(prev, props, designer);
            v2.setTextColor(((Integer) props.get("textColor")).intValue());
            return v2;
        }
    }
}
