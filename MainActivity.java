package com.electronicadeoccidente.reparacionestv;
import android.app.*; import android.os.*; import android.webkit.*; import android.content.*; import android.net.Uri;
public class MainActivity extends Activity {
 WebView web; ValueCallback<Uri[]> chooser; final int FILE=100;
 @Override public void onCreate(Bundle b){super.onCreate(b); web=new WebView(this); setContentView(web);
  WebSettings s=web.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setAllowFileAccess(true);
  web.setWebViewClient(new WebViewClient());
  web.setWebChromeClient(new WebChromeClient(){@Override public boolean onShowFileChooser(WebView w,ValueCallback<Uri[]> cb,FileChooserParams p){chooser=cb;startActivityForResult(p.createIntent(),FILE);return true;}});
  web.loadUrl("file:///android_asset/index.html");
 }
 @Override protected void onActivityResult(int r,int c,Intent d){super.onActivityResult(r,c,d);if(r==FILE&&chooser!=null){chooser.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(c,d));chooser=null;}}
 @Override public void onBackPressed(){if(web.canGoBack())web.goBack();else super.onBackPressed();}
}