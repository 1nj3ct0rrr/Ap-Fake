package anywheresoftware.b4a.objects;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import anywheresoftware.b4a.BA;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("EditText")
public class EditTextWrapper extends TextViewWrapper<EditText> {
    public static final int INPUT_TYPE_DECIMAL_NUMBERS = 12290;
    public static final int INPUT_TYPE_NONE = 0;
    public static final int INPUT_TYPE_NUMBERS = 2;
    public static final int INPUT_TYPE_PHONE = 3;
    public static final int INPUT_TYPE_TEXT = 1;

    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        ((TextViewWrapper) this).ba = ba;
        if (!keepOldObject) {
            setObject(new EditText(ba.context));
        }
        super.innerInitialize(ba, eventName, true);
        if (ba.subExists(String.valueOf(eventName) + "_textchanged")) {
            ((EditText) getObject()).addTextChangedListener(new TextWatcher() {
                /* class anywheresoftware.b4a.objects.EditTextWrapper.AnonymousClass1 */
                private CharSequence old;

                public void afterTextChanged(Editable s) {
                    ba.raiseEvent2(EditTextWrapper.this.getObject(), false, String.valueOf(eventName) + "_textchanged", true, this.old, ((EditText) EditTextWrapper.this.getObject()).getText().toString());
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    this.old = s.toString();
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
        }
        if (ba.subExists(String.valueOf(eventName) + "_enterpressed")) {
            ((EditText) getObject()).setOnEditorActionListener(new TextView.OnEditorActionListener() {
                /* class anywheresoftware.b4a.objects.EditTextWrapper.AnonymousClass2 */

                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    ba.raiseEvent(EditTextWrapper.this.getObject(), String.valueOf(eventName) + "_enterpressed", new Object[0]);
                    return false;
                }
            });
        }
        if (ba.subExists(String.valueOf(eventName) + "_focuschanged")) {
            ((EditText) getObject()).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                /* class anywheresoftware.b4a.objects.EditTextWrapper.AnonymousClass3 */

                public void onFocusChange(View v, boolean hasFocus) {
                    ba.raiseEventFromUI(EditTextWrapper.this.getObject(), String.valueOf(eventName) + "_focuschanged", Boolean.valueOf(hasFocus));
                }
            });
        }
    }

    public void setForceDoneButton(boolean value) {
        if (value) {
            ((EditText) getObject()).setImeOptions(6);
        } else {
            ((EditText) getObject()).setImeOptions(0);
        }
    }

    @Override // anywheresoftware.b4a.objects.TextViewWrapper
    public void setSingleLine(boolean singleLine) {
        ((EditText) getObject()).setSingleLine(singleLine);
    }

    public void setPasswordMode(boolean value) {
        if (value) {
            ((EditText) getObject()).setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            ((EditText) getObject()).setTransformationMethod(null);
        }
    }

    public int getSelectionStart() {
        return Selection.getSelectionStart(((EditText) getObject()).getText());
    }

    public void setSelectionStart(int value) {
        ((EditText) getObject()).setSelection(value);
    }

    public int getSelectionLength() {
        return Math.max(0, Selection.getSelectionEnd(((EditText) getObject()).getText()) - getSelectionStart());
    }

    public void SetSelection(int Start, int Length) {
        Selection.setSelection(((EditText) getObject()).getText(), Start, Start + Length);
    }

    public void SelectAll() {
        Selection.selectAll(((EditText) getObject()).getText());
    }

    public void setInputType(int value) {
        ((EditText) getObject()).setInputType(value);
    }

    public int getInputType() {
        return ((EditText) getObject()).getInputType();
    }

    public void setWrap(boolean value) {
        ((EditText) getObject()).setHorizontallyScrolling(!value);
    }

    public void setHint(String text) {
        ((EditText) getObject()).setHint(text);
    }

    public String getHint() {
        CharSequence c = ((EditText) getObject()).getHint();
        return c == null ? "" : String.valueOf(c);
    }

    public void setHintColor(int Color) {
        ((EditText) getObject()).setHintTextColor(Color);
    }

    public int getHintColor() {
        return ((EditText) getObject()).getCurrentHintTextColor();
    }

    @Override // anywheresoftware.b4a.objects.TextViewWrapper
    @BA.RaisesSynchronousEvents
    public void setText(CharSequence Text) {
        super.setText(Text);
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        EditText v;
        if (prev == null) {
            v = (EditText) ViewWrapper.buildNativeView((Context) tag, EditText.class, props, designer);
        } else {
            v = (EditText) prev;
        }
        TextViewWrapper.build(v, props, designer);
        ColorStateList defaultHintColor = null;
        if (designer) {
            defaultHintColor = (ColorStateList) ViewWrapper.getDefault(v, "hintColor", v.getHintTextColors());
        }
        int hintColor = ((Integer) BA.gm(props, "hintColor", Integer.valueOf((int) ViewWrapper.defaultColor))).intValue();
        if (hintColor != -984833) {
            v.setHintTextColor(hintColor);
        } else if (designer) {
            v.setHintTextColor(defaultHintColor);
        }
        String hint = (String) BA.gm(props, "hint", "");
        if (designer && hint.length() == 0) {
            hint = (String) props.get("name");
        }
        v.setHint(hint);
        String inputType = (String) props.get("inputType");
        if (inputType != null) {
            v.setInputType(((Integer) EditTextWrapper.class.getField("INPUT_TYPE_" + inputType).get(null)).intValue());
        }
        boolean singleLine = ((Boolean) props.get("singleLine")).booleanValue();
        v.setSingleLine(singleLine);
        if (designer && singleLine) {
            v.setInputType(524288);
        }
        if (((Boolean) props.get("password")).booleanValue()) {
            v.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            v.setTransformationMethod(null);
        }
        v.setHorizontallyScrolling(!((Boolean) BA.gm(props, "wrap", true)).booleanValue());
        if (((Boolean) BA.gm(props, "forceDone", false)).booleanValue()) {
            v.setImeOptions(6);
        } else {
            v.setImeOptions(0);
        }
        return v;
    }
}
