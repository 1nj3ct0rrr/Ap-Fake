package anywheresoftware.b4a.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.drawable.CanvasWrapper;
import java.io.InputStream;
import java.util.HashMap;

@BA.ActivityObject
@BA.ShortName("WebView")
public class WebViewWrapper extends ViewWrapper<WebView> {
    @Override // anywheresoftware.b4a.objects.ViewWrapper
    @BA.Hide
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject) {
            setObject(new WebView(ba.context));
            ((WebView) getObject()).getSettings().setJavaScriptEnabled(true);
            ((WebView) getObject()).getSettings().setBuiltInZoomControls(true);
            ((WebView) getObject()).getSettings().setPluginsEnabled(true);
        }
        super.innerInitialize(ba, eventName, true);
        ((WebView) getObject()).setWebViewClient(new WebViewClient() {
            /* class anywheresoftware.b4a.objects.WebViewWrapper.AnonymousClass1 */

            public void onPageFinished(WebView view, String url) {
                ba.raiseEvent(WebViewWrapper.this.getObject(), String.valueOf(eventName) + "_pagefinished", url);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Boolean b = (Boolean) ba.raiseEvent(WebViewWrapper.this.getObject(), String.valueOf(eventName) + "_overrideurl", url);
                if (b != null) {
                    return b.booleanValue();
                }
                return false;
            }

            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                Object o = ba.raiseEvent(WebViewWrapper.this.getObject(), String.valueOf(eventName) + "_userandpasswordrequired", host, realm);
                if (o == null) {
                    handler.cancel();
                    return;
                }
                String[] s = (String[]) o;
                handler.proceed(s[0], s[1]);
            }
        });
        ((WebView) getObject()).setOnTouchListener(new View.OnTouchListener() {
            /* class anywheresoftware.b4a.objects.WebViewWrapper.AnonymousClass2 */

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case 0:
                    case 1:
                        if (v.hasFocus()) {
                            return false;
                        }
                        v.requestFocus();
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    public void LoadUrl(String Url) {
        ((WebView) getObject()).loadUrl(Url);
    }

    public void LoadHtml(String Html) {
        ((WebView) getObject()).loadDataWithBaseURL("file:///", Html, "text/html", "UTF8", null);
    }

    public void StopLoading() {
        ((WebView) getObject()).stopLoading();
    }

    public CanvasWrapper.BitmapWrapper CaptureBitmap() {
        Picture pic = ((WebView) getObject()).capturePicture();
        CanvasWrapper.BitmapWrapper bw = new CanvasWrapper.BitmapWrapper();
        bw.InitializeMutable(pic.getWidth(), pic.getHeight());
        CanvasWrapper cw = new CanvasWrapper();
        cw.Initialize2((Bitmap) bw.getObject());
        pic.draw(cw.canvas);
        return bw;
    }

    public String getUrl() {
        return ((WebView) getObject()).getUrl();
    }

    public boolean getJavaScriptEnabled() {
        return ((WebView) getObject()).getSettings().getJavaScriptEnabled();
    }

    public void setJavaScriptEnabled(boolean value) {
        ((WebView) getObject()).getSettings().setJavaScriptEnabled(value);
    }

    public void setZoomEnabled(boolean v) {
        ((WebView) getObject()).getSettings().setBuiltInZoomControls(v);
    }

    public boolean getZoomEnabled() {
        return ((WebView) getObject()).getSettings().getBuiltInZoomControls();
    }

    public boolean Zoom(boolean In) {
        if (In) {
            return ((WebView) getObject()).zoomIn();
        }
        return ((WebView) getObject()).zoomOut();
    }

    public void Back() {
        ((WebView) getObject()).goBack();
    }

    public void Forward() {
        ((WebView) getObject()).goForward();
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            if (designer) {
                View v = new View((Context) tag);
                InputStream in = ((Context) tag).getAssets().open("webview.jpg");
                BitmapDrawable bd = new BitmapDrawable(in);
                in.close();
                v.setBackgroundDrawable(bd);
                prev = v;
            } else {
                WebView wv = (WebView) ViewWrapper.buildNativeView((Context) tag, WebView.class, props, designer);
                wv.getSettings().setJavaScriptEnabled(((Boolean) props.get("javaScriptEnabled")).booleanValue());
                wv.getSettings().setBuiltInZoomControls(((Boolean) props.get("zoomEnabled")).booleanValue());
                wv.getSettings().setPluginsEnabled(true);
                prev = wv;
            }
        }
        ViewWrapper.build(prev, props, designer);
        return (View) prev;
    }
}
