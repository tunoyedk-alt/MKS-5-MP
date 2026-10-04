package com.mks5mp.builder;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.PermissionRequest;
import android.content.pm.PackageManager;

public class MainActivity extends Activity {
    WebView web;
    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        web = new WebView(this);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient() {
            @Override public void onPermissionRequest(PermissionRequest r) {
                runOnUiThread(() -> r.grant(r.getResources()));
            }
        });
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);
        web.loadUrl("file:///android_asset/index.html");
        setContentView(web);
        if (android.os.Build.VERSION.SDK_INT >= 23)
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA}, 10);
        if (android.os.Build.VERSION.SDK_INT >= 33)
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 11);
    }
}
