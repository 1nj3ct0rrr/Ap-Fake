package anywheresoftware.b4a.agraham.richstring;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@BA.Version(1.3f)
@BA.Author("Andrew Graham")
@BA.ShortName("RichStringBuilder")
public class RichStringBuilder extends AbsObjectWrapper<SpannableStringBuilder> {
    private static final double version = 1.3d;

    public double getVersion() {
        return version;
    }

    public void LIBRARY_DOC() {
    }

    public void Initialize() {
        setObject(new SpannableStringBuilder());
    }

    public int getLength() {
        return ((SpannableStringBuilder) getObject()).length();
    }

    @Override // anywheresoftware.b4a.AbsObjectWrapper
    @BA.Hide
    public String toString() {
        return ((SpannableStringBuilder) getObject()).toString();
    }

    public RichStringBuilder Append(CharSequence text) {
        ((SpannableStringBuilder) getObject()).append(text);
        return this;
    }

    public RichStringBuilder Insert(int offset, CharSequence text) {
        ((SpannableStringBuilder) getObject()).insert(offset, text);
        return this;
    }

    public RichStringBuilder Remove(int startoffset, int endoffset) {
        ((SpannableStringBuilder) getObject()).delete(startoffset, endoffset);
        return this;
    }

    @BA.ShortName("RichString")
    public static class RichString extends AbsObjectWrapper<SpannableString> {
        public final int STYLE_BOLD = 1;
        public final int STYLE_BOLD_ITALIC = 3;
        public final int STYLE_ITALIC = 2;
        public final int STYLE_NORMAL = 0;

        public void Initialize(CharSequence text) {
            setObject(new SpannableString(text));
        }

        public int getLength() {
            return ((SpannableString) getObject()).length();
        }

        @Override // anywheresoftware.b4a.AbsObjectWrapper
        @BA.Hide
        public String toString() {
            return ((SpannableString) getObject()).toString();
        }

        public RichString Style(int style, int start, int end) {
            ((SpannableString) getObject()).setSpan(new StyleSpan(style), start, end, 0);
            return this;
        }

        public RichString Style2(int style, String metachars) {
            setSpanBetweenTokens(metachars, new StyleSpan(style));
            return this;
        }

        public RichString BackColor(int color, int start, int end) {
            ((SpannableString) getObject()).setSpan(new BackgroundColorSpan(color), start, end, 0);
            return this;
        }

        public RichString BackColor2(int color, String metachars) {
            setSpanBetweenTokens(metachars, new BackgroundColorSpan(color));
            return this;
        }

        public RichString Color(int color, int start, int end) {
            ((SpannableString) getObject()).setSpan(new ForegroundColorSpan(color), start, end, 0);
            return this;
        }

        public RichString Color2(int color, String metachars) {
            setSpanBetweenTokens(metachars, new ForegroundColorSpan(color));
            return this;
        }

        public RichString RelativeSize(float scale, int start, int end) {
            ((SpannableString) getObject()).setSpan(new RelativeSizeSpan(scale), start, end, 0);
            return this;
        }

        public RichString RelativeSize2(float scale, String metachars) {
            setSpanBetweenTokens(metachars, new RelativeSizeSpan(scale));
            return this;
        }

        public RichString ScaleX(float scale, int start, int end) {
            ((SpannableString) getObject()).setSpan(new ScaleXSpan(scale), start, end, 0);
            return this;
        }

        public RichString ScaleX2(float scale, String metachars) {
            setSpanBetweenTokens(metachars, new ScaleXSpan(scale));
            return this;
        }

        public RichString Strikethrough(int start, int end) {
            ((SpannableString) getObject()).setSpan(new StrikethroughSpan(), start, end, 0);
            return this;
        }

        public RichString Strikethrough2(String metachars) {
            setSpanBetweenTokens(metachars, new StrikethroughSpan());
            return this;
        }

        public RichString Subscript(int start, int end) {
            ((SpannableString) getObject()).setSpan(new SubscriptSpan(), start, end, 0);
            return this;
        }

        public RichString Subscript2(String metachars) {
            setSpanBetweenTokens(metachars, new SubscriptSpan());
            return this;
        }

        public RichString Superscript(int start, int end) {
            ((SpannableString) getObject()).setSpan(new SuperscriptSpan(), start, end, 0);
            return this;
        }

        public RichString Superscript2(String metachars) {
            setSpanBetweenTokens(metachars, new SuperscriptSpan());
            return this;
        }

        public RichString Typeface(String typefamily, int start, int end) {
            ((SpannableString) getObject()).setSpan(new TypefaceSpan(typefamily), start, end, 0);
            return this;
        }

        public RichString Typeface2(String typefamily, String metachars) {
            setSpanBetweenTokens(metachars, new TypefaceSpan(typefamily));
            return this;
        }

        public RichString Underscore(int start, int end) {
            ((SpannableString) getObject()).setSpan(new UnderlineSpan(), start, end, 0);
            return this;
        }

        public RichString Underscore2(String metachars) {
            setSpanBetweenTokens(metachars, new UnderlineSpan());
            return this;
        }

        private void setSpanBetweenTokens(String token, CharacterStyle cs) {
            int tokenlen = token.length();
            SpannableStringBuilder ssb = new SpannableStringBuilder((CharSequence) getObject());
            StringBuilder text = new StringBuilder(ssb.toString());
            boolean changed = false;
            int start = text.indexOf(token);
            while (start > -1) {
                int start2 = start + tokenlen;
                int end = text.indexOf(token, start2);
                if (start2 > -1 && end > -1) {
                    ssb.setSpan(CharacterStyle.wrap(cs), start2, end, 0);
                    ssb.delete(end, end + tokenlen);
                    ssb.delete(start2 - tokenlen, start2);
                    text.delete(end, end + tokenlen);
                    text.delete(start2 - tokenlen, start2);
                    changed = true;
                }
                start = text.indexOf(token, start2);
            }
            if (changed) {
                Initialize(ssb);
            }
        }
    }

    @BA.ShortName("RichStringFormatter")
    public static class RichStringFormatter {
        public final int FORMAT_BACKCOLOR = 0;
        public final int FORMAT_COLOR = 1;
        public final int FORMAT_RELATIVESIZE = 2;
        public final int FORMAT_SCALEX = 9;
        public final int FORMAT_STRIKETHROUGH = 3;
        public final int FORMAT_STYLE = 4;
        public final int FORMAT_SUBSCRIPT = 5;
        public final int FORMAT_SUPERSCRIPT = 6;
        public final int FORMAT_TYPEFACE = 7;
        public final int FORMAT_UNDERSCORE = 8;
        public final int STYLE_BOLD = 1;
        public final int STYLE_BOLD_ITALIC = 3;
        public final int STYLE_ITALIC = 2;
        public final int STYLE_NORMAL = 0;
        private String charE;
        private String charS;
        private HashMap<String, CharacterStyle> styles = new HashMap<>();
        private HashMap<String, Integer> tokens = new HashMap<>();
        private ArrayList<String> unimplemented = new ArrayList<>();

        public void Initialize(String sampletoken) {
            this.styles.clear();
            this.charS = sampletoken.substring(0, 1);
            this.charE = sampletoken.substring(sampletoken.length() - 1);
        }

        public void AddToken(String token, int format, String data) {
            if (token.charAt(0) == this.charS.charAt(0) && token.charAt(token.length() - 1) == this.charE.charAt(0)) {
                switch (format) {
                    case 0:
                        this.styles.put(token, new BackgroundColorSpan(Integer.parseInt(data)));
                        return;
                    case 1:
                        this.styles.put(token, new ForegroundColorSpan(Integer.parseInt(data)));
                        return;
                    case 2:
                        this.styles.put(token, new RelativeSizeSpan(Float.parseFloat(data)));
                        return;
                    case 3:
                        this.styles.put(token, new StrikethroughSpan());
                        return;
                    case 4:
                        this.styles.put(token, new StyleSpan(Integer.parseInt(data)));
                        return;
                    case 5:
                        this.styles.put(token, new SubscriptSpan());
                        return;
                    case 6:
                        this.styles.put(token, new SuperscriptSpan());
                        return;
                    case 7:
                        this.styles.put(token, new TypefaceSpan(data));
                        return;
                    case 8:
                        this.styles.put(token, new UnderlineSpan());
                        return;
                    case 9:
                        this.styles.put(token, new ScaleXSpan(Float.parseFloat(data)));
                        return;
                    default:
                        return;
                }
            } else {
                throw new RuntimeException("Invalid start or end character for token " + token);
            }
        }

        public RichString Format(SpannableString richstring) {
            this.tokens.clear();
            this.unimplemented.clear();
            SpannableStringBuilder ssb = new SpannableStringBuilder(richstring);
            StringBuilder text = new StringBuilder(ssb.toString());
            int start = text.indexOf(this.charS);
            while (start > -1) {
                int end = text.indexOf(this.charE, start) + 1;
                if (end > 0) {
                    String token = text.substring(start, end);
                    ssb.delete(start, end);
                    text.delete(start, end);
                    if (this.styles.containsKey(token)) {
                        if (this.tokens.containsKey(token)) {
                            int end2 = end - token.length();
                            int start2 = this.tokens.get(token).intValue();
                            this.tokens.remove(token);
                            ssb.setSpan(CharacterStyle.wrap(this.styles.get(token)), start2, end2, 0);
                        } else {
                            this.tokens.put(token, Integer.valueOf(start));
                        }
                    } else if (!this.unimplemented.contains(token)) {
                        this.unimplemented.add(token);
                    }
                    start = text.indexOf(this.charS);
                } else {
                    start = -1;
                }
            }
            RichString rs = new RichString();
            rs.Initialize(ssb);
            return rs;
        }

        public String[] UnimplementedTokens() {
            return (String[]) this.unimplemented.toArray(new String[this.unimplemented.size()]);
        }

        public String[] UnPairedTokens() {
            Set<String> s = this.tokens.keySet();
            return (String[]) s.toArray(new String[s.size()]);
        }
    }
}
