package combib.libs.rtflabel;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.agraham.reflection.Reflection;
import anywheresoftware.b4a.agraham.richstring.RichStringBuilder;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.keywords.DateTime;
import anywheresoftware.b4a.keywords.Regex;
import anywheresoftware.b4a.keywords.StringBuilderWrapper;
import anywheresoftware.b4a.keywords.constants.Colors;
import anywheresoftware.b4a.keywords.constants.Gravity;
import anywheresoftware.b4a.keywords.constants.TypefaceWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ScrollViewWrapper;
import anywheresoftware.b4a.objects.StringUtils;
import anywheresoftware.b4a.objects.Timer;
import anywheresoftware.b4a.objects.collections.List;
import anywheresoftware.b4a.objects.collections.Map;
import anywheresoftware.b4a.objects.drawable.CanvasWrapper;
import anywheresoftware.b4a.objects.drawable.ColorDrawable;
import anywheresoftware.b4a.objects.drawable.StateListDrawable;
import anywheresoftware.b4a.objects.streams.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class rtflabel extends B4AClass.ImplB4AClass implements BA.SubDelegator {
    private static HashMap<String, Method> htSubs;
    public Common __c = null;
    public _clsrtflblparagraph[] _absaetze = null;
    public CanvasWrapper.BitmapWrapper _bildber = null;
    public Object _callback = null;
    public CanvasWrapper _canvasber = null;
    public boolean _classlock = false;
    public boolean _cmarkon = false;
    public boolean _cpagedown = false;
    public boolean _cpageup = false;
    public String _event = "";
    public boolean _eventsperre = false;
    public _clsrtflblframe[] _frames = null;
    public int _gesbr = 0;
    public int _gesho = 0;
    public ImageViewWrapper[] _imgmark = null;
    public LabelWrapper _labelber = null;
    public List _links = null;
    public _clsrtflbloptions _opt = null;
    public _clsrtflbltextmark _pmark = null;
    public StringUtils _strutils = null;
    public LabelWrapper _talbl = null;
    public LabelWrapper _tamsg = null;
    public PanelWrapper _tapanel = null;
    public ScrollViewWrapper _tasv = null;
    public _clsrtflbltextdata _textdat = null;
    public Timer _timer1 = null;
    public TypefaceWrapper[] _tyface = null;
    public _clsrtflblrowdata[] _zeiledat = null;
    public int _zh1 = 0;
    public int _zh2 = 0;
    public int _zhkorry = 0;

    private void innerInitialize(BA ba) throws Exception {
        if (this.ba == null) {
            this.ba = new BA(ba, this, htSubs, "combib.libs.rtflabel.rtflabel");
            if (htSubs == null) {
                this.ba.loadHtSubs(getClass());
                htSubs = this.ba.htSubs;
            }
        }
        _class_globals();
    }

    public static class _clsrtflbltextdata {
        public int AbsaetzeCount;
        public boolean IsInitialized;
        public int ScrollMax;
        public String Text;
        public int TextHeight;
        public int ZeilenCount;

        public void Initialize() {
            this.IsInitialized = true;
            this.Text = "";
            this.TextHeight = 0;
            this.ScrollMax = 0;
            this.AbsaetzeCount = 0;
            this.ZeilenCount = 0;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public static class _clsrtflblimage {
        public int Height;
        public boolean IsInitialized;
        public String Name;
        public int PosLeft;
        public int PosTop;
        public int Width;

        public void Initialize() {
            this.IsInitialized = true;
            this.Name = "";
            this.PosLeft = 0;
            this.PosTop = 0;
            this.Width = 0;
            this.Height = 0;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public static class _clsrtflblparagraph {
        public List Bilder;
        public int BilderCount;
        public int BilderGesHeight;
        public int BilderGesWidth;
        public String BulletChars;
        public int CharsColor;
        public int CharsFormat;
        public int DistanceTop;
        public int FrameID;
        public int IndentLevel;
        public int IndentMinWidth;
        public boolean IsInitialized;
        public int OnlyFirstLine;
        public int RightIndentLevel;
        public int TextColor;
        public float TextSize;
        public boolean WithFrame;
        public int ZeileBis;
        public int ZeileHo1;
        public int ZeileHo2;
        public int ZeileKorrY;
        public int ZeileVon;
        public int Zentriert;

        public void Initialize() {
            this.IsInitialized = true;
            this.ZeileVon = 0;
            this.ZeileBis = 0;
            this.IndentLevel = 0;
            this.IndentMinWidth = 0;
            this.BulletChars = "";
            this.CharsFormat = 0;
            this.CharsColor = 0;
            this.OnlyFirstLine = 0;
            this.RightIndentLevel = 0;
            this.WithFrame = false;
            this.FrameID = 0;
            this.DistanceTop = 0;
            this.TextColor = 0;
            this.TextSize = Common.Density;
            this.ZeileHo1 = 0;
            this.ZeileHo2 = 0;
            this.ZeileKorrY = 0;
            this.Zentriert = 0;
            this.Bilder = new List();
            this.BilderCount = 0;
            this.BilderGesHeight = 0;
            this.BilderGesWidth = 0;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public static class _clsrtflblrow {
        public int AbsatzID;
        public byte BeginnFormat;
        public boolean InList;
        public boolean IsInitialized;
        public int KorrZentriert;
        public int LblPosX;
        public int LblWidth;
        public int TextPosAb;
        public int TextPosBis;
        public StringBuilderWrapper TextRtf;
        public int ZeilePosTop;

        public void Initialize() {
            this.IsInitialized = true;
            this.AbsatzID = 0;
            this.TextPosAb = 0;
            this.TextPosBis = 0;
            this.LblPosX = 0;
            this.LblWidth = 0;
            this.ZeilePosTop = 0;
            this.BeginnFormat = 0;
            this.TextRtf = new StringBuilderWrapper();
            this.InList = false;
            this.KorrZentriert = 0;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public static class _clsrtflblrowdata {
        public int AbsatzID;
        public byte BeginnFormat;
        public boolean IsInitialized;
        public int KorrZentriert;
        public int LblPosX;
        public int LblWidth;
        public int TextPosAb;
        public int TextPosBis;
        public String TextRtf;
        public int ZeileHo2;
        public int ZeilePosTop;

        public void Initialize() {
            this.IsInitialized = true;
            this.AbsatzID = 0;
            this.TextPosAb = 0;
            this.TextPosBis = 0;
            this.LblPosX = 0;
            this.LblWidth = 0;
            this.BeginnFormat = 0;
            this.TextRtf = "";
            this.ZeilePosTop = 0;
            this.ZeileHo2 = 0;
            this.KorrZentriert = 0;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public static class _clsrtflbllink {
        public String EventText;
        public int Height;
        public boolean IsInitialized;
        public int Left;
        public int Top;
        public int Width;
        public int Zeile;

        public void Initialize() {
            this.IsInitialized = true;
            this.EventText = "";
            this.Left = 0;
            this.Top = 0;
            this.Width = 0;
            this.Height = 0;
            this.Zeile = 0;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public static class _clsrtflblframe {
        public int BorderWidth;
        public int Color;
        public int Height;
        public boolean IsInitialized;
        public int Left;
        public int Top;
        public int Width;

        public void Initialize() {
            this.IsInitialized = true;
            this.Left = 0;
            this.Top = 0;
            this.Width = 0;
            this.Height = 0;
            this.Color = 0;
            this.BorderWidth = 0;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public static class _clsrtflbltextmark {
        public boolean BisVorZeile;
        public int DownX;
        public int DownZeileA;
        public int DownZeileB;
        public boolean IsInitialized;
        public boolean IstAn;
        public int PressX;
        public int PressZeileA;
        public int PressZeileB;
        public int Scroll;
        public int ScrollMax;
        public int ScrollPos;
        public int TextPosBis;
        public int TextPosVon;
        public int TopIndex;
        public boolean VonVorZeile;
        public int ZeileMax;
        public int ZeileMin;

        public void Initialize() {
            this.IsInitialized = true;
            this.IstAn = false;
            this.TextPosVon = 0;
            this.TextPosBis = 0;
            this.DownX = 0;
            this.TopIndex = 0;
            this.PressX = 0;
            this.DownZeileA = 0;
            this.PressZeileA = 0;
            this.DownZeileB = 0;
            this.PressZeileB = 0;
            this.Scroll = 0;
            this.ScrollMax = 0;
            this.ScrollPos = 0;
            this.ZeileMin = 0;
            this.ZeileMax = 0;
            this.VonVorZeile = false;
            this.BisVorZeile = false;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public static class _clsrtflblmarkpositionvon {
        public boolean IsInitialized;
        public int LblVon;
        public int TextPosVon;
        public boolean VonVorZeile;

        public void Initialize() {
            this.IsInitialized = true;
            this.LblVon = 0;
            this.TextPosVon = 0;
            this.VonVorZeile = false;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public static class _clsrtflblmarkpositionbis {
        public boolean BisVorZeile;
        public boolean IsInitialized;
        public int LblBis;
        public int TextPosBis;

        public void Initialize() {
            this.IsInitialized = true;
            this.LblBis = 0;
            this.TextPosBis = 0;
            this.BisVorZeile = false;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public static class _clsrtflbloptions {
        public boolean AnzeigeGeloescht;
        public int[] BackColors;
        public int Color;
        public boolean Enabled;
        public Map Images;
        public int ImagesBorder;
        public int IndentWidth;
        public String IndentWidthString;
        public boolean IsInitialized;
        public int Left;
        public int LinkFormat;
        public int LinkPressedColor;
        public int LinkPressedCornerRadius;
        public int LinkTextColor;
        public int MarkColor;
        public boolean MarkFunction;
        public int TextBorder;
        public int TextColor;
        public int[] TextColors;
        public String[] TextColorsSignUsed;
        public int TextColorsSignUsedCount;
        public int[] TextColorsSignUsedId;
        public float TextSize;
        public float TextSizeSmall;
        public int Top;

        public void Initialize() {
            this.IsInitialized = true;
            this.Color = 0;
            this.TextColor = 0;
            this.TextSize = Common.Density;
            this.TextSizeSmall = Common.Density;
            this.TextBorder = 0;
            this.MarkFunction = false;
            this.MarkColor = 0;
            this.LinkFormat = 0;
            this.LinkTextColor = 0;
            this.Enabled = false;
            this.LinkPressedColor = 0;
            this.LinkPressedCornerRadius = 0;
            this.IndentWidth = 0;
            this.IndentWidthString = "";
            this.TextColors = new int[10];
            this.TextColorsSignUsed = new String[10];
            Arrays.fill(this.TextColorsSignUsed, "");
            this.TextColorsSignUsedId = new int[10];
            this.TextColorsSignUsedCount = 0;
            this.BackColors = new int[10];
            this.Images = new Map();
            this.ImagesBorder = 0;
            this.AnzeigeGeloescht = false;
            this.Left = 0;
            this.Top = 0;
        }

        public String toString() {
            return BA.TypeToString(this, false);
        }
    }

    public boolean _bringtofront() throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        this._tasv.BringToFront();
        this._tapanel.BringToFront();
        Common common2 = this.__c;
        return true;
    }

    public String _class_globals() throws Exception {
        this._classlock = false;
        this._tyface = new TypefaceWrapper[4];
        int length = this._tyface.length;
        for (int i = 0; i < length; i++) {
            this._tyface[i] = new TypefaceWrapper();
        }
        TypefaceWrapper typefaceWrapper = this._tyface[0];
        Common common = this.__c;
        TypefaceWrapper typefaceWrapper2 = Common.Typeface;
        typefaceWrapper.setObject(TypefaceWrapper.DEFAULT);
        TypefaceWrapper typefaceWrapper3 = this._tyface[1];
        Common common2 = this.__c;
        TypefaceWrapper typefaceWrapper4 = Common.Typeface;
        typefaceWrapper3.setObject(TypefaceWrapper.DEFAULT_BOLD);
        TypefaceWrapper typefaceWrapper5 = this._tyface[2];
        Common common3 = this.__c;
        TypefaceWrapper typefaceWrapper6 = Common.Typeface;
        typefaceWrapper5.setObject(TypefaceWrapper.DEFAULT);
        TypefaceWrapper typefaceWrapper7 = this._tyface[3];
        Common common4 = this.__c;
        TypefaceWrapper typefaceWrapper8 = Common.Typeface;
        typefaceWrapper7.setObject(TypefaceWrapper.DEFAULT);
        TypefaceWrapper typefaceWrapper9 = this._tyface[2];
        Common common5 = this.__c;
        TypefaceWrapper typefaceWrapper10 = Common.Typeface;
        Common common6 = this.__c;
        TypefaceWrapper typefaceWrapper11 = Common.Typeface;
        typefaceWrapper9.setObject(TypefaceWrapper.CreateNew((Typeface) this._tyface[2].getObject(), 2));
        TypefaceWrapper typefaceWrapper12 = this._tyface[3];
        Common common7 = this.__c;
        TypefaceWrapper typefaceWrapper13 = Common.Typeface;
        Common common8 = this.__c;
        TypefaceWrapper typefaceWrapper14 = Common.Typeface;
        typefaceWrapper12.setObject(TypefaceWrapper.CreateNew((Typeface) this._tyface[3].getObject(), 3));
        this._textdat = new _clsrtflbltextdata();
        this._textdat.Initialize();
        this._absaetze = new _clsrtflblparagraph[0];
        int length2 = this._absaetze.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this._absaetze[i2] = new _clsrtflblparagraph();
        }
        this._zeiledat = new _clsrtflblrowdata[0];
        int length3 = this._zeiledat.length;
        for (int i3 = 0; i3 < length3; i3++) {
            this._zeiledat[i3] = new _clsrtflblrowdata();
        }
        this._links = new List();
        this._links.Initialize();
        this._frames = new _clsrtflblframe[0];
        int length4 = this._frames.length;
        for (int i4 = 0; i4 < length4; i4++) {
            this._frames[i4] = new _clsrtflblframe();
        }
        this._pmark = new _clsrtflbltextmark();
        this._timer1 = new Timer();
        this._timer1.Initialize(this.ba, "Timer1", 100);
        this._imgmark = new ImageViewWrapper[0];
        int length5 = this._imgmark.length;
        for (int i5 = 0; i5 < length5; i5++) {
            this._imgmark[i5] = new ImageViewWrapper();
        }
        this._callback = new Object();
        this._event = "";
        this._opt = new _clsrtflbloptions();
        this._opt.Initialize();
        _clsrtflbloptions _clsrtflbloptions2 = this._opt;
        Common common9 = this.__c;
        _clsrtflbloptions2.Enabled = true;
        _clsrtflbloptions _clsrtflbloptions3 = this._opt;
        Common common10 = this.__c;
        _clsrtflbloptions3.ImagesBorder = Common.DipToCurrent(6);
        this._opt.Images.Initialize();
        this._opt.TextSizeSmall = 0.7f;
        _clsrtflbloptions _clsrtflbloptions4 = this._opt;
        Common common11 = this.__c;
        Colors colors = Common.Colors;
        _clsrtflbloptions4.Color = -1;
        _clsrtflbloptions _clsrtflbloptions5 = this._opt;
        Common common12 = this.__c;
        Colors colors2 = Common.Colors;
        _clsrtflbloptions5.TextColor = Colors.Black;
        this._opt.TextSize = 18.0f;
        _clsrtflbloptions _clsrtflbloptions6 = this._opt;
        Common common13 = this.__c;
        _clsrtflbloptions6.TextBorder = Common.DipToCurrent(2);
        _clsrtflbloptions _clsrtflbloptions7 = this._opt;
        Common common14 = this.__c;
        _clsrtflbloptions7.MarkFunction = false;
        _clsrtflbloptions _clsrtflbloptions8 = this._opt;
        Common common15 = this.__c;
        Colors colors3 = Common.Colors;
        _clsrtflbloptions8.MarkColor = Colors.ARGB(255, 255, 195, 33);
        this._opt.LinkFormat = 1;
        _clsrtflbloptions _clsrtflbloptions9 = this._opt;
        Common common16 = this.__c;
        Colors colors4 = Common.Colors;
        _clsrtflbloptions9.LinkPressedColor = Colors.ARGB(255, 255, 195, 33);
        this._opt.LinkPressedCornerRadius = 0;
        _clsrtflbloptions _clsrtflbloptions10 = this._opt;
        Common common17 = this.__c;
        Colors colors5 = Common.Colors;
        _clsrtflbloptions10.LinkTextColor = Colors.Blue;
        this._opt.IndentWidthString = "xxx";
        int[] iArr = this._opt.TextColors;
        Common common18 = this.__c;
        Colors colors6 = Common.Colors;
        iArr[0] = -16776961;
        int[] iArr2 = this._opt.TextColors;
        Common common19 = this.__c;
        Colors colors7 = Common.Colors;
        iArr2[1] = -16711681;
        int[] iArr3 = this._opt.TextColors;
        Common common20 = this.__c;
        Colors colors8 = Common.Colors;
        iArr3[2] = -12303292;
        int[] iArr4 = this._opt.TextColors;
        Common common21 = this.__c;
        Colors colors9 = Common.Colors;
        iArr4[3] = -7829368;
        int[] iArr5 = this._opt.TextColors;
        Common common22 = this.__c;
        Colors colors10 = Common.Colors;
        iArr5[4] = -16711936;
        int[] iArr6 = this._opt.TextColors;
        Common common23 = this.__c;
        Colors colors11 = Common.Colors;
        iArr6[5] = -3355444;
        int[] iArr7 = this._opt.TextColors;
        Common common24 = this.__c;
        Colors colors12 = Common.Colors;
        iArr7[6] = -65281;
        int[] iArr8 = this._opt.TextColors;
        Common common25 = this.__c;
        Colors colors13 = Common.Colors;
        iArr8[7] = -65536;
        int[] iArr9 = this._opt.TextColors;
        Common common26 = this.__c;
        Colors colors14 = Common.Colors;
        iArr9[8] = -256;
        int[] iArr10 = this._opt.TextColors;
        Common common27 = this.__c;
        Colors colors15 = Common.Colors;
        iArr10[9] = -16777216;
        this._cpageup = false;
        this._cpagedown = false;
        this._cmarkon = false;
        this._tasv = new ScrollViewWrapper();
        this._talbl = new LabelWrapper();
        this._tamsg = new LabelWrapper();
        this._tapanel = new PanelWrapper();
        this._labelber = new LabelWrapper();
        this._bildber = new CanvasWrapper.BitmapWrapper();
        this._canvasber = new CanvasWrapper();
        this._bildber.InitializeMutable(1, 1);
        this._canvasber.Initialize2((Bitmap) this._bildber.getObject());
        this._strutils = new StringUtils();
        this._zh1 = 0;
        this._zh2 = 0;
        this._zhkorry = 0;
        this._eventsperre = false;
        this._gesbr = 0;
        this._gesho = 0;
        return "";
    }

    public boolean _clearimages() throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        this._opt.Images.Clear();
        Common common2 = this.__c;
        return true;
    }

    public boolean _getavailablemarkon() throws Exception {
        boolean z = this._opt.Enabled;
        Common common = this.__c;
        if (z) {
            return this._cmarkon;
        }
        Common common2 = this.__c;
        return false;
    }

    public boolean _getavailablepagedown() throws Exception {
        boolean z = this._opt.Enabled;
        Common common = this.__c;
        if (z) {
            return this._cpagedown;
        }
        Common common2 = this.__c;
        return false;
    }

    public boolean _getavailablepageup() throws Exception {
        boolean z = this._opt.Enabled;
        Common common = this.__c;
        if (z) {
            return this._cpageup;
        }
        Common common2 = this.__c;
        return false;
    }

    public Object _getbackground() throws Exception {
        if (!this._classlock) {
            return this._tasv.getBackground();
        }
        Common common = this.__c;
        return false;
    }

    public boolean _getenabled() throws Exception {
        if (!this._classlock) {
            return this._opt.Enabled;
        }
        Common common = this.__c;
        return false;
    }

    public int _getheight() throws Exception {
        return this._gesho;
    }

    public int _getleft() throws Exception {
        return this._opt.Left;
    }

    public Object _gettag() throws Exception {
        if (!this._classlock) {
            return this._tasv.getTag();
        }
        Common common = this.__c;
        return false;
    }

    public int _gettextheight() throws Exception {
        return this._textdat.TextHeight;
    }

    public Object _gettextpositiontop() throws Exception {
        int scrollPosition;
        int i = -1;
        if (this._classlock) {
            return -1;
        }
        int _xtopindex = _xtopindex(this._tasv.getScrollPosition());
        if (_xtopindex == -1) {
            scrollPosition = 0;
        } else {
            i = this._zeiledat[_xtopindex].TextPosAb;
            scrollPosition = this._zeiledat[_xtopindex].ZeilePosTop - this._tasv.getScrollPosition();
        }
        return BA.NumberToString(i) + "/" + BA.NumberToString(scrollPosition);
    }

    public int _gettop() throws Exception {
        return this._opt.Top;
    }

    public boolean _getvisible() throws Exception {
        if (!this._classlock) {
            return this._tapanel.getVisible();
        }
        Common common = this.__c;
        return false;
    }

    public int _getwidth() throws Exception {
        return this._gesbr;
    }

    public boolean _gotoanchor(String str) throws Exception {
        int i = 0;
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        boolean z = this._pmark.IstAn;
        Common common2 = this.__c;
        if (z) {
            _markoff();
        }
        String str2 = "{a/" + str + "}";
        int indexOf = this._textdat.Text.indexOf(str2);
        if (indexOf == -1) {
            Common common3 = this.__c;
            return false;
        }
        int length = indexOf + str2.length();
        double d = (double) (this._textdat.ZeilenCount - 1);
        int i2 = 0;
        while (true) {
            if (((double) i2) <= d) {
                if (length <= this._zeiledat[i2].TextPosBis) {
                    break;
                }
                i2 = (int) (((double) i2) + 1.0d);
            } else {
                i2 = 0;
                break;
            }
        }
        if (i2 != 0) {
            int i3 = this._zeiledat[i2].ZeilePosTop;
            Common common4 = this.__c;
            i = i3 - Common.DipToCurrent(10);
        }
        if (i > this._textdat.ScrollMax) {
            i = this._textdat.ScrollMax;
        }
        this._tasv.setScrollPosition(i);
        Common common5 = this.__c;
        Common.DoEvents();
        this._tasv.setScrollPosition(i);
        Common common6 = this.__c;
        return true;
    }

    public boolean _gotofirstrow() throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        boolean z = this._pmark.IstAn;
        Common common2 = this.__c;
        if (z) {
            _markoff();
        }
        ScrollViewWrapper scrollViewWrapper = this._tasv;
        Common common3 = this.__c;
        scrollViewWrapper.FullScroll(false);
        Common common4 = this.__c;
        return true;
    }

    public boolean _gotolastrow() throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        boolean z = this._pmark.IstAn;
        Common common2 = this.__c;
        if (z) {
            _markoff();
        }
        ScrollViewWrapper scrollViewWrapper = this._tasv;
        Common common3 = this.__c;
        scrollViewWrapper.FullScroll(true);
        Common common4 = this.__c;
        return true;
    }

    public boolean _initialize(BA ba, Object obj, String str, Object obj2, int i, int i2, int i3, int i4) throws Exception {
        innerInitialize(ba);
        this._callback = obj;
        this._event = str;
        this._gesbr = i3;
        this._gesho = i4;
        this._opt.Left = i;
        this._opt.Top = i2;
        if (i3 < 10 || i4 < 10) {
            Common common = this.__c;
            StringBuilder append = new StringBuilder().append(this._event).append(".Initialize");
            Common common2 = this.__c;
            Common.Msgbox(append.append(Common.CRLF).append("Incorrect values for 'Width' or 'Height'!").toString(), "Error", this.ba);
            Common common3 = this.__c;
            this._classlock = true;
            Common common4 = this.__c;
            return false;
        } else if (str.trim().equals("")) {
            Common common5 = this.__c;
            StringBuilder append2 = new StringBuilder().append(this._event).append("RtfLabel - Initialize");
            Common common6 = this.__c;
            Common.Msgbox(append2.append(Common.CRLF).append("Incorrect values for 'EventName'!").toString(), "Error", this.ba);
            Common common7 = this.__c;
            this._classlock = true;
            Common common8 = this.__c;
            return false;
        } else {
            try {
                Common common9 = this.__c;
                Common.SubExists(this.ba, this._callback, this._event + "_MarkOnAvailable");
                this._tasv.Initialize2(this.ba, 0, "taSv");
                this._tasv.setColor(this._opt.Color);
                this._tapanel.Initialize(this.ba, "");
                this._labelber.Initialize(this.ba, "");
                if (obj2 instanceof BALayout) {
                    ActivityWrapper activityWrapper = new ActivityWrapper();
                    activityWrapper.setObject((BALayout) obj2);
                    activityWrapper.AddView((View) this._tasv.getObject(), i, i2, this._gesbr, this._gesho);
                    activityWrapper.AddView((View) this._tapanel.getObject(), i, i2, this._gesbr, this._gesho);
                } else if (obj2 instanceof ViewGroup) {
                    PanelWrapper panelWrapper = new PanelWrapper();
                    panelWrapper.setObject((ViewGroup) obj2);
                    panelWrapper.AddView((View) this._tasv.getObject(), i, i2, this._gesbr, this._gesho);
                    panelWrapper.AddView((View) this._tapanel.getObject(), i, i2, this._gesbr, this._gesho);
                } else {
                    Common common10 = this.__c;
                    StringBuilder append3 = new StringBuilder().append(this._event).append(".Initialize");
                    Common common11 = this.__c;
                    Common.Msgbox(append3.append(Common.CRLF).append("'Parent' must be Activity or Panel!").toString(), "Error", this.ba);
                    Common common12 = this.__c;
                    this._classlock = true;
                    Common common13 = this.__c;
                    return false;
                }
                Reflection reflection = new Reflection();
                reflection.Target = this._tapanel.getObject();
                reflection.SetOnTouchListener(this.ba, "taPanel_Touch");
                LabelWrapper labelWrapper = this._labelber;
                Common common14 = this.__c;
                labelWrapper.setVisible(false);
                this._tapanel.AddView((View) this._labelber.getObject(), i, i2, 300, 10);
                _xanzeigeloeschen();
                _xschriftgroesseeinrichten();
                Common common15 = this.__c;
                return true;
            } catch (Exception e) {
                this.ba.setLastException(e);
                Common common16 = this.__c;
                StringBuilder append4 = new StringBuilder().append(this._event).append(".Initialize");
                Common common17 = this.__c;
                Common.Msgbox(append4.append(Common.CRLF).append("Incorrect values for 'CallbackModule'!").toString(), "Error", this.ba);
                Common common18 = this.__c;
                this._classlock = true;
                Common common19 = this.__c;
                return false;
            }
        }
    }

    public boolean _markoff() throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        boolean z = this._pmark.IstAn;
        Common common2 = this.__c;
        if (!z) {
            Common common3 = this.__c;
            return false;
        }
        double d = (double) (this._textdat.ZeilenCount - 1);
        for (int i = 0; ((double) i) <= d; i = (int) (((double) i) + 1.0d)) {
            ImageViewWrapper imageViewWrapper = this._imgmark[i];
            Common common4 = this.__c;
            imageViewWrapper.setVisible(false);
        }
        this._pmark.Initialize();
        Common common5 = this.__c;
        if (Common.SubExists(this.ba, this._callback, this._event + "_MarkOff")) {
            Common common6 = this.__c;
            Common.CallSubNew(this.ba, this._callback, this._event + "_MarkOff");
        }
        boolean z2 = this._eventsperre;
        Common common7 = this.__c;
        if (z2) {
            Common common8 = this.__c;
            return false;
        }
        _tasv_scrollchanged(this._tasv.getScrollPosition());
        Common common9 = this.__c;
        return true;
    }

    public boolean _markon() throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        boolean z = this._opt.Enabled;
        Common common2 = this.__c;
        if (!z) {
            Common common3 = this.__c;
            return false;
        }
        boolean z2 = this._opt.MarkFunction;
        Common common4 = this.__c;
        if (z2) {
            boolean z3 = this._cmarkon;
            Common common5 = this.__c;
            if (z3) {
                boolean z4 = this._pmark.IstAn;
                Common common6 = this.__c;
                if (z4) {
                    Common common7 = this.__c;
                    return true;
                }
                Common common8 = this.__c;
                this._cpageup = false;
                Common common9 = this.__c;
                this._cpagedown = false;
                Common common10 = this.__c;
                if (Common.SubExists(this.ba, this._callback, this._event + "_PageUpDownAvailable")) {
                    Common common11 = this.__c;
                    Common.CallSubNew3(this.ba, this._callback, this._event + "_PageUpDownAvailable", Boolean.valueOf(this._cpageup), Boolean.valueOf(this._cpagedown));
                }
                this._pmark.Initialize();
                _clsrtflbltextmark _clsrtflbltextmark2 = this._pmark;
                Common common12 = this.__c;
                _clsrtflbltextmark2.IstAn = true;
                this._pmark.ScrollMax = this._textdat.ScrollMax;
                this._pmark.ScrollPos = this._tasv.getScrollPosition();
                Common common13 = this.__c;
                return true;
            }
        }
        Common common14 = this.__c;
        return false;
    }

    public boolean _pagedown() throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        boolean z = this._cpagedown;
        Common common2 = this.__c;
        if (!z) {
            Common common3 = this.__c;
            return false;
        }
        int scrollPosition = this._tasv.getScrollPosition() + this._gesho;
        Common common4 = this.__c;
        int DipToCurrent = scrollPosition - Common.DipToCurrent(24);
        if (DipToCurrent > this._textdat.ScrollMax) {
            DipToCurrent = this._textdat.ScrollMax;
        }
        this._tasv.setScrollPosition(DipToCurrent);
        Common common5 = this.__c;
        return true;
    }

    public boolean _pageup() throws Exception {
        int i = 0;
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        boolean z = this._cpageup;
        Common common2 = this.__c;
        if (!z) {
            Common common3 = this.__c;
            return false;
        }
        int scrollPosition = this._tasv.getScrollPosition() - this._gesho;
        Common common4 = this.__c;
        int DipToCurrent = scrollPosition + Common.DipToCurrent(24);
        if (DipToCurrent >= 0) {
            i = DipToCurrent;
        }
        this._tasv.setScrollPosition(i);
        Common common5 = this.__c;
        return true;
    }

    public boolean _removeview() throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        this._tapanel.RemoveView();
        this._tasv.RemoveView();
        _textclear();
        Common common2 = this.__c;
        this._classlock = true;
        Common common3 = this.__c;
        return true;
    }

    public boolean _sendtoback() throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        this._tapanel.SendToBack();
        this._tasv.SendToBack();
        Common common2 = this.__c;
        return true;
    }

    public String _setbackground(Object obj) throws Exception {
        if (this._classlock) {
            return "";
        }
        try {
            this._tasv.setBackground((Drawable) obj);
        } catch (Exception e) {
            this.ba.setLastException(e);
            Common common = this.__c;
            Common.Log("RtfLabel (" + this._event + ") invalid value for BackGround!");
        }
        return "";
    }

    public boolean _setbackgroundimage(CanvasWrapper.BitmapWrapper bitmapWrapper) throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        } else if (bitmapWrapper.getObjectOrNull() instanceof Bitmap) {
            this._tasv.SetBackgroundImage((Bitmap) bitmapWrapper.getObject());
            Common common2 = this.__c;
            return true;
        } else {
            Common common3 = this.__c;
            return false;
        }
    }

    public String _setcolor(int i) throws Exception {
        if (this._classlock) {
            return "";
        }
        this._opt.Color = i;
        this._tasv.setColor(i);
        return "";
    }

    public String _setenabled(boolean z) throws Exception {
        if (this._classlock || this._opt.Enabled == z) {
            return "";
        }
        this._opt.Enabled = z;
        boolean z2 = this._opt.Enabled;
        Common common = this.__c;
        if (z2) {
            if (this._textdat.ZeilenCount > 0) {
                boolean z3 = this._opt.MarkFunction;
                Common common2 = this.__c;
                if (z3) {
                    Common common3 = this.__c;
                    this._cmarkon = true;
                    Common common4 = this.__c;
                    if (Common.SubExists(this.ba, this._callback, this._event + "_MarkOnAvailable")) {
                        Common common5 = this.__c;
                        Common.CallSubNew2(this.ba, this._callback, this._event + "_MarkOnAvailable", Boolean.valueOf(this._cmarkon));
                    }
                }
            }
            _tasv_scrollchanged(this._tasv.getScrollPosition());
        } else {
            boolean z4 = this._pmark.IstAn;
            Common common6 = this.__c;
            if (z4) {
                double d = (double) (this._textdat.ZeilenCount - 1);
                for (int i = 0; ((double) i) <= d; i = (int) (((double) i) + 1.0d)) {
                    ImageViewWrapper imageViewWrapper = this._imgmark[i];
                    Common common7 = this.__c;
                    imageViewWrapper.setVisible(false);
                }
                this._pmark.Initialize();
                Common common8 = this.__c;
                if (Common.SubExists(this.ba, this._callback, this._event + "_MarkOff")) {
                    Common common9 = this.__c;
                    Common.CallSubNew(this.ba, this._callback, this._event + "_MarkOff");
                }
            }
            Common common10 = this.__c;
            this._cpageup = false;
            Common common11 = this.__c;
            this._cpagedown = false;
            Common common12 = this.__c;
            this._cmarkon = false;
            Common common13 = this.__c;
            if (Common.SubExists(this.ba, this._callback, this._event + "_MarkOnAvailable")) {
                Common common14 = this.__c;
                Common.CallSubNew2(this.ba, this._callback, this._event + "_MarkOnAvailable", Boolean.valueOf(this._cmarkon));
            }
            Common common15 = this.__c;
            if (Common.SubExists(this.ba, this._callback, this._event + "_PageUpDownAvailable")) {
                Common common16 = this.__c;
                Common.CallSubNew3(this.ba, this._callback, this._event + "_PageUpDownAvailable", Boolean.valueOf(this._cpageup), Boolean.valueOf(this._cpagedown));
            }
        }
        return "";
    }

    public boolean _setframecolors(int i, int i2) throws Exception {
        if (i < 0 || i > 9) {
            Common common = this.__c;
            return false;
        }
        this._opt.BackColors[i] = i2;
        Common common2 = this.__c;
        return true;
    }

    public String _setheight(int i) throws Exception {
        if (this._classlock) {
            return "";
        }
        if (i < 10) {
            i = 10;
        }
        this._gesho = i;
        this._tasv.setHeight(i);
        this._tapanel.setHeight(i);
        this._textdat.ScrollMax = this._textdat.TextHeight - this._gesho;
        if (this._textdat.ScrollMax < 0) {
            this._textdat.ScrollMax = 0;
        }
        _tasv_scrollchanged(this._tasv.getScrollPosition());
        return "";
    }

    public boolean _setimages(String str, CanvasWrapper.BitmapWrapper bitmapWrapper) throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        String trim = str.trim();
        if (trim.equals("")) {
            Common common2 = this.__c;
            return false;
        }
        this._opt.Images.Put(trim, bitmapWrapper.getObject());
        Common common3 = this.__c;
        return true;
    }

    public String _setimagesborder(int i) throws Exception {
        if (this._classlock) {
            return "";
        }
        if (i < 0) {
            i = 0;
        }
        this._opt.ImagesBorder = i;
        _xanzeigeloeschen();
        return "";
    }

    public String _setindentwidth(String str) throws Exception {
        if (str.length() < 1) {
            str = "xxx";
        }
        this._opt.IndentWidthString = str;
        _xanzeigeloeschen();
        return "";
    }

    public boolean _setlayout(int i, int i2, int i3, int i4) throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        _markoff();
        if (i3 != this._gesbr) {
            _xanzeigeloeschen();
        }
        this._tasv.SetLayout(i, i2, i3, i4);
        this._tapanel.SetLayout(i, i2, i3, i4);
        if (i3 != this._gesbr) {
            this._gesbr = i3;
            this._gesho = i4;
            _xanzeigeloeschen();
        } else {
            this._gesho = i4;
            this._textdat.ScrollMax = this._textdat.TextHeight - this._gesho;
            if (this._textdat.ScrollMax < 0) {
                this._textdat.ScrollMax = 0;
            }
            _tasv_scrollchanged(this._tasv.getScrollPosition());
        }
        Common common2 = this.__c;
        return true;
    }

    public String _setleft(int i) throws Exception {
        if (this._classlock) {
            return "";
        }
        this._opt.Left = i;
        this._tasv.setLeft(i);
        this._tapanel.setLeft(i);
        return "";
    }

    public String _setlinkformat(int i) throws Exception {
        if (i < 1 || i > 3) {
            i = 1;
        }
        this._opt.LinkFormat = i;
        _xanzeigeloeschen();
        return "";
    }

    public String _setlinkpressedcolor(int i) throws Exception {
        this._opt.LinkPressedColor = i;
        _xanzeigeloeschen();
        return "";
    }

    public String _setlinkpressedcornerradius(int i) throws Exception {
        this._opt.LinkPressedCornerRadius = i;
        _xanzeigeloeschen();
        return "";
    }

    public String _setlinktextcolor(int i) throws Exception {
        this._opt.LinkTextColor = i;
        _xanzeigeloeschen();
        return "";
    }

    public String _setmarkcolor(int i) throws Exception {
        this._opt.MarkColor = i;
        _xanzeigeloeschen();
        return "";
    }

    public String _setmarkfunction(boolean z) throws Exception {
        this._opt.MarkFunction = z;
        _xanzeigeloeschen();
        return "";
    }

    public String _settag(Object obj) throws Exception {
        if (this._classlock) {
            return "";
        }
        this._tasv.setTag(obj);
        return "";
    }

    public String _settext(String str) throws Exception {
        this._textdat.Text = str;
        _xanzeigeloeschen();
        return "";
    }

    public String _settextborder(int i) throws Exception {
        if (i < 0) {
            i = 0;
        }
        Common common = this.__c;
        if (i > Common.DipToCurrent(12)) {
            Common common2 = this.__c;
            i = Common.DipToCurrent(12);
        }
        this._opt.TextBorder = i;
        _xanzeigeloeschen();
        return "";
    }

    public String _settextcolor(int i) throws Exception {
        this._opt.TextColor = i;
        _xanzeigeloeschen();
        return "";
    }

    public boolean _settextcolors(int i, int i2) throws Exception {
        if (i < 0 || i > 9) {
            Common common = this.__c;
            return false;
        }
        this._opt.TextColors[i] = i2;
        Common common2 = this.__c;
        return true;
    }

    public String _settextpositiontop(Object obj) throws Exception {
        int i = 0;
        if (this._classlock) {
            return "";
        }
        boolean z = this._pmark.IstAn;
        Common common = this.__c;
        if (z) {
            _markoff();
        }
        try {
            String valueOf = String.valueOf(obj);
            int indexOf = valueOf.indexOf("/");
            if (indexOf < 1) {
                return "";
            }
            int parseDouble = (int) Double.parseDouble(valueOf.substring(0, indexOf));
            int parseDouble2 = (int) Double.parseDouble(valueOf.substring(indexOf + 1));
            if (parseDouble > this._textdat.Text.length() - 1 || parseDouble < 0) {
                return "";
            }
            double d = (double) (this._textdat.ZeilenCount - 1);
            int i2 = 0;
            while (true) {
                if (((double) i2) <= d) {
                    if (parseDouble <= this._zeiledat[i2].TextPosBis) {
                        break;
                    }
                    i2 = (int) (((double) i2) + 1.0d);
                } else {
                    i2 = 0;
                    break;
                }
            }
            if (i2 != 0) {
                int i3 = this._zeiledat[i2].ZeilePosTop - parseDouble2;
                if (i3 > this._textdat.ScrollMax) {
                    i3 = this._textdat.ScrollMax;
                }
                if (i3 >= 0) {
                    i = i3;
                }
            }
            this._tasv.setScrollPosition(i);
            Common common2 = this.__c;
            Common.DoEvents();
            this._tasv.setScrollPosition(i);
            return "";
        } catch (Exception e) {
            this.ba.setLastException(e);
            Common common3 = this.__c;
            Common.Log("Error in SetTextPosTop");
        }
    }

    public String _settextsize(float f) throws Exception {
        if (this._classlock) {
            return "";
        }
        if (f < 8.0f) {
            f = 8.0f;
        }
        this._opt.TextSize = f;
        _xanzeigeloeschen();
        _xschriftgroesseeinrichten();
        return "";
    }

    public String _settextsizesmall(float f) throws Exception {
        float f2;
        if (((double) f) < 0.5d) {
            f2 = 0.5f;
        } else {
            f2 = f;
        }
        if (((double) f2) > 0.9d) {
            f2 = 0.9f;
        }
        this._opt.TextSizeSmall = f2;
        _xanzeigeloeschen();
        return "";
    }

    public String _settop(int i) throws Exception {
        if (this._classlock) {
            return "";
        }
        this._opt.Top = i;
        this._tasv.setTop(i);
        this._tapanel.setTop(i);
        return "";
    }

    public String _settypeface(TypefaceWrapper typefaceWrapper) throws Exception {
        this._tyface[0] = typefaceWrapper;
        TypefaceWrapper typefaceWrapper2 = this._tyface[1];
        Common common = this.__c;
        TypefaceWrapper typefaceWrapper3 = Common.Typeface;
        Common common2 = this.__c;
        TypefaceWrapper typefaceWrapper4 = Common.Typeface;
        typefaceWrapper2.setObject(TypefaceWrapper.CreateNew((Typeface) typefaceWrapper.getObject(), 1));
        TypefaceWrapper typefaceWrapper5 = this._tyface[2];
        Common common3 = this.__c;
        TypefaceWrapper typefaceWrapper6 = Common.Typeface;
        Common common4 = this.__c;
        TypefaceWrapper typefaceWrapper7 = Common.Typeface;
        typefaceWrapper5.setObject(TypefaceWrapper.CreateNew((Typeface) typefaceWrapper.getObject(), 2));
        TypefaceWrapper typefaceWrapper8 = this._tyface[3];
        Common common5 = this.__c;
        TypefaceWrapper typefaceWrapper9 = Common.Typeface;
        Common common6 = this.__c;
        TypefaceWrapper typefaceWrapper10 = Common.Typeface;
        typefaceWrapper8.setObject(TypefaceWrapper.CreateNew((Typeface) typefaceWrapper.getObject(), 3));
        _xanzeigeloeschen();
        _xschriftgroesseeinrichten();
        return "";
    }

    public String _setvisible(boolean z) throws Exception {
        if (this._classlock) {
            return "";
        }
        boolean z2 = this._pmark.IstAn;
        Common common = this.__c;
        if (z2) {
            _markoff();
        }
        this._tasv.setVisible(z);
        this._tapanel.setVisible(z);
        return "";
    }

    public String _setwidth(int i) throws Exception {
        if (this._classlock) {
            return "";
        }
        if (i < 10) {
            i = 10;
        }
        this._gesbr = i;
        _markoff();
        this._tasv.setWidth(i);
        this._tapanel.setWidth(i);
        _xanzeigeloeschen();
        return "";
    }

    public String _talbllink_click() throws Exception {
        LabelWrapper labelWrapper = new LabelWrapper();
        Common common = this.__c;
        labelWrapper.setObject((TextView) Common.Sender(this.ba));
        Common common2 = this.__c;
        if (!Common.SubExists(this.ba, this._callback, this._event + "_LinkClick")) {
            return "";
        }
        Common common3 = this.__c;
        Common.CallSubNew2(this.ba, this._callback, this._event + "_LinkClick", labelWrapper.getTag());
        return "";
    }

    public boolean _tapanel_touch(Object obj, int i, float f, float f2, Object obj2) throws Exception {
        boolean z = this._opt.Enabled;
        Common common = this.__c;
        if (!z) {
            Common common2 = this.__c;
            return true;
        }
        if (this._textdat.ZeilenCount >= 1) {
            boolean z2 = this._pmark.IstAn;
            Common common3 = this.__c;
            if (z2) {
                boolean z3 = this._opt.MarkFunction;
                Common common4 = this.__c;
                if (z3) {
                    switch (i) {
                        case 0:
                            double d = (double) this._pmark.ZeileMax;
                            for (int i2 = this._pmark.ZeileMin; ((double) i2) <= d; i2 = (int) (((double) i2) + 1.0d)) {
                                ImageViewWrapper imageViewWrapper = this._imgmark[i2];
                                Common common5 = this.__c;
                                imageViewWrapper.setVisible(false);
                            }
                            this._pmark.TextPosVon = -1;
                            this._pmark.TextPosBis = -1;
                            _clsrtflbltextmark _clsrtflbltextmark2 = this._pmark;
                            Common common6 = this.__c;
                            _clsrtflbltextmark2.BisVorZeile = false;
                            _clsrtflbltextmark _clsrtflbltextmark3 = this._pmark;
                            Common common7 = this.__c;
                            _clsrtflbltextmark3.VonVorZeile = false;
                            this._pmark.DownZeileA = -1;
                            this._pmark.DownZeileB = -1;
                            this._pmark.PressZeileA = -1;
                            this._pmark.PressZeileB = -1;
                            this._pmark.ScrollPos = this._tasv.getScrollPosition();
                            this._pmark.TopIndex = _xtopindex(this._pmark.ScrollPos);
                            int i3 = (int) (((float) this._pmark.ScrollPos) + f2);
                            int i4 = -1;
                            double d2 = (double) (this._textdat.ZeilenCount - 1);
                            int i5 = 0;
                            while (true) {
                                if (((double) i5) <= d2) {
                                    if (i3 < this._zeiledat[i5].ZeilePosTop + this._zeiledat[i5].ZeileHo2) {
                                        i4 = i5;
                                    } else {
                                        i5 = (int) (((double) i5) + 1.0d);
                                    }
                                }
                            }
                            if (i4 == -1) {
                                i4 = this._textdat.ZeilenCount - 1;
                            }
                            if (i3 < this._zeiledat[i4].ZeilePosTop) {
                                this._pmark.DownZeileA = i4;
                                this._pmark.PressZeileA = i4;
                            } else {
                                this._pmark.DownZeileB = i4;
                                this._pmark.PressZeileB = i4;
                            }
                            int i6 = (int) ((f - ((float) this._opt.TextBorder)) - ((float) this._zeiledat[i4].KorrZentriert));
                            if (i6 < 0) {
                                i6 = 0;
                            }
                            this._pmark.DownX = i6;
                            this._pmark.PressX = i6;
                            this._pmark.Scroll = 0;
                            this._pmark.ZeileMin = i4;
                            this._pmark.ZeileMax = i4;
                            _xtextmarkieren("Down");
                            break;
                        case 1:
                            _xtextmarkieren("Up");
                            Timer timer = this._timer1;
                            Common common8 = this.__c;
                            timer.setEnabled(false);
                            break;
                        case 2:
                            int i7 = (int) (((float) this._pmark.ScrollPos) + f2);
                            int i8 = -1;
                            double d3 = (double) (this._textdat.ZeilenCount - 1);
                            int i9 = 0;
                            while (true) {
                                if (((double) i9) <= d3) {
                                    if (i7 < this._zeiledat[i9].ZeilePosTop + this._zeiledat[i9].ZeileHo2) {
                                        i8 = i9 > this._textdat.ZeilenCount - 1 ? this._textdat.ZeilenCount - 1 : i9;
                                        if (i8 < 0) {
                                            i8 = 0;
                                        }
                                    } else {
                                        i9 = (int) (((double) i9) + 1.0d);
                                    }
                                }
                            }
                            if (i8 == -1) {
                                i8 = this._textdat.ZeilenCount - 1;
                            }
                            if (i7 < this._zeiledat[i8].ZeilePosTop) {
                                this._pmark.PressZeileA = i8;
                                this._pmark.PressZeileB = -1;
                            } else {
                                this._pmark.PressZeileB = i8;
                                this._pmark.PressZeileA = -1;
                            }
                            int i10 = (int) ((f - ((float) this._opt.TextBorder)) - ((float) this._zeiledat[i8].KorrZentriert));
                            if (i10 < 0) {
                                i10 = 0;
                            }
                            this._pmark.PressX = i10;
                            if (this._pmark.TopIndex <= 0 || f2 > ((float) this._zeiledat[i8].ZeileHo2)) {
                                if (this._pmark.ScrollPos >= this._pmark.ScrollMax || f2 < ((float) (this._gesho - this._zeiledat[i8].ZeileHo2))) {
                                    Timer timer2 = this._timer1;
                                    Common common9 = this.__c;
                                    timer2.setEnabled(false);
                                    _xtextmarkieren("Move");
                                    break;
                                } else {
                                    this._pmark.Scroll = 1;
                                    Timer timer3 = this._timer1;
                                    Common common10 = this.__c;
                                    timer3.setEnabled(true);
                                    break;
                                }
                            } else {
                                this._pmark.Scroll = -1;
                                Timer timer4 = this._timer1;
                                Common common11 = this.__c;
                                timer4.setEnabled(true);
                                break;
                            }
                            break;
                    }
                    Common common12 = this.__c;
                    return true;
                }
            }
        }
        Common common13 = this.__c;
        return false;
    }

    public String _tasv_scrollchanged(int i) throws Exception {
        boolean z = this._pmark.IstAn;
        Common common = this.__c;
        if (z) {
            Common common2 = this.__c;
            this._cpageup = false;
            Common common3 = this.__c;
            this._cpagedown = false;
            return "";
        }
        boolean z2 = this._cpageup;
        boolean z3 = this._cpagedown;
        if (i > 0) {
            Common common4 = this.__c;
            this._cpageup = true;
        } else {
            Common common5 = this.__c;
            this._cpageup = false;
        }
        if (i < this._textdat.ScrollMax) {
            Common common6 = this.__c;
            this._cpagedown = true;
        } else {
            Common common7 = this.__c;
            this._cpagedown = false;
        }
        boolean z4 = this._opt.Enabled;
        Common common8 = this.__c;
        if (!z4 || this._textdat.ZeilenCount == 0) {
            Common common9 = this.__c;
            this._cpageup = false;
            Common common10 = this.__c;
            this._cpagedown = false;
        }
        if (z2 == this._cpageup && z3 == this._cpagedown) {
            return "";
        }
        Common common11 = this.__c;
        if (!Common.SubExists(this.ba, this._callback, this._event + "_PageUpDownAvailable")) {
            return "";
        }
        Common common12 = this.__c;
        Common.CallSubNew3(this.ba, this._callback, this._event + "_PageUpDownAvailable", Boolean.valueOf(this._cpageup), Boolean.valueOf(this._cpagedown));
        return "";
    }

    public boolean _textclear() throws Exception {
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        this._textdat.Text = "";
        _xanzeigeloeschen();
        Common common2 = this.__c;
        return true;
    }

    public boolean _textshow(boolean z) throws Exception {
        int i;
        RichStringBuilder.RichString Format;
        RichStringBuilder.RichString richString;
        if (this._classlock) {
            Common common = this.__c;
            return false;
        }
        String str = "";
        long j = 0;
        _xanzeigeloeschen();
        if (this._textdat.Text.length() < 1) {
            Common common2 = this.__c;
            return false;
        }
        Common common3 = this.__c;
        DateTime dateTime = Common.DateTime;
        long now = DateTime.getNow();
        Common common4 = this.__c;
        if (z) {
            boolean _xtextcheck = _xtextcheck();
            Common common5 = this.__c;
            if (!_xtextcheck) {
                _clsrtflbloptions _clsrtflbloptions2 = this._opt;
                Common common6 = this.__c;
                _clsrtflbloptions2.AnzeigeGeloescht = false;
                Common common7 = this.__c;
                return false;
            }
            StringBuilder append = new StringBuilder().append("Text prüfen: ");
            Common common8 = this.__c;
            DateTime dateTime2 = Common.DateTime;
            StringBuilder append2 = append.append(BA.NumberToString(DateTime.getNow() - now));
            Common common9 = this.__c;
            str = append2.append(Common.CRLF).toString();
            Common common10 = this.__c;
            DateTime dateTime3 = Common.DateTime;
            j = 0 + (DateTime.getNow() - now);
            Common common11 = this.__c;
            DateTime dateTime4 = Common.DateTime;
            now = DateTime.getNow();
        }
        boolean _xtextberechnen = _xtextberechnen();
        Common common12 = this.__c;
        if (!_xtextberechnen) {
            Common common13 = this.__c;
            if (!z) {
                Common common14 = this.__c;
                Common.Log("RtfLabel (" + this._event + ") Error in the text!");
                Common common15 = this.__c;
                Common.Log("Show the Text with 'ShowText(CheckBefore = True)' on.");
            } else {
                Common common16 = this.__c;
                Common.Log("RtfLabel (" + this._event + ") unknown Error in Sub 'xTextBerechnen'");
            }
            Common common17 = this.__c;
            return false;
        }
        StringBuilder append3 = new StringBuilder().append(str).append("Text berechnen: ");
        Common common18 = this.__c;
        DateTime dateTime5 = Common.DateTime;
        StringBuilder append4 = append3.append(BA.NumberToString(DateTime.getNow() - now));
        Common common19 = this.__c;
        String sb = append4.append(Common.CRLF).toString();
        Common common20 = this.__c;
        DateTime dateTime6 = Common.DateTime;
        long now2 = j + (DateTime.getNow() - now);
        Common common21 = this.__c;
        DateTime dateTime7 = Common.DateTime;
        long now3 = DateTime.getNow();
        double length = (double) (this._frames.length - 1);
        for (int i2 = 0; ((double) i2) <= length; i2 = (int) (((double) i2) + 1.0d)) {
            ImageViewWrapper imageViewWrapper = new ImageViewWrapper();
            imageViewWrapper.Initialize(this.ba, "");
            imageViewWrapper.setColor(this._frames[i2].Color);
            this._tasv.getPanel().AddView((View) imageViewWrapper.getObject(), this._frames[i2].Left, this._frames[i2].Top, this._frames[i2].Width, this._frames[i2].Height);
        }
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.Initialize(this._opt.LinkPressedColor, this._opt.LinkPressedCornerRadius);
        double size = (double) (this._links.getSize() - 1);
        for (int i3 = 0; ((double) i3) <= size; i3 = (int) (((double) i3) + 1.0d)) {
            new _clsrtflbllink();
            _clsrtflbllink _clsrtflbllink2 = (_clsrtflbllink) this._links.Get(i3);
            LabelWrapper labelWrapper = new LabelWrapper();
            labelWrapper.Initialize(this.ba, "taLblLink");
            labelWrapper.setTag(_clsrtflbllink2.EventText);
            this._tasv.getPanel().AddView((View) labelWrapper.getObject(), this._opt.TextBorder + _clsrtflbllink2.Left + this._zeiledat[_clsrtflbllink2.Zeile].KorrZentriert, _clsrtflbllink2.Top, _clsrtflbllink2.Width, _clsrtflbllink2.Height);
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.Initialize();
            stateListDrawable.AddState(StateListDrawable.State_Pressed, (Drawable) colorDrawable.getObject());
            labelWrapper.setBackground((Drawable) stateListDrawable.getObject());
        }
        boolean z2 = this._opt.MarkFunction;
        Common common22 = this.__c;
        if (z2) {
            this._imgmark = new ImageViewWrapper[this._textdat.ZeilenCount];
            int length2 = this._imgmark.length;
            for (int i4 = 0; i4 < length2; i4++) {
                this._imgmark[i4] = new ImageViewWrapper();
            }
        }
        RichStringBuilder.RichString richString2 = new RichStringBuilder.RichString();
        RichStringBuilder.RichStringFormatter richStringFormatter = new RichStringBuilder.RichStringFormatter();
        richStringFormatter.Initialize("{sample}");
        switch (BA.switchObjectToInt(Integer.valueOf(this._opt.LinkFormat), 0, 1, 2, 3)) {
            case 0:
            case 1:
                richStringFormatter.getClass();
                Common common23 = this.__c;
                richStringFormatter.AddToken("{u}", 8, String.valueOf(Common.Null));
                break;
            case 2:
                richStringFormatter.getClass();
                richStringFormatter.AddToken("{u}", 1, BA.NumberToString(this._opt.LinkTextColor));
                break;
            case 3:
                richStringFormatter.getClass();
                Common common24 = this.__c;
                richStringFormatter.AddToken("{u}", 8, String.valueOf(Common.Null));
                richStringFormatter.getClass();
                richStringFormatter.AddToken("{c}", 1, BA.NumberToString(this._opt.LinkTextColor));
                break;
        }
        richStringFormatter.getClass();
        richStringFormatter.getClass();
        richStringFormatter.AddToken("{b}", 4, BA.NumberToString(1));
        richStringFormatter.getClass();
        richStringFormatter.getClass();
        richStringFormatter.AddToken("{i}", 4, BA.NumberToString(2));
        if (this._textdat.Text.indexOf("{s}") > -1) {
            richStringFormatter.getClass();
            richStringFormatter.AddToken("{s}", 2, BA.NumberToString((double) this._opt.TextSizeSmall));
        }
        double d = (double) (this._opt.TextColorsSignUsedCount - 1);
        for (int i5 = 0; ((double) i5) <= d; i5 = (int) (((double) i5) + 1.0d)) {
            String str2 = this._opt.TextColorsSignUsed[i5];
            richStringFormatter.getClass();
            richStringFormatter.AddToken(str2, 1, BA.NumberToString(this._opt.TextColors[this._opt.TextColorsSignUsedId[i5]]));
        }
        RichStringBuilder.RichStringFormatter richStringFormatter2 = new RichStringBuilder.RichStringFormatter();
        richStringFormatter2.Initialize("{sample}");
        richStringFormatter.getClass();
        richStringFormatter.getClass();
        richStringFormatter2.AddToken("{b}", 4, BA.NumberToString(1));
        richStringFormatter.getClass();
        richStringFormatter.getClass();
        richStringFormatter2.AddToken("{i}", 4, BA.NumberToString(2));
        double d2 = (double) (this._textdat.AbsaetzeCount - 1);
        for (int i6 = 0; ((double) i6) <= d2; i6 = (int) (((double) i6) + 1.0d)) {
            double d3 = (double) (this._absaetze[i6].BilderCount - 1);
            for (int i7 = 0; ((double) i7) <= d3; i7 = (int) (((double) i7) + 1.0d)) {
                ImageViewWrapper imageViewWrapper2 = new ImageViewWrapper();
                new _clsrtflblimage();
                _clsrtflblimage _clsrtflblimage2 = (_clsrtflblimage) this._absaetze[i6].Bilder.Get(i7);
                imageViewWrapper2.Initialize(this.ba, "");
                Common common25 = this.__c;
                Gravity gravity = Common.Gravity;
                imageViewWrapper2.setGravity(Gravity.FILL);
                if (_clsrtflblimage2.Name.indexOf(".") == -1) {
                    imageViewWrapper2.setBitmap((Bitmap) this._opt.Images.Get(_clsrtflblimage2.Name));
                } else {
                    String lowerCase = _clsrtflblimage2.Name.toLowerCase();
                    Common common26 = this.__c;
                    File file = Common.File;
                    Common common27 = this.__c;
                    File file2 = Common.File;
                    if (File.Exists(File.getDirAssets(), lowerCase)) {
                        Common common28 = this.__c;
                        Common common29 = this.__c;
                        File file3 = Common.File;
                        imageViewWrapper2.setBitmap((Bitmap) Common.LoadBitmap(File.getDirAssets(), lowerCase).getObject());
                    }
                }
                this._tasv.getPanel().AddView((View) imageViewWrapper2.getObject(), _clsrtflblimage2.PosLeft, _clsrtflblimage2.PosTop, _clsrtflblimage2.Width, _clsrtflblimage2.Height);
            }
        }
        int i8 = this._gesbr - (this._opt.TextBorder * 2);
        Common common30 = this.__c;
        int DipToCurrent = i8 + Common.DipToCurrent(50);
        LabelWrapper[] labelWrapperArr = new LabelWrapper[this._textdat.ZeilenCount];
        int length3 = labelWrapperArr.length;
        for (int i9 = 0; i9 < length3; i9++) {
            labelWrapperArr[i9] = new LabelWrapper();
        }
        int i10 = -1;
        double d4 = (double) (this._textdat.ZeilenCount - 1);
        int i11 = 0;
        while (((double) i11) <= d4) {
            if (this._zeiledat[i11].AbsatzID > i10) {
                i = i10 + 1;
            } else {
                i = i10;
            }
            boolean z3 = this._opt.MarkFunction;
            Common common31 = this.__c;
            if (z3) {
                this._imgmark[i11].Initialize(this.ba, "");
                this._imgmark[i11].setColor(this._opt.MarkColor);
                ImageViewWrapper imageViewWrapper3 = this._imgmark[i11];
                Common common32 = this.__c;
                imageViewWrapper3.setVisible(false);
                this._tasv.getPanel().AddView((View) this._imgmark[i11].getObject(), this._opt.TextBorder, this._zeiledat[i11].ZeilePosTop, 0, this._absaetze[i].ZeileHo2);
            }
            labelWrapperArr[i11].Initialize(this.ba, "");
            labelWrapperArr[i11].setTypeface((Typeface) this._tyface[0].getObject());
            labelWrapperArr[i11].setTextColor(this._absaetze[i].TextColor);
            labelWrapperArr[i11].setTextSize(this._absaetze[i].TextSize);
            this._tasv.getPanel().AddView((View) labelWrapperArr[i11].getObject(), this._zeiledat[i11].LblPosX + this._zeiledat[i11].KorrZentriert, this._zeiledat[i11].ZeilePosTop - this._absaetze[i].ZeileKorrY, DipToCurrent, this._absaetze[i].ZeileHo1);
            if ((this._absaetze[i].CharsFormat == 1 || this._absaetze[i].CharsFormat == 3) && this._zeiledat[i11].TextRtf.indexOf("{b}") == -1) {
                this._zeiledat[i11].TextRtf += "{b} {b}";
            }
            if (this._zeiledat[i11].TextRtf.indexOf("{") == -1) {
                labelWrapperArr[i11].setText((Object) this._zeiledat[i11].TextRtf);
                Format = richString2;
            } else {
                richString2.Initialize(this._zeiledat[i11].TextRtf);
                Format = richStringFormatter.Format((SpannableString) richString2.getObject());
                labelWrapperArr[i11].setText(Format.getObject());
            }
            if (this._absaetze[i].BulletChars.length() <= 0 || i11 != this._absaetze[i].ZeileVon) {
                richString = Format;
            } else {
                LabelWrapper labelWrapper2 = new LabelWrapper();
                labelWrapper2.Initialize(this.ba, "");
                labelWrapper2.setTextColor(this._absaetze[i].CharsColor);
                labelWrapper2.setTextSize(this._absaetze[i].TextSize);
                labelWrapper2.setTypeface((Typeface) this._tyface[0].getObject());
                String str3 = this._absaetze[i].BulletChars;
                switch (BA.switchObjectToInt(Integer.valueOf(this._absaetze[i].CharsFormat), 0, 1, 2, 3)) {
                    case 0:
                        if (this._zeiledat[i11].TextRtf.indexOf("{b}") > -1) {
                            str3 = str3 + "{b} {b}";
                            break;
                        }
                        break;
                    case 1:
                        str3 = "{b}" + str3 + "{b}";
                        break;
                    case 2:
                        str3 = "{i}" + str3 + "{i}";
                        if (this._zeiledat[i11].TextRtf.indexOf("{b}") > -1) {
                            str3 = str3 + "{b} {b}";
                            break;
                        }
                        break;
                    case 3:
                        str3 = "{i}{b}" + str3 + "{b}{i}";
                        break;
                }
                Format.Initialize(str3);
                RichStringBuilder.RichString Format2 = richStringFormatter2.Format((SpannableString) Format.getObject());
                labelWrapper2.setText(Format2.getObject());
                this._tasv.getPanel().AddView((View) labelWrapper2.getObject(), ((this._opt.TextBorder + (this._absaetze[i].IndentLevel * this._opt.IndentWidth)) - this._opt.IndentWidth) + this._zeiledat[i11].KorrZentriert, this._zeiledat[i11].ZeilePosTop - this._absaetze[i].ZeileKorrY, this._absaetze[i].IndentMinWidth, this._absaetze[i].ZeileHo1);
                richString = Format2;
            }
            richString2 = richString;
            i11 = (int) (((double) i11) + 1.0d);
            i10 = i;
        }
        this._tasv.getPanel().setHeight(this._textdat.TextHeight);
        StringBuilder append5 = new StringBuilder().append(sb).append("Text anzeigen: ");
        Common common33 = this.__c;
        DateTime dateTime8 = Common.DateTime;
        StringBuilder append6 = append5.append(BA.NumberToString(DateTime.getNow() - now3));
        Common common34 = this.__c;
        append6.append(Common.CRLF).toString();
        Common common35 = this.__c;
        DateTime dateTime9 = Common.DateTime;
        long now4 = (DateTime.getNow() - now3) + now2;
        if (this._textdat.ZeilenCount > 0) {
            boolean z4 = this._opt.MarkFunction;
            Common common36 = this.__c;
            if (z4) {
                boolean z5 = this._opt.Enabled;
                Common common37 = this.__c;
                if (z5) {
                    Common common38 = this.__c;
                    this._cmarkon = true;
                    Common common39 = this.__c;
                    if (Common.SubExists(this.ba, this._callback, this._event + "_MarkOnAvailable")) {
                        Common common40 = this.__c;
                        Common.CallSubNew2(this.ba, this._callback, this._event + "_MarkOnAvailable", Boolean.valueOf(this._cmarkon));
                    }
                }
            }
        }
        _clsrtflbloptions _clsrtflbloptions3 = this._opt;
        Common common41 = this.__c;
        _clsrtflbloptions3.AnzeigeGeloescht = false;
        _tasv_scrollchanged(0);
        Common common42 = this.__c;
        return true;
    }

    public String _timer1_tick() throws Exception {
        int i = 0;
        boolean z = this._pmark.IstAn;
        Common common = this.__c;
        if (!z) {
            Timer timer = this._timer1;
            Common common2 = this.__c;
            timer.setEnabled(false);
            return "";
        }
        Common common3 = this.__c;
        int Max = (int) Common.Max(this._pmark.PressZeileA, this._pmark.PressZeileB);
        if (this._pmark.Scroll == 1) {
            if (this._pmark.ScrollPos == this._pmark.ScrollMax) {
                Timer timer2 = this._timer1;
                Common common4 = this.__c;
                timer2.setEnabled(false);
            } else if (Max >= this._textdat.ZeilenCount - 1) {
                this._pmark.ScrollPos = this._pmark.ScrollMax;
                this._tasv.setScrollPosition(this._pmark.ScrollPos);
                Common common5 = this.__c;
                i = 1;
            } else {
                this._pmark.ScrollPos = ((this._zeiledat[Max + 1].ZeilePosTop + this._zeiledat[Max + 1].ZeileHo2) - (this._zeiledat[Max].ZeilePosTop + this._zeiledat[Max].ZeileHo2)) + this._pmark.ScrollPos;
                if (this._pmark.ScrollPos > this._pmark.ScrollMax) {
                    this._pmark.ScrollPos = this._pmark.ScrollMax;
                }
                this._tasv.setScrollPosition(this._pmark.ScrollPos);
                int i2 = Max + 1;
                if (i2 > this._textdat.ZeilenCount) {
                    i2 = this._textdat.ZeilenCount;
                }
                if (this._pmark.PressZeileA > -1) {
                    this._pmark.PressZeileA = i2;
                }
                if (this._pmark.PressZeileB > -1) {
                    this._pmark.PressZeileB = i2;
                }
                Common common6 = this.__c;
                i = 1;
            }
        } else if (this._pmark.Scroll == -1) {
            if (this._pmark.ScrollPos == 0) {
                Timer timer3 = this._timer1;
                Common common7 = this.__c;
                timer3.setEnabled(false);
            } else {
                if (Max < 2) {
                    this._pmark.ScrollPos = 0;
                    this._tasv.setScrollPosition(this._pmark.ScrollPos);
                    Common common8 = this.__c;
                } else {
                    this._pmark.ScrollPos -= (this._zeiledat[Max - 1].ZeilePosTop + this._zeiledat[Max - 1].ZeileHo2) - (this._zeiledat[Max - 2].ZeilePosTop + this._zeiledat[Max - 2].ZeileHo2);
                    if (this._pmark.ScrollPos < 0) {
                        this._pmark.ScrollPos = 0;
                    }
                    this._tasv.setScrollPosition(this._pmark.ScrollPos);
                    int i3 = Max - 1;
                    if (i3 >= 0) {
                        i = i3;
                    }
                    Common common9 = this.__c;
                }
                if (this._pmark.PressZeileA > -1) {
                    this._pmark.PressZeileA = i;
                }
                if (this._pmark.PressZeileB > -1) {
                    this._pmark.PressZeileB = i;
                }
                i = 1;
            }
        }
        this._pmark.TopIndex = _xtopindex(this._pmark.ScrollPos);
        Common common10 = this.__c;
        if (i == 1) {
            _xtextmarkieren("Move");
        }
        return "";
    }

    public String _xanzeigeloeschen() throws Exception {
        if (this._classlock) {
            return "";
        }
        boolean z = this._opt.AnzeigeGeloescht;
        Common common = this.__c;
        if (z) {
            return "";
        }
        Common common2 = this.__c;
        this._cpageup = false;
        Common common3 = this.__c;
        this._cpagedown = false;
        Common common4 = this.__c;
        this._cmarkon = false;
        Common common5 = this.__c;
        this._eventsperre = true;
        _markoff();
        Common common6 = this.__c;
        this._eventsperre = false;
        Common common7 = this.__c;
        if (Common.SubExists(this.ba, this._callback, this._event + "_MarkOnAvailable")) {
            Common common8 = this.__c;
            Common.CallSubNew2(this.ba, this._callback, this._event + "_MarkOnAvailable", Boolean.valueOf(this._cmarkon));
        }
        Common common9 = this.__c;
        if (Common.SubExists(this.ba, this._callback, this._event + "_PageUpDownAvailable")) {
            Common common10 = this.__c;
            Common.CallSubNew3(this.ba, this._callback, this._event + "_PageUpDownAvailable", Boolean.valueOf(this._cpageup), Boolean.valueOf(this._cpagedown));
        }
        this._textdat.AbsaetzeCount = 0;
        this._textdat.TextHeight = 0;
        this._textdat.ScrollMax = 0;
        this._textdat.ZeilenCount = 0;
        this._links.Clear();
        this._zeiledat = new _clsrtflblrowdata[0];
        int length = this._zeiledat.length;
        for (int i = 0; i < length; i++) {
            this._zeiledat[i] = new _clsrtflblrowdata();
        }
        this._frames = new _clsrtflblframe[0];
        int length2 = this._frames.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this._frames[i2] = new _clsrtflblframe();
        }
        this._absaetze = new _clsrtflblparagraph[0];
        int length3 = this._absaetze.length;
        for (int i3 = 0; i3 < length3; i3++) {
            this._absaetze[i3] = new _clsrtflblparagraph();
        }
        for (int numberOfViews = this._tasv.getPanel().getNumberOfViews() - 1; ((double) numberOfViews) >= 0.0d; numberOfViews = (int) (((double) numberOfViews) - 4.0d)) {
            this._tasv.getPanel().RemoveViewAt(numberOfViews);
        }
        this._tasv.getPanel().setHeight(0);
        this._tasv.setScrollPosition(0);
        Common common11 = this.__c;
        Common.DoEvents();
        this._tasv.setScrollPosition(0);
        this._pmark.Initialize();
        _clsrtflbloptions _clsrtflbloptions2 = this._opt;
        Common common12 = this.__c;
        _clsrtflbloptions2.AnzeigeGeloescht = true;
        return "";
    }

    public String _xschriftgroesseeinrichten() throws Exception {
        if (this._classlock) {
            return "";
        }
        this._labelber.setTypeface((Typeface) this._tyface[0].getObject());
        this._labelber.setTextSize(this._opt.TextSize);
        this._zh1 = this._strutils.MeasureMultilineTextHeight((TextView) this._labelber.getObject(), "ÄÖÜgq");
        StringBuilder append = new StringBuilder().append("ÄÖÜgq");
        Common common = this.__c;
        StringBuilder append2 = append.append(String.valueOf(Common.Chr(13)));
        Common common2 = this.__c;
        this._zh2 = this._strutils.MeasureMultilineTextHeight((TextView) this._labelber.getObject(), append2.append(String.valueOf(Common.Chr(10))).append("ÄÖÜgq").toString()) - this._zh1;
        this._zhkorry = (this._zh1 - this._zh2) - 1;
        return "";
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 872
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        */
    public boolean _xtextberechnen() throws java.lang.Exception {
        /*
        // Method dump skipped, instructions count: 8184
        */
        throw new UnsupportedOperationException("Method not decompiled: combib.libs.rtflabel.rtflabel._xtextberechnen():boolean");
    }

    public boolean _xtextcheck() throws Exception {
        String str;
        String str2;
        int i;
        String str3;
        String str4;
        int indexOf;
        String str5;
        int i2;
        int i3;
        String str6;
        int indexOf2;
        Common common = this.__c;
        Colors colors = Common.Colors;
        Common common2 = this.__c;
        Colors colors2 = Common.Colors;
        this._opt.IndentWidth = (int) this._canvasber.MeasureStringWidth(this._opt.IndentWidthString, (Typeface) this._tyface[0].getObject(), this._opt.TextSize);
        this._talbl.Initialize(this.ba, "");
        LabelWrapper labelWrapper = this._talbl;
        Common common3 = this.__c;
        labelWrapper.setVisible(false);
        LabelWrapper labelWrapper2 = this._talbl;
        Common common4 = this.__c;
        Colors colors3 = Common.Colors;
        labelWrapper2.setColor(-1);
        LabelWrapper labelWrapper3 = this._talbl;
        Common common5 = this.__c;
        Colors colors4 = Common.Colors;
        labelWrapper3.setTextColor(Colors.Black);
        this._talbl.setTextSize(18.0f);
        this._tamsg.Initialize(this.ba, "");
        LabelWrapper labelWrapper4 = this._tamsg;
        Common common6 = this.__c;
        labelWrapper4.setVisible(false);
        LabelWrapper labelWrapper5 = this._tamsg;
        Common common7 = this.__c;
        Colors colors5 = Common.Colors;
        labelWrapper5.setColor(Colors.ARGB(255, 230, 230, 230));
        LabelWrapper labelWrapper6 = this._tamsg;
        Common common8 = this.__c;
        Colors colors6 = Common.Colors;
        labelWrapper6.setTextColor(Colors.Black);
        this._tamsg.setTextSize(18.0f);
        this._tasv.getPanel().AddView((View) this._tamsg.getObject(), 0, 0, this._gesbr, 100);
        this._tasv.getPanel().AddView((View) this._talbl.getObject(), 0, 0, this._gesbr, 100);
        if (this._textdat.Text.length() < 1) {
            this._tamsg.setText((Object) "Error: No text available!");
            this._tamsg.setHeight(this._strutils.MeasureMultilineTextHeight((TextView) this._tamsg.getObject(), "Error: No text available!"));
            this._tasv.getPanel().setHeight(this._tamsg.getHeight());
            LabelWrapper labelWrapper7 = this._tamsg;
            Common common9 = this.__c;
            labelWrapper7.setVisible(true);
            Common common10 = this.__c;
            return false;
        }
        _clsrtflblparagraph _clsrtflblparagraph2 = new _clsrtflblparagraph();
        RichStringBuilder.RichString richString = new RichStringBuilder.RichString();
        int i4 = 0;
        int i5 = 0;
        Arrays.fill(new String[0], "");
        Arrays.fill(new String[0], "");
        Common common11 = this.__c;
        Regex regex = Common.Regex;
        String[] Split = Regex.Split("/", "{b}/{i}/{s}/{tc0}/{tc1}/{tc2}/{tc3}/{tc4}/{tc5}/{tc6}/{tc7}/{tc8}/{tc9}");
        int length = this._textdat.Text.length();
        int i6 = 0;
        String str7 = "";
        int i7 = -1;
        int i8 = 0;
        while (i8 <= length - 1) {
            String str8 = this._textdat.Text;
            Common common12 = this.__c;
            int indexOf3 = str8.indexOf(Common.CRLF, i8);
            if (indexOf3 == -1) {
                indexOf3 = length - 1;
            }
            int i9 = i7 + 1;
            String substring = this._textdat.Text.substring(i8, indexOf3 + 1);
            int length2 = i8 + substring.length();
            Common common13 = this.__c;
            if (substring.equals(Common.CRLF)) {
                i8 = length2;
                i7 = i9;
            } else {
                for (int indexOf4 = substring.indexOf("{"); indexOf4 > -1; indexOf4 = substring.indexOf("{", indexOf2 + 1)) {
                    indexOf2 = substring.indexOf("}", indexOf4 + 1);
                    if (indexOf2 == -1) {
                        str7 = "{... - '}' missing";
                        indexOf2 = indexOf4;
                    } else {
                        String substring2 = substring.substring(indexOf4, indexOf2 + 1);
                        if (substring2.length() == 2) {
                            str7 = "{...} missing data";
                        } else if (substring2.indexOf(" ") > -1) {
                            str7 = "{...} Spaces not allowed";
                        } else if (substring2.indexOf("{", indexOf4 + 1) > -1) {
                            str7 = "{...} '{' not allowed";
                        }
                    }
                    if (str7.length() > 0) {
                        richString.Initialize(substring);
                        richString.Color(Colors.Red, indexOf4, indexOf2 + 1);
                        richString.getClass();
                        richString.Style(1, indexOf4, indexOf2 + 1);
                        StringBuilder append = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                        Common common14 = this.__c;
                        _xtextcheckmsg(richString, substring, append.append(Common.CRLF).append(str7).toString());
                        Common common15 = this.__c;
                        return false;
                    }
                }
                double length3 = (double) (Split.length - 1);
                int i10 = 0;
                String str9 = "";
                int i11 = i4;
                while (((double) i10) <= length3) {
                    int indexOf5 = substring.indexOf(Split[i10]);
                    if (indexOf5 <= -1 || str9.indexOf(Split[i10]) != -1) {
                        str5 = str9;
                        i3 = indexOf5;
                        i2 = 0;
                    } else {
                        str5 = str9 + Split[i10] + "/";
                        i3 = indexOf5;
                        i2 = 0;
                    }
                    while (i3 > -1) {
                        i2++;
                        i3 = substring.indexOf(Split[i10], i3 + 1);
                    }
                    if (i2 % 2 != 0) {
                        String str10 = Split[i10] + " - not present in pairs";
                        richString.Initialize(substring);
                        int i12 = -1;
                        double d = (double) (i2 - 1);
                        for (int i13 = 0; ((double) i13) <= d; i13 = (int) (((double) i13) + 1.0d)) {
                            i12 = substring.indexOf(Split[i10], i12 + 1);
                            if (i13 < i2 - 1) {
                                richString.Color(Colors.Blue, i12, Split[i10].length() + i12);
                            } else {
                                richString.Color(Colors.Red, i12, Split[i10].length() + i12);
                                richString.getClass();
                                richString.Style(1, i12, Split[i10].length() + i12);
                            }
                        }
                        StringBuilder append2 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                        Common common16 = this.__c;
                        _xtextcheckmsg(richString, substring, append2.append(Common.CRLF).append(str10).toString());
                        Common common17 = this.__c;
                        return false;
                    }
                    int i14 = i2;
                    int indexOf6 = substring.indexOf(Split[i10]);
                    while (indexOf6 > -1) {
                        int indexOf7 = substring.indexOf(Split[i10], indexOf6 + 1);
                        int length4 = (indexOf7 - indexOf6) - Split[i10].length();
                        if (length4 > 0) {
                            str6 = substring.substring(Split[i10].length() + indexOf6, indexOf7);
                        } else {
                            str6 = "";
                        }
                        if (str6.trim().length() < 1) {
                            richString.Initialize(substring);
                            richString.Color(Colors.Red, indexOf6, Split[i10].length() + indexOf7);
                            richString.getClass();
                            richString.Style(1, indexOf6, Split[i10].length() + indexOf7);
                            StringBuilder append3 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                            Common common18 = this.__c;
                            _xtextcheckmsg(richString, substring, append3.append(Common.CRLF).append(Split[i10] + "..." + Split[i10] + " - no text in the range").toString());
                            Common common19 = this.__c;
                            return false;
                        }
                        indexOf6 = substring.indexOf(Split[i10], indexOf7 + 1);
                        i14 = length4;
                    }
                    i10 = (int) (((double) i10) + 1.0d);
                    i11 = i14;
                    str9 = str5;
                }
                String str11 = str7;
                int indexOf8 = substring.indexOf("{a");
                while (indexOf8 > -1) {
                    int indexOf9 = substring.indexOf("}", indexOf8 + 1);
                    String substring3 = substring.substring(indexOf8, indexOf9 + 1);
                    if (indexOf8 > -1 && str9.indexOf(substring3) == -1) {
                        str9 = str9 + substring3 + "/";
                    }
                    if (!substring.substring(indexOf8 + 2, indexOf8 + 3).equals("/")) {
                        str11 = "{a/... - '/' missing";
                    } else if (substring3.length() < 5) {
                        str11 = "{a/...} - Text missing";
                    }
                    if (str11.length() > 0) {
                        richString.Initialize(substring);
                        richString.Color(Colors.Red, indexOf8, indexOf9 + 1);
                        richString.getClass();
                        richString.Style(1, indexOf8, indexOf9 + 1);
                        StringBuilder append4 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                        Common common20 = this.__c;
                        _xtextcheckmsg(richString, substring, append4.append(Common.CRLF).append(str11).toString());
                        Common common21 = this.__c;
                        return false;
                    }
                    indexOf8 = substring.indexOf("{a", indexOf9 + 1);
                }
                int indexOf10 = substring.indexOf("{h");
                String str12 = str11;
                while (indexOf10 > -1) {
                    int indexOf11 = substring.indexOf("}", indexOf10 + 1);
                    String substring4 = substring.substring(indexOf10, indexOf11 + 1);
                    if (indexOf10 > -1 && str9.indexOf(substring4) == -1) {
                        str9 = str9 + substring4 + "/";
                    }
                    if (!substring.substring(indexOf10 + 2, indexOf10 + 3).equals("/")) {
                        str12 = "{h/... - '/' missing";
                        indexOf = i5;
                    } else if (substring4.length() < 5) {
                        str12 = "{h/EventText} - 'EventText' missing";
                        indexOf = i5;
                    } else {
                        indexOf = substring.indexOf("{h}", indexOf11);
                        if (indexOf == -1) {
                            str12 = "{h/EventText}Text{h} - '{h}' missing";
                        } else if (substring.indexOf("{h", indexOf10 + 1) < indexOf) {
                            str12 = "{h/EventText}Text{h} - '{h}' missing";
                        } else if ((indexOf - indexOf11) - 1 < 1) {
                            str12 = "{h/EventText}Text{h} - 'Text' missing";
                        } else if (substring.substring(indexOf11 + 1, indexOf11 + 2).equals(" ") || substring.substring(indexOf - 1, indexOf).equals(" ")) {
                            str12 = "{h/EventText}Text{h} - no Spaces before or after 'Text'";
                        }
                    }
                    if (str12.length() > 0) {
                        richString.Initialize(substring);
                        richString.Color(Colors.Red, indexOf10, indexOf11 + 1);
                        richString.getClass();
                        richString.Style(1, indexOf10, indexOf11 + 1);
                        StringBuilder append5 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                        Common common22 = this.__c;
                        _xtextcheckmsg(richString, substring, append5.append(Common.CRLF).append(str12).toString());
                        Common common23 = this.__c;
                        return false;
                    }
                    if (str9.indexOf("{h}") == -1) {
                        str9 = str9 + "{h}" + "/";
                    }
                    indexOf10 = substring.indexOf("{h", indexOf + 1);
                    i5 = indexOf;
                }
                _clsrtflblparagraph2.Initialize();
                _clsrtflblparagraph2.TextSize = this._opt.TextSize;
                _clsrtflblparagraph2.IndentMinWidth = this._opt.IndentWidth;
                int indexOf12 = substring.indexOf("{p", 0);
                String str13 = str12;
                String str14 = str9;
                int i15 = i11;
                int i16 = i6;
                int i17 = 0;
                while (indexOf12 > -1 && indexOf12 == i17) {
                    i16 = substring.indexOf("}", indexOf12);
                    Common common24 = this.__c;
                    Regex regex2 = Common.Regex;
                    String[] Split2 = Regex.Split("/", substring.substring(i17, i16));
                    String substring5 = substring.substring(indexOf12, i16 + 1);
                    if (str14.indexOf(substring5 + "/") == -1) {
                        str14 = str14 + substring5 + "/";
                    }
                    i17 = i16 + 1;
                    switch (BA.switchObjectToInt(Split2[0], "{pect", "{pc", "{ptc", "{pts", "{pdt", "{pri", "{pi", "{pbi", "{pf", "{pimage")) {
                        case 0:
                            str = !substring5.equals("{pect}") ? substring5 + " - invalid formatting" : str13;
                            if (str.length() > 0) {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append6 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common25 = this.__c;
                                _xtextcheckmsg(richString, substring, append6.append(Common.CRLF).append(str).toString());
                                Common common26 = this.__c;
                                return false;
                            }
                            str2 = str;
                            i = i15;
                            break;
                        case 1:
                            String str15 = !substring5.equals("{pc}") ? substring5 + " - invalid formatting" : str13;
                            if (str15.length() <= 0) {
                                _clsrtflblparagraph2.Zentriert = 1;
                                str2 = str15;
                                i = i15;
                                break;
                            } else {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append7 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common27 = this.__c;
                                _xtextcheckmsg(richString, substring, append7.append(Common.CRLF).append(str15).toString());
                                Common common28 = this.__c;
                                return false;
                            }
                        case 2:
                            if (Split2.length < 2) {
                                i = i15;
                                str2 = "{ptc/ColorId} value for 'ColorId' missing";
                            } else {
                                String substring6 = Split2[1].substring(Split2[1].indexOf(":") + 1);
                                Common common29 = this.__c;
                                boolean IsNumber = Common.IsNumber(substring6);
                                Common common30 = this.__c;
                                if (!IsNumber) {
                                    i = i15;
                                    str2 = "{ptc/ColorId} value for 'ColorId' missing (numeric value 0 to 9)";
                                } else {
                                    int parseDouble = (int) Double.parseDouble(substring6);
                                    if (parseDouble < 0 || parseDouble > 9) {
                                        i = parseDouble;
                                        str2 = "{ptc/ColorId} incorrect value for 'ColorId' (numeric value 0 to 9)";
                                    } else {
                                        i = parseDouble;
                                        str2 = str13;
                                    }
                                }
                            }
                            if (str2.length() <= 0) {
                                _clsrtflblparagraph2.TextColor = this._opt.TextColors[i];
                                break;
                            } else {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append8 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common31 = this.__c;
                                _xtextcheckmsg(richString, substring, append8.append(Common.CRLF).append(str2).toString());
                                Common common32 = this.__c;
                                return false;
                            }
                        case 3:
                            if (Split2.length < 2) {
                                str4 = "{pts/Value} 'Value' missing";
                            } else {
                                String substring7 = Split2[1].substring(Split2[1].indexOf(":") + 1);
                                Common common33 = this.__c;
                                boolean IsNumber2 = Common.IsNumber(substring7);
                                Common common34 = this.__c;
                                if (!IsNumber2) {
                                    str4 = "{pts/Value} missing value for relative font size (numeric value 0.1 to 2)";
                                } else {
                                    float parseDouble2 = (float) Double.parseDouble(substring7);
                                    if (((double) parseDouble2) < 0.1d || parseDouble2 > 2.0f) {
                                        str4 = "{pts/Value} incorrect value for relative font size (numeric value 0.1 to 2)";
                                    } else {
                                        str4 = str13;
                                    }
                                }
                            }
                            if (str4.length() <= 0) {
                                _clsrtflblparagraph2.TextSize = ((float) Double.parseDouble(Split2[1].substring(Split2[1].indexOf(":") + 1))) * this._opt.TextSize;
                                str2 = str4;
                                i = i15;
                                break;
                            } else {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append9 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common35 = this.__c;
                                _xtextcheckmsg(richString, substring, append9.append(Common.CRLF).append(str4).toString());
                                Common common36 = this.__c;
                                return false;
                            }
                            break;
                        case 4:
                            if (Split2.length < 2) {
                                str3 = "{pdt/DistanceTop} value for 'DistanceTop' missing";
                            } else {
                                String substring8 = Split2[1].substring(Split2[1].indexOf(":") + 1);
                                Common common37 = this.__c;
                                boolean IsNumber3 = Common.IsNumber(substring8);
                                Common common38 = this.__c;
                                if (!IsNumber3) {
                                    str3 = "{pdt/DistanceTop} missing value for 'DistanceTop' (numeric value 0.1 to 2)";
                                } else {
                                    float parseDouble3 = (float) Double.parseDouble(substring8);
                                    if (((double) parseDouble3) < 0.1d || parseDouble3 > 2.0f) {
                                        str3 = "{pdt/DistanceTop} incorrect value for 'DistanceTop' (numeric value 0.1 to 2)";
                                    } else {
                                        str3 = str13;
                                    }
                                }
                            }
                            if (str3.length() <= 0) {
                                float parseDouble4 = (float) Double.parseDouble(Split2[1].substring(Split2[1].indexOf(":") + 1));
                                str2 = str3;
                                i = i15;
                                break;
                            } else {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append10 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common39 = this.__c;
                                _xtextcheckmsg(richString, substring, append10.append(Common.CRLF).append(str3).toString());
                                Common common40 = this.__c;
                                return false;
                            }
                            break;
                        case 5:
                            if (Split2.length < 2) {
                                i = i15;
                                str2 = "{pri/RightIndentLevel} value for 'RightIndentLevel' missing";
                            } else {
                                String substring9 = Split2[1].substring(Split2[1].indexOf(":") + 1);
                                Common common41 = this.__c;
                                boolean IsNumber4 = Common.IsNumber(substring9);
                                Common common42 = this.__c;
                                if (!IsNumber4) {
                                    i = i15;
                                    str2 = "{pri/RightIndentLevel} missing value for 'RightIndentLevel' (numeric value 1 to 9)";
                                } else {
                                    int parseDouble5 = (int) Double.parseDouble(substring9);
                                    if (parseDouble5 < 1 || parseDouble5 > 9) {
                                        i = parseDouble5;
                                        str2 = "{pri/RightIndentLevel} incorrect value for 'RightIndentLevel' (numeric value 1 to 9)";
                                    } else {
                                        i = parseDouble5;
                                        str2 = str13;
                                    }
                                }
                            }
                            if (str2.length() <= 0) {
                                _clsrtflblparagraph2.RightIndentLevel = i;
                                break;
                            } else {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append11 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common43 = this.__c;
                                _xtextcheckmsg(richString, substring, append11.append(Common.CRLF).append(str2).toString());
                                Common common44 = this.__c;
                                return false;
                            }
                            break;
                        case 6:
                            if (Split2.length < 3) {
                                i = i15;
                                str2 = "{pi/IndentLevel/OnlyFirstLine} value missing";
                            } else {
                                String substring10 = Split2[1].substring(Split2[1].indexOf(":") + 1);
                                Common common45 = this.__c;
                                boolean IsNumber5 = Common.IsNumber(substring10);
                                Common common46 = this.__c;
                                if (!IsNumber5) {
                                    str13 = "{pi/IndentLevel/OnlyFirstLine} missing value for 'IndentLevel' (numeric value 1 to 9)";
                                } else {
                                    i15 = (int) Double.parseDouble(substring10);
                                    if (i15 < 1 || i15 > 9) {
                                        str13 = "{pi/IndentLevel/OnlyFirstLine} incorrect value for 'IndentLevel' (numeric value 1 to 9)";
                                    }
                                }
                                String substring11 = Split2[2].substring(Split2[2].indexOf(":") + 1);
                                Common common47 = this.__c;
                                boolean IsNumber6 = Common.IsNumber(substring11);
                                Common common48 = this.__c;
                                if (!IsNumber6) {
                                    str13 = "{pi/IndentLevel/OnlyFirstLine} missing value for 'OnlyFirstLine' (numeric value 0 or 1)";
                                } else {
                                    i15 = (int) Double.parseDouble(substring11);
                                    if (i15 < 0 || i15 > 1) {
                                        str13 = "{pi/IndentLevel/OnlyFirstLine} incorrect value for 'OnlyFirstLine' (numeric value 0 or 1)";
                                    }
                                }
                                if (substring.indexOf("{pbi/") > -1) {
                                    i = i15;
                                    str2 = "{pi/...} AND {pbi/...} are not permitted in a paragraph";
                                } else {
                                    i = i15;
                                    str2 = str13;
                                }
                            }
                            if (str2.length() <= 0) {
                                _clsrtflblparagraph2.IndentLevel = (int) Double.parseDouble(Split2[1].substring(Split2[1].indexOf(":") + 1));
                                _clsrtflblparagraph2.OnlyFirstLine = (int) Double.parseDouble(Split2[2].substring(Split2[2].indexOf(":") + 1));
                                break;
                            } else {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append12 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common49 = this.__c;
                                _xtextcheckmsg(richString, substring, append12.append(Common.CRLF).append(str2).toString());
                                Common common50 = this.__c;
                                return false;
                            }
                        case 7:
                            if (Split2.length < 7) {
                                i = i15;
                                str2 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} value missing";
                            } else {
                                String substring12 = Split2[1].substring(Split2[1].indexOf(":") + 1);
                                Common common51 = this.__c;
                                boolean IsNumber7 = Common.IsNumber(substring12);
                                Common common52 = this.__c;
                                if (!IsNumber7) {
                                    str13 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} missing value for 'IndentLevel' (numeric value 1 to 9)";
                                } else {
                                    i15 = (int) Double.parseDouble(substring12);
                                    if (i15 < 1 || i15 > 9) {
                                        str13 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} incorrect value for 'IndentLevel' (numeric value 1 to 9)";
                                    }
                                }
                                if (Split2[2].substring(Split2[2].indexOf(":") + 1).length() < 1) {
                                    str13 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} missing value for 'MinWidth' (at least one text character)";
                                }
                                String substring13 = Split2[3].substring(Split2[3].indexOf(":") + 1);
                                Common common53 = this.__c;
                                boolean IsNumber8 = Common.IsNumber(substring13);
                                Common common54 = this.__c;
                                if (!IsNumber8) {
                                    str13 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} missing value for 'OnlyFirstLine' (numeric value 0 or 1)";
                                } else {
                                    i15 = (int) Double.parseDouble(substring13);
                                    if (i15 < 0 || i15 > 1) {
                                        str13 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} incorrect value for 'OnlyFirstLine' (numeric value 0 or 1)";
                                    }
                                }
                                if (Split2[4].substring(Split2[4].indexOf(":") + 1).length() < 1) {
                                    str13 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} missing value for 'BulletChars' (at least one text character)";
                                }
                                String substring14 = Split2[5].substring(Split2[5].indexOf(":") + 1);
                                Common common55 = this.__c;
                                boolean IsNumber9 = Common.IsNumber(substring14);
                                Common common56 = this.__c;
                                if (!IsNumber9) {
                                    str13 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} missing value for 'CharsFormat' (numeric value 0 to 3)";
                                } else {
                                    i15 = (int) Double.parseDouble(substring14);
                                    if (i15 < 0 || i15 > 3) {
                                        str13 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} incorrect value for 'CharsFormat' (numeric value 0 to 3)";
                                    }
                                }
                                String substring15 = Split2[6].substring(Split2[6].indexOf(":") + 1);
                                Common common57 = this.__c;
                                boolean IsNumber10 = Common.IsNumber(substring15);
                                Common common58 = this.__c;
                                if (!IsNumber10) {
                                    str13 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} missing value for 'CharsColorId' (numeric value 0 to 9 or -1)";
                                } else {
                                    i15 = (int) Double.parseDouble(substring15);
                                    if (i15 < -1 || i15 > 9) {
                                        str13 = "{pbi/IndentLevel/MinWidth/OnlyFirstLine/BulletChars/CharsFormat/CharsColorId} incorrect value for 'CharsColorId' (numeric value 0 to 9 or -1)";
                                    }
                                }
                                if (substring.indexOf("{pi/") > -1) {
                                    i = i15;
                                    str2 = "{pbi/...} AND {pi/...} are not permitted in a paragraph";
                                } else {
                                    i = i15;
                                    str2 = str13;
                                }
                            }
                            if (str2.length() <= 0) {
                                _clsrtflblparagraph2.IndentLevel = (int) Double.parseDouble(Split2[1].substring(Split2[1].indexOf(":") + 1));
                                _clsrtflblparagraph2.OnlyFirstLine = (int) Double.parseDouble(Split2[3].substring(Split2[3].indexOf(":") + 1));
                                break;
                            } else {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append13 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common59 = this.__c;
                                _xtextcheckmsg(richString, substring, append13.append(Common.CRLF).append(str2).toString());
                                Common common60 = this.__c;
                                return false;
                            }
                        case 8:
                            if (Split2.length < 5) {
                                i = i15;
                                str2 = "{pf/IndentLevel/RightIndentLevel/ColorId/BorderWidth} value missing";
                            } else {
                                String substring16 = Split2[1].substring(Split2[1].indexOf(":") + 1);
                                Common common61 = this.__c;
                                boolean IsNumber11 = Common.IsNumber(substring16);
                                Common common62 = this.__c;
                                if (!IsNumber11) {
                                    str13 = "{pf/IndentLevel/RightIndentLevel/ColorId/BorderWidth} missing value for 'IndentLevel' (numeric value 0 to 9)";
                                } else {
                                    i15 = (int) Double.parseDouble(substring16);
                                    if (i15 < 0 || i15 > 9) {
                                        str13 = "{pf/IndentLevel/RightIndentLevel/ColorId/BorderWidth} incorrect value for 'IndentLevel' (numeric value 0 to 9)";
                                    }
                                }
                                String substring17 = Split2[2].substring(Split2[2].indexOf(":") + 1);
                                Common common63 = this.__c;
                                boolean IsNumber12 = Common.IsNumber(substring17);
                                Common common64 = this.__c;
                                if (!IsNumber12) {
                                    str13 = "{pf/IndentLevel/RightIndentLevel/ColorId/BorderWidth} missing value for 'RightIndentLevel' (numeric value 0 to 9)";
                                } else {
                                    i15 = (int) Double.parseDouble(substring17);
                                    if (i15 < 0 || i15 > 9) {
                                        str13 = "{pf/IndentLevel/RightIndentLevel/ColorId/BorderWidth} incorrect value for 'RightIndentLevel' (numeric value 0 to 9)";
                                    }
                                }
                                String substring18 = Split2[3].substring(Split2[3].indexOf(":") + 1);
                                Common common65 = this.__c;
                                boolean IsNumber13 = Common.IsNumber(substring18);
                                Common common66 = this.__c;
                                if (!IsNumber13) {
                                    str13 = "{pf/IndentLevel/RightIndentLevel/ColorId/BorderWidth} value for 'ColorId' missing (numeric value 0 to 9)";
                                } else {
                                    i15 = (int) Double.parseDouble(substring18);
                                    if (i15 < 0 || i15 > 9) {
                                        str13 = "{pf/IndentLevel/RightIndentLevel/ColorId/BorderWidth} incorrect value for 'ColorId' (numeric value 0 to 9)";
                                    }
                                }
                                String substring19 = Split2[4].substring(Split2[4].indexOf(":") + 1);
                                Common common67 = this.__c;
                                boolean IsNumber14 = Common.IsNumber(substring19);
                                Common common68 = this.__c;
                                if (!IsNumber14) {
                                    i = i15;
                                    str2 = "{pf/IndentLevel/RightIndentLevel/ColorId/BorderWidth} missing value for 'BorderWidth' (numeric value 0 to 0.9)";
                                } else {
                                    float parseDouble6 = (float) Double.parseDouble(substring19);
                                    if (parseDouble6 < Common.Density || ((double) parseDouble6) > 0.9d) {
                                        i = i15;
                                        str2 = "{pf/IndentLevel/RightIndentLevel/ColorId/BorderWidth} incorrect value for 'BorderWidth' (numeric value 0 to 0.9)";
                                    } else {
                                        i = i15;
                                        str2 = str13;
                                    }
                                }
                            }
                            if (str2.length() <= 0) {
                                break;
                            } else {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append14 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common69 = this.__c;
                                _xtextcheckmsg(richString, substring, append14.append(Common.CRLF).append(str2).toString());
                                Common common70 = this.__c;
                                return false;
                            }
                        case 9:
                            if (Split2.length < 4) {
                                i = i15;
                                str2 = "{pimage/Name/Width/Height} value missing";
                            } else {
                                if (Split2[1].substring(Split2[1].indexOf(":") + 1).length() < 1) {
                                    str13 = "{pimage/Name/Width/Height} missing value for 'Name' (at least one text character)";
                                }
                                String substring20 = Split2[2].substring(Split2[2].indexOf(":") + 1);
                                if (substring20.indexOf("dip") > -1) {
                                    str13 = "{pimage/Name/Width/Height} value for 'Width' (numeric value in Pixel)";
                                } else {
                                    Common common71 = this.__c;
                                    boolean IsNumber15 = Common.IsNumber(substring20);
                                    Common common72 = this.__c;
                                    if (!IsNumber15) {
                                        str13 = "{pimage/Name/Width/Height} missing value for 'Width' (numeric value in Pixel)";
                                    } else {
                                        i15 = (int) Double.parseDouble(substring20);
                                        if (i15 < 1) {
                                            str13 = "{pimage/Name/Width/Height} incorrect value for 'Width' (numeric value >1)";
                                        }
                                    }
                                }
                                String substring21 = Split2[3].substring(Split2[3].indexOf(":") + 1);
                                if (substring21.indexOf("dip") > -1) {
                                    i = i15;
                                    str2 = "{pimage/Name/Width/Height} value for 'Height' (numeric value in Pixel)";
                                } else {
                                    Common common73 = this.__c;
                                    boolean IsNumber16 = Common.IsNumber(substring21);
                                    Common common74 = this.__c;
                                    if (!IsNumber16) {
                                        i = i15;
                                        str2 = "{pimage/Name/Width/Height} missing value for 'Height' (numeric value in Pixel)";
                                    } else {
                                        int parseDouble7 = (int) Double.parseDouble(substring21);
                                        if (parseDouble7 < 1) {
                                            i = parseDouble7;
                                            str2 = "{pimage/Name/Width/Height} incorrect value for 'Height' (numeric value >1)";
                                        } else {
                                            i = parseDouble7;
                                            str2 = str13;
                                        }
                                    }
                                }
                            }
                            if (str2.length() <= 0) {
                                _clsrtflblimage _clsrtflblimage2 = new _clsrtflblimage();
                                _clsrtflblimage2.Initialize();
                                _clsrtflblimage2.Name = Split2[1].substring(Split2[1].indexOf(":") + 1);
                                Split2[2] = Split2[2].replace("dip", "");
                                Split2[3] = Split2[3].replace("dip", "");
                                double parseDouble8 = Double.parseDouble(Split2[2].substring(Split2[2].indexOf(":") + 1));
                                Common common75 = this.__c;
                                _clsrtflblimage2.Width = (int) (parseDouble8 * ((double) Common.DipToCurrent(1)));
                                double parseDouble9 = Double.parseDouble(Split2[3].substring(Split2[3].indexOf(":") + 1));
                                Common common76 = this.__c;
                                _clsrtflblimage2.Height = (int) (parseDouble9 * ((double) Common.DipToCurrent(1)));
                                _clsrtflblparagraph2.BilderCount++;
                                if (_clsrtflblimage2.Height > _clsrtflblparagraph2.BilderGesHeight) {
                                    _clsrtflblparagraph2.BilderGesHeight = _clsrtflblimage2.Height;
                                }
                                _clsrtflblparagraph2.BilderGesWidth = _clsrtflblimage2.Width + _clsrtflblparagraph2.BilderGesWidth + this._opt.ImagesBorder;
                                break;
                            } else {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append15 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common77 = this.__c;
                                _xtextcheckmsg(richString, substring, append15.append(Common.CRLF).append(str2).toString());
                                Common common78 = this.__c;
                                return false;
                            }
                        default:
                            str = substring.substring(indexOf12, i16 + 1) + " - invalid formatting!";
                            if (str.length() > 0) {
                                richString.Initialize(substring);
                                richString.Color(Colors.Red, indexOf12, i16 + 1);
                                richString.getClass();
                                richString.Style(1, indexOf12, i16 + 1);
                                StringBuilder append16 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                                Common common79 = this.__c;
                                _xtextcheckmsg(richString, substring, append16.append(Common.CRLF).append(str).toString());
                                Common common80 = this.__c;
                                return false;
                            }
                            str2 = str;
                            i = i15;
                            break;
                    }
                    indexOf12 = substring.indexOf("{p", i17);
                    str13 = str2;
                    i15 = i;
                }
                Common common81 = this.__c;
                Regex regex3 = Common.Regex;
                String[] Split3 = Regex.Split(",", "{pts/,{ptc/,{pc},{pdt/,{pri/,{pi/,{pbi/,{pf/,{pimage/");
                double length5 = (double) (Split3.length - 1);
                for (int i18 = 0; ((double) i18) <= length5; i18 = (int) (((double) i18) + 1.0d)) {
                    int indexOf13 = substring.indexOf(Split3[i18], i16);
                    if (indexOf13 > -1) {
                        String str16 = Split3[i18] + " - Allowed only at the beginning of paragraph (not in the text area and before sign {b}, {i}, {tc...}, {s}, {h/...}, {a/...})";
                        richString.Initialize(substring);
                        richString.Color(Colors.Red, indexOf13, Split3[i18].length() + indexOf13);
                        richString.getClass();
                        richString.Style(1, indexOf13, Split3[i18].length() + indexOf13);
                        StringBuilder append17 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                        Common common82 = this.__c;
                        _xtextcheckmsg(richString, substring, append17.append(Common.CRLF).append(str16).toString());
                        Common common83 = this.__c;
                        return false;
                    }
                }
                double length6 = (double) (Split3.length - 1);
                for (int i19 = 0; ((double) i19) <= length6; i19 = (int) (((double) i19) + 1.0d)) {
                    int indexOf14 = substring.indexOf(Split3[i19]);
                    if (indexOf14 > -1 && !Split3[i19].equals("{pimage/")) {
                        int indexOf15 = substring.indexOf(Split3[i19], indexOf14 + 1);
                        if (indexOf15 > -1) {
                            String str17 = Split3[i19] + " - exists doubly";
                            richString.Initialize(substring);
                            richString.Color(Colors.Blue, indexOf14, Split3[i19].length() + indexOf14);
                            richString.getClass();
                            richString.Style(1, indexOf14, Split3[i19].length() + indexOf14);
                            richString.Color(Colors.Red, indexOf15, Split3[i19].length() + indexOf15);
                            richString.getClass();
                            richString.Style(1, indexOf15, Split3[i19].length() + indexOf15);
                            StringBuilder append18 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                            Common common84 = this.__c;
                            _xtextcheckmsg(richString, substring, append18.append(Common.CRLF).append(str17).toString());
                            Common common85 = this.__c;
                            return false;
                        }
                    }
                }
                if (_clsrtflblparagraph2.BilderCount > 0) {
                    Common common86 = this.__c;
                    boolean z = true;
                    if ((substring.length() - 1) - i17 > 0 && substring.substring(i17).trim().length() > 0) {
                        Common common87 = this.__c;
                        z = false;
                    }
                    Common common88 = this.__c;
                    if (!z && _clsrtflblparagraph2.Zentriert == 1) {
                        int indexOf16 = substring.indexOf("{pc}");
                        richString.Initialize(substring);
                        richString.Color(Colors.Red, indexOf16, indexOf16 + 4);
                        richString.getClass();
                        richString.Style(1, indexOf16, indexOf16 + 4);
                        StringBuilder append19 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                        Common common89 = this.__c;
                        _xtextcheckmsg(richString, substring, append19.append(Common.CRLF).append("Paragraph may not be centered when image and text exist").toString());
                        Common common90 = this.__c;
                        return false;
                    }
                }
                int i20 = _clsrtflblparagraph2.IndentLevel * this._opt.IndentWidth;
                if (i20 > 0) {
                    i20 = (i20 - this._opt.IndentWidth) + _clsrtflblparagraph2.IndentMinWidth;
                }
                int i21 = ((this._gesbr - (this._opt.TextBorder * 2)) - i20) - (_clsrtflblparagraph2.RightIndentLevel * this._opt.IndentWidth);
                if (i21 < ((int) this._canvasber.MeasureStringWidth("AW", (Typeface) this._tyface[3].getObject(), _clsrtflblparagraph2.TextSize))) {
                    String str18 = "Space for text too small (" + BA.NumberToString(i21) + " Pixel)";
                    int indexOf17 = substring.indexOf("{pi");
                    if (indexOf17 == -1) {
                        indexOf17 = substring.indexOf("{pbi");
                    }
                    if (indexOf17 == -1) {
                        indexOf17 = substring.indexOf("{pri");
                    }
                    if (indexOf17 == -1) {
                        indexOf17 = 0;
                    }
                    richString.Initialize(substring);
                    richString.Color(Colors.Red, indexOf17, indexOf17 + 4);
                    richString.getClass();
                    richString.Style(1, indexOf17, indexOf17 + 4);
                    StringBuilder append20 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                    Common common91 = this.__c;
                    _xtextcheckmsg(richString, substring, append20.append(Common.CRLF).append(str18).toString());
                    Common common92 = this.__c;
                    return false;
                }
                String str19 = str13;
                int indexOf18 = substring.indexOf("{");
                while (indexOf18 > -1) {
                    int indexOf19 = substring.indexOf("}", indexOf18 + 1);
                    String substring22 = substring.substring(indexOf18, indexOf19 + 1);
                    if (str14.indexOf(substring22) == -1) {
                        StringBuilder append21 = new StringBuilder().append("Error in paragraph ").append(BA.NumberToString(i9)).append(":");
                        Common common93 = this.__c;
                        str19 = append21.append(Common.CRLF).append(substring22).append(" - invalid formatting!").toString();
                    }
                    if (str19.length() > 0) {
                        richString.Initialize(substring);
                        richString.Color(Colors.Red, indexOf18, indexOf19 + 1);
                        richString.getClass();
                        richString.Style(1, indexOf18, indexOf19 + 1);
                        _xtextcheckmsg(richString, substring, str19);
                        Common common94 = this.__c;
                        return false;
                    }
                    indexOf18 = substring.indexOf("{", indexOf19 + 1);
                }
                i7 = i9;
                i6 = i16;
                i4 = i15;
                str7 = str19;
                i8 = length2;
            }
        }
        for (int numberOfViews = this._tasv.getPanel().getNumberOfViews() - 1; ((double) numberOfViews) >= 0.0d; numberOfViews = (int) (((double) numberOfViews) - 4.0d)) {
            this._tasv.getPanel().RemoveViewAt(numberOfViews);
        }
        Common common95 = this.__c;
        return true;
    }

    public String _xtextcheckmsg(RichStringBuilder.RichString richString, String str, String str2) throws Exception {
        this._tamsg.setText((Object) str2);
        this._tamsg.setHeight(this._strutils.MeasureMultilineTextHeight((TextView) this._tamsg.getObject(), str2));
        this._talbl.setText(richString.getObject());
        this._talbl.setTop(this._tamsg.getHeight());
        this._talbl.setHeight(this._strutils.MeasureMultilineTextHeight((TextView) this._talbl.getObject(), str));
        this._tasv.getPanel().setHeight(this._tamsg.getHeight() + this._talbl.getHeight());
        LabelWrapper labelWrapper = this._tamsg;
        Common common = this.__c;
        labelWrapper.setVisible(true);
        LabelWrapper labelWrapper2 = this._talbl;
        Common common2 = this.__c;
        labelWrapper2.setVisible(true);
        return "";
    }

    /* JADX WARNING: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x01ea  */
    public String _xtextmarkieren(String str) throws Exception {
        int i;
        String str2;
        String str3;
        String trim;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        Common common = this.__c;
        int Max = (int) Common.Max(this._pmark.DownZeileA, this._pmark.DownZeileB);
        Common common2 = this.__c;
        int Max2 = (int) Common.Max(this._pmark.PressZeileA, this._pmark.PressZeileB);
        if (Max2 < Max) {
            i5 = this._pmark.PressZeileA;
            i6 = this._pmark.DownZeileA;
            i7 = this._pmark.PressZeileB;
            i8 = this._pmark.DownZeileB;
            i9 = this._pmark.PressX;
            i10 = this._pmark.DownX;
        } else if (Max2 >= Max) {
            i5 = this._pmark.DownZeileA;
            i6 = this._pmark.PressZeileA;
            i7 = this._pmark.DownZeileB;
            i8 = this._pmark.PressZeileB;
            i9 = this._pmark.DownX;
            i10 = this._pmark.PressX;
        }
        Common common3 = this.__c;
        int Max3 = (int) Common.Max(i5, i7);
        Common common4 = this.__c;
        int Max4 = (int) Common.Max(i6, i8);
        double d = (double) (Max3 - 1);
        for (int i11 = this._pmark.ZeileMin; ((double) i11) <= d; i11 = (int) (((double) i11) + 1.0d)) {
            ImageViewWrapper imageViewWrapper = this._imgmark[i11];
            Common common5 = this.__c;
            imageViewWrapper.setVisible(false);
        }
        double d2 = (double) this._pmark.ZeileMax;
        for (int i12 = Max4 + 1; ((double) i12) <= d2; i12 = (int) (((double) i12) + 1.0d)) {
            ImageViewWrapper imageViewWrapper2 = this._imgmark[i12];
            Common common6 = this.__c;
            imageViewWrapper2.setVisible(false);
        }
        this._pmark.ZeileMin = Max3;
        this._pmark.ZeileMax = Max4;
        double d3 = (double) Max4;
        int i13 = i6;
        int i14 = i5;
        int i15 = i8;
        int i16 = i7;
        int i17 = i10;
        int i18 = i9;
        for (int i19 = Max3; ((double) i19) <= d3; i19 = (int) (((double) i19) + 1.0d)) {
            _clsrtflblmarkpositionvon _clsrtflblmarkpositionvon2 = new _clsrtflblmarkpositionvon();
            _clsrtflblmarkpositionbis _clsrtflblmarkpositionbis2 = new _clsrtflblmarkpositionbis();
            if (this._absaetze[this._zeiledat[i19].AbsatzID].ZeileVon != i19 || this._absaetze[this._zeiledat[i19].AbsatzID].BulletChars.length() <= 0) {
                i2 = 0;
            } else {
                i2 = this._absaetze[this._zeiledat[i19].AbsatzID].IndentMinWidth;
            }
            if (i19 > Max3 && i19 < Max4) {
                if (this._zeiledat[i19].TextPosBis > this._zeiledat[i19].TextPosAb) {
                    _clsrtflblmarkpositionvon2.LblVon = this._zeiledat[i19].LblPosX - i2;
                    _clsrtflblmarkpositionbis2.LblBis = (this._zeiledat[i19].LblPosX + this._zeiledat[i19].LblWidth) - 1;
                } else {
                    _clsrtflblmarkpositionvon2.LblVon = -1;
                    _clsrtflblmarkpositionbis2.LblBis = -1;
                }
            }
            if (this._zeiledat[i19].TextPosAb == this._zeiledat[i19].TextPosBis) {
                _clsrtflblmarkpositionvon2.LblVon = -1;
                _clsrtflblmarkpositionbis2.LblBis = -1;
                if (i19 == Max3) {
                    this._pmark.TextPosVon = this._zeiledat[i19].TextPosAb;
                } else if (i19 == Max4) {
                    this._pmark.TextPosBis = this._zeiledat[i19].TextPosBis;
                }
            } else {
                if (i19 == Max3 && Max3 < Max4) {
                    _clsrtflblmarkpositionvon2 = _xtextmarkierenposvonermitteln(i19, i18, i2);
                    this._pmark.TextPosVon = _clsrtflblmarkpositionvon2.TextPosVon;
                    this._pmark.VonVorZeile = _clsrtflblmarkpositionvon2.VonVorZeile;
                    _clsrtflblmarkpositionbis2.LblBis = (this._zeiledat[i19].LblPosX + this._zeiledat[i19].LblWidth) - 1;
                }
                if (i19 == Max4 && Max4 > Max3) {
                    if (i13 > -1) {
                        _clsrtflblmarkpositionvon2.LblVon = -1;
                        _clsrtflblmarkpositionbis2.LblBis = -1;
                        this._pmark.TextPosBis = this._zeiledat[i19].TextPosAb - 1;
                    } else {
                        _clsrtflblmarkpositionbis2 = _xtextmarkierenposbisermitteln(i19, i17, i2);
                        this._pmark.TextPosBis = _clsrtflblmarkpositionbis2.TextPosBis;
                        this._pmark.BisVorZeile = _clsrtflblmarkpositionbis2.BisVorZeile;
                        _clsrtflblmarkpositionvon2.LblVon = this._zeiledat[i19].LblPosX - i2;
                    }
                }
                if (i19 == Max3 && i19 == Max4) {
                    if (i14 <= -1 || i13 <= -1) {
                        if (i16 <= -1 || i15 <= -1 || i18 <= i17) {
                            i3 = i18;
                            i4 = i17;
                        } else {
                            i3 = this._pmark.PressX;
                            i4 = this._pmark.DownX;
                        }
                        if (i16 > -1 && i13 > -1) {
                            i14 = this._pmark.PressZeileA;
                            i13 = this._pmark.DownZeileA;
                            i16 = this._pmark.PressZeileB;
                            i15 = this._pmark.DownZeileB;
                            i3 = this._pmark.PressX;
                            i4 = this._pmark.DownX;
                        }
                        if (i14 > -1) {
                            i3 = 0;
                        }
                        _clsrtflblmarkpositionvon _xtextmarkierenposvonermitteln = _xtextmarkierenposvonermitteln(i19, i3, i2);
                        this._pmark.TextPosVon = _xtextmarkierenposvonermitteln.TextPosVon;
                        this._pmark.VonVorZeile = _xtextmarkierenposvonermitteln.VonVorZeile;
                        _clsrtflblmarkpositionbis _xtextmarkierenposbisermitteln = _xtextmarkierenposbisermitteln(i19, i4, i2);
                        this._pmark.TextPosBis = _xtextmarkierenposbisermitteln.TextPosBis;
                        this._pmark.BisVorZeile = _xtextmarkierenposbisermitteln.BisVorZeile;
                        _clsrtflblmarkpositionvon2 = _xtextmarkierenposvonermitteln;
                        i17 = i4;
                        i18 = i3;
                        _clsrtflblmarkpositionbis2 = _xtextmarkierenposbisermitteln;
                    } else {
                        _clsrtflblmarkpositionvon2.LblVon = -1;
                        _clsrtflblmarkpositionbis2.LblBis = -1;
                        this._pmark.TextPosVon = -1;
                        this._pmark.TextPosBis = -1;
                    }
                }
            }
            if (this._pmark.TextPosBis > this._textdat.Text.length() - 1) {
                this._pmark.TextPosBis = this._textdat.Text.length() - 1;
            }
            if (_clsrtflblmarkpositionbis2.LblBis <= -1 || _clsrtflblmarkpositionvon2.LblVon <= -1) {
                ImageViewWrapper imageViewWrapper3 = this._imgmark[i19];
                Common common7 = this.__c;
                imageViewWrapper3.setVisible(false);
            } else {
                if (_clsrtflblmarkpositionbis2.LblBis > (this._gesbr - this._opt.TextBorder) - 1) {
                    _clsrtflblmarkpositionbis2.LblBis = (this._gesbr - this._opt.TextBorder) - 1;
                }
                this._imgmark[i19].SetLayout(_clsrtflblmarkpositionvon2.LblVon + this._zeiledat[i19].KorrZentriert, this._imgmark[i19].getTop(), (_clsrtflblmarkpositionbis2.LblBis - _clsrtflblmarkpositionvon2.LblVon) + 1, this._zeiledat[i19].ZeileHo2);
                ImageViewWrapper imageViewWrapper4 = this._imgmark[i19];
                Common common8 = this.__c;
                imageViewWrapper4.setVisible(true);
            }
        }
        if (!str.equals("Up")) {
            return "";
        }
        String str4 = "";
        if (this._pmark.TextPosVon > -1 && this._pmark.TextPosBis >= this._pmark.TextPosVon) {
            if (this._pmark.TextPosBis > this._textdat.Text.length() - 1) {
                this._pmark.TextPosBis = this._textdat.Text.length() - 1;
            }
            str4 = _xtextmarkohneformatzeichen(this._textdat.Text.substring(this._pmark.TextPosVon, this._pmark.TextPosBis + 1));
        }
        if (Max2 < Max) {
            i = Max2;
        } else {
            i = Max;
            Max = Max2;
        }
        String str5 = this._absaetze[this._zeiledat[i].AbsatzID].BulletChars;
        if (str5.length() > 0) {
            String str6 = str5 + " ";
            boolean z = this._pmark.VonVorZeile;
            Common common9 = this.__c;
            if (!z && this._pmark.TextPosVon == this._zeiledat[i].TextPosAb && str4.indexOf(str6) == 0) {
                str2 = str4.length() > str6.length() ? str4.substring(str6.length()) : "";
                str3 = this._absaetze[this._zeiledat[Max].AbsatzID].BulletChars;
                if (str3.length() > 0) {
                    String str7 = str3 + " ";
                    boolean z2 = this._pmark.BisVorZeile;
                    Common common10 = this.__c;
                    if (z2) {
                        str2 = str2 + str7;
                    }
                }
                trim = str2.trim();
                if (trim.length() > 1) {
                    Common common11 = this.__c;
                    boolean endsWith = trim.endsWith(Common.CRLF);
                    Common common12 = this.__c;
                    if (endsWith) {
                        trim = trim.substring(0, trim.length() - 1);
                    }
                }
                if (trim.length() > 0) {
                    return "";
                }
                Common common13 = this.__c;
                if (!Common.SubExists(this.ba, this._callback, this._event + "_MarkComplete")) {
                    return "";
                }
                Common common14 = this.__c;
                Common.CallSubNew2(this.ba, this._callback, this._event + "_MarkComplete", trim);
                return "";
            }
        }
        str2 = str4;
        str3 = this._absaetze[this._zeiledat[Max].AbsatzID].BulletChars;
        if (str3.length() > 0) {
        }
        trim = str2.trim();
        if (trim.length() > 1) {
        }
        if (trim.length() > 0) {
        }
    }

    public _clsrtflblmarkpositionbis _xtextmarkierenposbisermitteln(int i, int i2, int i3) throws Exception {
        int i4;
        int i5;
        int i6;
        int i7;
        char c;
        String str;
        float f;
        _clsrtflblmarkpositionbis _clsrtflblmarkpositionbis2 = new _clsrtflblmarkpositionbis();
        _clsrtflblmarkpositionbis2.Initialize();
        int i8 = 0;
        int i9 = 0;
        char c2 = 0;
        _clsrtflblmarkpositionbis2.LblBis = -1;
        _clsrtflblmarkpositionbis2.TextPosBis = -1;
        if (i3 > 0 && i2 < this._zeiledat[i].LblPosX && i2 > (this._zeiledat[i].LblPosX - i3) - 1) {
            _clsrtflblmarkpositionbis2.LblBis = this._zeiledat[i].LblPosX - 1;
            _clsrtflblmarkpositionbis2.TextPosBis = this._zeiledat[i].TextPosAb - 1;
            Common common = this.__c;
            _clsrtflblmarkpositionbis2.BisVorZeile = true;
            return _clsrtflblmarkpositionbis2;
        } else if (i2 < this._zeiledat[i].LblPosX) {
            _clsrtflblmarkpositionbis2.TextPosBis = this._zeiledat[i].TextPosAb - 1;
            return _clsrtflblmarkpositionbis2;
        } else if (i2 > (this._zeiledat[i].LblPosX + this._zeiledat[i].LblWidth) - 1) {
            _clsrtflblmarkpositionbis2.TextPosBis = this._zeiledat[i].TextPosBis;
            _clsrtflblmarkpositionbis2.LblBis = (this._zeiledat[i].LblPosX + this._zeiledat[i].LblWidth) - 1;
            return _clsrtflblmarkpositionbis2;
        } else {
            int i10 = i2 - this._zeiledat[i].LblPosX;
            float f2 = this._absaetze[this._zeiledat[i].AbsatzID].TextSize;
            byte b = this._zeiledat[i].BeginnFormat;
            if (b == 1 || b == 3 || b == 5 || b == 7) {
                i8 = 1;
            }
            if (b == 2 || b == 3 || b == 6 || b == 7) {
                i9 = 2;
            }
            if (b > 3) {
                c2 = 4;
            }
            int[] iArr = new int[100];
            int[] iArr2 = new int[100];
            int[] iArr3 = new int[100];
            int[] iArr4 = new int[100];
            String substring = this._textdat.Text.substring(this._zeiledat[i].TextPosAb, this._zeiledat[i].TextPosBis + 1);
            Common common2 = this.__c;
            int indexOf = substring.indexOf(Common.CRLF);
            if (indexOf > -1) {
                substring = substring.substring(0, indexOf);
            }
            int i11 = 0;
            int i12 = 0;
            int i13 = i9;
            int i14 = 0;
            while (true) {
                if (i11 <= substring.length()) {
                    int indexOf2 = substring.indexOf(" ", i11);
                    if (indexOf2 == -1) {
                        i4 = substring.length() - 1;
                    } else {
                        i4 = indexOf2;
                    }
                    if (i11 <= i4) {
                        String substring2 = substring.substring(i11, i4 + 1);
                        iArr3[i12] = i11;
                        iArr4[i12] = i4;
                        iArr[i12] = -1;
                        int i15 = i14;
                        while (true) {
                            if (substring2.length() > 0) {
                                while (true) {
                                    boolean startsWith = substring2.startsWith("{");
                                    Common common3 = this.__c;
                                    if (startsWith) {
                                        switch (BA.switchObjectToInt(substring2.substring(0, 3), "{s}", "{b}", "{i}")) {
                                            case 0:
                                                if (c2 == 4) {
                                                    c2 = 0;
                                                } else {
                                                    c2 = 4;
                                                }
                                                substring2 = substring2.substring(3);
                                                i11 += 3;
                                                break;
                                            case 1:
                                                if (i8 == 1) {
                                                    i8 = 0;
                                                } else {
                                                    i8 = 1;
                                                }
                                                substring2 = substring2.substring(3);
                                                i11 += 3;
                                                break;
                                            case 2:
                                                if (i13 == 2) {
                                                    i13 = 0;
                                                } else {
                                                    i13 = 2;
                                                }
                                                substring2 = substring2.substring(3);
                                                i11 += 3;
                                                break;
                                            default:
                                                int indexOf3 = substring2.indexOf("}");
                                                substring2 = substring2.substring(indexOf3 + 1);
                                                i11 = i11 + indexOf3 + 1;
                                                break;
                                        }
                                        if (substring2.length() < 2) {
                                            i5 = i13;
                                            i6 = i8;
                                            str = substring2;
                                            i7 = i11;
                                            c = c2;
                                        }
                                    } else {
                                        i5 = i13;
                                        i6 = i8;
                                        str = substring2;
                                        i7 = i11;
                                        c = c2;
                                    }
                                }
                                if (str.length() != 0) {
                                    int indexOf4 = str.indexOf("{");
                                    if (indexOf4 > 0) {
                                        String substring3 = str.substring(0, indexOf4);
                                        substring2 = str.substring(indexOf4);
                                        str = substring3;
                                    } else {
                                        substring2 = "";
                                    }
                                    int length = i7 + str.length();
                                    if (c == 0) {
                                        f = f2;
                                    } else {
                                        f = this._opt.TextSizeSmall * f2;
                                    }
                                    int MeasureStringWidth = (int) this._canvasber.MeasureStringWidth(str, (Typeface) this._tyface[i6 + i5].getObject(), f);
                                    int i16 = i15 + MeasureStringWidth;
                                    if (iArr[i12] == -1) {
                                        iArr[i12] = i15;
                                    }
                                    iArr2[i12] = (MeasureStringWidth + i15) - 1;
                                    i13 = i5;
                                    i8 = i6;
                                    i15 = i16;
                                    c2 = c;
                                    i11 = length;
                                }
                            } else {
                                i5 = i13;
                                i6 = i8;
                                i7 = i11;
                                c = c2;
                            }
                        }
                        if (i10 < iArr[i12] || i10 > iArr2[i12]) {
                            int i17 = i12 + 1;
                            if (i7 <= substring.length() - 1) {
                                i12 = i17;
                                i13 = i5;
                                c2 = c;
                                i14 = i15;
                                i11 = i7;
                                i8 = i6;
                            }
                        } else {
                            _clsrtflblmarkpositionbis2.LblBis = iArr2[i12];
                            _clsrtflblmarkpositionbis2.TextPosBis = iArr4[i12] + this._zeiledat[i].TextPosAb;
                        }
                    }
                }
            }
            if (_clsrtflblmarkpositionbis2.TextPosBis > -1) {
                _clsrtflblmarkpositionbis2.LblBis += this._zeiledat[i].LblPosX;
            }
            return _clsrtflblmarkpositionbis2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0147  */
    public _clsrtflblmarkpositionvon _xtextmarkierenposvonermitteln(int i, int i2, int i3) throws Exception {
        int i4;
        int i5;
        int i6;
        int i7;
        char c;
        String str;
        float f;
        _clsrtflblmarkpositionvon _clsrtflblmarkpositionvon2 = new _clsrtflblmarkpositionvon();
        _clsrtflblmarkpositionvon2.Initialize();
        int i8 = 0;
        int i9 = 0;
        char c2 = 0;
        _clsrtflblmarkpositionvon2.LblVon = -1;
        _clsrtflblmarkpositionvon2.TextPosVon = -1;
        if (i2 < this._zeiledat[i].LblPosX) {
            _clsrtflblmarkpositionvon2.LblVon = this._zeiledat[i].LblPosX - i3;
            _clsrtflblmarkpositionvon2.TextPosVon = this._zeiledat[i].TextPosAb;
            Common common = this.__c;
            _clsrtflblmarkpositionvon2.VonVorZeile = true;
            return _clsrtflblmarkpositionvon2;
        } else if (i2 > (this._zeiledat[i].LblPosX + this._zeiledat[i].LblWidth) - 1) {
            _clsrtflblmarkpositionvon2.TextPosVon = this._zeiledat[i].TextPosBis;
            String substring = this._textdat.Text.substring(this._zeiledat[i].TextPosBis, this._zeiledat[i].TextPosBis + 1);
            Common common2 = this.__c;
            if (!substring.equals(Common.CRLF)) {
                _clsrtflblmarkpositionvon2.TextPosVon++;
            }
            Common common3 = this.__c;
            _clsrtflblmarkpositionvon2.VonVorZeile = false;
            return _clsrtflblmarkpositionvon2;
        } else {
            int i10 = i2 - this._zeiledat[i].LblPosX;
            float f2 = this._absaetze[this._zeiledat[i].AbsatzID].TextSize;
            byte b = this._zeiledat[i].BeginnFormat;
            if (b == 1 || b == 3 || b == 5 || b == 7) {
                i8 = 1;
            }
            if (b == 2 || b == 3 || b == 6 || b == 7) {
                i9 = 2;
            }
            if (b > 3) {
                c2 = 4;
            }
            int[] iArr = new int[100];
            int[] iArr2 = new int[100];
            int[] iArr3 = new int[100];
            int[] iArr4 = new int[100];
            String substring2 = this._textdat.Text.substring(this._zeiledat[i].TextPosAb, this._zeiledat[i].TextPosBis + 1);
            Common common4 = this.__c;
            int indexOf = substring2.indexOf(Common.CRLF);
            if (indexOf > -1) {
                substring2 = substring2.substring(0, indexOf);
            }
            int i11 = 0;
            int i12 = 0;
            int i13 = i9;
            int i14 = 0;
            while (i11 <= substring2.length()) {
                int indexOf2 = substring2.indexOf(" ", i11);
                if (indexOf2 == -1) {
                    i4 = substring2.length() - 1;
                } else {
                    i4 = indexOf2;
                }
                if (i11 <= i4) {
                    String substring3 = substring2.substring(i11, i4 + 1);
                    iArr3[i12] = i11;
                    iArr4[i12] = i4;
                    iArr[i12] = -1;
                    int i15 = i14;
                    while (true) {
                        if (substring3.length() > 0) {
                            while (true) {
                                boolean startsWith = substring3.startsWith("{");
                                Common common5 = this.__c;
                                if (startsWith) {
                                    switch (BA.switchObjectToInt(substring3.substring(0, 3), "{s}", "{b}", "{i}")) {
                                        case 0:
                                            if (c2 == 4) {
                                                c2 = 0;
                                            } else {
                                                c2 = 4;
                                            }
                                            substring3 = substring3.substring(3);
                                            i11 += 3;
                                            break;
                                        case 1:
                                            if (i8 == 1) {
                                                i8 = 0;
                                            } else {
                                                i8 = 1;
                                            }
                                            substring3 = substring3.substring(3);
                                            i11 += 3;
                                            break;
                                        case 2:
                                            if (i13 == 2) {
                                                i13 = 0;
                                            } else {
                                                i13 = 2;
                                            }
                                            substring3 = substring3.substring(3);
                                            i11 += 3;
                                            break;
                                        default:
                                            int indexOf3 = substring3.indexOf("}");
                                            substring3 = substring3.substring(indexOf3 + 1);
                                            i11 = i11 + indexOf3 + 1;
                                            break;
                                    }
                                    if (substring3.length() < 2) {
                                        i5 = i13;
                                        i6 = i8;
                                        str = substring3;
                                        i7 = i11;
                                        c = c2;
                                    }
                                } else {
                                    i5 = i13;
                                    i6 = i8;
                                    str = substring3;
                                    i7 = i11;
                                    c = c2;
                                }
                            }
                            if (str.length() != 0) {
                                int indexOf4 = str.indexOf("{");
                                if (indexOf4 > 0) {
                                    String substring4 = str.substring(0, indexOf4);
                                    substring3 = str.substring(indexOf4);
                                    str = substring4;
                                } else {
                                    substring3 = "";
                                }
                                int length = i7 + str.length();
                                if (c == 0) {
                                    f = f2;
                                } else {
                                    f = this._opt.TextSizeSmall * f2;
                                }
                                int MeasureStringWidth = (int) this._canvasber.MeasureStringWidth(str, (Typeface) this._tyface[i6 + i5].getObject(), f);
                                int i16 = i15 + MeasureStringWidth;
                                if (iArr[i12] == -1) {
                                    iArr[i12] = i15;
                                }
                                iArr2[i12] = (MeasureStringWidth + i15) - 1;
                                if (i10 < iArr[i12] || i10 > iArr2[i12]) {
                                    i13 = i5;
                                    i8 = i6;
                                    i15 = i16;
                                    c2 = c;
                                    i11 = length;
                                } else {
                                    _clsrtflblmarkpositionvon2.LblVon = iArr[i12];
                                    _clsrtflblmarkpositionvon2.TextPosVon = iArr3[i12] + this._zeiledat[i].TextPosAb;
                                    i7 = length;
                                    i15 = i16;
                                }
                            }
                        } else {
                            i5 = i13;
                            i6 = i8;
                            i7 = i11;
                            c = c2;
                        }
                    }
                    if (_clsrtflblmarkpositionvon2.TextPosVon <= -1) {
                        int i17 = i12 + 1;
                        if (i7 <= substring2.length() - 1) {
                            i12 = i17;
                            i13 = i5;
                            c2 = c;
                            i14 = i15;
                            i11 = i7;
                            i8 = i6;
                        }
                    }
                }
                if (_clsrtflblmarkpositionvon2.TextPosVon > -1) {
                    _clsrtflblmarkpositionvon2.LblVon += this._zeiledat[i].LblPosX;
                }
                return _clsrtflblmarkpositionvon2;
            }
            if (_clsrtflblmarkpositionvon2.TextPosVon > -1) {
            }
            return _clsrtflblmarkpositionvon2;
        }
    }

    public String _xtextmarkohneformatzeichen(String str) throws Exception {
        int i = 0;
        Arrays.fill(new String[0], "");
        StringBuilderWrapper stringBuilderWrapper = new StringBuilderWrapper();
        stringBuilderWrapper.Initialize();
        int indexOf = str.indexOf("{");
        if (indexOf == -1) {
            return str;
        }
        while (true) {
            if (indexOf <= -1) {
                break;
            }
            stringBuilderWrapper.Append(str.substring(i, indexOf));
            int indexOf2 = str.indexOf("}", indexOf);
            if (indexOf2 == -1) {
                break;
            }
            if (str.substring(indexOf, indexOf + 4).equals("{pbi")) {
                Common common = this.__c;
                Regex regex = Common.Regex;
                String[] Split = Regex.Split("/", str.substring(indexOf, indexOf2));
                String substring = Split[4].substring(Split[4].indexOf(":") + 1);
                if (substring.indexOf("(+)") > -1) {
                    substring = substring.replace("(+)", "●");
                }
                if (substring.indexOf("(-)") > -1) {
                    substring = substring.replace("(-)", "○");
                }
                if (substring.indexOf("_") > -1) {
                    substring = substring.replace("_", " ");
                }
                stringBuilderWrapper.Append(substring).Append(" ");
            }
            i = indexOf2 + 1;
            indexOf = str.indexOf("{", i);
            if (indexOf == -1) {
                stringBuilderWrapper.Append(str.substring(i));
                break;
            }
        }
        return stringBuilderWrapper.ToString();
    }

    public int _xtopindex(int i) throws Exception {
        if (this._textdat.ZeilenCount == 0) {
            return -1;
        }
        if (i < 3) {
            return 0;
        }
        double d = (double) (this._textdat.ZeilenCount - 1);
        for (int i2 = 0; ((double) i2) <= d; i2 = (int) (((double) i2) + 1.0d)) {
            int i3 = this._zeiledat[i2].ZeilePosTop;
            Common common = this.__c;
            if (i3 > Common.DipToCurrent(9) + i) {
                return i2;
            }
        }
        return 0;
    }

    public int _xwortbreite(String str, int i, int i2, int i3, float f) throws Exception {
        float f2;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        int length = str.length() - 1;
        int i8 = 0;
        int i9 = i3;
        int i10 = i2;
        int i11 = i;
        while (i8 <= length) {
            int indexOf = str.indexOf("{", i8);
            if (indexOf == i8) {
                int indexOf2 = str.indexOf("}", indexOf);
                if (indexOf2 == -1) {
                    return -indexOf;
                }
                switch (BA.switchObjectToInt(str.substring(indexOf, indexOf + 3), "{s}", "{b}", "{i}")) {
                    case 0:
                        if (i11 != 0) {
                            i5 = i10;
                            i6 = 0;
                            i4 = i9;
                            break;
                        } else {
                            i5 = i10;
                            i6 = 4;
                            i4 = i9;
                            break;
                        }
                    case 1:
                        if (i10 != 0) {
                            i5 = 0;
                            i4 = i9;
                            i6 = i11;
                            break;
                        } else {
                            i5 = 1;
                            i4 = i9;
                            i6 = i11;
                            break;
                        }
                    case 2:
                        if (i9 != 0) {
                            i4 = 0;
                            i5 = i10;
                            i6 = i11;
                            break;
                        } else {
                            i4 = 2;
                            i5 = i10;
                            i6 = i11;
                            break;
                        }
                    default:
                        i4 = i9;
                        i5 = i10;
                        i6 = i11;
                        break;
                }
                if (indexOf2 == length) {
                    return i7;
                }
                i8 = indexOf2 + 1;
                i11 = i6;
                i10 = i5;
                i9 = i4;
            } else if (indexOf > i8) {
                if (i11 == 0) {
                    f2 = f;
                } else {
                    f2 = this._opt.TextSizeSmall * f;
                }
                i7 = (int) (this._canvasber.MeasureStringWidth(str.substring(i8, indexOf), (Typeface) this._tyface[i10 + i9].getObject(), f2) + ((float) i7));
                i8 = indexOf;
            } else {
                if (i11 != 0) {
                    f *= this._opt.TextSizeSmall;
                }
                return (int) (this._canvasber.MeasureStringWidth(str.substring(i8), (Typeface) this._tyface[i9 + i10].getObject(), f) + ((float) i7));
            }
        }
        return i7;
    }

    @Override // anywheresoftware.b4a.BA.SubDelegator
    public Object callSub(String str, Object obj, Object[] objArr) throws Exception {
        this.ba.sharedProcessBA.sender = obj;
        return BA.SubDelegator.SubNotFound;
    }
}
