package anywheresoftware.b4a.keywords;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import anywheresoftware.b4a.BA;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class DateTime {
    public static final long TicksPerDay = 86400000;
    public static final long TicksPerHour = 3600000;
    public static final long TicksPerMinute = 60000;
    public static final long TicksPerSecond = 1000;
    private static DateTime _instance;
    private static long lastTimeSetEvent;
    private static boolean listenToTimeZone = false;
    private static TimeZone zeroTimeZone = new SimpleTimeZone(0, "13256");
    private Calendar cal = Calendar.getInstance(Locale.US);
    private Date date;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private SimpleDateFormat timeFormat;
    private TimeZone timeZone;

    private static DateTime getInst() {
        if (_instance == null) {
            _instance = new DateTime();
        }
        return _instance;
    }

    private DateTime() {
        this.dateFormat.setLenient(false);
        this.timeFormat = new SimpleDateFormat("HH:mm:ss");
        this.timeFormat.setLenient(false);
        this.date = new Date(0);
        this.timeZone = TimeZone.getDefault();
    }

    public static void ListenToExternalTimeChanges(final BA ba) {
        if (!listenToTimeZone) {
            listenToTimeZone = true;
            BroadcastReceiver br = new BroadcastReceiver() {
                /* class anywheresoftware.b4a.keywords.DateTime.AnonymousClass1 */

                public void onReceive(Context context, Intent intent) {
                    if (intent.hasExtra("time-zone")) {
                        DateTime.setTimeZoneInternal(TimeZone.getTimeZone(intent.getStringExtra("time-zone")));
                    }
                    if (DateTime.getNow() - DateTime.lastTimeSetEvent > 100) {
                        BA.this.raiseEventFromDifferentThread(null, null, 0, "datetime_timechanged", false, null);
                    }
                    DateTime.lastTimeSetEvent = DateTime.getNow();
                }
            };
            IntentFilter fil = new IntentFilter("android.intent.action.TIMEZONE_CHANGED");
            fil.addAction("android.intent.action.TIME_SET");
            BA.applicationContext.registerReceiver(br, fil);
        }
    }

    public static long getNow() {
        return System.currentTimeMillis();
    }

    public static String Date(long Ticks) {
        DateTime d = getInst();
        d.date.setTime(Ticks);
        return d.dateFormat.format(d.date);
    }

    public static String Time(long Ticks) {
        DateTime d = getInst();
        d.date.setTime(Ticks);
        return d.timeFormat.format(d.date);
    }

    public static String getTimeFormat() {
        return getInst().timeFormat.toPattern();
    }

    public static void setTimeFormat(String Pattern) {
        getInst().timeFormat.applyPattern(Pattern);
    }

    public static String getDateFormat() {
        return getInst().dateFormat.toPattern();
    }

    public static void setDateFormat(String Pattern) {
        getInst().dateFormat.applyPattern(Pattern);
    }

    public static long DateParse(String Date) throws ParseException {
        return getInst().dateFormat.parse(Date).getTime();
    }

    public static String getDeviceDefaultDateFormat() {
        return ((SimpleDateFormat) DateFormat.getDateInstance()).toPattern();
    }

    public static String getDeviceDefaultTimeFormat() {
        return ((SimpleDateFormat) DateFormat.getTimeInstance()).toPattern();
    }

    public static long TimeParse(String Time) throws ParseException {
        SimpleDateFormat tf = getInst().timeFormat;
        tf.setTimeZone(zeroTimeZone);
        long time = tf.parse(Time).getTime();
        tf.setTimeZone(getInst().timeZone);
        long offsetInMinutes = Math.round(getTimeZoneOffset() * 60.0d);
        long dayStartInUserTimeZone = System.currentTimeMillis() + (TicksPerMinute * offsetInMinutes);
        return (time % TicksPerDay) + ((dayStartInUserTimeZone - (dayStartInUserTimeZone % TicksPerDay)) - (TicksPerMinute * offsetInMinutes));
    }

    public static long DateTimeParse(String Date, String Time) throws ParseException {
        SimpleDateFormat df = getInst().dateFormat;
        SimpleDateFormat tf = getInst().timeFormat;
        df.setTimeZone(zeroTimeZone);
        tf.setTimeZone(zeroTimeZone);
        try {
            long total = DateParse(Date) + tf.parse(Time).getTime();
            int endShift = (int) (GetTimeZoneOffsetAt(total) * 3600000.0d);
            long total2 = total - ((long) endShift);
            return total2 + ((long) (endShift - ((int) (GetTimeZoneOffsetAt(total2) * 3600000.0d))));
        } finally {
            tf.setTimeZone(getInst().timeZone);
            df.setTimeZone(getInst().timeZone);
        }
    }

    public static void SetTimeZone(double OffsetHours) {
        setTimeZoneInternal(new SimpleTimeZone((int) Math.round(3600.0d * OffsetHours * 1000.0d), ""));
    }

    @BA.Hide
    public static void SetTimeZone(int OffsetHours) {
        setTimeZoneInternal(new SimpleTimeZone(OffsetHours * 3600 * 1000, ""));
    }

    /* access modifiers changed from: private */
    public static void setTimeZoneInternal(TimeZone tz) {
        getInst().timeZone = tz;
        getInst().cal.setTimeZone(getInst().timeZone);
        getInst().dateFormat.setTimeZone(getInst().timeZone);
        getInst().timeFormat.setTimeZone(getInst().timeZone);
    }

    public static double getTimeZoneOffset() {
        return ((double) getInst().timeZone.getOffset(System.currentTimeMillis())) / 3600000.0d;
    }

    public static double GetTimeZoneOffsetAt(long Date) {
        return ((double) getInst().timeZone.getOffset(Date)) / 3600000.0d;
    }

    public static int GetYear(long Ticks) {
        getInst().cal.setTimeInMillis(Ticks);
        return getInst().cal.get(1);
    }

    public static int GetMonth(long Ticks) {
        getInst().cal.setTimeInMillis(Ticks);
        return getInst().cal.get(2) + 1;
    }

    public static int GetDayOfMonth(long Ticks) {
        getInst().cal.setTimeInMillis(Ticks);
        return getInst().cal.get(5);
    }

    public static int GetDayOfYear(long Ticks) {
        getInst().cal.setTimeInMillis(Ticks);
        return getInst().cal.get(6);
    }

    public static int GetDayOfWeek(long Ticks) {
        getInst().cal.setTimeInMillis(Ticks);
        return getInst().cal.get(7);
    }

    public static int GetHour(long Ticks) {
        getInst().cal.setTimeInMillis(Ticks);
        return getInst().cal.get(11);
    }

    public static int GetSecond(long Ticks) {
        getInst().cal.setTimeInMillis(Ticks);
        return getInst().cal.get(13);
    }

    public static int GetMinute(long Ticks) {
        getInst().cal.setTimeInMillis(Ticks);
        return getInst().cal.get(12);
    }

    public static long Add(long Ticks, int Years, int Months, int Days) {
        Calendar c = getInst().cal;
        c.setTimeInMillis(Ticks);
        c.add(1, Years);
        c.add(2, Months);
        c.add(6, Days);
        return c.getTimeInMillis();
    }
}
