package anywheresoftware.b4a.objects;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import java.util.Iterator;
import java.util.LinkedList;

@BA.ShortName("CSBuilder")
public class CSBuilder extends AbsObjectWrapper<SpannableStringBuilder> implements BA.B4aDebuggable {
    private LinkedList<SpanMark> spanOpenings() {
        return (LinkedList) AbsObjectWrapper.getExtraTags(getObject()).get("marks");
    }

    public CSBuilder Initialize() {
        setObject(new SpannableStringBuilder() {
            /* class anywheresoftware.b4a.objects.CSBuilder.AnonymousClass1 */

            public int hashCode() {
                return System.identityHashCode(this);
            }

            public boolean equals(Object o) {
                return this == o;
            }
        });
        AbsObjectWrapper.getExtraTags(getObject()).put("marks", new LinkedList());
        return this;
    }

    public CSBuilder Append(CharSequence Text) {
        ((SpannableStringBuilder) getObject()).append(Text);
        return this;
    }

    public CSBuilder Underline() {
        return open(new UnderlineSpan());
    }

    public CSBuilder Clickable(final BA ba, String EventName, final Object Tag) {
        final String eventName = String.valueOf(EventName.toLowerCase(BA.cul)) + "_click";
        return open(new ClickableSpan() {
            /* class anywheresoftware.b4a.objects.CSBuilder.AnonymousClass2 */

            public void onClick(View widget) {
                ba.raiseEventFromUI(CSBuilder.this.getObject(), eventName, Tag);
            }

            public void updateDrawState(TextPaint ds) {
            }
        });
    }

    public CSBuilder Alignment(Layout.Alignment Alignment) {
        return open(new AlignmentSpan.Standard(Alignment));
    }

    public CSBuilder Bold() {
        return open(new StyleSpan(1));
    }

    @BA.Hide
    public CSBuilder open(Object span) {
        spanOpenings().add(new SpanMark(span, ((SpannableStringBuilder) getObject()).length()));
        return this;
    }

    public CSBuilder Pop() {
        LinkedList<SpanMark> marks = spanOpenings();
        SpanMark sm = marks.removeLast();
        sm.markEnd = ((SpannableStringBuilder) getObject()).length();
        marks.addFirst(sm);
        if (marks.getLast().markEnd != -1) {
            Iterator<SpanMark> it = marks.iterator();
            while (it.hasNext()) {
                SpanMark sm2 = it.next();
                ((SpannableStringBuilder) getObject()).setSpan(sm2.span, sm2.markStart, sm2.markEnd, 0);
            }
            marks.clear();
        }
        return this;
    }

    public CSBuilder PopAll() {
        LinkedList<SpanMark> marks = spanOpenings();
        while (marks.size() > 0) {
            Pop();
        }
        return this;
    }

    public CSBuilder Color(int Color) {
        return open(new ForegroundColorSpan(Color));
    }

    public CSBuilder BackgroundColor(int Color) {
        return open(new BackgroundColorSpan(Color));
    }

    public CSBuilder Size(int Size) {
        return open(new AbsoluteSizeSpan(Size, true));
    }

    public CSBuilder RelativeSize(float Proportion) {
        return open(new RelativeSizeSpan(Proportion));
    }

    public CSBuilder Typeface(Typeface Typeface) {
        return open(new CustomTypefaceSpan(Typeface));
    }

    public CSBuilder Strikethrough() {
        return open(new StrikethroughSpan());
    }

    public CSBuilder VerticalAlign(@BA.Pixel int Shift) {
        return open(new VerticalAlignedSpan(Shift));
    }

    public CSBuilder Image(Bitmap Bitmap, @BA.Pixel int Width, @BA.Pixel int Height, boolean Baseline) {
        int i = 0;
        BitmapDrawable bd = new BitmapDrawable(BA.applicationContext.getResources(), Bitmap);
        bd.setBounds(0, 0, Width, Height);
        if (Baseline) {
            i = 1;
        }
        return open(new ImageSpan(bd, i)).Append("_").Pop();
    }

    public CSBuilder ScaleX(float Proportion) {
        return open(new ScaleXSpan(Proportion));
    }

    public void EnableClickEvents(TextView Label) {
        Label.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public int getLength() {
        return ((SpannableStringBuilder) getObject()).length();
    }

    public String ToString() {
        return ((SpannableStringBuilder) getObject()).toString();
    }

    @Override // anywheresoftware.b4a.AbsObjectWrapper
    @BA.Hide
    public String toString() {
        return ToString();
    }

    @Override // anywheresoftware.b4a.BA.B4aDebuggable
    @BA.Hide
    public Object[] debug(int limit, boolean[] outShouldAddReflectionFields) {
        Object[] res = {"Length", Integer.valueOf(getLength()), "ToString", ToString()};
        outShouldAddReflectionFields[0] = true;
        return res;
    }

    @BA.Hide
    public static class SpanMark {
        public int markEnd = -1;
        public final int markStart;
        public final Object span;

        public SpanMark(Object span2, int markStart2) {
            this.span = span2;
            this.markStart = markStart2;
        }

        public String toString() {
            return this.span.getClass() + " " + this.markStart + " -> " + this.markEnd;
        }
    }

    @BA.Hide
    public static class CustomTypefaceSpan extends MetricAffectingSpan {
        private final Typeface typeface;

        public CustomTypefaceSpan(Typeface typeface2) {
            this.typeface = typeface2;
        }

        public void updateDrawState(TextPaint drawState) {
            apply(drawState);
        }

        public void updateMeasureState(TextPaint paint) {
            apply(paint);
        }

        private void apply(Paint paint) {
            Typeface oldTypeface = paint.getTypeface();
            int fakeStyle = (oldTypeface != null ? oldTypeface.getStyle() : 0) & (this.typeface.getStyle() ^ -1);
            if ((fakeStyle & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((fakeStyle & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.setTypeface(this.typeface);
        }
    }

    @BA.Hide
    public static class VerticalAlignedSpan extends MetricAffectingSpan {
        int shift;

        public VerticalAlignedSpan(int shift2) {
            this.shift = shift2;
        }

        public void updateDrawState(TextPaint tp) {
            tp.baselineShift += this.shift;
        }

        public void updateMeasureState(TextPaint tp) {
            tp.baselineShift += this.shift;
        }
    }
}
