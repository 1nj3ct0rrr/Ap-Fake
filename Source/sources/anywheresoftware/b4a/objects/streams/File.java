package anywheresoftware.b4a.objects.streams;

import android.net.Uri;
import android.os.Environment;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.collections.List;
import anywheresoftware.b4a.objects.collections.Map;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

public class File {
    @BA.Hide
    public static final String ContentDir = "ContentDir";
    private static final String assetsDir = "AssetsDir";
    @BA.Hide
    public static String virtualAssetsFolder = null;

    public static String getDirAssets() {
        return assetsDir;
    }

    public static String getDirInternal() {
        return BA.applicationContext.getFilesDir().toString();
    }

    public static String getDirInternalCache() {
        java.io.File cd = BA.applicationContext.getCacheDir();
        if (cd == null) {
            return getDirInternal();
        }
        return cd.toString();
    }

    public static String getDirRootExternal() {
        return Environment.getExternalStorageDirectory().toString();
    }

    public static String getDirDefaultExternal() {
        java.io.File file = new java.io.File(Environment.getExternalStorageDirectory(), "/Android/data/" + BA.packageName + "/files/");
        file.mkdirs();
        return file.toString();
    }

    public static boolean Exists(String Dir, String FileName) throws IOException {
        if (!Dir.equals(assetsDir)) {
            return new java.io.File(Dir, FileName).exists();
        }
        if (virtualAssetsFolder != null) {
            return new java.io.File(virtualAssetsFolder, FileName).exists();
        }
        return Arrays.asList(BA.applicationContext.getAssets().list("")).indexOf(FileName.toLowerCase(BA.cul)) > -1;
    }

    public static boolean Delete(String Dir, String FileName) {
        return new java.io.File(Dir, FileName).delete();
    }

    public static void MakeDir(String Parent, String Dir) {
        new java.io.File(Parent, Dir).mkdirs();
    }

    public static long Size(String Dir, String FileName) {
        return new java.io.File(Dir, FileName).length();
    }

    public static long LastModified(String Dir, String FileName) {
        return new java.io.File(Dir, FileName).lastModified();
    }

    public static boolean IsDirectory(String Dir, String FileName) {
        return new java.io.File(Dir, FileName).isDirectory();
    }

    public static String Combine(String Dir, String FileName) {
        return new java.io.File(Dir, FileName).toString();
    }

    public static List ListFiles(String Dir) throws IOException {
        List list = new List();
        if (!Dir.equals(assetsDir)) {
            java.io.File folder = new java.io.File(Dir);
            if (!folder.isDirectory()) {
                throw new IOException(String.valueOf(Dir) + " is not a folder.");
            }
            String[] f = folder.list();
            if (f == null) {
                return list;
            }
            list.setObject(Arrays.asList(f));
            return list;
        } else if (virtualAssetsFolder != null) {
            return ListFiles(virtualAssetsFolder);
        } else {
            list.setObject(Arrays.asList(BA.applicationContext.getAssets().list("")));
            return list;
        }
    }

    public static boolean getExternalWritable() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    public static boolean getExternalReadable() {
        String state = Environment.getExternalStorageState();
        if ("mounted".equals(state) || "mounted_ro".equals(state)) {
            return true;
        }
        return false;
    }

    public static InputStreamWrapper OpenInput(String Dir, String FileName) throws IOException {
        InputStreamWrapper is = new InputStreamWrapper();
        if (Dir.equals(assetsDir)) {
            if (virtualAssetsFolder != null) {
                is.setObject(new GZIPInputStream(new FileInputStream(new java.io.File(virtualAssetsFolder, FileName.toLowerCase(BA.cul)))));
            } else {
                is.setObject(BA.applicationContext.getAssets().open(FileName.toLowerCase(BA.cul)));
            }
        } else if (Dir.equals(ContentDir)) {
            is.setObject(BA.applicationContext.getContentResolver().openInputStream(Uri.parse(FileName)));
        } else {
            is.setObject(new BufferedInputStream(new FileInputStream(new java.io.File(Dir, FileName)), 4096));
        }
        return is;
    }

    public static String GetText(String Dir, String FileName) throws IOException {
        InputStreamWrapper in = OpenInput(Dir, FileName);
        TextReaderWrapper tr = new TextReaderWrapper();
        tr.Initialize((InputStream) in.getObject());
        return tr.ReadAll();
    }

    public static List ReadList(String Dir, String FileName) throws IOException {
        InputStreamWrapper in = OpenInput(Dir, FileName);
        TextReaderWrapper tr = new TextReaderWrapper();
        tr.Initialize((InputStream) in.getObject());
        return tr.ReadList();
    }

    public static void WriteList(String Dir, String FileName, List List) throws IOException {
        OutputStreamWrapper out = OpenOutput(Dir, FileName, false);
        TextWriterWrapper tw = new TextWriterWrapper();
        tw.Initialize((OutputStream) out.getObject());
        tw.WriteList(List);
        tw.Close();
    }

    public static void WriteString(String Dir, String FileName, String Text) throws IOException {
        OutputStreamWrapper out = OpenOutput(Dir, FileName, false);
        TextWriterWrapper tw = new TextWriterWrapper();
        tw.Initialize((OutputStream) out.getObject());
        tw.Write(Text);
        tw.Close();
    }

    public static String ReadString(String Dir, String FileName) throws IOException {
        InputStreamWrapper in = OpenInput(Dir, FileName);
        TextReaderWrapper tr = new TextReaderWrapper();
        tr.Initialize((InputStream) in.getObject());
        String res = tr.ReadAll();
        in.Close();
        return res;
    }

    public static void WriteMap(String Dir, String FileName, Map Map) throws IOException {
        OutputStreamWrapper out = OpenOutput(Dir, FileName, false);
        Properties p = new Properties();
        for (Map.Entry<Object, Object> e : ((java.util.Map) Map.getObject()).entrySet()) {
            p.setProperty(String.valueOf(e.getKey()), String.valueOf(e.getValue()));
        }
        p.store((OutputStream) out.getObject(), (String) null);
        out.Close();
    }

    public static anywheresoftware.b4a.objects.collections.Map ReadMap(String Dir, String FileName) throws IOException {
        return ReadMap2(Dir, FileName, null);
    }

    public static anywheresoftware.b4a.objects.collections.Map ReadMap2(String Dir, String FileName, anywheresoftware.b4a.objects.collections.Map Map) throws IOException {
        InputStreamWrapper in = OpenInput(Dir, FileName);
        Properties p = new Properties();
        p.load((InputStream) in.getObject());
        if (Map == null) {
            Map = new anywheresoftware.b4a.objects.collections.Map();
        }
        if (!Map.IsInitialized()) {
            Map.Initialize();
        }
        for (Map.Entry<Object, Object> e : p.entrySet()) {
            Map.Put(e.getKey(), e.getValue());
        }
        in.Close();
        return Map;
    }

    public static void Copy(String DirSource, String FileSource, String DirTarget, String FileTarget) throws IOException {
        Delete(DirTarget, FileTarget);
        InputStream in = (InputStream) OpenInput(DirSource, FileSource).getObject();
        OutputStream out = (OutputStream) OpenOutput(DirTarget, FileTarget, false).getObject();
        Copy2(in, out);
        in.close();
        out.close();
    }

    @BA.Hide
    public static String getUnpackedVirtualAssetFile(String fileName) throws IOException {
        String fileName2 = fileName.toLowerCase(BA.cul);
        String unpacked = String.valueOf(fileName2) + ".unpacked";
        if (!Exists(virtualAssetsFolder, unpacked)) {
            Copy(getDirAssets(), fileName2, virtualAssetsFolder, unpacked);
        }
        return unpacked;
    }

    public static void Copy2(InputStream In, OutputStream Out) throws IOException {
        byte[] buffer = new byte[8192];
        while (true) {
            int count = In.read(buffer);
            if (count <= 0) {
                In.close();
                return;
            }
            Out.write(buffer, 0, count);
        }
    }

    public static OutputStreamWrapper OpenOutput(String Dir, String FileName, boolean Append) throws FileNotFoundException {
        if (Dir == assetsDir) {
            throw new RuntimeException("The Assets folder is a read-only folder");
        }
        OutputStreamWrapper o = new OutputStreamWrapper();
        o.setObject(new BufferedOutputStream(new FileOutputStream(new java.io.File(Dir, FileName), Append)));
        return o;
    }

    @BA.ShortName("InputStream")
    public static class InputStreamWrapper extends AbsObjectWrapper<InputStream> {
        public void InitializeFromBytesArray(byte[] Buffer, int StartOffset, int MaxCount) {
            setObject(new ByteArrayInputStream(Buffer, StartOffset, MaxCount));
        }

        public void Close() throws IOException {
            ((InputStream) getObject()).close();
        }

        public int ReadBytes(byte[] Buffer, int StartOffset, int MaxCount) throws IOException {
            return ((InputStream) getObject()).read(Buffer, StartOffset, MaxCount);
        }

        public int BytesAvailable() throws IOException {
            return ((InputStream) getObject()).available();
        }
    }

    @BA.ShortName("OutputStream")
    public static class OutputStreamWrapper extends AbsObjectWrapper<OutputStream> {
        public void InitializeToBytesArray(int StartSize) {
            setObject(new ByteArrayOutputStream(StartSize));
        }

        public byte[] ToBytesArray() {
            if (getObject() instanceof ByteArrayOutputStream) {
                return ((ByteArrayOutputStream) getObject()).toByteArray();
            }
            throw new RuntimeException("ToBytes can only be called after InitializeToBytesArray.");
        }

        public void Close() throws IOException {
            ((OutputStream) getObject()).close();
        }

        public void Flush() throws IOException {
            ((OutputStream) getObject()).flush();
        }

        public void WriteBytes(byte[] Buffer, int StartOffset, int Length) throws IOException {
            ((OutputStream) getObject()).write(Buffer, StartOffset, Length);
        }
    }

    @BA.ShortName("TextWriter")
    public static class TextWriterWrapper extends AbsObjectWrapper<BufferedWriter> {
        public void Initialize(OutputStream OutputStream) {
            setObject(new BufferedWriter(new OutputStreamWriter(OutputStream, Charset.forName("UTF8")), 4096));
        }

        public void Initialize2(OutputStream OutputStream, String Encoding) {
            setObject(new BufferedWriter(new OutputStreamWriter(OutputStream, Charset.forName(Encoding)), 4096));
        }

        public void Write(String Text) throws IOException {
            ((BufferedWriter) getObject()).write(Text);
        }

        public void WriteLine(String Text) throws IOException {
            ((BufferedWriter) getObject()).write(String.valueOf(Text) + Common.CRLF);
        }

        public void WriteList(List List) throws IOException {
            for (Object line : (java.util.List) List.getObject()) {
                WriteLine(String.valueOf(line));
            }
        }

        public void Close() throws IOException {
            ((BufferedWriter) getObject()).close();
        }

        public void Flush() throws IOException {
            ((BufferedWriter) getObject()).flush();
        }
    }

    @BA.ShortName("TextReader")
    public static class TextReaderWrapper extends AbsObjectWrapper<BufferedReader> {
        public void Initialize(InputStream InputStream) {
            setObject(new BufferedReader(new InputStreamReader(InputStream, Charset.forName("UTF8")), 4096));
        }

        public void Initialize2(InputStream InputStream, String Encoding) {
            setObject(new BufferedReader(new InputStreamReader(InputStream, Charset.forName(Encoding)), 4096));
        }

        public String ReadLine() throws IOException {
            return ((BufferedReader) getObject()).readLine();
        }

        public int Read(char[] Buffer, int StartOffset, int Length) throws IOException {
            return ((BufferedReader) getObject()).read(Buffer, StartOffset, Length);
        }

        public boolean Ready() throws IOException {
            return ((BufferedReader) getObject()).ready();
        }

        public String ReadAll() throws IOException {
            char[] buffer = new char[1024];
            StringBuilder sb = new StringBuilder(1024);
            while (true) {
                int count = Read(buffer, 0, buffer.length);
                if (count == -1) {
                    Close();
                    return sb.toString();
                } else if (count < buffer.length) {
                    sb.append(new String(buffer, 0, count));
                } else {
                    sb.append(buffer);
                }
            }
        }

        public List ReadList() throws IOException {
            List List = new List();
            List.Initialize();
            while (true) {
                String line = ReadLine();
                if (line == null) {
                    Close();
                    return List;
                }
                List.Add(line);
            }
        }

        public int Skip(int NumberOfCharaceters) throws IOException {
            return (int) ((BufferedReader) getObject()).skip((long) NumberOfCharaceters);
        }

        public void Close() throws IOException {
            ((BufferedReader) getObject()).close();
        }
    }
}
