package com.example.hired;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Method;

@SuppressLint("SetJavaScriptEnabled")
public class MultimediaPlayer extends AppCompatActivity
{
    private WebView mWebView;
    private boolean mIsPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        String media_url = "https://www.youtube.com/watch?v=R1HW6Comeno";

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebChromeClient(new WebChromeClient());

        WebSettings ws = mWebView.getSettings();
        ws.setBuiltInZoomControls(true);
        ws.setJavaScriptEnabled(true);

        mIsPaused = true;
        resumeBrowser();
        mWebView.loadUrl(media_url);
    }

    @Override
    protected void onPause()
    {
        pauseBrowser();
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        resumeBrowser();
        super.onResume();
    }

    private void pauseBrowser()
    {
        if (!mIsPaused)
        {
            // pause flash and javascript etc
            callHiddenWebViewMethod(mWebView, "onPause");
            mWebView.pauseTimers();
            mIsPaused = true;
        }
    }

    private void resumeBrowser()
    {
        if (mIsPaused)
        {
            // resume flash and javascript etc
            callHiddenWebViewMethod(mWebView, "onResume");
            mWebView.resumeTimers();
            mIsPaused = false;
        }
    }

    private void callHiddenWebViewMethod(final WebView wv, final String name)
    {
        try
        {
            final Method method = WebView.class.getMethod(name);
            method.invoke(mWebView);
        } catch (final Exception e)
        {}
    }
}