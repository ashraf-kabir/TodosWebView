package com.ashrafkabir.todoswebview;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView myWebView;
    String Url = "https://todos.ashrafkabir.com/";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView = findViewById(R.id.activity_main_webview);

        myWebView.setWebViewClient(new WebViewClient());

        // permission to use javascript, device zoom
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        // REMOTE RESOURCE
        myWebView.loadUrl(Url);
        //SwipeRefreshLayout
        final SwipeRefreshLayout finalMySwipeRefreshLayout1;
        finalMySwipeRefreshLayout1 = findViewById(R.id.swiperefresh);
        finalMySwipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                myWebView.loadUrl(myWebView.getUrl());
                // stop the refreshing
                finalMySwipeRefreshLayout1.setRefreshing(false);
            }
        });

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".doc")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    // if want to download pdf manually create AsyncTask here
                    // and download file
                    return true;
                } else if (url.endsWith(".docx")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else if (url.endsWith(".pdf")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else if (url.endsWith(".ppt")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else if (url.endsWith(".pptx")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else if (url.endsWith(".jpg")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else if (url.endsWith(".jpeg")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else if (url.endsWith(".png")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else if (url.endsWith(".gif")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                }
                return false;
            }
        });

        // LOCAL RESOURCE
        // mWebView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
