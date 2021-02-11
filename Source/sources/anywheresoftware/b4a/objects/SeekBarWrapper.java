package anywheresoftware.b4a.objects;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;
import anywheresoftware.b4a.BA;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("SeekBar")
public class SeekBarWrapper extends ViewWrapper<SeekBar> {
    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject) {
            setObject(new SeekBar(ba.context));
        }
        super.innerInitialize(ba, eventName, true);
        if (ba.subExists(String.valueOf(eventName) + "_valuechanged")) {
            ((SeekBar) getObject()).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                /* class anywheresoftware.b4a.objects.SeekBarWrapper.AnonymousClass1 */

                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    ba.raiseEventFromUI(SeekBarWrapper.this.getObject(), String.valueOf(eventName) + "_valuechanged", Integer.valueOf(progress), Boolean.valueOf(fromUser));
                }

                public void onStartTrackingTouch(SeekBar arg0) {
                }

                public void onStopTrackingTouch(SeekBar arg0) {
                }
            });
        }
    }

    public int getMax() {
        return ((SeekBar) getObject()).getMax();
    }

    public void setMax(int value) {
        ((SeekBar) getObject()).setMax(value);
    }

    public int getValue() {
        return ((SeekBar) getObject()).getProgress();
    }

    public void setValue(int value) {
        ((SeekBar) getObject()).setProgress(value);
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context) tag, SeekBar.class, props, designer);
        }
        SeekBar v = (SeekBar) ViewWrapper.build(prev, props, designer);
        int oldMax = v.getMax();
        v.setMax(((Integer) props.get("max")).intValue());
        if (v.getMax() != oldMax) {
            v.setProgress(-1);
        }
        v.setProgress(((Integer) props.get("value")).intValue());
        return v;
    }
}
