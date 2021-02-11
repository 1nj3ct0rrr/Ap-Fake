package anywheresoftware.b4a.objects;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.DynamicBuilder;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("Button")
public class ButtonWrapper extends TextViewWrapper<Button> {
    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject) {
            setObject(new Button(ba.context));
            removeCaps((Button) getObject());
        }
        super.innerInitialize(ba, eventName, true);
        if (ba.subExists(String.valueOf(eventName) + "_down") || ba.subExists(String.valueOf(eventName) + "_up")) {
            ((Button) getObject()).setOnTouchListener(new View.OnTouchListener() {
                /* class anywheresoftware.b4a.objects.ButtonWrapper.AnonymousClass1 */
                private boolean down = false;

                public boolean onTouch(View v, MotionEvent event) {
                    int[] states;
                    if (event.getAction() == 0) {
                        this.down = true;
                        ba.raiseEventFromUI(ButtonWrapper.this.getObject(), String.valueOf(eventName) + "_down", new Object[0]);
                    } else if (this.down && (event.getAction() == 1 || event.getAction() == 3)) {
                        this.down = false;
                        ba.raiseEventFromUI(ButtonWrapper.this.getObject(), String.valueOf(eventName) + "_up", new Object[0]);
                    } else if (event.getAction() == 2 && (states = v.getDrawableState()) != null) {
                        int i = 0;
                        while (true) {
                            if (i >= states.length) {
                                if (this.down) {
                                    ba.raiseEventFromUI(ButtonWrapper.this.getObject(), String.valueOf(eventName) + "_up", new Object[0]);
                                    this.down = false;
                                }
                            } else if (states[i] != 16842919) {
                                i++;
                            } else if (!this.down) {
                                ba.raiseEventFromUI(ButtonWrapper.this.getObject(), String.valueOf(eventName) + "_down", new Object[0]);
                                this.down = true;
                            }
                        }
                    }
                    return false;
                }
            });
        }
    }

    private static void removeCaps(Button btn) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                TextView.class.getDeclaredMethod("setAllCaps", Boolean.TYPE).invoke(btn, false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context) tag, Button.class, props, designer);
            removeCaps((Button) prev);
        }
        TextView v = (TextView) TextViewWrapper.build(prev, props, designer);
        Drawable d = (Drawable) DynamicBuilder.build(prev, (HashMap) props.get("drawable"), designer, null);
        if (d != null) {
            v.setBackgroundDrawable(d);
        }
        if (designer) {
            v.setPressed(((Boolean) props.get("pressed")).booleanValue());
        }
        return v;
    }
}
